package com.jingu.IOT.dao;

import com.jingu.IOT.entity.ExpFiledEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by weifengxu on 2018/9/26.
 */
@Component
public class ExpFieldDao {
    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> listExpFields(ExpFiledEntity ep) {
        String sql = "SELECT * from exp_field where 1 = 1";

        if (ep.getC_id() > 0) {
            sql += " and c_id = " + ep.getC_id();
        }
        if (ep.getC_id() > 0) {
            sql += " and tu_id = " + ep.getTu_id();
        }
        if (ep.getC_id() > 0) {
            sql += " and exp_field_id = " + ep.getExp_field_id();
        }

        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 根据专家分类去查找用户
     * @param ep
     * @return
     */
    public List<Map<String, Object>> listExpByFields(ExpFiledEntity ep) {
        String sql =
                "SELECT * from exp_field e" +
                        "Left JOIN t_user u on  e.tu_id = u.tu_id " +
                        "where 1 = 1";

        if (ep.getC_id() > 0) {
            sql += " and c_id = " + ep.getC_id();
        }
        if (ep.getC_id() > 0) {
            sql += " and tu_id = " + ep.getTu_id();
        }
        if (ep.getC_id() > 0) {
            sql += " and exp_field_id = " + ep.getExp_field_id();
        }
        sql += " GROUP BY u.tu_id";

        return jdbcTemplate.queryForList(sql);
    }


}
