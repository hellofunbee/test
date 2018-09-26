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
 * Created by weifengxu on 2018/9/26.
 */
@Component
public class ExpProDao {
    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int update(PageData pd) {
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_pro_id"));
        String sql = "update exp_pro set exp_pro_id = ?";

        if (CommonUtils.has(pd.get("exp_pro_title"))) {
            sql += " , exp_pro_title = ? ";
            p.add(pd.get("exp_pro_title"));
        }
        if (CommonUtils.has(pd.get("exp_pro_content"))) {
            sql += " , exp_pro_content = ? ";
            p.add(pd.get("exp_pro_content"));
        }

        if (CommonUtils.has(pd.get("tu_id"))) {
            sql += " , tu_id = ? ";
            p.add(pd.get("tu_id"));
        }

        sql += " ,update_time = NOW()";



        return jdbcTemplate.update(sql, p.toArray());
    }

    public int del(PageData pd) {
        String sql = "DELETE from exp_pro where exp_pro_id = ?";
        return jdbcTemplate.update(sql, pd.get("exp_pro_id"));
    }

    public int save(PageData pd) {
        String sql = "insert into exp_pro (" +
                "exp_pro_title," +
                "exp_pro_content," +
                "tu_id," +
                "update_time," +
                "create_time) VALUES (?,?,?,NOW(),NOW())";
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_pro_title"));
        p.add(pd.get("exp_pro_content"));
        p.add(pd.get("tu_id"));

        return jdbcTemplate.update(sql, p.toArray());
    }

    public List<Map<String, Object>> list(PageData pd) {

        String sql = "select * from exp_pro where 1 = 1 ";

        List<Object> p = new ArrayList<>();

        if (CommonUtils.has(pd.get("exp_pro_id"))) {
            sql += " and  exp_pro_id = ? ";
            p.add(pd.get("exp_pro_id"));
        }

        if (CommonUtils.has(pd.get("exp_pro_title"))) {
            sql += " and exp_pro_title = ? ";
            p.add(pd.get("exp_pro_title"));
        }

        if (CommonUtils.has(pd.get("exp_pro_content"))) {
            sql += " and exp_pro_content = ? ";
            p.add(pd.get("exp_pro_content"));
        }
        if (CommonUtils.has(pd.get("tu_id"))) {
            sql += " and tu_id = ? ";
            p.add(pd.get("tu_id"));
        }


        return jdbcTemplate.queryForList(sql, p.toArray());
    }
}
