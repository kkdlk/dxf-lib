package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 椭圆
 */
@Getter
@Setter
public class DxfEllipse extends BaseDxfEntity {

    /**
     * 中心点
     */
    private Vector3 centerPoint = new Vector3(0, 0, 0);
    /**
     * 长轴相对于中心点的坐标
     */
    private Vector3 majorAxisPoint = new Vector3(200, 200, 0);
    /**
     * 短轴相对于长轴的缩放比例
     */
    private BigDecimal shortAxisScale = new BigDecimal("1.0");
    /**
     * 图形是否闭合
     */
    private boolean close = true;
    /**
     * 开始角度（0-360），当图形闭合时默认为0
     */
    private BigDecimal startAngle = new BigDecimal(0);
    /**
     * 结束角度(0-360)，当图形闭合时，默认是360
     */
    private BigDecimal endAngle = new BigDecimal(360);

    public <T extends Number> void setShortAxisScale(T shortAxisScale) {
        this.shortAxisScale = new BigDecimal(shortAxisScale.toString());
    }

    public <T extends Number> void setStartAngle(T startAngle) {
        this.startAngle = new BigDecimal(startAngle.toString());
    }


    public <T extends Number> void setEndAngle(T endAngle) {
        this.endAngle = new BigDecimal(endAngle.toString());
    }

    public void setCenterPoint(Vector3 centerPoint) {
        this.centerPoint = centerPoint;
    }

    public <T extends Number> void setCenterPoint(T x, T y, T z) {
        this.centerPoint = new Vector3(x, y, z);
    }

    public void setMajorAxisPoint(Vector3 majorAxisPoint) {
        this.majorAxisPoint = majorAxisPoint;
    }

    /**
     * 设置长轴
     **/
    public <T extends Number> void setMajorAxisPoint(T x, T y, T z) {
        this.majorAxisPoint = new Vector3(x, y, z);
    }


    @Override
    protected String getChildDxfStr() {
        DxfLineBuilder lineBuilder = DxfLineBuilder.build();
        lineBuilder
                .append(10, centerPoint.getX())
                .append(20, centerPoint.getY())
                .append(30, centerPoint.getZ())
                .append(11, majorAxisPoint.getX())
                .append(21, majorAxisPoint.getY())
                .append(31, majorAxisPoint.getZ())
                .append(40, shortAxisScale);
        if (close) {
            lineBuilder.append(41, 0);
            lineBuilder.append(42, 2 * Math.PI);
        } else {
            lineBuilder.append(41, startAngle.divide(new BigDecimal(180)).multiply(new BigDecimal(Math.PI)));
            lineBuilder.append(42, endAngle.divide(new BigDecimal(180)).multiply(new BigDecimal(Math.PI)));
        }

        return lineBuilder.toString();
    }

    @Override
    public String getEntityName() {
        return "ELLIPSE";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbEllipse";
    }
}
