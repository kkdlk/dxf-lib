package cn.kkdlk.parse.dxf.enums;


import cn.kkdlk.parse.dxf.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * cad版本，默认字符编码，默认的时间 儒略日
 *
 * @author ytzjj
 * @date 2023/11/27
 */
@AllArgsConstructor
@Getter
public enum CadVersionEnum {

    /**
     * 文件版本
     */
    // AutoCAD 2000
    AC1015("AC1015", "GBK", DateUtils.CAD_LESS_THAN_2010_JULIAN_DAY),
    //AutoCAD 2004
    AC1018("AC1018", "GBK", DateUtils.CAD_LESS_THAN_2010_JULIAN_DAY),
    //AutoCAD 2010
    AC1021("AC2021", "UTF-8", DateUtils.CAD_LESS_THAN_2010_JULIAN_DAY),
    //AutoCAD 2013
    AC1024("AC1024", "UTF-8", DateUtils.CAD_GREATER_THAN_2010_JULIAN_DAY),
    //AutoCAD 2018
    AC1027("AC1027", "UTF-8", DateUtils.CAD_GREATER_THAN_2010_JULIAN_DAY),
    //AutoCAD 2023
    AC1032("AC1032", "UTF-8", DateUtils.CAD_GREATER_THAN_2010_JULIAN_DAY),
    ;

    private final String version;
    private final String charset;
    private final Double julianDay;

    /**
     * 根据CAD版本获取枚举对象
     *
     * @param version
     * @return
     */
    public static CadVersionEnum getCadVersion(String version) {
        for (CadVersionEnum value : CadVersionEnum.values()) {
            if (version.equals(value.getVersion())) {
                return value;
            }
        }
        return CadVersionEnum.AC1032;
    }


}
