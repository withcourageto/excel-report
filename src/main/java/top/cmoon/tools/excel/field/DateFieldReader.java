package top.cmoon.tools.excel.field;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import top.cmoon.tools.excel.annotation.DatePattern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

class DateFieldReader implements FieldReader {


    @Override
    public void readToCell(Cell cell, FieldInfo fieldInfo, Object data) {


        if (fieldInfo.withAnno(DatePattern.class)) {

            String pattern = fieldInfo.getAnno(DatePattern.class).value();
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            fieldInfo.getVal(data)
                    .ifPresent(val -> cell.setCellValue(format.format(val)));
        } else {
            Date val = FieldReaderUtil.readDate(fieldInfo, data);
            cell.setCellValue(val);
        }
    }


}
