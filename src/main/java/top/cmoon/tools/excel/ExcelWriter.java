package top.cmoon.tools.excel;


import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ExcelWriter<T> extends Closeable {

    void write(List<T> dataList);

    void out(OutputStream outputStream) throws IOException;

    /**
     * 关闭写，并且将输入写入 excel
     */
    void close();
}
