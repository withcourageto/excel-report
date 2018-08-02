package top.cmoon.tools.excel;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Optional;

public class FieldInfo {

    private Field field;

    private Class<?> fieldType;

    private String readerType;


    public boolean isString() {
        return fieldType == String.class;
    }

    public boolean isNumber() {
        return Number.class.isAssignableFrom(fieldType);
    }

    public boolean isDate() {
        return fieldType == Date.class;
    }


    public boolean isBoolean() {
        return fieldType == boolean.class || fieldType == Boolean.class;
    }

    public String getReaderType() {
        return readerType;
    }

    public FieldInfo(Field field) {
        if (field == null) {
            throw new NullPointerException();
        }

        this.field = field;
        this.field.setAccessible(true);
        fieldType = field.getType();
    }


    public Optional<Object> getVal(Object obj) {

        if (!field.isAccessible())
            field.setAccessible(true);

        try {
            return Optional.ofNullable(field.get(obj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
