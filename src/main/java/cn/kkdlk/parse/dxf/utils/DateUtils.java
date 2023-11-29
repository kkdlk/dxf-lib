package cn.kkdlk.parse.dxf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @AUTHOR ZhangJunJie
 * @DATE 2023/11/25
 * <p/>
 * 概要：时间工具类
 */
public class DateUtils {

    /**
     * CAD 版本大于 2010 儒略日
     */
    public static final Double CAD_GREATER_THAN_2010_JULIAN_DAY = 2440587.5;
    /**
     * CAD 版本小于等于 2010 儒略日
     */
    public static final Double CAD_LESS_THAN_2010_JULIAN_DAY = 2415020.5;

    /**
     * @param dxfTime   dxf中的时间
     * @param julianDay 儒略日（CAD中AutoCAD 2010 及更早版本基准儒略日更改为 2415020.5）
     * @return
     */
    public static Date dxfTime2Date(String dxfTime, Double julianDay) {
        double days = Double.parseDouble(dxfTime);
        double milliseconds = (days - julianDay) * 86400000; // 转换为毫秒数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date((long) milliseconds));

        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
