package com.jingu.IOT.dao;

import com.jingu.IOT.entity.ExpFiledEntity;
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
        List<Object> param = new ArrayList();
        String sql =
                "SELECT u.*," +
                        " ( " +
                        " select GROUP_CONCAT(c1.c_name) from exp_field e1" +
                        " LEFT JOIN class c1 on c1.c_id = e1.c_id "+
                        " where e1.tu_id = u.tu_id "+
                        " ) fields "+
                        " from exp_field e" +
                        " inner JOIN t_user u on  e.tu_id = u.tu_id " +
                        " left JOIN class c on c.c_id = e.c_id "+
                        " where 1 = 1";

        if (ep.getC_id() > 0) {
            sql += " and c.c_id = ? ";
            param.add(ep.getC_id());
        }
        if (ep.getTu_id() > 0) {
            sql += " and u.tu_id = ?";
            param.add(ep.getTu_id());
        }
        if (ep.getExp_field_id() > 0) {
            sql += " and e.exp_field_id = ? ";
            param.add(ep.getExp_field_id());
        }
        sql += " GROUP BY u.tu_id";

        return jdbcTemplate.queryForList(sql,param.toArray());
    }


}
