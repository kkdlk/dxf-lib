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
 * @DateTime 2023/11/27 9:17
 * @Description 圆
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeometricCircle extends GeometricModel implements Serializable {

    private static final long serialVersionUID = 1301011989021713849L;
    /**
     * 圆心的半径
     */
    private BigDecimal radius;

    /**
     * 圆心的x坐标
     */
    private BigDecimal x;

    /**
     * 圆心的y坐标
     */
    private BigDecimal y;

    /**
     * 圆心的z坐标
     */
    private BigDecimal z;
}
