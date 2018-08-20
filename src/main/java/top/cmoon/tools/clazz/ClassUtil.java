package top.cmoon.tools.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ClassUtil {

    private ClassUtil() {
    }

    public static boolean isUserClass(Class<?> clazz) {
        return clazz.getClassLoader() != null;
    }

    public static <T> List<T> visitDeclaredFields(Class<?> clazz, BiFunction<Class<?>, Field, T> visitor) {

        if (clazz == null) {
            throw new NullPointerException();
        }

        if (visitor == null) {
            throw new NullPointerException();
        }

        List<Field> fields = getDeclaredPropertyFields(clazz);
        List<T> results = new ArrayList<>(fields.size());
        for (Field field : fields) {
            results.add(visitor.apply(clazz, field));
        }
        return results;
    }

    public static <T> List<T> visitDeclaredFields(Class<?> clazz, BiFunction<Class<?>, List<Field>, List<Field>> filter, BiFunction<Class<?>, Field, T> visitor) {

        if (clazz == null) {
            throw new NullPointerException();
        }

        if (visitor == null) {
            throw new NullPointerException();
        }

        List<Field> fields = filter.apply(clazz, getDeclaredPropertyFields(clazz));
        List<T> results = new ArrayList<>(fields.size());
        for (Field field : fields) {
            results.add(visitor.apply(clazz, field));
        }
        return results;
    }


    /**
     * 获取属性字段，即非 static, final 修饰的字段
     *
     * @param clazz
     * @return
     */
    public static List<Field> getDeclaredPropertyFields(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException();

        Field[] fields = clazz.getDeclaredFields();


        return Arrays.stream(fields)
                .filter(ClassUtil::isPropertyField)
                .collect(Collectors.toList());
    }

    private static boolean isPropertyField(Field f) {
        return !Modifier.isFinal(f.getModifiers()) && !Modifier.isStatic(f.getModifiers());
    }


}
