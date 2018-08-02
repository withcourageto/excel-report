package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.FieldInfo;

class BooleanFieldReader implements FieldReader {

    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {
        Boolean va = FieldReaderUtil.readBool(fieldInfo, data);
        cell.setCellValue(va);
    }
}
