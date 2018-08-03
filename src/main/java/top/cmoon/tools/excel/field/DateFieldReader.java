package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

class DateFieldReader implements FieldReader {

    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {

        Date val = FieldReaderUtil.readDate(fieldInfo, data);
        cell.setCellValue(val);
    }
}
