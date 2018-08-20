package top.cmoon.tools.excel;


import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ExcelWriter<T> extends Closeable {

    void write(List<T> dataList);

    /**
     * 将 excel 写入指定输出流，并且关闭输入流和 excel writer
     *
     * @param outputStream 输出流，导出的目的地
     * @throws IOException io error
     */
    void out(OutputStream outputStream) throws IOException;

    /**
     * 关闭写，并且将输入写入 excel
     */
    void close();
}
