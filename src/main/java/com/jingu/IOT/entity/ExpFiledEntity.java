/**
 * 项目名称：nxy
 * 类名称：A
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年8月8日 上午11:16:36
 * 修改人：jianghu
 * 修改时间：2017年8月8日 上午11:16:36
 * 修改备注： 上午11:16:36
 *
 * @version
 */
package com.jingu.IOT.entity;

import java.io.Serializable;


/**
 * 专家领域表
 */
public class ExpFiledEntity implements Serializable {

    int exp_field_id;//主键
    int c_id;//专家类别id
    int tu_id;//用户id

    public int getExp_field_id() {
        return exp_field_id;
    }

    public void setExp_field_id(int exp_field_id) {
        this.exp_field_id = exp_field_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getTu_id() {
        return tu_id;
    }

    public void setTu_id(int tu_id) {
        this.tu_id = tu_id;
    }
}
