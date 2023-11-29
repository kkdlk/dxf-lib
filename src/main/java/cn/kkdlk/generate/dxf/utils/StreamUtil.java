package cn.kkdlk.generate.dxf.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StreamUtil {

    /**
     * close all stream
     */
    public static void closeStream(Closeable... cs) throws IOException {
        if (cs == null) {
            return;
        }
        for (Closeable c : cs) {
            c.close();
        }
    }

    public static BufferedReader getFileReader(String filePath) throws FileNotFoundException {
        return getReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
    }

    public static BufferedReader getFileReader(String filePath, Charset charset) throws FileNotFoundException {
        return getReader(new FileInputStream(filePath), charset);
    }

    public static BufferedReader getReader(InputStream is, Charset charset) {
        return new BufferedReader(new InputStreamReader(is, charset));
    }

    public static BufferedReader getReader(InputStream is) {
        return getReader(is, StandardCharsets.UTF_8);
    }

    public static BufferedWriter getFileWriter(String filePath, Charset charset) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), charset));
    }

    public static InputStream getResourceStream(String fileName) {
        return StreamUtil.class.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * 在dxf中，每个信息标记都是由代码-数据组合而成的，因此，我们可以通过读取文件两行来读取下一部分
     *
     * @param br BufferedReader
     * @return next tag info
     * @throws IOException if read file error,will throw this exception
     */
    public static String[] readNextPair(BufferedReader br) throws IOException {
        String code = br.readLine();
        if (code == null) {
            return null;
        }
        String codedata = br.readLine();
        return new String[]{code, codedata};
    }
}
