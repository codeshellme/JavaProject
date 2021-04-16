import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DllLoadTest {
    /**
     * 医保监控接口使用
     */
    private static String HmaSiInterfacePath = "/MT/eclipse/HmaSiInterface.dll"; 	// TODO 设置
    private static AdviceMonitorHmaSiInterface hmaSiInterface =
            (AdviceMonitorHmaSiInterface) Native.loadLibrary(HmaSiInterfacePath, AdviceMonitorHmaSiInterface.class);

    private String hspNum = "200002";				// 医疗机构编号	固定 TODO 设置
    private String operatorNum = "001";				// 操作员编号		固定	TODO 设置
    private String centerNum = "0000";				// 中心编码		固定 TODO 设置
    private String onlineFlag = "1";				// 联机标志		固定
    private String cycleNum = "";					// 业务周期号 调佣签到接口时返回

    public void test(String param) throws UnsupportedEncodingException {
        // 请求接口
        if (!adviceMedicalInsuranceMonitorInit()) {
            // 初始化失败 TODO
            return;
        }

        // 签到
        byte[] bt = new byte[2048];
        int ret = adviceMedicalInsuranceMonitorSignIn(bt);
        if (ret < 0) {
            // 签到失败 TODO
            return;
        }

        // 调用监控接口
        ret = adviceMedicalInsuranceMonitorOp(param, bt);
        if (ret < 0) {
            // 调用失败 TODO
            return;
        }

        // 调用成功，处理结果
        // 合格数据 20210412102339-00200002-1265^1^0^0|||^
        String s = Native.toString(bt,"gbk");
        System.out.println("结果：" + s);
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
        String d1 = "26221|0|张俊英|19610318|01|342123196103181041|2|01|20201110193251|20210412182519|12|骨一科|542|于淼|26221||0|0|0|0|0|0|0|0|0||0|0|0|1&T00.901|全身多处软组织挫伤|1&||&1852563|1|2|11|202104090002|20210409080331|6903|86906014000510|氢氯噻嗪片|氢氯噻嗪片||0.0290|1.00||100s*25mg|mg|10.0000|21||片|1||542|于淼|||";
        String d2 = "26221|0|张俊英|19610318|01|342123196103181041|2|01|20201110193251|20210412182519|12|骨一科|542|于淼|26221||0|0|0|0|0|0|0|0|0||0|0|0|1&T00.901|全身多处软组织挫伤|1&||&1852564|1|2|11|202104090002|20210409080331|7226|86901075000349|盐酸小檗碱片|盐酸小檗碱片||0.1250|1.00||0.1g*100片|g|0.1000|21||片|1||542|于淼|||";

        new DllLoadTest().test(d1);
        new DllLoadTest().test(d2);
    }


    /**
     * 医保监控接口使用
     * @return
     */
    // 接口初始化
    private boolean adviceMedicalInsuranceMonitorInit() {
        byte[] bt = new byte[2048];		// 初始值为 0

        //初始化接口
        int	init = hmaSiInterface.INIT(bt);
        if(init < 0){
            String initResult = Native.toString(bt,"gbk");
            System.out.println("医保监控接口 初始化失败：" + initResult);
            return false;
        }

        return true;
    }

    // 接口签到
    private int adviceMedicalInsuranceMonitorSignIn(byte[] bt) {
        String data = "9100^" + this.staticParmInit() + "^|^" + this.onlineFlag + "^";
        int ret =  hmaSiInterface.BUSINESS_HANDLE(data, bt);
        if(ret < 0){
            return ret;
        }

        // TODO 业务周期号赋值 待测试
        String signResult = Native.toString(bt,"gbk");
        this.cycleNum = signResult.split("^")[2].split("|")[0];
        return ret;
    }

    // 调用业务接口
    private int adviceMedicalInsuranceMonitorOp(String inputData, byte[] bt) throws UnsupportedEncodingException {
        String data = "5210^" + this.staticParmOp() +"^"+ inputData +"^"+ this.onlineFlag + "^";

        String utf8 = new String(data.getBytes( "UTF-8"));
        String unicode = new String(utf8.getBytes(),"UTF-8");
        String gbk = new String(unicode.getBytes("GBK"));

        return hmaSiInterface.BUSINESS_HANDLE(gbk, bt);
    }

    private String serialNum() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        String t = ft.format(new Date());
        return t +"-"+ this.hspNum +"-"+ Math.round((Math.random() + 1)*1000);
    }

    private String staticParmInit() {
        return this.hspNum +"^"+ this.operatorNum +"^^"+ this.serialNum() +"^"+ this.centerNum;
    }

    private String staticParmOp() {
        return this.hspNum +"^"+ this.operatorNum +"^"+ this.cycleNum + "^"+ this.serialNum() +"^"+ this.centerNum;
    }
}

interface AdviceMonitorHmaSiInterface extends Library {
    public int INIT(byte[] pErrMsg);
    public int BUSINESS_HANDLE(String inputData, byte[] pErrMsg);
//    String p = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "libcurl-x64.dll";
//    TestDll dll = (TestDll) Native.loadLibrary(p, TestDll.class);
}