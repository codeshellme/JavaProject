import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        String curDir = System.getProperty("user.dir");
        System.out.println("你当前的工作目录为 :" + curDir);

        File file = new File(curDir);
        File[] files = file.listFiles();

        for (File file1 : files) {
            if (!file1.isDirectory() || file1.getName().startsWith(".")) {
                continue;
            }

            System.out.println("子路径：" + file1.getPath());
            System.out.println("子路径名称：" + file1.getName());
        }
    }
}
