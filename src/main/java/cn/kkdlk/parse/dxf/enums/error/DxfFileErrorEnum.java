package cn.kkdlk.parse.dxf.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 16:25
 * @Description DXF 异常枚举
 */


@Getter
@AllArgsConstructor
public enum DxfFileErrorEnum {


    DXF_FILE_TYPE_ERROR(1000, "DXF文件路径或IO访问错误"),

    DXF_FILE_READ_ERROR(1001, "DXF文件读取错误");


    private final Integer code;


    private final String message;
}
