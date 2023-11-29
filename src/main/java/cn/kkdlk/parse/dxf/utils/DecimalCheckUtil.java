package cn.kkdlk.parse.dxf.utils;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalCheckUtil {

    /**
     * 判断是否为数字
     *
     * @param checkStr
     * @return
     */
    public static boolean check(String checkStr) {
        if (!Strings.isNullOrEmpty(checkStr)) {
            String regExp = "^(-?\\d+)(\\.\\d+)?$";
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(checkStr);
            return matcher.matches();
        }
        return false;
    }
}
