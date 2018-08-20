package generic;

import org.junit.Test;
import top.cmoon.tools.excel.BatchPoiExcelWriter;
import top.cmoon.tools.excel.ExcelWriter;
import top.cmoon.tools.excel.SimplePoiExcelWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestExcelWriter {

    @Test
    public void testWriter() throws IOException {

        ExcelWriter<DataVo> exporter = new SimplePoiExcelWriter<>(DataVo.class);

        int shift = 0;

        List<DataVo> datas = genericData(shift, 10000);

        long start = System.currentTimeMillis();
        exporter.write(datas);
        long end = System.currentTimeMillis();

        System.out.println("写入耗时：" + (end - start));
        File file = new File("/home/thomas/test/test_export_" + System.currentTimeMillis() + ".xlsx");

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        exporter.out(bufferedOutputStream);

        System.out.println("完成耗时:" + (System.currentTimeMillis() - start));
    }

    private List<DataVo> genericData(int start, int number) {


        long startTime = System.currentTimeMillis();


        List<DataVo> dataVos = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {

            DataVo vo = new DataVo("name" + (start + i), i % 50, new Date());
            vo.setBalance(Math.random());
            dataVos.add(vo);
        }

        long end = System.currentTimeMillis();
        System.out.println("生产数据耗时耗时：" + (end - startTime));
        return dataVos;
    }


}
