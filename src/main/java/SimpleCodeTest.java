import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 */
public class SimpleCodeTest {
    /**
     * 获取汉字串拼音首字母，英文字符不变
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        char[] arr = chinese.toCharArray();
        StringBuilder pybf = new StringBuilder();

        for (char c: arr) {
            if (c <= 128) {
                pybf.append(c);
                continue;
            }

            try {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, format);
                pybf.append(temp[0].charAt(0));
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        char[] arr = chinese.toCharArray();
        StringBuilder pybf = new StringBuilder();

        for (char c : arr) {
            if (c <= 128) {
                pybf.append(c);
                continue;
            }

            try {
                pybf.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        return pybf.toString();
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变（注意只转换中文）
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] arr = inputString.trim().toCharArray();
        StringBuilder output = new StringBuilder();

        for (char c : arr) {
            if (!Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                // 不是汉字
                output.append(c);
                continue;
            }

            // 是汉字
            try {
                output.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        String s = getPingYin("王z了");
        System.out.println(s);

        s = getFirstSpell("中华人民共和国");
        System.out.println(s);

        s = getFullSpell("王z了");
        System.out.println(s);

        s = getFullSpell("王z了");
        System.out.println(s);
    }
}