package cn.kkdlk.parse.dxf.utils;

import cn.kkdlk.generate.dxf.utils.StreamUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 10:05
 * @Description 多文本样式解析
 */
public class MTextStyleParse {

    /**
     * cad默认颜色ACI编码
     */
    private static final String DEFAULT_COLOR_ACI_CODE = "7";

    /**
     * cad默认颜色与16进制颜色转换文件
     */
    private static final String DEFAULT_COLOR_JSON_FILE_PATH = "dxf/color.json";

    /**
     * CAD颜色列表
     */
    private static JSONArray colorReflection = null;


    static {
        try (
                InputStream resourceStream = StreamUtil.getResourceStream(DEFAULT_COLOR_JSON_FILE_PATH);
                JSONReader reader = new JSONReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
        ) {
            colorReflection = reader.readObject(JSONArray.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 解析CAD ACI色号为16进制颜色标识
     *
     * @param cadCode
     * @return
     */
    public static String parseCadColorToHexColorCode(String cadCode) {
        String defaultHexColor = "";
        for (int i = 0; i < colorReflection.size(); i++) {
            JSONObject json = colorReflection.getJSONObject(i);
            if (json.getString("code").equals(cadCode)) {
                defaultHexColor = json.getString("color");
                if (defaultHexColor.startsWith("#")) {
                    defaultHexColor = defaultHexColor.substring(1);
                }
                // 添加透明度信息，默认为不透明（FF）
                defaultHexColor = (defaultHexColor + "FF").toUpperCase();
            } else if (json.getString("code").equals(DEFAULT_COLOR_ACI_CODE)) {
                defaultHexColor = json.getString("color");
                if (defaultHexColor.startsWith("#")) {
                    defaultHexColor = defaultHexColor.substring(1);
                }
                // 添加透明度信息，默认为不透明（FF）
                defaultHexColor = (defaultHexColor + "FF").toUpperCase();
            }
        }
        return defaultHexColor;
    }


}
