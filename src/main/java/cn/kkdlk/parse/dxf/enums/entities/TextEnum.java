package cn.kkdlk.parse.dxf.enums.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * dxf 文本枚举
 *
 * @author ytzjj
 * @date 2023/11/27
 */
@Getter
@AllArgsConstructor
public enum TextEnum {
    /**
     * 文本名称
     */
    TEXT_NAME("TEXT", "文本名称"),

    /**
     * 颜色
     */
    COLOR("62", "颜色"),

    /**
     * 文字高度
     */
    HEIGH("40", "文字高度"),

    /**
     * 文字宽度
     */
    WIDTH("41", "文字宽度"),
    /**
     * 文字旋转角度
     */
    ANGLE("50", "文字旋转角度"),
     /**
     * 文字倾斜角度
     */
    INCLINATION("51", "文字倾斜角度"),
    /**
     * 文本内容
     */
    TEXT_CONTENT("1", "文本内容"),
    /**
     * 图层名称
     */
    LAYER_NAME("8", "图层名称"),
    /**
     * 中心点x坐标
     */
    COORDINATE_X("10", "中心点x坐标"),
    /**
     * 中心点y坐标
     */
    COORDINATE_Y("20", "中心点y坐标"),
    /**
     * 中心点z坐标
     */
    COORDINATE_Z("30", "中心点z坐标");
    /**
     * 组码
     */
    private String code;

    /**
     * 组码名称
     */
    private String fieldName;
}
