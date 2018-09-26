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
public class ExpClientDao {
    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int update(PageData pd) {
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_client_id"));
        String sql = "update exp_client set exp_client_id = ?";

        if (CommonUtils.has(pd.get("exp_id"))) {
            sql += " , exp_id = ? ";
            p.add(pd.get("exp_id"));
        }
        if (CommonUtils.has(pd.get("user_id"))) {
            sql += " , user_id = ? ";
            p.add(pd.get("user_id"));
        }

        if (CommonUtils.has(pd.get("if_delete"))) {
            sql += " , if_delete = ? ";
            p.add(pd.get("if_delete"));
        }

        sql += " ,update_time = NOW()";


        return jdbcTemplate.update(sql, p.toArray());
    }

    public int del(PageData pd) {
        String sql = "DELETE from exp_client where exp_client_id = ?";
        return jdbcTemplate.update(sql, pd.get("exp_client_id"));
    }

    public int save(PageData pd) {
        String sql = "insert into exp_client (" +
                "exp_id," +
                "user_id," +
                "if_delete," +
                "update_time," +
                "create_time) VALUES (?,?,?,NOW(),NOW())";
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_id"));
        p.add(pd.get("user_id"));
        p.add(pd.get("if_delete"));

        return jdbcTemplate.update(sql, p.toArray());
    }

    public List<Map<String, Object>> list(PageData pd) {

        String sql = "select * from exp_client where 1 = 1 ";

        List<Object> p = new ArrayList<>();

        if (CommonUtils.has(pd.get("if_delete"))) {
            sql += " and  if_delete = ? ";
            p.add(pd.get("if_delete"));
        }

        if (CommonUtils.has(pd.get("exp_id"))) {
            sql += " and exp_id = ? ";
            p.add(pd.get("exp_id"));
        }

        if (CommonUtils.has(pd.get("user_id"))) {
            sql += " and user_id = ? ";
            p.add(pd.get("user_id"));
        }
        if (CommonUtils.has(pd.get("exp_client_id"))) {
            sql += " and exp_client_id = ? ";
            p.add(pd.get("exp_client_id"));
        }


        return jdbcTemplate.queryForList(sql, p.toArray());
    }
}
