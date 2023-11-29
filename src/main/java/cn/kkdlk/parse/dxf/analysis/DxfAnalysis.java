package cn.kkdlk.parse.dxf.analysis;

import cn.kkdlk.parse.dxf.enums.CadVersionEnum;
import cn.kkdlk.parse.dxf.enums.DxfSystemEnum;
import cn.kkdlk.parse.dxf.enums.entities.*;
import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.model.headers.HeaderModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @AUTHOR ZhangJunJie
 * @DATE 2023/11/25
 * <p/>
 * 概要：DXF 文件解析
 */
public class DxfAnalysis {


    /**
     * 读取所有的行
     *
     * @param reader BufferedReader
     * @return 返回文件的所有数据，以每一行数据为一个item
     * @throws IOException IO异常
     */
    private static List<String> readAllLine(BufferedReader reader) throws IOException {
        List<String> list = Lists.newArrayList();
        String line = null;
        while ((line = reader.readLine()) != null) {
            list.add(line.trim());
        }
        return list;
    }

    /**
     * 解析dxf文件头信息，形成对象模型
     *
     * @param inputStream 文件流
     * @param charset     字符编码 （UTF-8  GBK）
     * @return
     * @throws IOException
     */
    public static HeaderModel parseDxfHeaderModel(InputStream inputStream, String charset) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            List<String> dxfAllLine = readAllLine(reader);
            return DxfHeaderParse.getHeaderParse(dxfAllLine);
        }
    }


    /**
     * 返回几何图像解析数据
     *
     * @param inputStream    dxf文件流
     * @param dxfHeaderModel dxf解析出的头信息
     * @return
     * @throws IOException
     */
    public static Map<String, List<GeometricModel>> parseDxfGeometricList(InputStream inputStream, HeaderModel dxfHeaderModel) throws IOException {
        /**
         * 从cad版本信息中获取字符编码
         */
        CadVersionEnum cadVersionEnum = CadVersionEnum.getCadVersion(dxfHeaderModel.getCadVersion());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            Map<String, List<GeometricModel>> map = new HashMap<>();
            //读取dxf所有的数据
            List<String> lineList = readAllLine(reader);
            // 解析dxf文件结构
            parseFile(lineList, map, dxfHeaderModel);
            return map;
        }
    }

    /**
     * 解析dxf文件结构
     *
     * @param lineList 总数据
     * @param map      接收解析的数据map
     */
    private static void parseFile(List<String> lineList, Map<String, List<GeometricModel>> map, HeaderModel dxfHeaderModel) {
        if (CollectionUtils.isEmpty(lineList)) {
            return;
        }
        int i = 0;
        String str = lineList.get(i);
        //未到文件结束标志
        while (!DxfSystemEnum.FILE_END.getCode().equals(str)) {
            str = lineList.get(++i);
            //实体段开始
            if (DxfSystemEnum.ENTITIES_START.getCode().equals(str)) {
                //解析实体
                parseEntities(i, lineList, map, dxfHeaderModel);
            }
            // 文件循环语句结束
        }
        // 解析函数结束

    }

    /**
     * 解析实体
     *
     * @param i              实体开始读取的行数
     * @param lineList       总数据
     * @param map            接收解析的数据map
     * @param dxfHeaderModel dxf文件的头信息模型
     */
    private static void parseEntities(int i, List<String> lineList, Map<String, List<GeometricModel>> map, HeaderModel dxfHeaderModel) {
        String str = null;
        while (true) {
            str = lineList.get(++i);

            //点开始
            if (PointEnum.POINT_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getPoint(i, lineList, map);
            }
            //圆开始
            if (CircleEnum.CIRCLE_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getCircle(i, lineList, map);
            }
            // 椭圆开始
            if (EllipseEnum.ELLIPSE_NAME.getCode().equals(str)) {
                // TODO 解析椭圆
            }
            // 圆弧开始
            if (ArcEnum.ARC_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getArc(i, lineList, map);
            }
            //直线开始
            if (LineEnum.LINE_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getLine(i, lineList, map);
            }
            //多线段
            if (PolyLineEnum.LWPOLYLINE_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getLWPolyLine(i, lineList, map);
            }
            // 多线段
            if (PolyLineEnum.POLYLINE_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getPolyLine(i, lineList, map);
            }
            // 文本
            if (TextEnum.TEXT_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getText(i, lineList, map);
            }
            // 多文本
            if (MTextEnum.MTEXT_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getMText(i, lineList, map);
            }
            // 插入图元
            if (InsertEnum.INSERT_NAME.getCode().equals(str)) {
                i = DxfEntitiesParse.getInsert(i, lineList, map);
            }
            //实体结束
            if (DxfSystemEnum.END_SEC.getCode().equals(str)) {
                break;
            }
        }
    }


}
