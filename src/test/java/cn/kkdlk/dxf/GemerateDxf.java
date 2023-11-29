package cn.kkdlk.dxf;

import cn.kkdlk.generate.dxf.DxfDocWriter;
import cn.kkdlk.generate.dxf.Vector3;
import cn.kkdlk.generate.dxf.model.entities.*;
import org.junit.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 16:44
 * @Description 生成dxf
 */
public class GemerateDxf {

    String url = "E:\\test\\te223st.dxf";

    @Test
    public void testWrite() {
        DxfDocWriter dxfDocWriter = new DxfDocWriter();
        Color color = new Color(255, 255, 255);

        // 创建多段线实体
        DxfLwPolyLine dxfLwPolyLine = new DxfLwPolyLine();
        java.util.List<java.util.List<Double>> points = new ArrayList<>();
        List<Double> point = new ArrayList();
        double x = 100;
        double y = 100;
        double z = 100;
        point.add(x);
        point.add(y);
        point.add(z);
        points.add(point);
        dxfLwPolyLine.setHeight(100D);

        x = 1000;
        y = 1000;
        z = 1000;
        point = new ArrayList();
        point.add(x);
        point.add(y);
        point.add(z);
        points.add(point);
        dxfLwPolyLine.setColor(color);

        x = 1100;
        y = 3000;
        z = 2500;
        point = new ArrayList();
        point.add(x);
        point.add(y);
        point.add(z);
        points.add(point);
        dxfLwPolyLine.addPoints(points);
        dxfLwPolyLine.setClose(true);
        dxfLwPolyLine.setAlpha(100);
        dxfLwPolyLine.setSolid(true);
        dxfLwPolyLine.setSolidColor(color);
        dxfDocWriter.addEntity(dxfLwPolyLine);

        // 创建圆弧
        DxfArc dxfArc = new DxfArc();

        dxfArc.setColor(color);
        dxfArc.setHeight(200D);
        dxfArc.setStartAngle(10);
        dxfArc.setEndAngel(160.0);
        dxfArc.setCenter(new Vector3(200.0, 100.0, 0.0));
        dxfArc.setRadius(new BigDecimal(200));
        dxfDocWriter.addEntity(dxfArc);

        // 创建圆
        DxfCircle dxfCircle = new DxfCircle();

        dxfCircle.setColor(new Color(100, 10, 150));
        dxfCircle.setHeight(300D);
        dxfCircle.setCenter(new Vector3(100.00, 100.00, 0.0));
        dxfCircle.setRadius(new BigDecimal(100));
        dxfCircle.setSolid(true);
        dxfCircle.setAlpha(100);
        dxfCircle.setSolidColor(new Color(100, 60, 70));
        dxfDocWriter.addEntity(dxfCircle);

        // 创建点
        DxfPoint dxfPoint = new DxfPoint();
        dxfPoint.setPoint(new Vector3(300.00, 300.0, 300.0));
        dxfPoint.setHeight(300D);
        dxfPoint.setColor(color);
        dxfDocWriter.addEntity(dxfPoint);

        // 创建文本
        DxfText dxfText = new DxfText();

        dxfText.setStartPoint(100, 200, 300);
        dxfText.setText("1234");
        dxfText.setColor(color);
        dxfText.setHigh(30);
        dxfText.setAlpha(100);
        dxfText.setSolidAlpha(50);

        dxfDocWriter.addEntity(dxfText);

        // 创建椭圆
        DxfEllipse dxfEllipse = new DxfEllipse();
        dxfEllipse.setClose(true);
        dxfEllipse.setColor(color);
        dxfEllipse.setCenterPoint(200, 200, 0);
        dxfEllipse.setMajorAxisPoint(600, 200, 0);
        dxfEllipse.setShortAxisScale(0.5);
        dxfDocWriter.addEntity(dxfEllipse);

        // 创建线段
        DxfLine dxfLine = new DxfLine();
        dxfLine.setStartPoint(1000, 1000, 0);
        dxfLine.setEndPoint(2000, 2000, 0);
        dxfLine.setColor(color);
        dxfDocWriter.addEntity(dxfLine);

        // 创建射线
        DxfRay dxfRay = new DxfRay();
        dxfRay.setStart(-100, -100, 0);
        dxfRay.setDirection(-200, -200, 0);
        dxfRay.setColor(color);
        dxfDocWriter.addEntity(dxfRay);

        dxfDocWriter.save(url, true);
    }
}
