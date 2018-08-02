package top.cmoon.tools.excel;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.field.FieldReader;
import top.cmoon.tools.excel.field.FieldReaderFactory;

public class DataCellWriterImpl implements DataCellWriter {

    private FieldReaderFactory fieldReaderFactory = new FieldReaderFactory();

    @Override
    public void write(Cell cell, FieldInfo fieldInfo, Object data) {
        FieldReader reader = fieldReaderFactory.getFieldReader(fieldInfo);
        reader.readToCell(cell, fieldInfo, data);
    }
}
