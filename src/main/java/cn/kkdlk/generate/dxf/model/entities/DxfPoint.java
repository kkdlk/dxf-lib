package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Data;

/**
 * 点实体对象
 **/
@Data
public class DxfPoint extends BaseDxfEntity {
    private Vector3 point = new Vector3(0, 0, 0);

    public DxfPoint() {

    }

    public <T extends Number> DxfPoint(T x, T y, T z) {
        this.point = new Vector3(x, y, z);
    }

    public DxfPoint(Vector3 point) {
        this.point = point;
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(10, point.getX())
                .append(20, point.getY())
                .append(30, point.getZ()).toString();
    }

    @Override
    public String getEntityName() {
        return "POINT";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbPoint";
    }
}
