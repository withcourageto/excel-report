package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.FieldInfo;

public interface FieldReader {

    void readToCell(Cell cell, FieldInfo fieldInfo, Object data);

}
