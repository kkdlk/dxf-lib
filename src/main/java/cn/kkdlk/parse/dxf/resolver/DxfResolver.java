package cn.kkdlk.parse.dxf.resolver;

import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.entities.*;
import cn.kkdlk.parse.dxf.transformation.model.DxfLine;

import java.util.List;
import java.util.Map;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 16:37
 * @Description Dxf解析器
 */
public interface DxfResolver {

    /**
     * 获取dxf几何数据（初步）
     *
     * @return 返回map数据
     */
    Map<String, List<GeometricModel>> getStructureMap();


    /**
     * 获取点线数据list，这种数据类型是符合rtas项目中使用的
     *
     * @return 点线数据
     */
    List<DxfLine> getDxfLineList();

    /**
     * 获取dxf文件中所有的几何点
     *
     * @return 所有的几何点
     */
    List<GeometricPoint> getGeometricPointList();

    /**
     * 获取dxf文件中所有的几何线
     *
     * @return 所有的几何线
     */
    List<GeometricLine> getGeometricLineList();


    /**
     * 获取dxf文件中所有的几何弧线
     *
     * @return 所有的几何弧线
     */
    List<GeometricArc> getGeometricArcList();


    /**
     * 获取dxf文件中所有的几何圆
     *
     * @return 所有的几何圆
     */
    List<GeometricCircle> getGeometricCircleList();

    /**
     * 获取dxf文件中所有的几何多线段
     *
     * @return 所有的几何多线段
     */
    List<GeometricPolyLine> getGeometricPolyLineList();

    /**
     * 获取dxf文件中所有的几何文本
     *
     * @return 所有的几何文本
     */
    List<GeometricText> getGeometricTextList();

}
