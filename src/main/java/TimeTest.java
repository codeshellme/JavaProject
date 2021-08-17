import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.*;
import java.text.*;

public class TimeTest {

    /**
     * 获取当前日期
     * - y      yy 表示 2 位年份，yyyy 表示 4 位年份
     * - M      一般用 MM 表示月份，如果使用 MMM，则会根据语言环境显示不同语言的月份
     * - d/D    d 表示月份中的天数，一般用 dd 表示天数
     *          D 表示年份中的天数，表示当天是当年的第几天，用 D 表示
     * - E      E 表示星期几，会根据语言环境的不同，显示不同语言的星期几
     * - h/H    h 表示 12 小时制，H 表示 24 小时制（要特别注意）
     * - m      一般使用 mm 表示分钟数
     * - s      一般使用 ss 表示秒数
     * - S      一般使用 SSS 表示毫秒数
     */
    public static String getNowDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return ft.format(new Date());
    }

    /**
     * 获取当前时间戳，单位秒
     */
    public static long getNowTimestamp() {
        // return new Date().getTime()/1000;
        return getNowTimestampMillis() / 1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     */
    public static long getNowTimestampMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前年份的天数
     */
    public static int getDaysOfCurrentYear() {
        return LocalDate.now().lengthOfYear();
    }

    /**
     * 获取指定年份的天数
     * @param year 哪一年
     */
    public static int getDaysOfYear(int year) {
        return LocalDate.of(year, 1, 1).lengthOfYear();
    }

    /**
     * 日期格式转时间戳，单位秒
     */
    public static String date2TimestampString(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期格式转时间戳，单位秒
     */
    public static long date2TimestampLong(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date).getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 时间戳（单位毫秒）转日期格式
     */
    public static String timestampMilliSecond2Date(String millisecond, String format) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat(format);
            return ft.format(new Date(Long.parseLong(millisecond)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 时间戳（单位秒）转日期格式
     */
    public static String timestamp2Date(String seconds, String format) {
        try {
            SimpleDateFormat ft  = new SimpleDateFormat(format);
            return ft.format(new Date(Long.parseLong(seconds+"000")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        /*
        String nowTime = getNowDate();
        System.out.println("当前时间为: " + nowTime);
        System.out.println("当前时间戳: " + getNowTimestamp());

        String date = "1995-11-12 14:00:00.0";
        date = "2020-4-27 14:00:00.0";
         */

//        System.out.println(getDaysOfYear(2011));
//        System.out.println(getDaysOfYear(2012));
//        System.out.println(getDaysOfYear(2013));
//        System.out.println(getDaysOfYear(2014));
        System.out.println(getNowDate());
    }
}