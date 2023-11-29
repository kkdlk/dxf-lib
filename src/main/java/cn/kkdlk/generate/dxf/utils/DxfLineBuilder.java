package cn.kkdlk.generate.dxf.utils;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 15:57
 * @Description: DXF文件构造工具
 */

public class DxfLineBuilder {


    private StringBuilder lineBuilder;

    private DxfLineBuilder() {
        this.lineBuilder = new StringBuilder();
    }

    public static DxfLineBuilder build(int code, String name) {
        return new DxfLineBuilder().append(code, name);
    }

    public static DxfLineBuilder build(String name) {
        return new DxfLineBuilder().append(0, name);
    }

    public static DxfLineBuilder build() {
        return new DxfLineBuilder();
    }

    public DxfLineBuilder append(int code, Object value) {
        lineBuilder.append(code).append("\r\n").append(value).append("\r\n");
        return this;
    }

    public DxfLineBuilder append(String line) {
        lineBuilder.append(line);
        return this;
    }

    @Override
    public String toString() {
        return lineBuilder.toString();
    }
}
