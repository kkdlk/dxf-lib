package cn.kkdlk.generate.dxf;

import lombok.*;
import org.checkerframework.framework.qual.QualifierArgument;

import java.math.BigDecimal;

/**
 * 三维矢量对象
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vector3 {

    public static Vector3 ZERO = new Vector3(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal z;

    public Vector3(String x, String y, String z) {
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
        this.z = new BigDecimal(z);
    }


    public <T extends Number> Vector3(T x, T y, T z) {
        this.x = new BigDecimal(x.toString());
        this.y = new BigDecimal(y.toString());
        this.z = new BigDecimal(z.toString());
    }

    public <T extends Number> Vector3(T x, T y) {
        this.x = new BigDecimal(x.toString());
        this.y = new BigDecimal(y.toString());
        this.z = new BigDecimal(0);
    }
}
