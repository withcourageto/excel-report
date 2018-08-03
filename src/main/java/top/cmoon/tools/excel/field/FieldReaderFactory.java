package top.cmoon.tools.excel.field;

import java.util.HashMap;
import java.util.Map;

public class FieldReaderFactory {

    private Map<String, FieldReader> readers = new HashMap<>();

    public FieldReaderFactory() {
        readers.put(FieldReaderKey.STRING, new StringFieldReader());
        readers.put(FieldReaderKey.DATE, new DateFieldReader());
        readers.put(FieldReaderKey.BOOLEAN, new BooleanFieldReader());
        readers.put(FieldReaderKey.NUMBER, new NumberFieldReader());
    }

    public FieldReader getFieldReader(FieldInfo fieldInfo) {
        return readers.get(fieldInfo.getReaderType());
    }
}
