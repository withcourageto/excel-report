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
import java.util.stream.Collectors;

public abstract class AbstractPoiExcelWriter<T> implements ExcelWriter<T> {

    protected Class<T> elementClass;
    protected List<TitleCellDef> titleCellDefList;
    protected volatile List<FieldInfo> fieldInfoList;
    protected volatile boolean titleAdded;

    // proceed bar =  total == 0 ? 0 : proceed / total

    protected volatile DataCellWriter dataCellWriter;

    protected SXSSFWorkbook wb;
    protected volatile Sheet sheet;


    // 记录 excel 已经写了多少行，之后的数据只能写在这些行之后
    private int currRow = 0;

    void init() {
        dataCellWriter = new DataCellWriterImpl();

        titleCellDefList = ClassUtil.getDeclaredPropertyFields(elementClass)
                .stream()
                .filter(TitleParserUtil::qualified)
                .map(TitleParserUtil::parseField)
                .collect(Collectors.toList());
    }


    void writeTitle() {

        long start = System.currentTimeMillis();
        initSheet();

        Row titleRow = nextRow();
        int cellIndex = 0;
        Cell cell;
        for (TitleCellDef title : titleCellDefList) {
            cell = titleRow.createCell(cellIndex++);
            cell.setCellValue(title.getValue());
        }

        titleAdded = true;
        long end = System.currentTimeMillis();
        System.out.println("写入头部耗时：" + (end - start));
    }

    void initFieldInfo() {


        long start = System.currentTimeMillis();

        List<Field> fields = ClassUtil.getDeclaredPropertyFields(elementClass);

        fieldInfoList = fields.stream()
                .map(FieldInfo::new)
                .collect(Collectors.toList());

        long end =
                System.currentTimeMillis();

        System.out.println("写入field info 耗时：" + (end - start));
    }

    void write0(List<T> dataList) {
        Row row;
        long start = System.currentTimeMillis();
        for (T data : dataList) {
            row = nextRow();
            writeRow(row, data);
        }
        long end = System.currentTimeMillis();
        System.out.println("写入数据耗时：" + (end - start));
    }

    private void writeRow(Row row, T data) {
        int cellIndex = 0;
        for (FieldInfo field : fieldInfoList) {
            dataCellWriter.write(row.createCell(cellIndex++), field, data);
        }

    }

    Row nextRow() {
        return sheet.createRow(currRow++);
    }

    void initSheet() {

        long start = System.currentTimeMillis();

        wb = new SXSSFWorkbook(100);
        sheet = wb.createSheet();

        long end = System.currentTimeMillis();

        System.out.println("初始化 sheet 耗时：" + (end - start));
    }

    void close(Closeable... closeables) {

        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                // ignore
            }
        }

    }

    @Override
    public void out(OutputStream outputStream) throws IOException {
        try {
            wb.write(outputStream);
        } finally {
            close(outputStream, wb);
        }
    }

}
