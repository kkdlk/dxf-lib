package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 圆
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class DxfCircle extends BaseDxfEntity {

    /**
     * 圆心位置
     */
    private Vector3 center = new Vector3(0, 0, 0);
    /**
     * 半径
     */
    private BigDecimal radius = new BigDecimal(100);

    /**
     * BigDecimal
     **/
    public DxfCircle(Vector3 center, BigDecimal radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * double类型构造对象
     **/
    public DxfCircle(Vector3 center, Double radius) {
        this.center = center;
        this.radius = new BigDecimal(radius.toString());
    }

    /**
     * 支持double、BigDecimal
     *
     **/
    public <T extends Number> DxfCircle(T x, T y, T z, T radius) {
        this.center = new Vector3(x, y, z);
        this.radius = new BigDecimal(radius.toString());
    }

    public void setCenter(Vector3 center) {
        this.center = center;
    }

    public <T extends Number> void setCenter(T x, T y, T z) {
        this.center = new Vector3(x, y, z);
    }

    public <T extends Number> void setRadius(T radius) {
        this.radius = new BigDecimal(radius.toString());
    }

    @Override
    public String getEntityName() {
        return "CIRCLE";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbCircle";
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(10, center.getX())
                .append(20, center.getY())
                .append(30, center.getZ())
                .append(40, radius)
                .toString();
    }
}