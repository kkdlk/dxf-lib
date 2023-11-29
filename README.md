# DXF-LIB

DXF解析参考资料：[DXF 格式 - 中文参考手册](https://documentation.help/AutoCAD-DXF-zh/WSfacf1429558a55de185c428100849a0ab7-5f35.htm)


```xml

```



## DXF 文件解析组件

这是一个可以解析dxf文件的jar包，目前支持解析的图形有点、圆、直线、ARC弧线、多线段、单行文本、多行文本、图元

将其解析为几何数据和点线数据，存储在`DxfResolverImpl的baseStructureMap`中，按类型（KEY）分为

```java
public interface EntityNameConstant {
    /**
     * 点的名称
     */
    String POINT_NAME = "POINT";
    /**
     * 文本的名称
     */
    String TEXT_NAME = "TEXT";

    /**
     * 线的名称
     */
    String LINE_NAME = "LINE";

    /**
     * 弧线的名称
     */
    String ARC_NAME = "ARC";

    /**
     * 圆的名称
     */
    String CIRCLE_NAME = "CIRCLE";

    /**
     * 多线段
     */
    String POLY_LINE_NAME = "POLY_LINE";
}
```

包内提供了转换工具类[DxfLineTransformationImpl.java](src%2Fmain%2Fjava%2Fcn%2Fkkdlk%2Fparse%2Fdxf%2Ftransformation%2Fimpl%2FDxfLineTransformationImpl.java)，
将几何点转为几何线`pointTransform`、将几何圆转为点圆`circleTransform`、
将几何弧线转为点弧线`arcTransform`、将几何线、转换为点线数据`lineTransform`、
将几何多线段、转为点多线段数据`polyLineTransform`

例：

```java
public class ParseDxf {
    @Test
    public void parseDxfFile() {
        try {
            DxfResolver build = ParseDXF.build("E:\\test\\白马2000-3-108.dxf");
            // DxfResolver builtd = ParseDXF.build(new FileInputStream("E:\\test\\白马2000-3-108.dxf"));
            Map<String, List<GeometricModel>> structureMap = build.getStructureMap();
            System.out.println(structureMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

该库内有一个DXF转KML的例子，可参考查看 [GenerateKml.java](src%2Fmain%2Fjava%2Fcn%2Fkkdlk%2Fgenerate%2Fkml%2FGenerateKml.java)





---

## 生成DXF文件

dxf文件处理库，可以创建、加载dxf文件，并向其中添加新的图元。目前可以添加进去的图元有：

Arc、Circle 、Line 、LwPolyLine 、Text 、Ellipse 、Ray 、XLine


### 创建一个新的dxf文件
```java
public class GemerateDxf {

    String url = "E:\\test\\te223st.dxf";

    @Test
    public void testWrite() {
        try (DxfDocWriter dxfDocWriter = new DxfDocWriter()) {
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
}
```

### 加载一个已经存在的文件
```java
public class TestReadDxfFile {
    public static void main(String[] args) {
        try (DxfDocWriter dxfDocWriter = new DxfDocument(path)) {
            // body code, same as before code
        } 
    }
}
```




## DXF格式详解


> DXF（Drawing Exchange Format）是由AutoCAD开发的一种用于存储和交换计算机辅助设计（CAD）数据的文件格式。
> DXF文件是以文本形式存储的，也可以选择以二进制形式存储。以下是DXF文件的基本结构和一些常见的标签：



DXF 文件本质上由代码及关联值对组成。代码（通常称为组码）表明其后的值的类型。使用这些组码和值对，可以将 DXF 文件组织到由记录组成的区域中，
这些记录由组码和数据项目组成。在 DXF 文件中，每个组码和值都各占一行。

每段都以一个后跟字符串 SECTION 的组码 0 开始，其后是组码 2 和表示该段名称的字符串（例如，HEADER）。每段都由定义其元素的组码和值组成。每段都以一个后跟字符串 ENDSEC 的组码 0 结束。





[DXF 格式 - 中文参考手册](https://documentation.help/AutoCAD-DXF-zh/WSfacf1429558a55de185c428100849a0ab7-5f35.htm)





### DXF 文件完整的结构如下：


```text
1. HEADER 段。包含图形的基本信息。它由 AutoCAD 数据库版本号和一些系统变量组成。每个参数都包含一个变量名称及其关联的值。
2. CLASSES 段。包含应用程序定义的类的信息，这些类的实例出现在数据库的 BLOCKS、ENTITIES 和 OBJECTS 段中。类定义在类的层次结构中是固定不变的。
3. TABLES 段。包含以下符号表的定义：
    1. APPID（应用程序标识表）
    2. BLOCK_RECORD（块参照表）
    3. DIMSTYLE（标注样式表）
    4. LAYER（图层表）
    5. LTYPE（线型表）
    6. STYLE（文字样式表）
    7. UCS（用户坐标系表）
    8. VIEW（视图表）
    9. VPORT（视口配置表） 
4. BLOCKS 段。包含构成图形中每个块参照的块定义和图形图元。
5. ENTITIES 段。包含图形中的图形对象（图元），其中包括块参照（插入图元）。
6. OBJECTS 段。包含图形中的非图形对象。除图元、符号表记录以及符号表以外的所有对象都存储在此段。OBJECTS 段中的条目样例是包含多线样式和组的词典。
7. THUMBNAILIMAGE 段。包含图形的预览图像数据。此段为可选。
```


### 组码值类型参考 (DXF)

根据下面的组码范围表，组码将相关值的类型定义为整数、浮点数或字符串。

| 组码值类型 |                                                              |
| :--------- | :----------------------------------------------------------- |
| 代码范围   | 组值类型                                                     |
| 0-9        | 字符串（随着从 AutoCAD 2000 起引入了扩展符号名称，字数限制已由 255 个字符扩大到 2049 个单字节字符，不包括行末的换行符） |
| 10-39      | 双精度三维点值                                               |
| 40-59      | 双精度浮点值                                                 |
| 60-79      | 16 位整数值                                                  |
| 90-99      | 32 位整数值                                                  |
| 100        | 字符串（最多 255 个字符；对于 Unicode 字符串，字符数要少一些） |
| 102        | 字符串（最多 255 个字符；对于 Unicode 字符串，字符数要少一些） |
| 105        | 表示 16 进制 (hex) 句柄值的字符串                            |
| 110-119    | 双精度浮点值                                                 |
| 120-129    | 双精度浮点值                                                 |
| 130-139    | 双精度浮点值                                                 |
| 140-149    | 双精度标量浮点值                                             |
| 160-169    | 64 位整数值                                                  |
| 170-179    | 16 位整数值                                                  |
| 210-239    | 双精度浮点值                                                 |
| 270-279    | 16 位整数值                                                  |
| 280-289    | 16 位整数值                                                  |
| 290-299    | 布尔标志值                                                   |
| 300-309    | 任意字符串                                                   |
| 310-319    | 表示二进制数据块的十六进制值的字符串                         |
| 320-329    | 表示 16 进制句柄值的字符串                                   |
| 330-369    | 表示十六进制对象 ID 的字符串                                 |
| 370-379    | 16 位整数值                                                  |
| 380-389    | 16 位整数值                                                  |
| 390-399    | 表示 16 进制句柄值的字符串                                   |
| 400-409    | 16 位整数值                                                  |
| 410-419    | 字符串                                                       |
| 420-429    | 32 位整数值                                                  |
| 430-439    | 字符串                                                       |
| 440-449    | 32 位整数值                                                  |
| 450-459    | 长整数                                                       |
| 460-469    | 双精度浮点值                                                 |
| 470-479    | 字符串                                                       |
| 480-481    | 表示 16 进制句柄值的字符串                                   |
| 999        | 注释（字符串）                                               |
| 1000-1009  | 字符串（与 0-9 代码范围的限制相同）                          |
| 1010-1059  | 双精度浮点值                                                 |
| 1060-1070  | 16 位整数值                                                  |
| 1071       | 32 位整数值                                                  |

参考来源： [DXF 参考手册: 组码值类型 (autodesk.com)](http://docs.autodesk.com/ACD/2011/CHS/indexDXF.html?url=./filesDXF/WSfacf1429558a55de185c428100849a0ab7-5f35.htm,topicNumber=DXFd0e47)



### 按数字次序排列的 DXF 组码参考

下表列出了组码或组码范围以及组码值的解释。在此表中，“固定”表明组码始终具有相同的用途。如果组码不固定，则其用途取决于上下文。

| 按 数 字 次 序 排 列 的 组 码 |                                                              |
| :---------------------------- | :----------------------------------------------------------- |
| 组码                          | 说明                                                         |
| -5                            | APP：永久反应器链                                            |
| -4                            | APP：条件运算符（*仅*与 *ssget* 一起使用）                   |
| -3                            | APP：扩展数据 (XDATA) 标记（固定）                           |
| -2                            | APP：图元名参照（固定）                                      |
| -1                            | APP：图元名。每次打开图形时，图元名都会发生变化，从不保存（固定） |
| 0                             | 表示图元类型的字符串（固定）                                 |
| 1                             | 图元的主文字值                                               |
| 2                             | 名称（属性标记、块名等）                                     |
| 3-4                           | 其他文字或名称值                                             |
| 5                             | 图元句柄；最多 16 个十六进制数字的字符串（固定）             |
| 6                             | 线型名（固定）                                               |
| 7                             | 文字样式名（固定）                                           |
| 8                             | 图层名（固定）                                               |
| 9                             | DXF：变量名称标识符（仅在 DXF 文件的 HEADER 段中使用）       |
| 10                            | 主要点；直线或文字图元的起点、圆的圆心，等等DXF：主要点的 *X* 值（后跟 *Y* 和 *Z* 值代码 20 和 30）APP：三维点（三个实数的列表） |
| 11-18                         | 其他点DXF：其他点的 *X* 值（后跟 *Y* 值代码 21-28 和 *Z* 值代码 31-38）APP：三维点（三个实数的列表） |
| 20, 30                        | DXF：主要点的 Y 值和 *Z* 值                                  |
| 21-28, 31-37                  | DXF：其他点的 *Y* 值和 *Z* 值                                |
| 38                            | DXF：如果非零，则为图元的标高                                |
| 39                            | 如果非零，则为图元的厚度（固定)                              |
| 40-48                         | 双精度浮点值（文字高度、缩放比例等）                         |
| 48                            | 线型比例；双精度浮点标量值；默认值适用于所有图元类型         |
| 49                            | 重复的双精度浮点值。一个图元的可变长度表（例如，LTYPE 表中的虚线长度）中可能会出现多个 49 组。7*x* 组始终出现在第一个 49 组*之前*，用以指定表的长度 |
| 50-58                         | 角度（在 DXF 文件中以度为单位，在 AutoLISP 和 ObjectARX 应用程序中以弧度为单位） |
| 60                            | 图元可见性；整数值；未赋值或值为 0 时表示可见；值为 1 时表示不可见 |
| 62                            | 颜色号（固定）                                               |
| 66                            | “后跟图元”标志（固定）                                       |
| 67                            | 空间 — 模型空间或图纸空间（固定）                            |
| 68                            | APP：指示视口是处于打开状态但在屏幕上完全不可见，还是未激活或处于关闭状态 |
| 69                            | APP：视口标识号                                              |
| 70-78                         | 整数值，例如重复计数、标志位或模式                           |
| 90-99                         | 32 位整数值                                                  |
| 100                           | 子类数据标记（将派生类名作为字符串）从其他具体类派生的所有对象和图元类必须具有此标记。子类数据标记用于分离由同一对象的继承链中的不同类定义的数据。对于从 ObjectARX 派生的每个不同的具体类的 DXF 名称来说，这是必须满足的额外要求（参见***\*[ 子类标记](http://docs.autodesk.com/ACD/2011/CHS/filesDXF/WS1a9193826455f5ff18cb41610ec0a2e719-7945.htm#WSc30cd3d5faa8f6d81cb25f1ffb755717d-7ff9)\****） |
| 102                           | 控制字符串，后跟“{<任意名称>”或“}”。与扩展数据 1002 组码类似，不同之处在于当字符串以“{”开始时，其后可跟任意字符串，字符串的解释取决于应用程序。唯一允许的另外一个控制字符串是作为组结束符的“}”。除了执行图形核查操作期间外，AutoCAD 不会解释这些字符串。它们供应用程序使用 |
| 105                           | DIMVAR 符号表条目的对象句柄                                  |
| 110                           | UCS 原点（仅当将代码 72 设定为 1 时才显示）DXF：*X* 值；APP：三维点 |
| 111                           | UCS *X* 轴（仅当将代码 72 设定为 1 时才显示）DXF：*X* 值；APP：三维矢量 |
| 112                           | UCS *Y* 轴（仅当将代码 72 设定为 1 时才显示）DXF：*X* 值；APP：三维矢量 |
| 120-122                       | DXF：UCS 原点的 *Y* 值，UCS *X* 轴和 UCS *Y* 轴              |
| 130-132                       | DXF：UCS 原点的 Z 值，UCS *X* 轴和 UCS *Y* 轴                |
| 140-149                       | 双精度浮点值（例如点、标高和 DIMSTYLE 设置）                 |
| 170-179                       | 16 位整数值，例如表示 DIMSTYLE 设置的标志位                  |
| 210                           | 拉伸方向（固定）DXF：拉伸方向的 *X* 值APP：三维拉伸方向矢量  |
| 220, 230                      | DXF：拉伸方向的 *Y* 值和 *Z* 值                              |
| 270-279                       | 16 位整数值                                                  |
| 280-289                       | 16 位整数值                                                  |
| 290-299                       | 布尔标志值                                                   |
| 300-309                       | 任意字符串                                                   |
| 310-319                       | 具有相同表示和 1004 组码限制的任意二进制块：用最大长度为 254 个字符的十六进制字符串表示最大长度为 127 个字节的数据块 |
| 320-329                       | 任意对象句柄；“按原样”获取的句柄值。它们在 INSERT 和 XREF 操作期间不进行转换 |
| 330-339                       | 软指针句柄；指向同一个 DXF 文件或图形中的其他对象的任意软指针。在 INSERT 和 XREF 操作期间进行转换 |
| 340-349                       | 硬指针句柄；指向同一个 DXF 文件或图形中的其他对象的任意硬指针。在 INSERT 和 XREF 操作期间进行转换 |
| 350-359                       | 软所有者句柄；指向同一个 DXF 文件或图形中的其他对象的任意软所有者指针。在 INSERT 和 XREF 操作期间进行转换 |
| 360-369                       | 硬所有者句柄；指向同一个 DXF 文件或图形中的其他对象的任意硬所有者指针。在 INSERT 和 XREF 操作期间进行转换 |
| 370-379                       | 线宽枚举值 (AcDb::LineWeight)。作为 16 位整数存储和移动。自定义非图元对象可以使用整个范围内的组码，但图元类只能在其表示中使用 371-379 DXF 组码，因为 AutoCAD 和 AutoLISP 都始终假定 370 组码是图元的线宽。这使 370 组码与其他“通用”图元字段具有相同的行为 |
| 380-389                       | PlotStyleName 类型枚举 (AcDb::PlotStyleNameType)。作为 16 位整数存储和移动。自定义非图元对象可以使用整个范围内的组码，但图元类只能在其表示中使用 381-389 DXF 组码，原因与上述线宽范围相同 |
| 390-399                       | 表示 PlotStyleName 对象的句柄值的字符串，本质上是硬指针，但范围不同，更容易处理向后兼容。作为对象 ID（在 DXF 文件中为句柄）和 AutoLISP 中的特殊类型存储和移动。自定义非图元对象可以使用整个范围内的组码，但图元类只能在其表示中使用 391-399 DXF 组码，原因与上述线宽范围相同 |
| 400-409                       | 16 位整数                                                    |
| 410-419                       | 字符串                                                       |
| 420-427                       | 32 位整数值。与真彩色一同使用时，表示 24 位颜色值的 32 位整数。高阶字节（8 位）为 0；低阶字节为包含“蓝色”值 (0-255)、然后是“绿色”值的无符号字符；次高阶字节是“红色”值。将此整数值转换为十六进制值将得到以下位掩码：0x00RRGGBB。例如，红色==200、绿色==100 和蓝色==50 的真彩色为 0x00C86432，而在 DXF 中以十进制表示则为 13132850 |
| 430-437                       | 字符串；用于真彩色时，则为表示颜色名称的字符串               |
| 440-447                       | 32 位整数值。用于真彩色时，表示透明度值                      |
| 450-459                       | 长整数                                                       |
| 460-469                       | 双精度浮点值                                                 |
| 470-479                       | 字符串                                                       |
| 480-481                       | 硬指针句柄；指向同一个 DXF 文件或图形中的其他对象的任意硬指针。在 INSERT 和 XREF 操作期间进行转换 |
| 999                           | DXF：999 组码指示后面的行是注释字符串。SAVEAS 不会在 DXF 输出文件中包含这样的组，但 OPEN 则包括这些组并忽略注释。可以使用 999 组在您编辑的 DXF 文件中包含注释 |
| 1000                          | 扩展数据中的 ASCII 字符串（最多可以包含 255 个字节）         |
| 1001                          | 扩展数据的注册应用程序名（最多可以包含 31 个字节的 ASCII 字符串） |
| 1002                          | 扩展数据控制字符串（“{”或“}”）                               |
| 1003                          | 扩展数据图层名                                               |
| 1004                          | 扩展数据中的字节数据块（最多可以包含 127 个字节）            |
| 1005                          | 扩展数据中的图元句柄；最多可以包含 16 个十六进制数字的字符串 |
| 1010                          | 扩展数据中的点DXF：*X* 值（后跟 1020 和 1030 组）APP：三维点 |
| 1020, 1030                    | DXF：点的 *Y* 值和 *Z* 值                                    |
| 1011                          | 扩展数据中的三维世界空间位置DXF：*X* 值（后跟 1021 和 1031 组）APP：三维点 |
| 1021, 1031                    | DXF：世界空间位置的 *Y* 值和 *Z* 值                          |
| 1012                          | 扩展数据中的三维世界空间位移DXF：*X* 值（后跟 1022 和 1032 组）APP：三维矢量 |
| 1022, 1032                    | DXF：世界空间位移的 *Y* 值和 *Z* 值                          |
| 1013                          | 扩展数据中的三维空间方向DXF：*X* 值（后跟 1022 和 1032 组）APP：三维矢量 |
| 1023, 1033                    | DXF：世界空间方向的 *Y* 和 *Z* 值                            |
| 1040                          | 扩展数据双精度浮点值                                         |
| 1041                          | 扩展数据距离值                                               |
| 1042                          | 扩展数据缩放比例                                             |
| 1070                          | 扩展数据 16 位有符号整数                                     |
| 1071                          | 扩展数据 32 位有符号长整数                                   |





### DXF 文件中的图元组码 (DXF)

以下是 DXF 文件 ENTITIES 段的样例：

| 0SECTION2ENTITIES                                            | **ENTITIES 段的开始**    |
| ------------------------------------------------------------ | ------------------------ |
| 0<br /><图元类型><br />5<br /><句柄><br />330<br /><指向所有者的指针><br />100<br />AcDbEntity<br />8<br /><图层><br />100<br />AcDb<br /><类名><br />.. <数据>. | **每个图元定义一个条目** |
| 0<br />ENDSEC                                                | **ENTITIES 段的结束**    |





### POINT (点图元)

以下组码适用于点图元。

| POINT 组码 |                                                              |
| ---------- | ------------------------------------------------------------ |
| **组码**   | **说明**                                                     |
| 100        | 子类标记 (AcDbPoint)                                         |
| 10         | 点位置（在 WCS 中）DXF：**X** 值；APP：三维点                |
| 20, 30     | DXF：点位置的 **Y** 值和 **Z** 值（在 WCS 中）               |
| 39         | 厚度（可选；默认值 = 0）                                     |
| 210        | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230   | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |
| 50         | 绘制点时所使用的 UCS 的 **X** 轴的角度（可选；默认值 = 0）；当 PDMODE 非零时使用 |



### LINE (直线图元)

以下组码适用于直线图元。

| LINE 组码 |                                                              |
| --------- | ------------------------------------------------------------ |
| **组码**  | **说明**                                                     |
| 100       | 子类标记 (AcDbLine)                                          |
| 39        | 厚度（可选；默认值 = 0）                                     |
| 10        | 起点（在 WCS 中）DXF：**X** 值；APP：三维点                  |
| 20, 30    | DXF：起点的 **Y** 值和 **Z** 值（在 WCS 中）                 |
| 11        | 端点（在 WCS 中）DXF：**X** 值；APP：三维点                  |
| 21, 31    | DXF：端点的 **Y** 值和 **Z** 值（在 WCS 中）                 |
| 210       | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230  | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |



### LWPOLYLINE (多段线图元)

以下组码适用于优化多段线图元。

| WPOLYLINE 组码 |                                                              |
| -------------- | ------------------------------------------------------------ |
| **组码**       | **说明**                                                     |
| 100            | 子类标记 (AcDbPolyline)                                      |
| 90             | 顶点数                                                       |
| 70             | 多段线标志（按位编码）；默认值为 0：1 = 关闭；128 = Plinegen |
| 43             | 固定宽度（可选；默认值 = 0）。如果设定为可变宽度（代码 40 和/或 41），则不使用 |
| 38             | 标高（可选；默认值 = 0）                                     |
| 39             | 厚度（可选；默认值 = 0）                                     |
| 10             | 顶点坐标（在 OCS 中），多个条目；每个顶点一个条目DXF：**X** 值；APP：二维点 |
| 20             | DXF：顶点坐标的 **Y** 值（在 OCS 中），多个条目；每个顶点一个条目 |
| 91             | 顶点标识符                                                   |
| 40             | 起点宽度（多个条目；每个顶点一个条目）（可选；默认值 = 0；多个条目）。如果设定为固定宽度（代码 43），则不使用 |
| 41             | 端点宽度（多个条目；每个顶点一个条目）（可选；默认值 = 0；多个条目）。如果设定为固定宽度（代码 43），则不使用 |
| 42             | 凸度（多个条目；每个顶点一个条目）（可选；默认值 = 0）       |
| 210            | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230       | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |






### POLYLINE (多段线图元)

以下组码适用于多段线图元。

| POLYLINE 组码 |                                                              |
| ------------- | ------------------------------------------------------------ |
| **组码**      | **说明**                                                     |
| 100           | 子类标记（AcDb2dPolyline 或 AcDb3dPolyline）                 |
| 66            | 已废弃；以前是“后跟图元标志”（可选；如果存在则忽略）         |
| 10            | DXF：始终为 0APP：“虚拟”点；**X** 值和 **Y** 值始终为 0，**Z** 值是多段线的标高（二维时在 OCS 中，三维时在 WCS 中） |
| 20            | DXF：始终为 0                                                |
| 30            | DXF：多段线的标高（二维时在 OCS 中，三维时在 WCS 中）        |
| 39            | 厚度（可选；默认值 = 0）                                     |
| 70            | 多段线标志（按位编码；默认值 = 0）：1 = 这是一个闭合多段线（或按 M 方向闭合的多边形网格）2 = 已添加曲线拟合顶点4 = 已添加样条曲线拟合顶点8 = 这是一条三维多段线16 = 这是一个三维多边形网格32 = 多边形网格以 N 方向闭合64 = 多段线是一个多面网格128 = 线型图案在该多段线顶点的周围连续生成 |
| 40            | 默认起点宽度（可选；默认值 = 0）                             |
| 41            | 默认端点宽度（可选；默认值 = 0）                             |
| 71            | 多边形网格 M 顶点计数（可选；默认值 = 0）                    |
| 72            | 多边形网格 N 顶点计数（可选；默认值 = 0）                    |
| 73            | 平滑曲面 M 密度（可选；默认值 = 0）                          |
| 74            | 平滑曲面 N 密度（可选；默认值 = 0）                          |
| 75            | 曲线和平滑曲面类型（可选；默认值 = 0）；整数代码，非按位编码：0 = 不拟合平滑曲面5 = 二次 B 样条曲面6 = 三次 B 样条曲面8 = Bezier 曲面 |
| 210           | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230      | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |

多段线图元后面将跟随应用程序 ID 为“AUTOCAD_POSTSCRIPT_FIGURE”的扩展数据。它包含与 PostScript 图像和 PostScript 填充信息相关的信息。



### CIRCLE (圆图元)

以下组码适用于圆图元。

| CIRCLE 组码 |                                                              |
| ----------- | ------------------------------------------------------------ |
| **组码**    | **说明**                                                     |
| 100         | 子类标记 (AcDbCircle)                                        |
| 39          | 厚度（可选；默认值 = 0）                                     |
| 10          | 中心点（在 OCS 中）DXF：X 值；APP：三维点                    |
| 20, 30      | DXF：中心点的 **Y** 值和 **Z** 值（在 OCS 中）               |
| 40          | 半径                                                         |
| 210         | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230    | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |



### ARC (圆弧图元)

以下组码适用于圆弧图元。

| ARC 组码 |                                                              |
| -------- | ------------------------------------------------------------ |
| **组码** | **说明**                                                     |
| 100      | 子类标记 (AcDbCircle)                                        |
| 39       | 厚度（可选；默认值 = 0）                                     |
| 10       | 中心点（在 OCS 中）DXF：X 值；APP：三维点                    |
| 20, 30   | DXF：中心点的 **Y** 值和 **Z** 值（在 OCS 中）               |
| 40       | 半径                                                         |
| 100      | 子类标记 (AcDbArc)                                           |
| 50       | 起点角度                                                     |
| 51       | 端点角度                                                     |
| 210      | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230 | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |



### ELLIPSE (椭圆图元)

以下组码适用于椭圆图元。

| ELLIPSE 组码 |                                                              |
| ------------ | ------------------------------------------------------------ |
| **组码**     | **说明**                                                     |
| 100          | 子类标记 (AcDbEllipse)                                       |
| 10           | 中心点（在 WCS 中）DXF：**X** 值；APP：三维点                |
| 20, 30       | DXF：中心点的 **Y** 值和 **Z** 值（在 WCS 中）               |
| 11           | 相对于中心的长轴端点（在 WCS 中）DXF：**X** 值；APP：三维点  |
| 21, 31       | DXF：相对于中心的长轴端点的 **Y** 值和 **Z** 值（在 WCS 中） |
| 210          | 拉伸方向（可选；默认值 = 0, 0, 1）DXF：**X** 值；APP：三维矢量 |
| 220, 230     | DXF：拉伸方向的 **Y** 值和 **Z** 值（可选）                  |
| 40           | 短轴与长轴的比例                                             |
| 41           | 起点参数 （对于闭合椭圆，该值为 0.0）                        |
| 42           | 端点参数 （对于闭合椭圆，该值为 2pi）                        |



### **HELIX (螺旋图元)**

以下组码适用于螺旋图元。

| **HELIX 组码** |                                              |
| -------------- | -------------------------------------------- |
| **组码**       | **说明**                                     |
|                | 样条曲线数据                                 |
| 100            | 子类标记 (AcDbHelix)                         |
| 90             | 主版本号                                     |
| 91             | 维护版本号                                   |
| 10, 20, 30     | 轴基点                                       |
| 11, 21, 31     | 起点                                         |
| 12, 22, 32     | 轴矢量                                       |
| 40             | 半径                                         |
| 41             | 圈数                                         |
| 42             | 圈高                                         |
| 290            | 左右手习惯；0 = 左手，1 = 右手               |
| 280            | 约束类型0 = 约束圈高1 = 约束圈数2 = 约束高度 |



### **SPLINE (曲线图元)**

以下组码适用于样条曲线图元。

| **SPLINE 组码** |                                                              |
| --------------- | ------------------------------------------------------------ |
| **组码**        | **说明**                                                     |
| 100             | 子类标记 (AcDbSpline)                                        |
| 210             | 法向矢量（如果样条曲线为非平面型，则省略）DXF：**X** 值；APP：三维矢量 |
| 220, 230        | DXF：法向矢量的 **Y** 值和 **Z** 值（可选）                  |
| 70              | 样条曲线标志（按位编码）：1 = 闭合样条曲线2 = 周期性样条曲线4 = 有理样条曲线8 = 平面16 = 线性（同时还设置平面位） |
| 71              | 样条曲线的阶数                                               |
| 72              | 节点数                                                       |
| 73              | 控制点数                                                     |
| 74              | 拟合点数（如果有）                                           |
| 42              | 节点公差（默认值 = 0.0000001）                               |
| 43              | 控制点公差（默认值 = 0.0000001）                             |
| 44              | 拟合公差（默认值 = 0.0000000001）                            |
| 12              | 起点切向 — 可以省略（在 WCS 中）DXF：**X** 值；APP：三维点   |
| 22, 32          | DXF：起点切向的 **Y** 值和 **Z** 值 — 可以省略（在 WCS 中）  |
| 13              | 端点切向 — 可以省略（在 WCS 中）DXF：**X** 值；APP：三维点   |
| 23, 33          | DXF：端点切向的 **Y** 值和 **Z** 值 — 可以省略（在 WCS 中）  |
| 40              | 节点值（每个节点一个条目）                                   |
| 41              | 权值（如果不为 1）；对于多组对，如果均不为 1，则出现。       |
| 10              | 控制点（在 WCS 中）；每个控制点一个条目DXF：**X** 值；APP：三维点 |
| 20, 30          | DXF：控制点的 **Y** 值和 **Z** 值（在 WCS 中）；每个控制点一个条目 |
| 11              | 拟合点（在 WCS 中）；每个拟合点一个条目DXF：**X** 值；APP：三维点 |
| 21, 31          | DXF：拟合点的 **Y** 值和 **Z** 值（在 WCS 中）；每个拟合点一个条目 |


