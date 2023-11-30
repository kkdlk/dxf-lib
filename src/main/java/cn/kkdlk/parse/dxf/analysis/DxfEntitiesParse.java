package cn.kkdlk.parse.dxf.analysis;

import cn.kkdlk.generate.dxf.utils.DxfUtil;
import cn.kkdlk.parse.dxf.constant.EntityNameConstant;
import cn.kkdlk.parse.dxf.constant.PolyLineConstant;
import cn.kkdlk.parse.dxf.enums.DxfEntitiesBaseEnum;
import cn.kkdlk.parse.dxf.enums.entities.*;
import cn.kkdlk.parse.dxf.enums.error.DxfAnalysisErrorEnum;
import cn.kkdlk.parse.dxf.exception.DxfAnalysisException;
import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.entities.*;
import cn.kkdlk.parse.dxf.utils.DecimalCheckUtil;
import cn.kkdlk.parse.dxf.utils.MTextStyleParse;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 11:00
 * @Description dxf 解析entities 节点
 */
public class DxfEntitiesParse {

    public static final Integer DECIMAL_SIZE = 10;

    /**
     * 获取图元
     *
     * @param i        多线段开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回多线段读完最后行数
     */
    public static int getInsert(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str;
        GeometricPoint point = new GeometricPoint();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, point);

