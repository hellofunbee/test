/**
 * 项目名称：IOT
 * 类名称：RoleDoa
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月13日 下午12:02:32
 * 修改人：jianghu
 * 修改时间：2017年10月13日 下午12:02:32
 * 修改备注： 下午12:02:32
 *
 * @version
 */
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.RoleEntity;
import com.jingu.IOT.util.CommonUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: RoleDoa
 * @Description: TODO
 * @date 2017年10月13日 下午12:02:32
 */
@Component
public class RoleDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> listRole(RoleEntity re) {
        String sql = "select * from role ";
        return jdbcTemplate.queryForList(sql);
    }

    public int updateRole(RoleEntity role) {
        List<Object> o = new ArrayList();
        String sql = "update role set r_id = ? ";
        o.add(role.getId());

        if (CommonUtils.has(role.getMenu_ids())) {
            sql += ", menu_ids = ? ";
            o.add(role.getMenu_ids());
        }

        if (CommonUtils.has(role.getR_cname())) {
            sql += ", r_cname = ? ";
            o.add(role.getR_cname());
        }

        if (CommonUtils.has(role.getR_name())) {
            sql += ", r_name = ? ";
            o.add(role.getR_name());
        }

        sql += " where r_id = ? ";
        o.add(role.getId());

       return jdbcTemplate.update(sql, o.toArray());
    }

}
