package cn.kkdlk.parse.dxf.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 16:32
 * @Description DXF 文件解析过程错误枚举
 */


@AllArgsConstructor
@Getter
public enum DxfAnalysisErrorEnum {


    /**
     * DXF文件解析未知错误
     */
    FILE_ANALYSIS_UNKNOWN_ERROR(2000, "DXF文件解析未知错误"),

    /**
     * 圆002x
     */
    CIRCLE_NOT_X(2020, "dxf解析圆数据没有获取到圆心x坐标"),
    CIRCLE_NOT_Y(2021, "dxf解析圆数据没有获取到圆心y坐标"),
    CIRCLE_NOT_Z(2022, "dxf解析圆数据没有获取到圆心z坐标"),
    CIRCLE_NOT_RADIUS(2023, "dxf解析圆数据没有获取到半径值"),

    /**
     * 点003x
     */
    POINT_NOT_X(2030, "dxf解析点数据没有获取到点x坐标"),
    POINT_NOT_Y(2031, "dxf解析点数据没有获取到点y坐标"),
    POINT_NOT_Z(2032, "dxf解析点数据没有获取到点z坐标"),

    /**
     * 直线004x
     */
    LINE_START_NOT_X(2040, "dxf解析直线数据没有获取到起点x坐标"),
    LINE_START_NOT_Y(2041, "dxf解析直线数据没有获取到起点y坐标"),
    LINE_START_NOT_Z(2042, "dxf解析直线数据没有获取到起点z坐标"),
    LINE_END_NOT_X(2043, "dxf解析直线数据没有获取到终点x坐标"),
    LINE_END_NOT_Y(2044, "dxf解析直线数据没有获取到终点y坐标"),
    LINE_END_NOT_Z(2045, "dxf解析直线数据没有获取到终点z坐标"),

    /**
     * 弧线005x
     */
    ARC_NOT_X(2050, "dxf解析弧线数据没有获取到圆心x坐标"),
    ARC_NOT_Y(2051, "dxf解析弧线数据没有获取到圆心y坐标"),
    ARC_NOT_Z(2052, "dxf解析弧线数据没有获取到圆心z坐标"),
    ARC_NOT_RADIUS(2053, "dxf解析弧线数据没有获取到半径"),
    ARC_NOT_START_ANGLE(2054, "dxf解析弧线数据没有获取到起始角度"),
    ARC_NOT_END_ANGLE(2055, "dxf解析弧线数据没有获取到中止角度"),


    /**
     * 多线段006x
     */
    POLY_LINE_NOT_COORDINATE_X(2060, "dxf解析多线段时没有获取到顶点x坐标"),
    POLY_LINE_NOT_COORDINATE_Y(2061, "dxf解析多线段时没有获取到顶点y坐标"),
    POLY_LINE_NOT_COORDINATE_Z(2062, "dxf解析多线段时没有获取到顶点z坐标"),
    POLY_LINE_NOT_SEQEND(2063, "dxf解析多线段时没有获取到结束标记");


    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;
}
