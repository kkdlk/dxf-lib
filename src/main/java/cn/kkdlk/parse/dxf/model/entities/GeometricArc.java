package cn.kkdlk.parse.dxf.model.entities;

import cn.kkdlk.parse.dxf.model.GeometricModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 9:16
 * @Description 弧度
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeometricArc extends GeometricModel implements Serializable {

    private static final long serialVersionUID = 1685606296528470644L;

    /**
     * 弧线的半径
     */
    private BigDecimal radius;

    /**
     * 弧的起始角度
     */
    private BigDecimal startArc;

    /**
     * 弧的终止角度
     */
    private BigDecimal endArc;

    /**
     * 弧线圆心的x坐标
     */
    private BigDecimal x;

    /**
     * 弧线圆心的y坐标
     */
    private BigDecimal y;

    /**
     * 弧线圆心的z坐标
     */
    private BigDecimal z;
}
