/**
 * 项目名称：IOT
 * 类名称：PointDoa
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年8月28日 下午5:43:37
 * 修改人：jianghu
 * 修改时间：2017年8月28日 下午5:43:37
 * 修改备注： 下午5:43:37
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
 * @ClassName: PointDoa
 * @Description: TODO
 * @date 2017年8月28日 下午5:43:37
 */
@Component
public class MenuDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    public int update(PageData pd) {
        List<Object> p = new ArrayList<>();

        p.add(pd.get("id"));
        String sql = "update sys_menu set id = ?";

        if (CommonUtils.has(pd.get("parent_id"))) {
            sql += " , parent_id = ? ";
            p.add(pd.get("parent_id"));
        }
        if (CommonUtils.has(pd.get("name"))) {
            sql += " , name = ? ";
            p.add(pd.get("name"));
        }

        if (CommonUtils.has(pd.get("sort"))) {
            sql += " , sort = ? ";
            p.add(pd.get("sort"));
        }

        if (CommonUtils.has(pd.get("href"))) {
            sql += " , href = ? ";
            p.add(pd.get("href"));
        }
        sql += " where id=?";
        p.add(pd.get("id"));

        return jdbcTemplate.update(sql, p.toArray());
    }

    public int del(PageData pd) {
        String sql = "DELETE from sys_menu where id = ?";
        return jdbcTemplate.update(sql, pd.get("id"));
    }

    public int save(PageData pd) {
        String sql = "insert into sys_menu (" +
                "parent_id," +
                "name," +
                "sort," +
                "href) VALUES (?,?,?,?)";
        List<Object> p = new ArrayList<>();
        p.add(pd.get("parent_id"));
        p.add(pd.get("name"));
        p.add(pd.get("sort"));
        p.add(pd.get("href"));

        return jdbcTemplate.update(sql, p.toArray());
    }

    public List<Map<String, Object>> list(PageData pd) {
        List<Object> p = new ArrayList<>();
        String sql = "select * from sys_menu where 1 = 1 ";

        if (CommonUtils.has(pd.get("name"))) {
            sql += " and name like  '%" + pd.get("name") + "%'";
        }

        if (CommonUtils.has(pd.get("parent_id"))) {
            sql += " and  parent_id = ? ";
            p.add(pd.get("parent_id"));
        }

        if (CommonUtils.has(pd.get("href"))) {
            sql += " and href = ? ";
            p.add(pd.get("href"));
        }

        if (CommonUtils.has(pd.get("tu_id"))) {
            sql += " and e.tu_id = ? ";
            p.add(pd.get("tu_id"));
        }


        return jdbcTemplate.queryForList(sql, p.toArray());
    }

    public List<Map<String, Object>> listByIds(String menu_ids) {
        String sql = "select * from sys_menu where id in (" + menu_ids + ")";

        return jdbcTemplate.queryForList(sql);
    }
}
