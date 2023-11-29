package cn.kkdlk.generate.kml;

import cn.kkdlk.parse.dxf.constant.EntityNameConstant;
import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.entities.*;
import cn.kkdlk.parse.dxf.resolver.DxfResolver;
import cn.kkdlk.parse.dxf.transformation.DxfLineTransformation;
import cn.kkdlk.parse.dxf.transformation.impl.DxfLineTransformationImpl;
import cn.kkdlk.parse.dxf.transformation.model.DxfLine;
import cn.kkdlk.parse.dxf.transformation.model.DxfPoint;
import de.micromata.opengis.kml.v_2_2_0.*;
import org.locationtech.proj4j.ProjCoordinate;

import java.io.File;
import java.util.*;

/**
 * @Author ytzjj
 * @DateTime 2023/11/28 9:25
 * @Description kml文件生成
 */
public class GenerateKml {


    public static Kml gemerateKmlFile(File file, DxfResolver dxfResolver) {
        Kml kml = new Kml();
        // 构建kml
        Folder rootFolder = buildKml(kml, UUID.randomUUID().toString().replaceAll("-", ""), file.getName().substring(0, file.getName().indexOf(".")));

        Set<String> keyedSet = dxfResolver.getStructureMap().keySet();
        for (String key : keyedSet) {
            if (EntityNameConstant.POINT_NAME.equals(key)) {
                // 点
                List<GeometricPoint> geometricPointList = dxfResolver.getGeometricPointList();
                for (GeometricPoint geometricPoint : geometricPointList) {
                    Placemark placemark = rootFolder.createAndAddPlacemark();
                    placemark.setOpen(true);
                    String vertexId = String.valueOf(geometricPoint.getId());
                    placemark.setId(Optional.ofNullable(String.valueOf(vertexId)).filter(id -> !"".equals(id) && !"null".equals(id)).orElse(UUID.randomUUID().toString().replaceAll("-", "")));
                    placemark.setName(geometricPoint.getName());

                    Style style = placemark.createAndAddStyle();
                    LabelStyle labelStyle = new LabelStyle();
                    labelStyle.setColor(geometricPoint.getColor());
                    labelStyle.setColorMode(ColorMode.NORMAL);
                    labelStyle.setScale(1);
                    style.setLabelStyle(labelStyle);


                    Point point = new Point();
                    List<Coordinate> andSetCoordinates = point.createAndSetCoordinates();
                    Double x = geometricPoint.getX() != null ? geometricPoint.getX().doubleValue() : 0.0D;
                    Double y = geometricPoint.getY() != null ? geometricPoint.getY().doubleValue() : 0.0D;
                    Double z = geometricPoint.getZ() != null ? geometricPoint.getZ().doubleValue() : 0.0D;
                    ProjCoordinate projCoordinate = CGCS2000ToWGS84.converter(x, y, z);
                    projCoordinate.z = Double.isNaN(projCoordinate.z) ? 0D : projCoordinate.z;
                    andSetCoordinates.add(new Coordinate(projCoordinate.x, projCoordinate.y, projCoordinate.z));
                    placemark.setGeometry(point);
                }
            } else if (EntityNameConstant.LINE_NAME.equals(key)) {
                // 线条
                List<GeometricLine> geometricLineList = dxfResolver.getGeometricLineList();


                for (GeometricLine geometricLine : geometricLineList) {
                    Placemark placemark = rootFolder.createAndAddPlacemark();

                    Style andAddStyle = placemark.createAndAddStyle();
                    LabelStyle labelStyle = new LabelStyle();
                    labelStyle.setColor(geometricLine.getColor());
                    labelStyle.setColorMode(ColorMode.NORMAL);
                    andAddStyle.setLabelStyle(labelStyle);

                    placemark.setOpen(true);
                    String vertexId = String.valueOf(geometricLine.getId());
                    placemark.setId(Optional.ofNullable(String.valueOf(vertexId)).filter(id -> !"".equals(id) && !"null".equals(id)).orElse(UUID.randomUUID().toString().replaceAll("-", "")));
                    placemark.setName(geometricLine.getName());
                    LineString lineString = new LineString();
                    List<Coordinate> andSetCoordinates = lineString.createAndSetCoordinates();

                    Double x1 = geometricLine.getStartX() != null ? geometricLine.getStartX().doubleValue() : 0.0D;
                    Double y1 = geometricLine.getStartY() != null ? geometricLine.getStartY().doubleValue() : 0.0D;
                    Double z1 = geometricLine.getStartZ() != null ? geometricLine.getStartZ().doubleValue() : 0.0D;
                    ProjCoordinate projCoordinate1 = CGCS2000ToWGS84.converter(x1, y1, z1);
                    projCoordinate1.z = Double.isNaN(projCoordinate1.z) ? 0D : projCoordinate1.z;
                    andSetCoordinates.add(new Coordinate(projCoordinate1.x, projCoordinate1.y, projCoordinate1.z));


                    Double x2 = geometricLine.getEndX() != null ? geometricLine.getEndX().doubleValue() : 0.0D;
                    Double y2 = geometricLine.getEndY() != null ? geometricLine.getEndY().doubleValue() : 0.0D;
                    Double z2 = geometricLine.getEndZ() != null ? geometricLine.getEndZ().doubleValue() : 0.0D;
                    ProjCoordinate projCoordinate2 = CGCS2000ToWGS84.converter(x2, y2, z2);
                    projCoordinate2.z = Double.isNaN(projCoordinate2.z) ? 0D : projCoordinate2.z;
                    andSetCoordinates.add(new Coordinate(projCoordinate2.x, projCoordinate2.y, projCoordinate2.z));

                    placemark.setGeometry(lineString);
                }

            } else if (EntityNameConstant.POLY_LINE_NAME.equals(key)) {
                // 多线
                List<GeometricPolyLine> geometricPolyLineList = dxfResolver.getGeometricPolyLineList();

                for (GeometricPolyLine geometricPolyLine : geometricPolyLineList) {
                    List<GeometricVertex> vertexList = geometricPolyLine.getVertexList();

                    Placemark placemark = rootFolder.createAndAddPlacemark();
                    Style andAddStyle = placemark.createAndAddStyle();
                    LineStyle lineStyle = new LineStyle();
                    lineStyle.setColor(geometricPolyLine.getColor());
                    lineStyle.setColorMode(ColorMode.NORMAL);
                    lineStyle.setWidth(!Objects.equals(geometricPolyLine.getLineWidth(), 0D) ? geometricPolyLine.getLineWidth() : 1D);

                    String vertexId = String.valueOf(geometricPolyLine.getId());
                    placemark.setId(Optional.ofNullable(String.valueOf(vertexId)).filter(id -> !"".equals(id) && !"null".equals(id)).orElse(UUID.randomUUID().toString().replaceAll("-", "")));
                    placemark.setName(geometricPolyLine.getName());
                    LineString lineString = new LineString();
                    List<Coordinate> andSetCoordinates = lineString.createAndSetCoordinates();
                    for (GeometricVertex geometricVertex : vertexList) {
                        andAddStyle.setLineStyle(lineStyle);
                        Double x = geometricVertex.getX() != null ? geometricVertex.getX().doubleValue() : 0.0D;
                        Double y = geometricVertex.getY() != null ? geometricVertex.getY().doubleValue() : 0.0D;
                        Double z = geometricVertex.getZ() != null ? geometricVertex.getZ().doubleValue() : 0.0D;
                        ProjCoordinate projCoordinate = CGCS2000ToWGS84.converter(x, y, z);
                        projCoordinate.z = Double.isNaN(projCoordinate.z) ? 0D : projCoordinate.z;
                        andSetCoordinates.add(new Coordinate(projCoordinate.x, projCoordinate.y, projCoordinate.z));
                    }
                    placemark.setGeometry(lineString);
                }

            } else if (EntityNameConstant.ARC_NAME.equals(key)) {
                // 弧度
                List<GeometricArc> geometricArcList = dxfResolver.getGeometricArcList();
                List<GeometricModel> geometricModelList = new ArrayList<>(geometricArcList);
                // 将几何弧线、转换为点弧线数据
                DxfLineTransformation dxfLineTransformation = DxfLineTransformationImpl.getSingleInstance();
                List<DxfLine> dxfLines = dxfLineTransformation.arcTransform(geometricModelList);

                // 循环点弧线数据
                for (DxfLine dxfLine : dxfLines) {
                    List<DxfPoint> dataPointList = dxfLine.getDataPointList();
                    Placemark placemark = rootFolder.createAndAddPlacemark();
                    placemark.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                    placemark.setName(dxfLine.getLayerName());
                    LineString lineString = new LineString();
                    List<Coordinate> andSetCoordinates = lineString.createAndSetCoordinates();
                    // 循环弧线内的点数据
                    for (DxfPoint dxfPoint : dataPointList) {
                        Double x = dxfPoint.getX() != null ? dxfPoint.getX().doubleValue() : 0.0D;
                        Double y = dxfPoint.getY() != null ? dxfPoint.getY().doubleValue() : 0.0D;
                        Double z = dxfPoint.getZ() != null ? dxfPoint.getZ().doubleValue() : 0.0D;
                        ProjCoordinate projCoordinate = CGCS2000ToWGS84.converter(x, y, z);
                        projCoordinate.z = Double.isNaN(projCoordinate.z) ? 0D : projCoordinate.z;
                        andSetCoordinates.add(new Coordinate(projCoordinate.x, projCoordinate.y, projCoordinate.z));
                    }
                    placemark.setGeometry(lineString);
                }
            } else if (EntityNameConstant.CIRCLE_NAME.equals(key)) {
                // 圆形
                List<GeometricCircle> geometricCircleList = dxfResolver.getGeometricCircleList();
                for (GeometricCircle geometricCircle : geometricCircleList) {
                    Placemark placemark = rootFolder.createAndAddPlacemark();

                    Style andAddStyle = placemark.createAndAddStyle();
                    LabelStyle labelStyle = new LabelStyle();
                    labelStyle.setColor(geometricCircle.getColor());
                    labelStyle.setColorMode(ColorMode.NORMAL);
                    andAddStyle.setLabelStyle(labelStyle);


                    String vertexId = String.valueOf(geometricCircle.getId());
                    placemark.setId(Optional.ofNullable(String.valueOf(vertexId)).filter(id -> !"".equals(id) && !"null".equals(id)).orElse(UUID.randomUUID().toString().replaceAll("-", "")));
                    placemark.setName(geometricCircle.getName());
                    Point point = new Point();
                    List<Coordinate> andSetCoordinates = point.createAndSetCoordinates();
                    Double x = geometricCircle.getX() != null ? geometricCircle.getX().doubleValue() : 0.0D;
                    Double y = geometricCircle.getY() != null ? geometricCircle.getY().doubleValue() : 0.0D;
                    Double z = geometricCircle.getZ() != null ? geometricCircle.getZ().doubleValue() : 0.0D;
                    ProjCoordinate projCoordinate = CGCS2000ToWGS84.converter(x, y, z);
                    projCoordinate.z = Double.isNaN(projCoordinate.z) ? 0D : projCoordinate.z;
                    andSetCoordinates.add(new Coordinate(projCoordinate.x, projCoordinate.y, projCoordinate.z));
                    placemark.setGeometry(point);
                }

            } else if (EntityNameConstant.TEXT_NAME.equals(key)) {
                // 文本
                List<GeometricText> geometricTextList = dxfResolver.getGeometricTextList();
                for (GeometricText geometricText : geometricTextList) {
                    Placemark placemark = rootFolder.createAndAddPlacemark();

                    Style andAddStyle = placemark.createAndAddStyle();
                    LabelStyle labelStyle = new LabelStyle();
                    labelStyle.setColor(geometricText.getColor());
                    labelStyle.setColorMode(ColorMode.NORMAL);
                    andAddStyle.setLabelStyle(labelStyle);


                    String vertexId = String.valueOf(geometricText.getId());
                    placemark.setId(Optional.ofNullable(String.valueOf(vertexId)).filter(id -> !"".equals(id) && !"null".equals(id)).orElse(UUID.randomUUID().toString().replaceAll("-", "")));
                    placemark.setName(geometricText.getText());
                    Point point = new Point();
                    List<Coordinate> andSetCoordinates = point.createAndSetCoordinates();
                    Double x = geometricText.getX() != null ? geometricText.getX().doubleValue() : 0.0D;
                    Double y = geometricText.getY() != null ? geometricText.getY().doubleValue() : 0.0D;
                    Double z = geometricText.getZ() != null ? geometricText.getZ().doubleValue() : 0.0D;
                    ProjCoordinate projCoordinate = CGCS2000ToWGS84.converter(x, y, z);
                    projCoordinate.z = Double.isNaN(projCoordinate.z) ? 0D : projCoordinate.z;
                    andSetCoordinates.add(new Coordinate(projCoordinate.x, projCoordinate.y, projCoordinate.z));
                    placemark.setGeometry(point);
                }
            }
        }
        return kml;
    }

    public static Folder buildKml(Kml kml, String rootId, String rootName) {
        // 创建KML文档
        Document document = kml.createAndSetDocument();
        Folder rootFolder = document.createAndAddFolder();
        rootFolder.setId(rootId);
        rootFolder.setName(rootName);
        rootFolder.setOpen(true);
        return rootFolder;
    }

}
