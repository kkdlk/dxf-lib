package cn.kkdlk.parse.dxf.transformation.impl;

import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.entities.GeometricPoint;
import cn.kkdlk.parse.dxf.transformation.GeometricTransform;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class GeometricPointTransformImpl implements GeometricTransform<GeometricPoint> {

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
            synchronized (GeometricPointTransformImpl.class) {
                if (geometricTransform == null) {
                    geometricTransform = new GeometricPointTransformImpl();
                }
            }
        }
        return geometricTransform;
    }

    /**
     * 几何点的转换
     *
     * @param objectList 转换的数据
     * @return List<GeometricPoint>
     */
    @Override
    public List<GeometricPoint> transform(List<GeometricModel> objectList) {
        List<GeometricPoint> pointList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(objectList)) {
            for (GeometricModel object : objectList) {
                if (object instanceof GeometricPoint) {
                    pointList.add((GeometricPoint) object);
                }
            }
        }
        return pointList;
    }
}