            if (InsertEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setLayerName(str);
            }
            if (InsertEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_X);
                }
                point.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (InsertEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Y);
                }
                point.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (InsertEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Z);
                }
                point.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> pointList = map.get(EntityNameConstant.POINT_NAME);
        if (pointList == null) {
            pointList = Lists.newArrayList();
        }
        pointList.add(point);
        map.put(EntityNameConstant.POINT_NAME, pointList);
        return i;
    }


    /**
     * 获取多文本
     *
     * @param i        多线段开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回多线段读完最后行数
     */
    public static int getMText(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricText point = new GeometricText();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, point);

            if (MTextEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setLayerName(str);
            } else if (MTextEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_X);
                }
                point.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            } else if (MTextEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Y);
                }
                point.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            } else if (MTextEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Z);
                }
                point.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            } else if (MTextEnum.MTEXT_CONTENT.getCode().equals(str)) {
                str = lineList.get(++i);
                String rule = "[{]\\\\fSimSun([|][a-z\\d]+){4};";
                String end = "(?<!\\\\)}";
                String changeLine = "\\P";
                str = str.replaceAll(rule, "");
                str = str.replaceAll(end, "");
                str = str.replace(changeLine, "\n");
                str = str.replace("\\}", "}");
                str = str.replace("\\{", "{");
                str = str.replace("\\\\f", "\\f");
                point.setText(str);
            } else if (MTextEnum.HEIGH.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setHigh(Double.parseDouble(str));
            } else if (MTextEnum.WIDTH.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setWidth(Double.parseDouble(str));
            } else if (MTextEnum.ANGLE.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setAngle(new BigDecimal(str));
            } else if (MTextEnum.INCLINATION.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setInclination(new BigDecimal(str));
            } else if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            } else {
                i++;
            }
        }
        List<GeometricModel> testList = map.get(EntityNameConstant.TEXT_NAME);
        if (testList == null) {
            testList = Lists.newArrayList();
        }
        testList.add(point);
        map.put(EntityNameConstant.TEXT_NAME, testList);
        return i;
    }

    /**
     * 获取单行文本
     *
     * @param i        多线段开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回多线段读完最后行数
     */
    public static int getText(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricText point = new GeometricText();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, point);

            if (TextEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setLayerName(str);
            }
            if (TextEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_X);
                }
                point.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (TextEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Y);
                }
                point.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (TextEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Z);
                }
                point.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (TextEnum.TEXT_CONTENT.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setText(str);
            }

            if (TextEnum.HEIGH.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setHigh(Double.parseDouble(str));
            }
            if (TextEnum.WIDTH.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setWidth(Double.parseDouble(str));
            }
            if (TextEnum.ANGLE.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setAngle(new BigDecimal(str));
            }
            if (TextEnum.INCLINATION.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setInclination(new BigDecimal(str));
            }

            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> testList = map.get(EntityNameConstant.TEXT_NAME);
        if (testList == null) {
            testList = Lists.newArrayList();
        }
        testList.add(point);
        map.put(EntityNameConstant.TEXT_NAME, testList);
        return i;
    }

    /**
     * 获取多线段
     *
     * @param i        多线段开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回多线段读完最后行数
     */
    public static int getLWPolyLine(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricPolyLine polyLine = new GeometricPolyLine();
        polyLine.setLogicClose(false);
        List<GeometricVertex> vertices = new ArrayList<>();
        int vertexNum = 0;
        while (true) {
            str = lineList.get(++i);
            // 多段线闭合
            if (PolyLineEnum.CLOSE.getCode().equals(str)) {
                str = lineList.get(++i);
                // 多段线的标志，表明这是一个闭合的多段线
                if (str.equals(PolyLineConstant.POLYLINE_LOGIC_CLOSE)) {
                    polyLine.setLogicClose(true);
                } else if (str.equals(PolyLineConstant.POLYLINE_LOGIC_CLOSE_1)) {
                    polyLine.setLogicClose(true);
                }
            }
            // 图层名称
            if (PolyLineEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                polyLine.setLayerName(str);
            }
            // 获取矢量图形的基本信息
            i = getGeometricBase(i, lineList, str, polyLine);

            // 顶点数量
            if (PolyLineEnum.VERTEX_NUM.getCode().equals(str)) {
                str = lineList.get(++i);
                vertexNum = Integer.parseInt(str.trim());
                polyLine.setVertexNum(vertexNum);
            } else if (PolyLineEnum.COORDINATE_X.getCode().equals(str)) {
                for (int j = 0; j < vertexNum; j++) {
                    GeometricVertex vertex = new GeometricVertex();
                    //顶点的x坐标
                    if (PolyLineEnum.COORDINATE_X.getCode().equals(str)) {
                        str = lineList.get(++i);
                        if (DecimalCheckUtil.check(str.trim())) {
                            vertex.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
                            str = lineList.get(++i);
                        } else {
                            break;
                        }
                    }
                    //顶点的y坐标
                    if (PolyLineEnum.COORDINATE_Y.getCode().equals(str)) {
                        str = lineList.get(++i);
                        vertex.setY(new BigDecimal(str.trim()));
                        if (DecimalCheckUtil.check(str.trim())) {
                            vertex.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
                            str = lineList.get(++i);
                        }
                        vertices.add(vertex);
                    }
                    //起点宽度
                    if (PolyLineEnum.START_WIDTH.getCode().equals(str)) {
                        i += 2;
                        str = lineList.get(i);
                    }
                    //端点宽度
                    if (PolyLineEnum.END_WIDTH.getCode().equals(str)) {
                        i += 2;
                        str = lineList.get(i);
                    }
                    //凸度
                    if (PolyLineEnum.OUT_DO.getCode().equals(str)) {
                        i += 2;
                        str = lineList.get(i);
                    }
                    // 最后一个点位 重置 flag 字段为 -1
                    if (j == vertexNum - 1) {
                        vertexNum = -1;
                    }
                }
            }
            // 顶点结束
            if (vertexNum < 0) {
                break;
            }
        }
        polyLine.setVertexList(vertices);
        List<GeometricModel> polyLines = map.get(EntityNameConstant.POLY_LINE_NAME);
        if (polyLines == null) {
            polyLines = Lists.newArrayList();
            map.put(EntityNameConstant.POLY_LINE_NAME, polyLines);
        }
        polyLines.add(polyLine);
        return i;

    }


    /**
     * 解析多段线
     *
     * @param i        多线段开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return: 返回多线段读完最后行数
     **/
    public static int getPolyLine(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricPolyLine polyLine = new GeometricPolyLine();
        polyLine.setLogicClose(false);
        List<GeometricVertex> vertices = new ArrayList<>();
        while (true) {
            str = lineList.get(++i);
            // 逻辑闭合
            if (PolyLineEnum.CLOSE.getCode().equals(str)) {
                str = lineList.get(++i);
                if (str.equals(PolyLineConstant.POLYLINE_LOGIC_CLOSE)) {
                    polyLine.setLogicClose(true);
                } else if (str.equals(PolyLineConstant.POLYLINE_LOGIC_CLOSE_1)) {
                    polyLine.setLogicClose(true);
                }
            }
            // 图层名称
            if (PolyLineEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                polyLine.setLayerName(str);
            }
            // 获取矢量图形的基本信息
            i = getGeometricBase(i, lineList, str, polyLine);
            if (PolyLineEnum.BLOCK.getCode().equals(str)) {
                str = lineList.get(++i);
                if (PolyLineEnum.VERTEX_NAME.getCode().equals(str)) {
                    GeometricVertex vertex = new GeometricVertex();
                    //顶点的x坐标
                    while (!PolyLineEnum.COORDINATE_X.getCode().equals(str)) {
                        str = lineList.get(++i);
                    }
                    if (PolyLineEnum.COORDINATE_X.getCode().equals(str)) {
                        str = lineList.get(++i);
                        if (DecimalCheckUtil.check(str.trim())) {
                            vertex.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
                            str = lineList.get(++i);
                        } else {
                            break;
                        }
                    }
                    //顶点的y坐标
                    if (PolyLineEnum.COORDINATE_Y.getCode().equals(str)) {
                        str = lineList.get(++i);
                        if (DecimalCheckUtil.check(str.trim())) {
                            vertex.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
                            str = lineList.get(++i);
                        }
                        vertices.add(vertex);
                    }

                    //顶点的z坐标
                    if (PolyLineEnum.COORDINATE_Z.getCode().equals(str)) {
                        str = lineList.get(++i);
                        if (DecimalCheckUtil.check(str.trim())) {
                            vertex.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
                            str = lineList.get(++i);
                        }
                        vertices.add(vertex);
                    }
                } else if (PolyLineEnum.SEQEND.getCode().equals(str)) {
                    break;
                }
            }
        }
        polyLine.setVertexList(vertices);
        List<GeometricModel> polyLines = map.computeIfAbsent(EntityNameConstant.POLY_LINE_NAME, k -> Lists.newArrayList());
        polyLines.add(polyLine);
        return i;
    }

    /**
     * 获取线
     *
     * @param i        线开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回直线读完的最后行数
     */
    public static int getLine(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricLine line = new GeometricLine();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, line);

            //图层名称
            if (LineEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                line.setLayerName(str);
            }
            //起点x坐标
            if (LineEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_START_NOT_X);
                }
                line.setStartX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //起点y的坐标
            if (LineEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_START_NOT_Y);
                }
                line.setStartY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //起点z的坐标
            if (LineEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_START_NOT_Z);
                }
                line.setStartZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //终点的x坐标
            if (LineEnum.LINE_END_X_COORDINATES.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_END_NOT_X);
                }
                line.setEndX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //终点的y坐标
            if (LineEnum.LINE_END_Y_COORDINATES.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_END_NOT_Y);
                }
                line.setEndY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //终点的z坐标
            if (LineEnum.LINE_END_Z_COORDINATES.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.LINE_END_NOT_Z);
                }
                line.setEndZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }

            if (ArcEnum.COLOR.getCode().equals(str)) {
                str = lineList.get(++i);
                line.setColor(MTextStyleParse.parseCadColorToHexColorCode(str));
            }

            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> linesList = map.get(EntityNameConstant.LINE_NAME);
        if (linesList == null) {
            linesList = Lists.newArrayList();
        }
        linesList.add(line);
        map.put(EntityNameConstant.LINE_NAME, linesList);
        return i;
    }


    /**
     * 获取圆
     *
     * @param i        圆开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回圆读完的最后行数
     */
    public static int getCircle(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricCircle circle = new GeometricCircle();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, circle);

            //图层名
            if (CircleEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                circle.setLayerName(str);
            }
            //圆心的x坐标
            if (CircleEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.CIRCLE_NOT_X);
                }
                circle.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆心的y坐标
            if (CircleEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.CIRCLE_NOT_Y);
                }
                circle.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆心的z坐标
            if (CircleEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.CIRCLE_NOT_Z);
                }
                circle.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //解析圆的半径
            if (CircleEnum.CIRCULAR_RADIUS.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.CIRCLE_NOT_RADIUS);
                }
                circle.setRadius(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (CircleEnum.COLOR.getCode().equals(str)) {
                str = lineList.get(++i);
                circle.setColor(MTextStyleParse.parseCadColorToHexColorCode(str));
            }
            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> circleList = map.get(EntityNameConstant.CIRCLE_NAME);
        if (circleList == null) {
            circleList = Lists.newArrayList();
        }
        circleList.add(circle);
        map.put(EntityNameConstant.CIRCLE_NAME, circleList);
        return i;
    }


    /**
     * 解析点
     *
     * @param i        点开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回点读完的最后行数
     */
    public static int getPoint(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricPoint point = new GeometricPoint();

        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, point);

            if (PointEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setLayerName(str);
            }
            if (PointEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_X);
                }
                point.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (PointEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Y);
                }
                point.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (PointEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.POINT_NOT_Z);
                }
                point.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            if (PointEnum.COLOR.getCode().equals(str)) {
                str = lineList.get(++i);
                point.setColor(MTextStyleParse.parseCadColorToHexColorCode(str));
            }

            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> pointList = map.get(EntityNameConstant.POINT_NAME);
        if (pointList == null) {
            pointList = Lists.newArrayList();
        }
        pointList.add(point);
        map.put(EntityNameConstant.POINT_NAME, pointList);
        return i;
    }


    /**
     * 获取弧线
     *
     * @param i        弧线开始读取的行数
     * @param lineList 总数据
     * @param map      接收解析的数据map
     * @return 返回弧线读完的最后行数
     */
    public static int getArc(int i, List<String> lineList, Map<String, List<GeometricModel>> map) {
        String str = null;
        GeometricArc arc = new GeometricArc();
        while (true) {
            str = lineList.get(++i);

            i = getGeometricBase(i, lineList, str, arc);

            //图层名
            if (ArcEnum.LAYER_NAME.getCode().equals(str)) {
                str = lineList.get(++i);
                arc.setLayerName(str);
            }
            //圆弧圆心x坐标
            if (ArcEnum.COORDINATE_X.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_X);
                }
                arc.setX(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆弧圆心y坐标
            if (ArcEnum.COORDINATE_Y.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_Y);
                }
                arc.setY(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆弧圆心z坐标
            if (ArcEnum.COORDINATE_Z.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_Z);
                }
                arc.setZ(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆弧半径
            if (ArcEnum.ARC_RADIUS.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_RADIUS);
                }
                arc.setRadius(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆弧起始角度
            if (ArcEnum.ARC_START_ANGLE.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_START_ANGLE);
                }
                arc.setStartArc(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }
            //圆弧中止角度
            if (ArcEnum.ARC_END_ANGLE.getCode().equals(str)) {
                str = lineList.get(++i);
                if (!DecimalCheckUtil.check(str.trim())) {
                    throw new DxfAnalysisException(DxfAnalysisErrorEnum.ARC_NOT_END_ANGLE);
                }
                arc.setEndArc(new BigDecimal(str.trim()).setScale(DECIMAL_SIZE, RoundingMode.HALF_UP));
            }

            if (ArcEnum.COLOR.getCode().equals(str)) {
                str = lineList.get(++i);
                arc.setColor(MTextStyleParse.parseCadColorToHexColorCode(str));
            }

            if (str.equals("0") && "5".equals(lineList.get(i + 2))) {
                // 实体结束
                break;
            }
        }
        List<GeometricModel> arcList = map.get(EntityNameConstant.ARC_NAME);
        if (arcList == null) {
            arcList = Lists.newArrayList();
        }
        arcList.add(arc);
        map.put(EntityNameConstant.ARC_NAME, arcList);
        return i;
    }


    /**
     * 获取矢量图形的基本信息
     *
     * @param i
     * @param lineList
     * @param str
     * @param geomertic
     * @return
     */
    public static int getGeometricBase(int i, List<String> lineList, String str, GeometricModel geomertic) {
        if (DxfEntitiesBaseEnum.COLOR_16.getCode().equals(str)) {
            str = lineList.get(++i);
            geomertic.setColor(DxfUtil.convertToHtmlColor(Integer.parseInt(str)));
        }

        if (DxfEntitiesBaseEnum.COLOR_CODE.getCode().equals(str)) {
            str = lineList.get(++i);
            geomertic.setColor(MTextStyleParse.parseCadColorToHexColorCode(str));
        }

        if (DxfEntitiesBaseEnum.ALPHA.getCode().equals(str)) {
            str = lineList.get(++i);
            geomertic.setAlpha(Integer.parseInt(str));
        }
        if (DxfEntitiesBaseEnum.LINE_WIDTH.getCode().equals(str)) {
            str = lineList.get(++i);
            geomertic.setLineWidth(Double.parseDouble(str));
        }
        if (DxfEntitiesBaseEnum.HEIGHT.getCode().equals(str)) {
            str = lineList.get(++i);
            geomertic.setHeight(Double.parseDouble(str));
        }
        return i;
    }
}
