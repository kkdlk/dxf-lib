package cn.kkdlk.generate.dxf.model.entities;


import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;

/**
 * Hatch
 */
@Getter
public class DxfHatch extends BaseDxfEntity {

    private DxfSolid dxfSolid;

    public static DxfHatch buildHatchBy(BaseDxfEntity dxfCircle) {
        DxfHatch dxfHatch = new DxfHatch();
        dxfHatch.dxfSolid = new DxfSolid();
        dxfHatch.dxfSolid.setDxfEntity(dxfCircle);
        dxfHatch.color = dxfCircle.getSolidColor() == null ? dxfCircle.getColor() : dxfCircle.getSolidColor();
        dxfHatch.alpha = dxfCircle.getSolidAlpha();
        return dxfHatch;
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(10, 0.0)
                .append(20, 0.0)
                .append(30, 0.0)
                .append(210, 0.0)
                .append(220, 0.0)
                .append(230, 1.0)
                .append(dxfSolid.getDxfStr())
                .toString();
    }

    @Override
    public String getEntityName() {
        return "HATCH";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbHatch";
    }

    public enum HatchType {
        /**
         * Solid
         */
        SOLID;
    }

}
