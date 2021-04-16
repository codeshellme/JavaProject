import java.util.regex.*;

public class RegexTest {
    public static String regexSearch(String str, String pattern) {
        Pattern r = Pattern.compile(pattern);   // 创建 Pattern 对象
        Matcher m = r.matcher(str);             // 现在创建 matcher 对象

        if (m.find()) {
            return m.group(1);
            // System.out.println("Found value: " + m.group(0) );
            // System.out.println("Found value: " + m.group(1) );
        }

        // 没有匹配上
        return null;
    }

    public static void main( String[] args ){
        String line = "<reponseEnvelope><header><appCode>0</appCode><errorMessage briefMessage=\"\" detailMessage=\"\"/></header><body><parameters><parameter paraName=\"busiConfig\" paraValue=\"\"/><parameter paraName=\"endFlag\" paraValue=\"\"/><parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\" paraValue=\"20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||,\n" +
                "0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";
        line = "<reponseEnvelope><header><appCode>1</appCode><errorMessage briefMessage=\"1\" detailMessage=\"1\"/></header><body><parameters><parameter paraName=\"outputData\" paraValue=\"^^1^\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";

        line = "<reponseEnvelope><header><appCode>0</appCode><errorMessage briefMessage=\"\" detailMessage=\"\"/></header><body><parameters><parameter paraName=\"busiConfig\" paraValue=\"\"/><parameter paraName=\"endFlag\" paraValue=\"\"/><parameter paraName=\"writeCardInfo\" paraValue=\"\"/><parameter paraName=\"outputData\" paraValue=\"20210413111552-00200002-1394^1^00200002-00000001-20210409173019871|^0|||," +
                "0|||^\"/><parameter paraName=\"cardConfig\" paraValue=\"\"/></parameters><dataStores></dataStores></body></reponseEnvelope>";

        String pattern = "<appCode>(.*?)</appCode>";
        pattern = "outputData\" paraValue=\"(.*?)\"";


        String str = regexSearch(line, pattern);
        System.out.println(str);
    }
}
