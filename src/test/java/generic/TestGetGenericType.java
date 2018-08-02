package generic;

import org.junit.Test;
import top.cmoon.tools.excel.BatchPoiExcelWriter;

import java.util.ArrayList;

public class TestGetGenericType {


    @Test
    public void test() {

        BatchPoiExcelWriter<Integer> test = new BatchPoiExcelWriter<>(Integer.class, 10);
        test.write(new ArrayList<>());
    }
}
