package top.cmoon.tools.excel.field;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldInfo {

    private Field field;

    //private Class<?> fieldType;

    private String readerType;

    String getReaderType() {
        return readerType;
    }

    public FieldInfo(Field field) {
        if (field == null) {
            throw new NullPointerException();
        }

        this.field = field;
        this.field.setAccessible(true);
        // fieldType = field.getType();

        readerType = FieldReaderUtil.getReaderKey(field);
    }


    Optional<Object> getVal(Object obj) {

        if (!field.isAccessible())
            field.setAccessible(true);

        try {
            return Optional.ofNullable(field.get(obj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
