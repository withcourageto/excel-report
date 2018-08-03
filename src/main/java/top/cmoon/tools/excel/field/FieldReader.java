package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;

public interface FieldReader {

    void readToCell(Cell cell, FieldInfo fieldInfo, Object data);

}
