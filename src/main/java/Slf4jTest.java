
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);

    public static void main(String[] args) {
        System.out.println(logger.isDebugEnabled());
        logger.debug("好的！");
        logger.info("找到服务{}，提供者: {}.", "啦啦", "哈哈");
        logger.warn("afjaf");
        logger.error("爱丽丝的叫法附件");
    }
}
