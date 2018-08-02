package top.cmoon.tools.excel.field;

import top.cmoon.tools.excel.FieldInfo;

import java.util.Date;

final class FieldReaderUtil {


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
