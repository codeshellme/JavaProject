import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Test {

    public static String b64Encode(byte[] b) {
        return new BASE64Encoder().encode(b);
    }

    public static void main(String[] args) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        String text = "字串文字";
        byte[] textByte = text.getBytes("UTF-8");

        //编码
        String encodedText = encoder.encode(textByte);
        System.out.println(encodedText);

        //解码
        System.out.println(new String(decoder.decodeBuffer(encodedText), "UTF-8"));

        System.out.println(b64Encode(text.getBytes("UTF-8")));

        int n = 100;
        byte[] bytes = new byte[n];
    }
}