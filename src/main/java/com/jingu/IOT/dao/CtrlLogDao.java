/**
 * 项目名称：IOT
 * 类名称：AlarmDao
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2018年4月13日 下午1:53:32
 * 修改人：jianghu
 * 修改时间：2018年4月13日 下午1:53:32
 * 修改备注： 下午1:53:32
 *
 * @version
 */
package com.jingu.IOT.dao;

import com.jingu.IOT.util.CommonUtils;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: AlarmDao
 * @Description: TODO
 * @date 2018年4月13日 下午1:53:32
 */
@Component
public class CtrlLogDao {
    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int update(PageData pd) {
        List<Object> p = new ArrayList<>();
        p.add(pd.get("ctrl_log_id"));
        String sql = "update ctrl_log set ctrl_log_id = ?";

        if (CommonUtils.has(pd.get("ctrl_id"))) {
            sql += " , ctrl_id = ? ";
            p.add(pd.get("ctrl_id"));
        }
        if (CommonUtils.has(pd.get("ctrl_type"))) {
            sql += " , ctrl_type = ? ";
            p.add(pd.get("ctrl_type"));
        }

        if (pd.get("ctrl_act") != null) {
            sql += " , ctrl_act = ? ";
            p.add(pd.get("ctrl_act"));
        }
        sql += " where ctrl_log_id = ?";
        p.add(pd.get("ctrl_log_id"));


        return jdbcTemplate.update(sql, p.toArray());
    }

    public int del(PageData pd) {
        String sql = "DELETE from ctrl_log where ctrl_log_id = ?";
        return jdbcTemplate.update(sql, pd.get("ctrl_log_id"));
    }

    public int save(PageData pd) {
        String sql = "insert into ctrl_log (" +
                "ctrl_id," +
                "ctrl_type," +
                "ctrl_act," +
                "ctrl_from," +
                "ctrl_time) VALUES (?,?,?,?,NOW())";
        List<Object> p = new ArrayList<>();
        p.add(pd.get("ctrl_id"));
        p.add(pd.get("ctrl_type"));
        p.add(pd.get("ctrl_act"));
        p.add(pd.get("ctrl_from"));

        return jdbcTemplate.update(sql, p.toArray());
    }

    public List<Map<String, Object>> list(PageData pd) {

        String sql = "select e.* from ctrl_log e " +
                "where 1 = 1 ";

        List<Object> p = new ArrayList<>();

        if (CommonUtils.has(pd.get("ctrl_id"))) {
            sql += " and  ctrl_id = ? ";
            p.add(pd.get("ctrl_id"));
        }

        if (CommonUtils.has(pd.get("ctrl_type"))) {
            sql += " and ctrl_type = ? ";
            p.add(pd.get("ctrl_type"));
        }

        if (pd.get("ctrl_act") != null) {
            sql += " and e.ctrl_act = ? ";
            p.add(pd.get("ctrl_act"));
        }
        if (CommonUtils.has(pd.get("ctrl_from"))) {
            sql += " and ctrl_from = ? ";
            p.add(pd.get("ctrl_from"));
        }


        return jdbcTemplate.queryForList(sql, p.toArray());
    }

    public List<Map<String, Object>> findById(PageData params) {
        String sql = "select * from ctrl_log where ctrl_log_id = ?";
        return jdbcTemplate.queryForList(sql, params.get("ctrl_log_id"));

    }
}
