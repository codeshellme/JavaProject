import org.apache.commons.codec.digest.DigestUtils;

public class TestDigestUtils {
    public static void main(String[] args) {
        String s = DigestUtils.sha256Hex("123456");
        System.out.println(s);
    }
}