package cn.kkdlk.dxf;

import cn.kkdlk.parse.dxf.ParseDXF;
import cn.kkdlk.parse.dxf.model.GeometricModel;
import cn.kkdlk.parse.dxf.resolver.DxfResolver;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 16:44
 * @Description 解析dxf
 */
public class ParseDxf {
    @Test
    public void parseDxfFile() {
        try {
            DxfResolver build = ParseDXF.build("E:\\test\\白马2000-3-108.dxf");
//            DxfResolver builtd = ParseDXF.build(new FileInputStream("E:\\test\\白马2000-3-108.dxf"));
            Map<String, List<GeometricModel>> structureMap = build.getStructureMap();
            System.out.println(structureMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
