package top.cmoon.tools.excel;

import top.cmoon.tools.clazz.ClassUtil;

import java.io.IOException;
import java.util.List;

public class SimplePoiExcelWriter<T> extends AbstractPoiExcelWriter<T> implements ExcelWriter<T> {


    public SimplePoiExcelWriter(Class<T> elementType) {

        if (elementType == null) {
            throw new NullPointerException();
        }

        if (!ClassUtil.isUserClass(elementType)) {
            throw new IllegalArgumentException();
        }

        this.elementClass = elementType;

        init();
    }


    public void write(List<T> dataList) {

        if (!titleAdded) {
            writeTitle();
        }

        if (fieldInfoList == null) {
            initFieldInfo();
        }

        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        write0(dataList);
    }

    public void close() {
        try {
            wb.close();
        } catch (IOException e) {
            // ignore exception
        }
    }

}
