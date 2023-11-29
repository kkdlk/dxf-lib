package cn.kkdlk.parse.dxf.enums.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolyLineEnum {

    /**
     * 块开始
     */
    BLOCK("0", "模块开始结束标记"),
    /**
     * 多线段名称
     */
    LWPOLYLINE_NAME("LWPOLYLINE", "多线段名称"),
    /**
     * 二号多段名称
     */
    POLYLINE_NAME("POLYLINE", "二号多段线"),

    /**
     * 图层名称
     */
    LAYER_NAME("8", "图层名称"),

    /**
     * x坐标
     */
    COORDINATE_X("10", "x坐标"),

    /**
     * y坐标
     */
    COORDINATE_Y("20", "y坐标"),

    /**
     * z坐标
     */
    COORDINATE_Z("30", "z坐标"),

    /**
     * 顶点数量
     */
    VERTEX_NUM("90", "顶点数量"),
    /**
     * 起点宽度
     */
    START_WIDTH("40", "起点宽度"),
    /**
     * 端点宽度
     */
    END_WIDTH("41", "端点宽度"),
    /**
     * 凸度
     */
    OUT_DO("42", "凸度"),
    /**
     * 顶点名称
     */
    VERTEX_NAME("VERTEX", "顶点名称"),


    /**
     * 多线段结束
     */
    SEQEND("SEQEND", "多线段结束"),

    /**
     * 多段线闭合
     */
    CLOSE("70", "多段线闭合");

    /**
     * 组码
     */
    private final String code;

    /**
     * 组码名称
     */
    private final String fieldName;

}
