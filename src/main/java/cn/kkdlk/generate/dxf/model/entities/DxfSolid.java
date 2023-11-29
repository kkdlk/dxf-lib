package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.DxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import cn.kkdlk.generate.dxf.utils.DxfUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 图案填充，目前仅仅支持实体颜色填充，不支持图案填充与渐变填充
 */
@Getter
@Setter
public class DxfSolid {
    private DxfEntity dxfEntity;

    public String getDxfStr() {

        DxfLineBuilder builder = DxfLineBuilder.build(2, "SOLID")
                // 实体填充标志（实体填充 = 1；图案填充 = 0)
                .append(70, 1)
                // 关联性标志（关联 = 1；无关联 = 0）
                .append(71, 1)
                // 边界数
                .append(91, 1)
                // 边界路径类型标志0 = 默认；1 = 外部；2 = 多段线;4 = 导出；8 = 文本框；16 = 最外层
                .append(92, 1);
        // 顶点数(93=)?
        // 边界类型(72=) 1 = 直线；2 = 圆弧；3 = 椭圆弧；4 = 样条曲线
        if (dxfEntity instanceof DxfLwPolyLine && ((DxfLwPolyLine) dxfEntity).isClose()) {
            builder.append(93, ((DxfLwPolyLine) dxfEntity).getPoints().size());
            int size = ((DxfLwPolyLine) dxfEntity).getPoints().size();
            List<Vector3> points = ((DxfLwPolyLine) dxfEntity).getPoints();
            for (int i = 0; i < size; i++) {
                builder.append(72, 1);
                builder.append(10, points.get(i).getX());
                builder.append(20, points.get(i).getY());
                builder.append(11, points.get((i + 1) % size).getX());
                builder.append(21, points.get((i + 1) % size).getY());

            }

        } else if (dxfEntity instanceof DxfCircle) {
            builder.append(93, 1);
            builder.append(72, 2)
                    .append(10, ((DxfCircle) dxfEntity).getCenter().getX())
                    .append(20, ((DxfCircle) dxfEntity).getCenter().getY())
                    .append(30, ((DxfCircle) dxfEntity).getCenter().getZ())
                    .append(40, ((DxfCircle) dxfEntity).getRadius());
            // 下面是圆弧的属性，50-起始角度，51-端点角度， 73-逆时针标志
            if (dxfEntity instanceof DxfArc) {
                builder.append(50, ((DxfArc) dxfEntity).getStartAngle());
                builder.append(51, ((DxfArc) dxfEntity).getEndAngle());
            } else {
                builder.append(50, 0.0).append(51, 360);
            }
            builder.append(73, 1);
        }

        // 源边界对象数
        builder.append(97, 1)
                // 源边界对象的参照
                .append(330, DxfUtil.formatMeta(dxfEntity.getMeta()))
                // 图案填充样式：,0 = 填充“奇数奇偶校验”区域（普通样式）,1 = 仅填充最外层区域（“外部”样式）,2 = 填充整个区域（“忽略”样式）
                .append(75, 0)
                //填充图案类型：0 = 用户定义；1 = 预定义；2 = 自定义
                .append(76, 1)
                // 种子点数
                .append(98, 1);
        // 种子点位置 10-x, 20-y
        if (dxfEntity instanceof DxfCircle) {
            builder.append(10, ((DxfCircle) dxfEntity).getCenter().getX())
                    .append(20, ((DxfCircle) dxfEntity).getCenter().getY());
        } else {
            builder.append(10, 0.);
            builder.append(20, 0.);
        }
        return builder.toString();
    }
}
