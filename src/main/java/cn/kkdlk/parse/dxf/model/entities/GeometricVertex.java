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
 * @DateTime 2023/11/27 9:20
 * @Description 顶点
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GeometricVertex extends GeometricModel implements Serializable {

    private static final long serialVersionUID = 6202012001043042018L;

    /**
     * 顶点的x坐标
     */
    private BigDecimal x;

    /**
     * 顶点的y坐标
     */
    private BigDecimal y;

    /**
     * 顶点的z坐标
     */
    private BigDecimal z;

}
