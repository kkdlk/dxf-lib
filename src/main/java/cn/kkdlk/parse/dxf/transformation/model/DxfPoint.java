package cn.kkdlk.parse.dxf.transformation.model;

import cn.kkdlk.parse.dxf.model.entities.GeometricPoint;
import cn.kkdlk.parse.dxf.model.entities.GeometricText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DxfPoint implements Serializable {

    private static final long serialVersionUID = 496109316666003262L;

    /**
     * 点编号
     */
    private Long pointNum;

    /**
     * 点的x坐标
     */
    private BigDecimal x;

    /**
     * 点的y坐标
     */
    private BigDecimal y;

    /**
     * 点的z坐标
     */
    private BigDecimal z;


    /**
     * 将GeometricPoint转化成DxfPoint
     *
     * @param point
     */
    public DxfPoint(Long num, GeometricPoint point) {
        this.pointNum = num;
        this.x = point.getX();
        this.y = point.getY();
        this.z = point.getZ();
    }

    /**
     * 将GeometricText转化成DxfPoint
     *
     * @param text
     */
    public DxfPoint(Long num, GeometricText text) {
        this.pointNum = num;
        this.x = text.getX();
        this.y = text.getY();
        this.z = text.getZ();
    }

}
