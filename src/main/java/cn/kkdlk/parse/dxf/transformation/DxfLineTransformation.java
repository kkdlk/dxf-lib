package cn.kkdlk.parse.dxf.transformation;


import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.transformation.model.DxfLine;

import java.util.List;

public interface DxfLineTransformation {


    /**
     * 将几何点转换为几何线
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> pointTransform(List<GeometricModel> list);

    /**
     * 将几何圆、转换为点圆数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> circleTransform(List<GeometricModel> list);


    /**
     * 将几何弧线、转换为点弧线数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> arcTransform(List<GeometricModel> list);


    /**
     * 将几何椭圆、转换为点椭圆数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> ellipseTransform(List<GeometricModel> list);

    /**
     * 将几何线、转换为点线数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> lineTransform(List<GeometricModel> list);

    /**
     * 将几何多线段、转换为点多线段数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> polyLineTransform(List<GeometricModel> list);

    /**
     * 将几何多线段、转换为点多线段数据
     *
     * @param list 需要转换的数据
     * @return 返回点线数据
     */
    List<DxfLine> textTransform(List<GeometricModel> list);
}
