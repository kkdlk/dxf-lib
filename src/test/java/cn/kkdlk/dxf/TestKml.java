package cn.kkdlk.dxf;

import cn.kkdlk.generate.kml.GenerateKml;
import cn.kkdlk.parse.dxf.ParseDXF;
import cn.kkdlk.parse.dxf.resolver.DxfResolver;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import org.junit.Test;

import java.io.File;

/**
 * @Author ytzjj
 * @DateTime 2023/11/28 10:06
 * @Description
 */
public class TestKml {

    @Test
    public void testGenerateKml() throws Exception {
        String dxfPath = "E://test//白马2000-3-108.dxf";
        String kmlPath = "E://test//2000-3-108.kml";

        File kmlFile = new File(kmlPath);
        DxfResolver dxfResolver = ParseDXF.build(dxfPath);
        Kml kml = GenerateKml.gemerateKmlFile(kmlFile, dxfResolver);
        kml.marshal(kmlFile);
    }
}
