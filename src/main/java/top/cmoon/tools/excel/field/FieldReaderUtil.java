package top.cmoon.tools.excel.field;

import java.lang.reflect.Field;
import java.util.Date;

final class FieldReaderUtil {


    static String getReaderKey(Field field) {

        Class<?> type = field.getType();

        // TODO： use config file
        if (type == String.class) {
            return FieldReaderKey.STRING;
        } else if (type == Date.class) {
            return FieldReaderKey.DATE;
        } else if (type == boolean.class || type == Boolean.class) {
            return FieldReaderKey.BOOLEAN;
        } else if (Number.class.isAssignableFrom(type)) {
            return FieldReaderKey.NUMBER;
        } else {
            throw new RuntimeException("即将支持其他类型处理:" + type.getName());
        }


    }


    static String readStr(FieldInfo fieldInfo, Object obj) {
        return (String) fieldInfo.getVal(obj).orElse(null);
    }


    static Double readNum(FieldInfo fieldInfo, Object obj) {
        return fieldInfo.getVal(obj).map(v -> ((Number) v).doubleValue()).orElse(null);
    }


    static Date readDate(FieldInfo fieldInfo, Object obj) {
        return (Date) fieldInfo.getVal(obj).orElse(null);
    }

    static Boolean readBool(FieldInfo fieldInfo, Object obj) {
        return fieldInfo.getVal(obj).map(v -> Boolean.valueOf(v.toString())).orElse(null);
    }

}
