package cn.kkdlk.generate.dxf.model.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * 构造线
 */
@Getter
@Setter
public class DxfXLine extends DxfRay {

    @Override
    public String getEntityName() {
        return "XLINE";
    }

    @Override
    public String getEntityClassName() {
        return "AcDbXline";
    }
}
