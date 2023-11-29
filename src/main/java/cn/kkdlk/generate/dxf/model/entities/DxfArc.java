package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 圆弧
 */
@Getter
@Setter
@NoArgsConstructor
public class DxfArc extends DxfCircle {
    /**
     * 起始角度(0-360)
     */
    private BigDecimal startAngle = new BigDecimal(0);
    /**
     * 结束角度(0-360)
     */
    private BigDecimal endAngle = new BigDecimal(360);

    public <T extends Number> void setStartAngle(T startAngle) {
        this.startAngle = new BigDecimal(startAngle.toString());
    }

    public <T extends Number> void setEndAngel(T endAngel) {
        this.endAngle = new BigDecimal(endAngel.toString());
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(super.getChildDxfStr())
                .append(100, "AcDbArc")
                .append(50, startAngle)
                .append(51, endAngle)
                .toString();
    }

    @Override
    public String getEntityName() {
        return "ARC";
    }

}
