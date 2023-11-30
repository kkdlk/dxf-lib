package cn.kkdlk.generate.dxf.utils;

import cn.kkdlk.generate.dxf.model.entities.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class DxfUtil {


    public static boolean isPairNameEquals(String[] pair, String name) {
        if (pair == null) {
            return false;
        }
        return Objects.equals(name, pair[1].trim());
    }

    /**
     * 格式化图元句柄
     *
     * @param meta
     * @return
     */
    public static String formatMeta(long meta) {
        return StringUtil.appendStart('0', 3, Long.toHexString(meta)).toUpperCase();
    }

    /**
     * 读取最大图元句柄
     *
     * @param dxfFilePath
     * @return
     */
    public static long readMaxMeta(String dxfFilePath) {
        long maxMeta = 0;
        try (BufferedReader br = StreamUtil.getFileReader(dxfFilePath)) {
            while (true) {
                String[] pair = StreamUtil.readNextPair(br);
                if (pair == null) {
                    break;
                }
                if ("5".equals(pair[0].trim())) {
                    maxMeta = Math.max(maxMeta, Long.parseLong(pair[1].trim(), 16));
                }
            }
        } catch (IOException e) {
            System.err.println("read maxMeta error : " + e.getMessage());
        }
        return maxMeta;
    }

    /**
     * 格式颜色为DXF类型，大小写颜色十六进制代码为整数
     *
     * @param color
     * @return
     */
    public static int formatDxfColor(Color color) {
        if (color == null) {
            color = Color.BLACK;
        }
        return (color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue();
    }


    /**
     * 将整数的颜色值，转为16进制 类似html16进制颜色表示形式
     *
     * @param colorValue
     * @return
     */
    public static String convertToHtmlColor(int colorValue) {
        // 将整数值转为16进制字符串
        StringBuilder hexString = new StringBuilder(Integer.toHexString(colorValue));

        // 补足到6位
        while (hexString.length() < 6) {
            hexString.insert(0, "0");
        }

        // 提取透明度和颜色值的部分
        String alpha = hexString.substring(0, 2);
        String blue = hexString.substring(2, 4);
        String green = hexString.substring(4, 6);
        String red = hexString.substring(6);

        // 组合为KML的RGBA格式
        String kmlColor = alpha + blue + green + red;

        return kmlColor.toUpperCase();
    }

}
