package top.cmoon.tools.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import top.cmoon.tools.clazz.ClassUtil;
import top.cmoon.tools.excel.field.FieldInfo;
import top.cmoon.tools.excel.titile.TitleCellDef;
import top.cmoon.tools.excel.titile.TitleParserUtil;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BatchPoiExcelWriter<T> implements WithProgressBarExcelWriter<T> {

    private Class<T> elementClass;
    private List<TitleCellDef> titleCellDefList;
    private volatile List<FieldInfo> fieldInfoList;
    private volatile boolean titleAdded;

    // proceed bar =  total == 0 ? 0 : proceed / total
    private final int total;
    private AtomicInteger proceed;

    private volatile DataCellWriter dataCellWriter;

    private SXSSFWorkbook wb;
    private volatile Sheet sheet;

    // 记录 excel 已经写了多少行，之后的数据只能写在这些行之后
    private int currRow = 0;

    public BatchPoiExcelWriter(Class<T> elementType, int total) {

        if (total <= 0) {
            throw new IllegalArgumentException();
        }

        if (elementType == null) {
            throw new NullPointerException();
        }

        if (!ClassUtil.isUserClass(elementType)) {
            throw new IllegalArgumentException();
        }

        this.elementClass = elementType;
        this.total = total;

        init();
    }

    private void init() {
        dataCellWriter = new DataCellWriterImpl();
        proceed = new AtomicInteger();
        titleCellDefList = ClassUtil.visitDeclaredFields(elementClass, TitleParserUtil::parseField);
    }


    private void initSheet() {
        wb = new SXSSFWorkbook(total > 100 ? 100 : total);
        sheet = wb.createSheet();
    }

    private void writeTitle() {

        initSheet();

        Row titleRow = nextRow();
        int cellIndex = 0;
        Cell cell;
        for (TitleCellDef title : titleCellDefList) {
            cell = titleRow.createCell(cellIndex++);
            cell.setCellValue(title.getValue());
        }

        titleAdded = true;
    }


    private void initFieldInfo() {
        List<Field> fields = ClassUtil.getDeclaredPropertyFields(elementClass);

        fieldInfoList = fields.stream()
                .map(FieldInfo::new)
                .collect(Collectors.toList());
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

    @Override
    public void out(OutputStream outputStream) throws IOException {
        try {
            wb.write(outputStream);
        } finally {
            close(outputStream, wb);
        }
    }

    private void write0(List<T> dataList) {
        Row row;
        for (T data : dataList) {
            row = nextRow();
            writeRow(row, data);
        }
    }

    private void writeRow(Row row, T data) {
        int cellIndex = 0;
        for (FieldInfo field : fieldInfoList) {
            dataCellWriter.write(row.createCell(cellIndex++), field, data);
        }

        proceed.incrementAndGet();
    }

    private Row nextRow() {
        return sheet.createRow(currRow++);
    }

    private Row createRow(int rowNum) {
        try {
            return sheet.createRow(rowNum);
        } catch (RuntimeException e) {
            throw new RuntimeException("row number is :" + rowNum, e);
        }
    }

    public void close() {
        try {
            wb.close();
        } catch (IOException e) {
            // ignore exception
        }
    }

    protected final void close(Closeable... closeables) {

        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }

    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public int getProcessed() {
        return proceed.get();
    }
}