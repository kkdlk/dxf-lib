package cn.kkdlk.parse.dxf.transformation.impl;

import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.entities.GeometricArc;
import cn.kkdlk.parse.dxf.transformation.GeometricTransform;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class GeometricArcTransformImpl implements GeometricTransform<GeometricArc> {

    /**
     * 实例对象
     */
    private static GeometricTransform geometricTransform;


    /**
     * 采用单例模式
     *
     * @return 返回实例对象
     */
    public static GeometricTransform getSingleInstance() {
        if (geometricTransform == null) {
            synchronized (GeometricArcTransformImpl.class) {
                if (geometricTransform == null) {
                    geometricTransform = new GeometricArcTransformImpl();
                }
            }
        }
        return geometricTransform;
    }

    /**
     * 转换几何弧线线段
     *
     * @param objectList 转换的数据
     * @return 转换后的几何弧线线段
     */
    @Override
    public List<GeometricArc> transform(List<GeometricModel> objectList) {
        List<GeometricArc> arcList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(objectList)) {
            for (GeometricModel object : objectList) {
                if (object instanceof GeometricArc) {
                    arcList.add((GeometricArc) object);
                }
            }
        }
        return arcList;
    }
}
