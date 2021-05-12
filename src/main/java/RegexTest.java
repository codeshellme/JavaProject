import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegexTest {
    public static String regexSearch(String str, String pattern) {
        str = str.replaceAll("\n", "").replaceAll("\r\n", "");
        Pattern r = Pattern.compile(pattern);   // 创建 Pattern 对象
        Matcher m = r.matcher(str);             // 现在创建 matcher 对象

        System.out.println(m.groupCount());

        if (m.find()) {
            return m.group(1);
            // System.out.println("Found value: " + m.group(0) );
            // System.out.println("Found value: " + m.group(1) );
        }

        // 没有匹配上
        return null;
    }

    public static List<String> regexSearchAll(String str, String pattern) {
        str = str.replaceAll("\n", "").replaceAll("\r\n", "");
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        List<String> retList = new ArrayList<>();
        while (m.find()) {
            String item = m.group(1);
            retList.add(item);
        }

        return retList;
    }

    public static void main( String[] args ){
//        String line = "<reponseEnvelope><header><appCode>0</appCode><errorMessage briefMessage=\"\" detailMessage=\"\"/></header><body><parameters><parameter paraName=\"busiConfig\" paraValue=\"\"/><parameter paraName=\"endFlag\" paraValue=\"\"/><parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\" paraValue=\"20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||,\n" +
////                "0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";
////        line = "<reponseEnvelope><header><appCode>1</appCode><errorMessage briefMessage=\"1\" detailMessage=\"1\"/></header><body><parameters><parameter paraName=\"outputData\" paraValue=\"^^1^\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";
////
////        line = "<reponseEnvelope><header><appCode>0</appCode><errorMessage briefMessage=\"\" detailMessage=\"\"/></header><body><parameters><parameter paraName=\"busiConfig\" paraValue=\"\"/><parameter paraName=\"endFlag\" paraValue=\"\"/><parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\" paraValue=\"20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||," +
////                "0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";
////
////        String pattern = "<appCode>(.*?)</appCode>";
////        pattern = "outputData\" paraValue=\"(.*?)\"";
//////        pattern = "<appCode>(1)</appCode>";
////
////        String str = regexSearch(line, pattern);
////        System.out.println(str);

//        String s = "9995-11-12 14:00:00.0";
//        String year = regexSearch(s, "^(\\d\\d\\d\\d)-");
//        System.out.println(year);

        String s = "        <Item>\n" +
                "            <PayTypeID>1</PayTypeID>\n" +
                "            <PayModeName></PayModeName>\n" +
                "            <OpenBank></OpenBank>\n" +
                "            <OpenAccount>621036*********0947</OpenAccount>\n" +
                "            <POSTransNO>000087|000001|111237940632|FS390012|402641080628258|\n" +
                "                20140624|111317</POSTransNO>\n" +
                "            <Amount>110</Amount>\n" +
                "        </Item>\n" +
                "        <Item>\n" +
                "            <PayTypeID>5</PayTypeID>\n" +
                "            <PayModeName>个人账户</PayModeName>\n" +
                "            <OpenBank></OpenBank>\n" +
                "            <OpenAccount></OpenAccount>\n" +
                "            <POSTransNO></POSTransNO>\n" +
                "            <Amount>30</Amount>\n" +
                "        </Item>";

        List<String> items = regexSearchAll(s, "<Item>(.*?)</Item>");
        for(String item: items) {
            System.out.println(item);
        }
    }
}
