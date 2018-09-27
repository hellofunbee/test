/**
 * 项目名称：IOT
 * 类名称：StaticRequest
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年11月29日 下午5:21:07
 * 修改人：jianghu
 * 修改时间：2017年11月29日 下午5:21:07
 * 修改备注： 下午5:21:07
 *
 * @version
 */
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.StaticEntity;

import java.io.Serializable;

/**

 * @ClassName: StaticRequest
 * @Description: TODO
 * @author jianghu
 * @date 2017年11月29日 下午5:21:07

 */
public class StaticRequest extends StaticEntity implements Serializable {

    private String ckuid;
    private String cksid;
    private int type; //类型
    private int ckdata;//检查日期
    private int interval_type = 0;//0:day 1:month:2:year
    private int data_type = 0;//0:均值 1:谷值:2:峰值


    public int getCkdata() {
        return ckdata;
    }

    public void setCkdata(int ckdata) {
        this.ckdata = ckdata;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getInterval_type() {
        return interval_type;
    }

    public void setInterval_type(int interval_type) {
        this.interval_type = interval_type;
    }

    public int getData_type() {
        return data_type;
    }

    public void setData_type(int data_type) {
        this.data_type = data_type;
    }
}
