package cn.kkdlk.generate.dxf.model;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 15:56
 * @Description 图元信息接口
 */
public interface DxfEntity {
    /**
     * 获取图元的dxf字符信息，字符信息应是符合Dxf标准的字符组列表，并且使用CRLF换行符进行换行
     *
     * @return 图元dxf字符信息
     */
    String getDxfStr();

    /**
     * 获取图元的句柄值
     *
     * @return 图元句柄值
     */
    Long getMeta();

    /**
     * 设置图元句柄
     *
     * @param meta 句柄值
     */
    void setMeta(Long meta);

    /**
     * 获取图元名称
     *
     * @return 图元名称
     */
    String getEntityName();

    /**
     * 获取图元对应的子类名称
     *
     * @return 子类名称
     */
    String getEntityClassName();

}
