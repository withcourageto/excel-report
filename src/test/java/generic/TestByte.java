package generic;

import org.junit.Test;

public class TestByte {

    @Test
    public void test() {

        String str = "";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);


        str = new String(new byte[0]);

        System.out.println("str os byte:" + str);

    }
}
