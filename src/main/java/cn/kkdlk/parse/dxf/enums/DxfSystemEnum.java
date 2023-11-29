package cn.kkdlk.parse.dxf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @AUTHOR ZhangJunJie
 * @DATE 2023/11/25
 * <p/>
 * 概要：dxf 文件系统节点枚举
 */

@Getter
@AllArgsConstructor
public enum DxfSystemEnum {

    /**
     * cad版本的标志
     */
    ACADVER("$ACADVER", "cad版本"),

    /**
     * CAD 维护版本号
     */
    ACADMAINTVER("$ACADMAINTVER", "cad 维护版本号"),
    /**
     * CAD 字符编码（cad格式）
     */
    DWGCODEPAGE("$DWGCODEPAGE", "CAD 字符编码（cad格式）"),

    /**
     * CAD 基点
     */
    INBASE("zu", "CAD 基点"),

    /**
     * 图形边界框最小边界
     */
    EXTMIN("$EXTMIN", "CAD 基点"),

    /**
     * 图形边界框最大边界
     */
    EXTMAX("$EXTMAX", "CAD 基点"),

    /**
     * 图形文本大小
     */
    TEXTSIZE("$TEXTSIZE", "文本大小"),

    /**
     * 当前图层名称
     */
    CLAYER("$CLAYER", "当前图层名称"),

    /**
     * 当前图形创建时间
     */
    TDCREATE("$TDCREATE", "当前图层名称"),

    /**
     * 当前图形修改时间
     */
    TDUPDATE("$TDUPDATE", "当前图层名称"),

    /**
     * 当前图形唯一guid
     */
    FINGERPRINTGUID("$FINGERPRINTGUID", "当前图形唯一guid"),

    /**
     * 当前图形版本唯一guid
     */
    VERSIONGUID("$VERSIONGUID", "当前图形版本唯一guid"),


    /**
     * 文件结束的标志
     */
    FILE_END("EOF", "文件结束"),

    /**
     * 实体段开始（DXF的四大模块之一)
     */
    ENTITIES_START("ENTITIES", "实体开始"),

    /**
     * 每个4大模块结束标志
     */
    END_SEC("ENDSEC", "模块结束"),

    ;
    /**
     * 字段码
     */
    private final String code;

    /**
     * 组码名称
     */
    private final String fieldName;
}
