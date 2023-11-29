package cn.kkdlk.parse.dxf.transformation;


import cn.kkdlk.parse.dxf.constant.EntityNameConstant;
import cn.kkdlk.parse.dxf.transformation.impl.*;

import java.util.HashMap;
import java.util.Map;

public class TransformBuilder {

    /**
     * 容器
     */
    private Map<String, GeometricTransform> map;

    public TransformBuilder() {
        map = new HashMap<>();
        map.put(EntityNameConstant.ARC_NAME, GeometricArcTransformImpl.getSingleInstance());
        map.put(EntityNameConstant.CIRCLE_NAME, GeometricCircleTransformImpl.getSingleInstance());
        map.put(EntityNameConstant.LINE_NAME, GeometricLineTransformImpl.getSingleInstance());
        map.put(EntityNameConstant.POINT_NAME, GeometricPointTransformImpl.getSingleInstance());
        map.put(EntityNameConstant.TEXT_NAME, GeometricTextTransformImpl.getSingleInstance());
        map.put(EntityNameConstant.POLY_LINE_NAME, GeometricPolyLineTransformImpl.getSingleInstance());
    }

    /**
     * 构建方法
     *
     * @param type 类型
     * @return GeometricTransform
     */
    public static GeometricTransform builder(String type) {
        return Holder.transformBuilder.map.get(type);
    }

    /**
     * 静态内部类
     */
    public static class Holder {
        public static TransformBuilder transformBuilder = new TransformBuilder();
    }

}
