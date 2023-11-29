package cn.kkdlk.generate.dxf.utils;

public class StringUtil {

    /**
     * append line with  line separators 'CRLF'
     *
     * @param buffer will append content buffer
     * @param lines  lines
     */
    public static void appendLnCrLf(StringBuffer buffer, String... lines) {
        buffer.append(appendLnCrLf(lines));
    }

    /**
     * append line with  line separators 'CRLF'
     *
     * @param lines lines
     */
    public static String appendLnCrLf(String... lines) {
        StringBuilder result = new StringBuilder();
        for (String str : lines) {
            result.append(str).append("\r\n");
        }
        return result.toString();
    }


    /**
     * 将图表追加到res start，直到结果长度等于strLength
     *
     * @param start     您想要追加的图表开始
     * @param strLength 输出结果长度
     * @param res       res
     * @return 通过start chart追加到strLength
     */
    public static String appendStart(char start, int strLength, String res) {
        if (res.length() >= strLength) {
            return res;
        }
        int len = strLength - res.length();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < len; i++) {
            buffer.append(start);
        }
        buffer.append(res);
        return buffer.toString();
    }


}
