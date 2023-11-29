package cn.kkdlk.parse.dxf.model.headers;


import cn.kkdlk.parse.dxf.model.DxfModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 17:04
 * @Description 头内容
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HeaderModel extends DxfModel implements Serializable {

    private static final long serialVersionUID = 6301011998061133804L;

    /**
     * cad主版本
     */
    private String cadVersion;

    /**
     * cad 维护版本
     */
    private String cadMaintenanceRelease;


    /**
     * ANSI代码页，字符编码
     * ANSI_936 代表中文
     */
    private String cadANSICoding;

    /**
     * 基点X坐标
     */
    private BigDecimal inBaseX;
    /**
     * 基点Y坐标
     */
    private BigDecimal inBaseY;
    /**
     * 基点Z坐标
     */
    private BigDecimal inBaseZ;


    /**
     * 图形的边界框的最小顶点 X
     */
    private BigDecimal extMinX;
    /**
     * 图形的边界框的最小顶点Y
     */
    private BigDecimal extMinY;
    /**
     * 图形的边界框的最小顶点Z
     */
    private BigDecimal extMinZ;


    /**
     * 图形的边界框的最大顶点 X
     */
    private BigDecimal extMaxX;
    /**
     * 图形的边界框的最大顶点Y
     */
    private BigDecimal extMaxY;
    /**
     * 图形的边界框的最大顶点Z
     */
    private BigDecimal extMaxZ;


    /**
     * 文本大小
     */
    private Double textSize;

}
