package cn.kkdlk.parse.dxf.model.entities;

import cn.kkdlk.parse.dxf.model.GeometricModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 9:18
 * @Description 多插线
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GeometricPolyLine extends GeometricModel implements Serializable {


    private static final long serialVersionUID = 6202012001043042478L;
    /**
     * 顶点数量
     */
    private Integer vertexNum;

    /**
     * 多个顶点点组成
     */
    private List<GeometricVertex> vertexList;

    /**
     * 逻辑闭合
     */
    private boolean logicClose;

    public boolean isLogicClose() {
        return logicClose;
    }
}
