package top.cmoon.tools.excel;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.field.FieldInfo;

interface DataCellWriter {

    void write(Cell cell, FieldInfo fieldInfo, Object data);

}
