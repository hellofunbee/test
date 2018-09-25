package com.jingu.IOT.requset;

import com.jingu.IOT.entity.ExpFiledEntity;

/**
 * Created by weifengxu on 2018/9/26.
 */
public class ExpFieldRequest extends ExpFiledEntity {
    private String ckuid;
    private String cksid;
    public String getCkuid() {
        return ckuid;
    }
    public void setCkuid(String ckuid) {
        if (null == ckuid || ckuid.trim().length() == 0) {
            this.ckuid = "";
        }
        this.ckuid = ckuid;
    }
    public String getCksid() {
        return cksid;
    }
    public void setCksid(String cksid) {
        if (null == cksid || cksid.trim().length() == 0) {
            this.cksid = "";
        }
        this.cksid = cksid;
    }
}
