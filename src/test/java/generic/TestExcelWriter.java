package generic;

import org.junit.Test;
import top.cmoon.tools.excel.BatchPoiExcelWriter;
import top.cmoon.tools.excel.ExcelWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExcelWriter {

    @Test
    public void testWriter() throws IOException {

        long start = System.currentTimeMillis();
        ExcelWriter<DataVo> exporter = new BatchPoiExcelWriter<>(DataVo.class, 1000000);

        int shift = 0;
        for (int i = 0; i < 100; i++) {
            exporter.write(genericData(shift, 10000));
            shift += 10000;
        }

        long end = System.currentTimeMillis();

        System.out.println("写入耗时：" + (end - start));
        File file = new File("/home/thomas/test/test_export_" + System.currentTimeMillis() + ".xlsx");

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        exporter.out(bufferedOutputStream);

        System.out.println("完成耗时:" + (System.currentTimeMillis() - start));
    }

    private List<DataVo> genericData(int start, int number) {

        // simulate database query
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<DataVo> dataVos = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {
            dataVos.add(new DataVo("name" + (start + i), i % 50, new Date()));
        }

        return dataVos;
    }


}
