package cn.kkdlk.parse.dxf.analysis;

import cn.kkdlk.parse.dxf.enums.CadVersionEnum;
import cn.kkdlk.parse.dxf.enums.DxfSystemEnum;
import cn.kkdlk.parse.dxf.model.headers.HeaderModel;
import cn.kkdlk.parse.dxf.utils.DateUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ytzjj
 * @DateTime 2023/11/27 15:22
 * @Description dxf 的头信息解析
 */
public class DxfHeaderParse {


    /**
     * 从dxf文件中解析需要的头信息，并封装模型
     *
     * @param dxfAllLine
     * @return
     */
    public static HeaderModel getHeaderParse(List<String> dxfAllLine) {
        String str;
        CadVersionEnum cadVersion = CadVersionEnum.AC1032;
        HeaderModel headerModel = new HeaderModel();
        for (int i = 0; i < dxfAllLine.size(); i++) {
            str = dxfAllLine.get(i);
            if (DxfSystemEnum.ACADVER.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                cadVersion = CadVersionEnum.getCadVersion(str);
                headerModel.setCadVersion(cadVersion.getVersion());
            } else if (DxfSystemEnum.ACADMAINTVER.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setCadMaintenanceRelease(str);
            } else if (DxfSystemEnum.DWGCODEPAGE.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setCadANSICoding(str);
            } else if (DxfSystemEnum.INBASE.getCode().equals(str)) {
                while (true) {
                    ++i;
                    String line = dxfAllLine.get(i);
                    if ("10".equals(line)) {
                        headerModel.setInBaseX(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("20".equals(line)) {
                        headerModel.setInBaseY(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("30".equals(line)) {
                        headerModel.setInBaseZ(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("9".equals(line)) {
                        break;
                    }
                }
            } else if (DxfSystemEnum.EXTMIN.getCode().equals(str)) {
                while (true) {
                    ++i;
                    String line = dxfAllLine.get(i);
                    if ("10".equals(line)) {
                        headerModel.setExtMinX(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("20".equals(line)) {
                        headerModel.setExtMinY(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("30".equals(line)) {
                        headerModel.setExtMinZ(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("9".equals(line)) {
                        break;
                    }
                }
            } else if (DxfSystemEnum.EXTMAX.getCode().equals(str)) {
                while (true) {
                    ++i;
                    String line = dxfAllLine.get(i);
                    if ("10".equals(line)) {
                        headerModel.setExtMaxX(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("20".equals(line)) {
                        headerModel.setExtMaxY(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("30".equals(line)) {
                        headerModel.setExtMaxZ(new BigDecimal(dxfAllLine.get(++i)));
                    } else if ("9".equals(line)) {
                        break;
                    }
                }
            } else if (DxfSystemEnum.TEXTSIZE.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setTextSize(Double.parseDouble(str));
            } else if (DxfSystemEnum.CLAYER.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setCurrentLayer(str);
            } else if (DxfSystemEnum.TDCREATE.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setCreateDate(DateUtils.dxfTime2Date(str, cadVersion.getJulianDay()));
            } else if (DxfSystemEnum.TDUPDATE.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setUpdateDate(DateUtils.dxfTime2Date(str, cadVersion.getJulianDay()));
            } else if (DxfSystemEnum.FINGERPRINTGUID.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setSingleGuid(str);
            } else if (DxfSystemEnum.VERSIONGUID.getCode().equals(str)) {
                ++i;
                str = dxfAllLine.get(++i);
                headerModel.setVersionGuid(str);
            }
        }
        return headerModel;
    }
}
