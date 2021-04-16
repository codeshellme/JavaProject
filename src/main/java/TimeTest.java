import java.util.*;
import java.text.*;

public class TimeTest {
    public static void main(String args[]) {

        //Date dNow = new Date( );

//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        System.out.println("当前时间为: " + ft.format(new Date()));
    }
}