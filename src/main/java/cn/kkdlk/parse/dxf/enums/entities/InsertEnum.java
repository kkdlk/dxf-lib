package cn.kkdlk.parse.dxf.enums.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 插入图元
 *
 * @author ytzjj
 * @date 2023/11/27
 **/
@Getter
@AllArgsConstructor
public enum InsertEnum {
    /**
     * 插入图元
     */
    INSERT_NAME("INSERT", "插入图元"),
    /**
     * 图层名称
     */
    LAYER_NAME("8", "图层名称"),
    /**
     * x坐标
     */
    COORDINATE_X("10", "x坐标"),
    /**
     * y坐标
     */
    COORDINATE_Y("20", "y坐标"),
    /**
     * z坐标
     */
    COORDINATE_Z("30", "z坐标");
    /**
     * 组码
     */
    private String code;

    /**
     * 组码名称
     */
    private String fieldName;
}
