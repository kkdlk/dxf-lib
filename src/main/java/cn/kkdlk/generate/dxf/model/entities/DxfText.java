package cn.kkdlk.generate.dxf.model.entities;

import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.BaseDxfEntity;
import cn.kkdlk.generate.dxf.utils.DxfLineBuilder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 文字
 */
@Getter
@Setter
public class DxfText extends BaseDxfEntity {

    /**
     * 不翻转
     */
    public static final int REVERSE_TYPE_NONE = 0;
    /**
     * 沿X轴方向镜像翻转
     */
    public static final int REVERSE_TYPE_X = 2;
    /**
     * 沿Y轴方向镜像翻转
     */
    public static final int REVERSE_TYPE_Y = 4;

//    public static final int ALIGN_HORIZONTAL_LEFT = 0;
//    public static final int ALIGN_HORIZONTAL_CENTER = 1;
//    public static final int ALIGN_HORIZONTAL_RIGHT = 2;

//    public static final int ALIGN_VERTICAL_BASE_LINE = 0;
//    public static final int ALIGN_VERTICAL_BASE_BOTTOM = 1;
//    public static final int ALIGN_VERTICAL_BASE_CENTER = 2;
//    public static final int ALIGN_VERTICAL_BASE_TOP = 3;


    /**
     * 文字的起始位置
     */
    private Vector3 startPoint = new Vector3(0, 0, 0);
    /**
     * 文字的高度
     */
    private BigDecimal high = new BigDecimal(0);
    /**
     * 文字的宽度
     */
    private int width = 1;
    /**
     * 文字内容
     */
    private String text = "";
    /**
     * 旋转角度
     */
    private BigDecimal angle = new BigDecimal(0);
    /**
     * 倾斜角度
     */
    private BigDecimal inclination = new BigDecimal(0);
    //    private String textStyle = "标准";
    private int reverseType = REVERSE_TYPE_NONE;
    // 暂时不使用这两个属性
    //    private int alignHorizontal = ALIGN_HORIZONTAL_LEFT;
//    private int alignVertical = ALIGN_VERTICAL_BASE_LINE;
    // 这个值暂时也没用，只有当上面两个属性使用的时候这个值才有使用的必要
//    private Vector3 endPoint;

    public void setStartPoint(Vector3 startPoint) {
        this.startPoint = startPoint;
    }

    public <T extends Number> void setStartPoint(T x, T y, T z) {
        this.startPoint = new Vector3(x, y, z);
    }

    public <T extends Number> void setHigh(T high) {
        this.high = new BigDecimal(high.toString());
    }

    public <T extends Number> void setAngle(T angle) {
        this.angle = new BigDecimal(angle.toString());
    }

    public <T extends Number> void setInclination(T inclination) {
        this.inclination = new BigDecimal(inclination.toString());
    }

    @Override
    protected String getChildDxfStr() {
        DxfLineBuilder lineBuilder = DxfLineBuilder.build()
                .append(10, startPoint.getX())
                .append(20, startPoint.getY())
                .append(30, startPoint.getZ())
                .append(40, high)
                .append(1, text);
        if (angle.equals(0)) {
            lineBuilder.append(50, angle);
        }
        if (width != 1) {
            lineBuilder.append(41, width);
        }
        if (inclination.equals(0)) {
            lineBuilder.append(51, inclination);
        }
        if (reverseType != REVERSE_TYPE_NONE) {
            lineBuilder.append(71, reverseType);
        }
        lineBuilder.append(100, getEntityClassName());
        return lineBuilder.toString();
    }

    @Override
    public String getEntityName() {
        return "TEXT";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbText";
    }
}
