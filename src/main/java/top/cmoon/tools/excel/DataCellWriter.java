package top.cmoon.tools.excel;

import org.apache.poi.ss.usermodel.Cell;

public interface DataCellWriter {

    void write(Cell cell, FieldInfo fieldInfo, Object data);

}
