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
public class ExpAnsDao {
    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    public int update(PageData pd) {
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_ans_id"));
        String sql = "update exp_ans set exp_ans_id = ?";

        if (CommonUtils.has(pd.get("exp_ans_title"))) {
            sql += " , exp_ans_title = ? ";
            p.add(pd.get("exp_ans_title"));
        }
        if (CommonUtils.has(pd.get("exp_ans_content"))) {
            sql += " , exp_ans_content = ? ";
            p.add(pd.get("exp_ans_content"));
        }

        if (CommonUtils.has(pd.get("if_delete"))) {
            sql += " , if_delete = ? ";
            p.add(pd.get("if_delete"));
        }

        if (CommonUtils.has(pd.get("user_question_id"))) {
            sql += " , user_question_id = ? ";
            p.add(pd.get("user_question_id"));
        }

        if (CommonUtils.has(pd.get("tu_id"))) {
            sql += " , tu_id = ? ";
            p.add(pd.get("tu_id"));
        }

        sql += " ,update_time = NOW()";


        return jdbcTemplate.update(sql, p.toArray());
    }

    public int del(PageData pd) {
        String sql = "DELETE from exp_ans where exp_ans_id = ?";
        return jdbcTemplate.update(sql, pd.get("exp_ans_id"));
    }

    public int save(PageData pd) {
        String sql = "insert into exp_ans (" +
                "exp_ans_title," +
                "exp_ans_content," +
                "if_delete," +
                "user_question_id," +
                "tu_id," +
                "update_time," +
                "create_time) VALUES (?,?,?,?,?,NOW(),NOW())";
        List<Object> p = new ArrayList<>();
        p.add(pd.get("exp_ans_title"));
        p.add(pd.get("exp_ans_content"));
        p.add(pd.get("if_delete"));
        p.add(pd.get("user_question_id"));
        p.add(pd.get("tu_id"));

        return jdbcTemplate.update(sql,p.toArray());
    }

    public List<Map<String, Object>> list(PageData pd) {

        String sql = "select * from exp_ans where 1 = 1";

        List<Object> p = new ArrayList<>();
        if (CommonUtils.has(pd.get("exp_ans_title"))) {
            sql += " and exp_ans_title like  '%" + pd.get("exp_ans_title") + "%'";
        }

        if (CommonUtils.has(pd.get("if_delete"))) {
            sql += " and  if_delete = ? ";
            p.add(pd.get("if_delete"));
        }

        if (CommonUtils.has(pd.get("user_question_id"))) {
            sql += " and user_question_id = ? ";
            p.add(pd.get("user_question_id"));
        }

        if (CommonUtils.has(pd.get("tu_id"))) {
            sql += " and tu_id = ? ";
            p.add(pd.get("tu_id"));
        }


        return jdbcTemplate.queryForList(sql, p.toArray());
    }
}
