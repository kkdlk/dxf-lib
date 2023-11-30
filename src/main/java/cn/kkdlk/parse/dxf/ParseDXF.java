package cn.kkdlk.parse.dxf;

import cn.kkdlk.parse.dxf.enums.error.DxfFileErrorEnum;
import cn.kkdlk.parse.dxf.exception.DxfFileException;
import cn.kkdlk.parse.dxf.resolver.DxfResolver;
import cn.kkdlk.parse.dxf.resolver.impl.DxfResolverImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 16:21
 * @Description 解析DXF文件
 */
public class ParseDXF {


    /**
     * 上传文件、返回DXF数据采集者
     *
     * @return Dxf解析器
     * @throws IOException io异常
     */
    public static DxfResolver build(InputStream inputStream) throws IOException {
        return new DxfResolverImpl(inputStream);
    }


    /**
     * 上传文件、返回DXF数据采集者
     *
     * @return Dxf解析器
     * @throws IOException io异常
     */
    public static DxfResolver build(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new DxfFileException(DxfFileErrorEnum.DXF_FILE_TYPE_ERROR);
        }
        return new DxfResolverImpl(Files.newInputStream(file.toPath()));
    }


    /**
     * 上传文件、返回DXF数据采集者
     *
     * @return Dxf解析器
     * @throws IOException io异常
     */
    public static DxfResolver build(File file) throws IOException {
        if (!file.exists()) {
            throw new DxfFileException(DxfFileErrorEnum.DXF_FILE_TYPE_ERROR);
        }
        return new DxfResolverImpl(Files.newInputStream(file.toPath()));
    }
}
