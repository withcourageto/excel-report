package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;

class StringFieldReader implements FieldReader {

    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {
        String val = FieldReaderUtil.readStr(fieldInfo, data);
        cell.setCellValue(val);
    }
}
