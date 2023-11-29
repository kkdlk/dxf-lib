package cn.kkdlk.generate.kml;

import org.locationtech.proj4j.*;

public class CGCS2000ToWGS84 {

    /**
     * 经纬度坐标转换
     * 坐标代码：authority是PROJ.4支持的名称空间的代码。目前支持的取值为EPSG、ESRI、WORLD、NA83、NAD27。如果没有提供权限，则假定使用EPSG名称空间。
     * 代码是权威名称空间中坐标系统的id。例如，在EPSG命名空间中，代码是一个整数值，用于标识EPSG数据库中的CRS定义。(代码作为字符串读取和处理)
     *
     * @param sourceFromName 源坐标空间代码，如：EPSG:4524
     * @param targetFromName 目标坐标空间代码, 如: EPSG:4326
     * @param x              经度
     * @param y              纬度
     * @return
     */
    public static ProjCoordinate converter(String sourceFromName, String targetFromName, Double x, Double y, Double z) {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromName(sourceFromName); // 大地2000坐标系
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName(targetFromName); // 正常经纬度坐标系


        CoordinateTransformFactory ctf = new CoordinateTransformFactory();
        CoordinateTransform transform = ctf.createTransform(sourceCRS, targetCRS);


        // 3. 将大地2000坐标转换为正常经纬度坐标
        ProjCoordinate sourceCoordinate = new ProjCoordinate(x, y, z); // 大地2000坐标点
        ProjCoordinate targetCoordinate = new ProjCoordinate(); // 用于存储转换后的坐标点
        transform.transform(sourceCoordinate, targetCoordinate);

        return targetCoordinate;
    }


    /**
     * 经纬度坐标转换
     * 坐标代码：authority是PROJ.4支持的名称空间的代码。目前支持的取值为EPSG、ESRI、WORLD、NA83、NAD27。如果没有提供权限，则假定使用EPSG名称空间。
     * 代码是权威名称空间中坐标系统的id。例如，在EPSG命名空间中，代码是一个整数值，用于标识EPSG数据库中的CRS定义。(代码作为字符串读取和处理)
     *
     * @param sourceFromName 源坐标空间代码，如：EPSG:4524
     * @param targetFromName 目标坐标空间代码, 如:
     * @param x              经度
     * @param y              纬度
     * @return
     */
    public static ProjCoordinate converter(String sourceFromName, String targetFromName, Double x, Double y) {
        return converter(sourceFromName, targetFromName, x, y, 0D);
    }

    /**
     * CGCS2000 3度带 中央经线108 带号36 转 wgs84
     * 经纬度坐标转换 (EPSG: 4524, EPSG: 4326)
     *
     * @param x 经度
     * @param y 纬度
     * @return
     */
    public static ProjCoordinate converter(Double x, Double y) {
        return converter("EPSG:4524", "EPSG:4326", x, y, 0D);
    }

    /**
     * CGCS2000 3度带 中央经线108 带号36 转 wgs84
     * 经纬度坐标转换 (EPSG: 4524, EPSG: 4326)
     *
     * @param x 经度
     * @param y 纬度
     * @param z 高程
     * @return
     */
    public static ProjCoordinate converter(Double x, Double y, Double z) {
        return converter("EPSG:4524", "EPSG:4326", x, y, z);
    }
}
