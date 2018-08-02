package top.cmoon.tools.excel;


/**
 * 带进度条的 excel writer
 *
 * @param <T>
 */
public interface WithProgressBarExcelWriter<T> extends ExcelWriter<T> {


    int getTotal();

    int getProcessed();

}
