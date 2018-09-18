/**
 * 项目名称：sxcms
 * 类名称：HomePageEntity
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月19日 下午5:07:55
 * 修改人：jianghu
 * 修改时间：2017年10月19日 下午5:07:55
 * 修改备注： 下午5:07:55
 *
 * @version
 */
package com.jingu.IOT.entity;

import java.util.Date;

/**

 * @ClassName: HomePageEntity
 * @Description: TODO
 * @author jianghu
 * @date 2017年10月19日 下午5:07:55

 */
public class HomePageEntity {

    private int h_id,
            h_state,
            h_type,
            h_class1id,
            h_class2id;
    private String h_name,
            h_cover,
            h_url,
            h_class1name,
            h_class2name;

    private Date create_time;
    private Date update_time;


    public int getH_class1id() {
        return h_class1id;
    }

    public void setH_class1id(int h_class1id) {
        this.h_class1id = h_class1id;
    }

    public int getH_class2id() {
        return h_class2id;
    }

    public void setH_class2id(int h_class2id) {
        this.h_class2id = h_class2id;
    }

    public String getH_class1name() {
        return h_class1name;
    }

    public void setH_class1name(String h_class1name) {
        if (null == h_class1name || h_class1name.trim().length() == 0) {
            this.h_class1name = "";
        }
        this.h_class1name = h_class1name;
    }

    public String getH_class2name() {
        return h_class2name;
    }

    public void setH_class2name(String h_class2name) {
        if (null == h_class2name || h_class2name.trim().length() == 0) {
            this.h_class2name = "";
        }
        this.h_class2name = h_class2name;
    }

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public int getH_state() {
        return h_state;
    }

    public void setH_state(int h_state) {
        this.h_state = h_state;
    }

    public int getH_type() {
        return h_type;
    }

    public void setH_type(int h_type) {
        this.h_type = h_type;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        if (null == h_name || h_name.trim().length() == 0) {
            this.h_name = "";
        }
        this.h_name = h_name;
    }

    public String getH_cover() {
        return h_cover;
    }

    public void setH_cover(String h_cover) {
        if (null == h_cover || h_cover.trim().length() == 0) {
            this.h_cover = "";
        }
        this.h_cover = h_cover;
    }

    public String getH_url() {
        return h_url;
    }

    public void setH_url(String h_url) {
        if (null == h_url || h_url.trim().length() == 0) {
            this.h_url = "";
        }
        this.h_url = h_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
