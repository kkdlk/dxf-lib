package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * 射线
 */
@Getter
@Setter
public class DxfRay extends BaseDxfEntity {

    /**
     * 起始点位置
     */
    private Vector3 start = new Vector3(0, 0, 0);
    /**
     * 射线方向
     */
    private Vector3 direction = new Vector3(-100, -100, -100);

    public void setStart(Vector3 start) {
        this.start = start;
    }

    public <T extends Number> void setStart(T x, T y, T z) {
        this.start = new Vector3(x, y, z);
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public <T extends Number> void setDirection(T x, T y, T z) {
        this.direction = new Vector3(x, y, z);
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(10, start.getX())
                .append(20, start.getY())
                .append(30, start.getZ())
                .append(11, direction.getX())
                .append(21, direction.getY())
                .append(31, direction.getZ())
                .toString();
    }

    @Override
    public String getEntityName() {
        return "RAY";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbRay";
    }
}
