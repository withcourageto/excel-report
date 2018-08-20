package top.cmoon.tools.excel.titile;

import top.cmoon.tools.excel.annotation.ExportFieldType;
import top.cmoon.tools.excel.annotation.ExportFields;
import top.cmoon.tools.excel.annotation.Title;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class TitleParserUtil {


    public static List<Field> filterField(Class<?> clazz, List<Field> fields) {

        return fields
                .stream()
                .filter(f -> {
                    ExportFields exportFields = f.getDeclaringClass().getAnnotation(ExportFields.class);
                    if (exportFields == null || exportFields.value() == ExportFieldType.ALL) {
                        return true;
                    } else {
                        return f.isAnnotationPresent(Title.class);
                    }
                }).collect(Collectors.toList());
    }


    public static boolean qualified(Field field) {
        ExportFields exportFields = field.getDeclaringClass().getAnnotation(ExportFields.class);
        if (exportFields == null || exportFields.value() == ExportFieldType.ALL) {
            return true;
        } else {
            return field.isAnnotationPresent(Title.class);
        }
    }


    /**
     * 从 field 中获取标题信息，
     * <p>
     * <pre>
     *  1. 从 @Title 注解获取 value 值作为 title
     *  2. 如果没有 @Title,或者title 值为空白, 将 field 名称作为 title
     * </pre>
     *
     * @param clazz
     * @param field
     * @return
     */
    public static TitleCellDef parseField(Class<?> clazz, Field field) {


        Title title = field.getAnnotation(Title.class);

        TitleCellDef titleDef = new TitleCellDef();
        if (title != null && !"".equals(title.value().trim())) { // prefer to annotation define
            titleDef.setValue(title.value());
            return titleDef;
        }

        titleDef.setValue(field.getName());
        return titleDef;
    }


    public static TitleCellDef parseField(Field field) {

        return parseField(null, field);
    }
}
