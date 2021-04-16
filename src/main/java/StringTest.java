import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringTest {

    public static String getVal(Map<String, Object> m, String key) {
        if (m.get(key) == null) {
            return "";
        } else {
            return m.get(key).toString();
        }
    }

    public static void main(String[] args) {
//        String sqlFmt = "insert into inhospital(ihspcode, name, sex, indate, outdate, emrdatakind, isarchive, status, archivedate, archiveby, emrpageurl) " +
//                "values('%s', '%s', '%s', '%s', '%s', '1', 'CHK', 'SETT', NOW(), '%s', '%s');";
////        String sql = String.format(sqlFmt, "aaa");
//        Map<String, Object> params = new HashMap<String, Object>();
//
//        params.put("abc", "123");
//        String s = getVal(params, "abcd");
//
//
//        s = "E:\\2021320\\file4.pdf";
//        System.out.println(s);
//
//        s = s.replaceAll("\\\\", "\\\\\\\\");
//        System.out.println(s);
//
//        System.out.println(params.toString());

//        if (sex.equals("男")) {
////            sex = "M";
////        } else {
////            sex = "W";
////        }

//        sex = "M" if sex.equals("男") else "W";
//        sex = sex.equals("男") ? "M" : "W";
//
//        System.out.println(sex);

//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("abc", null);
//        String s = params.get("abc").toString();
//        System.out.println(s);

//        Random r = new Random();
//        System.out.println(r.nextInt(10000));

//        int max=9999, min=1000;
////        int ran2 = (int) (Math.random()*(max-min)+min);
//        System.out.println((int)(Math.random()*(max-min)+min));

//        String s = "20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||,0|||^";
//        s = s.split("\\^")[2].split("\\|")[0];
//        System.out.println(s);

//        String s = "26024|0|邹克志|19531021|01|342123195310218770|1|01|20201107110305|20210414100937|12|骨一科|542|于淼|26024||0|0|0|0|0|0|0|0|0||0|0|0|0&T00.901|胸腰部、右腕软组织挫伤|1&||&1852582|1|3|0|202104120006|20210412090912|7763|Z-A09BA-F0284|阿胶|阿胶||0.8500|6.00||||0||||1||542|于淼|||";
//        s = s.split("\\|")[41];
//        System.out.println(s);

        String s = "abc<>kkk";
        s = s.replaceAll("<", "%3C");
        System.out.println(s);

        Map<String, String> m = new HashMap<String, String>(){{
            put("name", "test");
            put("age", "20");
        }};

        String x = m.toString();
        System.out.println(x);
        System.out.println(m.get("name"));
    }
}