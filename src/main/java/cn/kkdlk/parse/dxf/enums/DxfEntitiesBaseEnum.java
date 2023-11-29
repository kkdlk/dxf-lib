package cn.kkdlk.parse.dxf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 17:21
 * @Description entities的基础信息枚举
 */

@Getter
@AllArgsConstructor
public enum DxfEntitiesBaseEnum {



    COLOR_16("420", "颜色16进制"),

    COLOR_CODE("62", "颜色编码"),

    ALPHA("440", "图形透明度"),

    LINE_WIDTH("370", "线宽"),

    HEIGHT("38", "标高"),


    ;

    /**
     * 组码
     */
    private String code;
    /**
     * 组码名称
     */
    private String fieldName;

}
