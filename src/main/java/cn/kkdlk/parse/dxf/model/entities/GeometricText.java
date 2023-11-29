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
 * @DateTime 2023/11/27 9:14
 * @Description 单行文本
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeometricText extends GeometricModel implements Serializable {

    private static final long serialVersionUID = 6202012001043042428L;
    /**
     * 文字名称
     */
    private String text;
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
     * 文字高度
     */
    private Double high = 1.0;

    /**
     * 文字的宽度
     */
    private Double width = 1.0;

    /**
     * 旋转角度
     */
    private BigDecimal angle = new BigDecimal(0);

    /**
     * 倾斜角度
     */
    private BigDecimal inclination = new BigDecimal(0);
}
