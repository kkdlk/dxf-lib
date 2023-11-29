package cn.kkdlk.parse.dxf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author ytzjj
 * @DateTime 2023/11/24 17:23
 * @Description DXF系统变量
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DxfModel implements Serializable {

    private static final long serialVersionUID = 6102011992053173970L;

    /**
     * 当前图层
     */
    private String currentLayer;


    /**
     * 图形的创建日期
     */
    private Date createDate;

    /**
     * 图形的保存（修改）日期
     */
    private Date updateDate;


    /**
     * 图形唯一ID
     */
    private String singleGuid;


    /**
     * 图形唯一版本
     */
    private String versionGuid;


}
