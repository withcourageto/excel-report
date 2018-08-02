package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.FieldInfo;

class NumberFieldReader implements FieldReader {

    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {


        Double val = FieldReaderUtil.readNum(fieldInfo, data);
        if (val != null)
            cell.setCellValue(val);

    }
}
