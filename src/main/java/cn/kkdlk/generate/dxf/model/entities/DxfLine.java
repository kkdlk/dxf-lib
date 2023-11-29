package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 线段
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DxfLine extends BaseDxfEntity {
    /**
     * 起始点位置
     */
    private Vector3 startPoint = new Vector3(0, 0, 0);
    /**
     * 结束点位置
     */
    private Vector3 endPoint = new Vector3(150, 150, 0);

    public void setStartPoint(Vector3 startPoint) {
        this.startPoint = startPoint;
    }

    public <T extends Number> void setStartPoint(T x, T y, T z) {
        this.startPoint = new Vector3(x, y, z);
    }

    public void setEndPoint(Vector3 endPoint) {
        this.endPoint = endPoint;
    }

    public <T extends Number> void setEndPoint(T x, T y, T z) {
        this.endPoint = new Vector3(x, y, z);
    }

    @Override
    protected String getChildDxfStr() {
        return DxfLineBuilder.build()
                .append(10, startPoint.getX())
                .append(20, startPoint.getY())
                .append(30, startPoint.getZ())
                .append(11, endPoint.getX())
                .append(21, endPoint.getY())
                .append(31, endPoint.getZ())
                .toString();
    }

    @Override
    public String getEntityName() {
        return "LINE";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbLine";
    }
}
