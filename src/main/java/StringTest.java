import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringTest {

    public static void main(String[] args) {
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

//        String s = "abc<>kkk";
//        s = s.replaceAll("<", "%3C");
//        System.out.println(s);
//
//        Map<String, String> m = new HashMap<String, String>(){{
//            put("name", "test");
//            put("age", "20");
//        }};
//
//        String x = m.toString();
//        System.out.println(x);
//        System.out.println(m.get("name"));
//        System.out.println(m.get("name"));

//        String s = "腰痛待查,zzz,null,null,null,null,null,null,null,瘤";
//        s = deleteFromString(s, "null");
//        System.out.println(s);

        // 合格数据   20210412102339-00200002-1265^1^0^0|||^
        // 不合格数据 20210414092800-00200002-1689^1^1|1000000045116588&&03&&费用构成主要为药品费用（排除肿瘤化疗）占比过高，实际占比：100.0%大于阈值设置：80.0%.&&202104120006^0|||^
//        String s = "20210412102339-00200002-1265^1^0^0|||^";
//        s = "20210414092800-00200002-1689^1^1|1000000045116588&&03&&费用构成主要为药品费用（排除肿瘤化疗）占比过高，实际占比：100.0%大于阈值设置：80.0%.&&202104120006^0|||^";
//
//        s = "26221|0|张俊英|19610318|01|342123196103181041|2|01|20201110193251|20210419093552|12|骨一科|542|于淼|26221||0|0|0|0|0|0|0|0|0||0|0|0|1&T00.901|全身多处软组织挫伤|1&||&1852553|1|3|0|202104070020|20210407103152|7763|Z-A09BA-F0284|阿胶|阿胶||0.8500|5.00||||0||||1||542|于淼|||";
////        s = s.split("\\|")[41];
//        System.out.println(s.split("\\|")[33].substring(1));

//        String retInfo = "20210422113903-00222007-4055^1^2|处方数量输入格式错误。^0|||^";
//
//        String s = "parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\n" +
//                "\" paraValue=\"20210422165147-00222007-9927^1^1|1000000045158923&&01&&太和药品中药\n" +
//                "饮片限复方，具体明细如下：\n" +
//                "1.药品【T001300477，昆布】不能单独使用。\n" +
//                "&&202104220209^0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></paramete\n" +
//                "rs><dataStores></dataStores></body></reponseEnvelope>parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\n" +
//                "\" paraValue=\"20210422165147-00222007-9927^1^1|1000000045158923&&01&&太和药品中药\n" +
//                "饮片限复方，具体明细如下：\n" +
//                "1.药品【T001300477，昆布】不能单独使用。\n" +
//                "&&202104220209^0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></paramete\n" +
//                "rs><dataStores></dataStores></body></reponseEnvelope>";
//
//        s = s.replaceAll("\n", "").replaceAll("\r\n", "");
//
//        // 获取返回信息
//        String pattern = "outputData\" paraValue=\"(.*?)\"";
//        retInfo = RegexTest.regexSearch(s, pattern);
//        if (retInfo == null) {	// 没有得到有用信息
//            System.out.println("没有得到有用信息");
//            System.exit(0);
//        }
//
//        String flag = retInfo.split("\\^")[2];
//        if (flag.startsWith("0")) {
//            // 合格 不用处理
//            System.out.println("合格 不用处理");
//            System.exit(0);
//        }
//
//        if (!flag.startsWith("1")) {
//            System.out.println("门诊，医保监控接口返回不合格信息：" + retInfo);
//            System.exit(0);
//        }
//
//        // 药品不合格，获取错误信息
//        String errInfo = flag.split("&&")[2];
//        System.out.println("errInfo:" + errInfo);

//        String s = "1995-11-12 14:00:00.0";
//        int l = s.split("\\|").length;
//        System.out.println(l);
//        System.out.println(s.split("\\|")[0]);

        Map<String, Object> m = new HashMap<>();
        System.out.println(m.get("af"));
    }

    private static String deleteFromString(String srcStr, String del) {
        StringBuilder ret = new StringBuilder();
        String[] split = srcStr.split(",");

        for (String value : split) {
            if (value.equals(del)) {
                continue;
            }

            ret.append(",").append(value);
        }

        return ret.substring(1);
    }

    public static String getVal(Map<String, Object> m, String key) {
        if (m.get(key) == null) {
            return "";
        } else {
            return m.get(key).toString();
        }
    }
}