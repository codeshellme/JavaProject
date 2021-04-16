import java.text.SimpleDateFormat;
import java.util.Date;

public class ThMoniterTest {

    private static final String ipPort = "172.54.7.226:7001";	// 医保接口IP端口 固定 TODO 设置
    private static final String hspNum = "222007";				// 医疗机构编号	固定 TODO 设置
    private static final String operatorNum = "001";			// 操作员编号		固定	TODO 设置
    private static final String centerNum = "0000";				// 中心编码		固定 TODO 设置
    private static final String areaCode = "341222";			// 统筹区编码		固定 TODO 设置
    private static final String onlineFlag = "1";				// 联机标志		固定
    private String cycleNum = "";					            // 业务周期号 调佣签到接口时返回
    private static String url = "http://" +ipPort+
            "/webroot/HMA_BIZ_Comp/HMA_Interface/BeforeHandInterfaceAction!beforeHandInterface.action?";
    private static final String dllparm = "1$" +areaCode+ "|1|141231|1|141231|1|141231|1|141231|1|141231|$";
    private static final String cardInfo = "|3501903672|0.0";

    private static String macAddr = null;
    private static String dllParam = null;
    static {
        try {
            macAddr = NetAddrTest.getMacList().get(0);
            dllParam = macAddr + "|50|460000E81EF0AB|" +hspNum+ "|";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String serialNum() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        String t = ft.format(new Date());
        return t +"-"+ hspNum +"-"+ Math.round((Math.random() + 1)*1000);
    }

    private static String staticParmInit() {
        return hspNum +"^"+ operatorNum +"^^"+ serialNum() +"^"+ centerNum;
    }

    private String staticParmOp() {
        return hspNum +"^"+ operatorNum +"^"+ cycleNum + "^"+ serialNum() +"^"+ centerNum;
    }

    private String getMonitorUrl(String inputData) {
        String u = String.format("%sinputData=%s&dllParam=%s&dllparm=%s&cardInfo=%s", url, inputData, dllParam, dllparm, cardInfo);
        u = u.replaceAll("\\^", "%5E");
        u = u.replaceAll("\\|", "%7C");
        u = u.replaceAll("<", "%3C");		// 特殊符号
        u = u.replaceAll(">", "%3E");		// 特殊符号
        return u;
    }

    private String parseCycleNum(String str) {
        String pattern = "<appCode>(.*?)</appCode>";
        String code = RegexTest.regexSearch(str, pattern);

        if (code == null || !code.equals("0")) {
            // 错误
            return null;
        }

        pattern = "outputData\" paraValue=\"(.*?),";
        return RegexTest.regexSearch(str, pattern);
    }

    // 接口签到
    public boolean adviceMedicalInsuranceMonitorSignIn() {
        String inputData = "9100^" + staticParmInit() + "^|^" + onlineFlag + "^";
        String u = getMonitorUrl(inputData);

        System.out.println("发起签到请求===============");
        String retStr = HttpClientUtils.httpGet(u, null, -1, -1, true);
        System.out.println("签到返回结果: " + retStr);

        if(retStr == null || retStr.equals("")) {
            return false;
        }

        retStr = parseCycleNum(retStr);
        if (retStr == null) {
            return false;
        }

        // 业务周期号赋值
        cycleNum = retStr.split("\\^")[2].split("\\|")[0];
        return true;
    }

    // 调用业务接口
    public String adviceMedicalInsuranceMonitorOp(String inputStr) {
        String inputData = "5210^" + staticParmOp() +"^"+ inputStr +"^"+ this.onlineFlag + "^";
        String u = getMonitorUrl(inputData.replaceAll("\\&", "%26"));

        System.out.println("发起业务请求===============");
        String retStr = HttpClientUtils.httpGet(u, null, -1, -1, true);
        System.out.println("业务返回结果: " + retStr);

        if(retStr == null || retStr.equals("")) {
            return null;
        }

        // 解析业务结果
        String pattern = "<appCode>(.*?)</appCode>";
        String code = RegexTest.regexSearch(retStr, pattern);
        pattern = "outputData\" paraValue=\"(.*?)\"";
        String msg = RegexTest.regexSearch(retStr, pattern);

        if (!code.equals("0")) {
            System.out.println("分析=业务返回结果，结果返回错误，Err，返回内容: " + retStr);
            return null;
        } else {
            System.out.println("分析=业务返回结果，结果返回正确，Ok: " + msg);
            return msg;
        }
    }

    // 签退接口
    public void adviceMedicalInsuranceMonitorClear() {
        String inputData = "9110^" + staticParmOp() + "^|^" + onlineFlag + "^";
        String u = getMonitorUrl(inputData);

        System.out.println("发起签退请求===============");
        String retStr = HttpClientUtils.httpGet(u, null, -1, -1, true);
        System.out.println("签到返回结果: " + retStr);
    }

    public static void main(String[] args) {
        // String s = "26024|0|邹克志|19531021|01|342123195310218770|1|01|20201107110305|20210412092439|12|骨一科|542|于淼|26024||0|0|0|0|0|0|0|0|0||0|0|0|0&T00.901|胸腰部、右腕软组织挫伤|1&||&1852582|1|3|0|202104120006|20210412090912|7763|Z-A09BA-F0284|阿胶|阿胶||0.8500|6.00||||0||||1||542|于淼|||";

        if (args.length < 1) {
            System.out.println("请输入一个参数！");
        } else {
            String inputStr = args[0];
            System.out.println("inputStr: " + inputStr);

            ThMoniterTest t = new ThMoniterTest();
            boolean ret = t.adviceMedicalInsuranceMonitorSignIn();
            if (!ret) {
                System.out.println("业务周期号获取失败，退出！");
                System.exit(0);
            }

            t.adviceMedicalInsuranceMonitorOp(inputStr);
            t.adviceMedicalInsuranceMonitorClear();
        }
    }
}
