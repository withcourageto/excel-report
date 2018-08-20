package top.cmoon.tools.excel.field;

import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.annotation.NumberPattern;

import java.text.DecimalFormat;

class NumberFieldReader implements FieldReader {

    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {

        Double val = FieldReaderUtil.readNum(fieldInfo, data);

        if (val != null) {
            if (fieldInfo.withAnno(NumberPattern.class)) {

                NumberPattern numberPattern = fieldInfo.getAnno(NumberPattern.class);
                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setMaximumFractionDigits(numberPattern.maxFractionDigits());

                cell.setCellValue(decimalFormat.format(val));
            } else
                cell.setCellValue(val);
        }

    }
}
