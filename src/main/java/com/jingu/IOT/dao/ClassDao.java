package com.jingu.IOT.dao;

import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.ClassRequest;
import com.jingu.IOT.util.CommonUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ClassDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //	@Autowired
//	public ClassDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
    public int addClass(ClassEntity c) {
        return jdbcTemplate.update("insert into class(c_id, c_lev,  c_rid,  c_state,  c_name,c_posi,c_photo,c_type)"
                + "values(?,?,?,?,?,?,?,?)", c.getC_id(), c.getC_lev(), c.getC_rid(), c.getC_state(), c.getC_name(), c.getC_posi(), c.getC_photo(), c.getC_type());
    }

    public List<ClassEntity> queryAllClass(ClassEntity c) {
        List<Object> list = new ArrayList<>();
        String sql = "select * from class where 1=1";
        if (c != null) {
            if (c.getC_id() > 0) {
                sql = sql + " and c_id=?";
                list.add(c.getC_id());
            }
            if (c.getC_lev() > 0) {
                sql += " and c_lev =?";
                list.add(c.getC_lev());
            }
            if (c.getC_rid() > 0) {
                sql = sql + " and c_rid=?";
                list.add(c.getC_rid());
            }
            if (c.getC_state() > 0 && c.getC_state() < 3) {
                sql = sql + " and c_state =?";
                list.add(c.getC_state());
            }
            if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
                sql = sql + " and c_name=?";
                list.add(c.getC_name());
            }
            if (c.getC_posi() > 0) {
                sql = sql + " and c_posi =?";
                list.add(c.getC_posi());
            }
            if (c.getC_photo() != null && c.getC_photo().trim().length() > 0) {
                sql = sql + " and c_photo=?";
                list.add(c.getC_photo());
            }
            if (c.getC_type() > 0) {
                sql = sql + " and c_type=?";
                list.add(c.getC_type());
            }
        }
        return jdbcTemplate.query(sql, new ClassEntity(), list.toArray());
    }

    public int updateClass(ClassEntity c) {
        if (c != null && c.getC_id() > 0) {
            List<Object> list = new ArrayList<>();
            String sql = "update class set c_id = ?";
            list.add(c.getC_id());
            if (c.getC_lev() > 0) {
                sql += " , c_lev =?";
                list.add(c.getC_lev());
            }
            if (c.getC_rid() > 0) {
                sql = sql + " , c_rid=?";
                list.add(c.getC_rid());
            }
            if (c.getC_state() > 0 && c.getC_state() < 3) {
                sql = sql + " , c_state =?";
                list.add(c.getC_state());
            }
            if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
                sql = sql + " , c_name=?";
                list.add(c.getC_name());
            }
            if (c.getC_photo() != null && c.getC_photo().trim().length() > 0) {
                sql = sql + " , c_photo=?";
                list.add(c.getC_photo());
            }
            if (c.getC_type() > 0) {
                sql = sql + " and c_type=?";
                list.add(c.getC_type());
            }
            sql += " where c_id = ?";
            list.add(c.getC_id());
            return jdbcTemplate.update(sql, list.toArray());
        } else {
            return 0;
        }
    }

    public int ckClassByName(ClassEntity c) {
        String sql = "select count(*) from class where c_name =? and c_lev=? and c_rid =? and c_state= 1";
        return jdbcTemplate.queryForObject(sql, Integer.class, c.getC_name(), c.getC_lev(), c.getC_rid());
    }

    public int deleteClass(ClassEntity c) {
        String sql = "delete from class where c_id = " + c.getC_id();
        return jdbcTemplate.update(sql);
    }

    public List<Map<String, Object>> listClass1(ClassEntity c) {
        String sql = "select * from class where c_lev =1 and c_state =1 and c_type = ?";
        if (c.getC_id() > 0) {
            sql += " and c_id =" + c.getC_id();
        }
        return jdbcTemplate.queryForList(sql, c.getC_type());
    }

    public List<Map<String, Object>> listClass2Byrid(ClassEntity c) {
        String sql = "select * from class where c_lev =2  and  c_state =1";
        ArrayList<Object> arrayList = new ArrayList<>();
        if (c.getC_rid() > 0) {
            sql += " and c_rid =?";
            arrayList.add(c.getC_rid());
        }
        if (c.getC_id() > 0) {
            sql += " and c_id = ?";
            arrayList.add(c.getC_id());
        }
        if (c.getC_type() > 0) {
            sql += " and c_type = ?";
            arrayList.add(c.getC_type());
        }
        return jdbcTemplate.queryForList(sql, arrayList.toArray());
    }

    //重置所有类目 设为不显示
    public int resetClassShow() {
        String sql = "update class set c_posi=0";
        return jdbcTemplate.update(sql);
    }


    // 模糊搜索名字
    public List<ClassEntity> queryClass(ClassEntity c) {
        List<Object> list = new ArrayList<>();
        String sql = "select * from class where 1=1";
        if (c != null) {
            if (c.getC_state() > 0 && c.getC_state() < 3) {
                sql = sql + " and c_state =?";
                list.add(c.getC_state());
            }
            if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
                sql = sql + " and c_name like '%" + c.getC_name() + "%' ";
            }
        }
        return jdbcTemplate.query(sql, new ClassEntity(), list.toArray());
    }

    public List<Map<String, Object>> listAllClass(ClassEntity c) {
        List<Object> list = new ArrayList<>();
        String sql = "select * from class where 1=1";
        if (c != null) {
            if (c.getC_id() > 0) {
                sql = sql + " and c_id=?";
                list.add(c.getC_id());
            }
            if (c.getC_lev() > 0) {
                sql += " and c_lev =?";
                list.add(c.getC_lev());
            }
            if (c.getC_rid() > 0) {
                sql = sql + " and c_rid=?";
                list.add(c.getC_rid());
            }
            if (c.getC_state() > 0 && c.getC_state() < 3) {
                sql = sql + " and c_state =?";
                list.add(c.getC_state());
            }
            if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
                sql = sql + " and c_name=?";
                list.add(c.getC_name());
            }
            if (c.getC_posi() > 0) {
                sql = sql + " and c_posi =?";
                list.add(c.getC_posi());
            }
            if (c.getC_photo() != null && c.getC_photo().trim().length() > 0) {
                sql = sql + " and c_photo=?";
                list.add(c.getC_photo());
            }
            if (c.getC_type() > 0) {
                sql = sql + " and c_type=?";
                list.add(c.getC_type());
            }
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }

    public List<Map<String, Object>> listClass(ClassRequest c) {
        List<Object> list = new ArrayList<>();
        String sql = "select *,c_rid pid from class where 1=1";
        if (c != null) {
            if (c.getC_id() > 0) {
                sql = sql + " and c_id=?";
                list.add(c.getC_id());
            }
            if (c.getC_lev() > 0) {
                sql += " and c_lev =?";
                list.add(c.getC_lev());
            }
            if (c.getC_rid() > 0) {
                sql = sql + " and c_rid=?";
                list.add(c.getC_rid());
            }
            if (c.getC_state() > 0 && c.getC_state() < 3) {
                sql = sql + " and c_state =?";
                list.add(c.getC_state());
            }
            if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
                sql = sql + " and c_name=?";
                list.add(c.getC_name());
            }
            if (c.getC_posi() > 0) {
                sql = sql + " and c_posi =?";
                list.add(c.getC_posi());
            }
            if (c.getC_photo() != null && c.getC_photo().trim().length() > 0) {
                sql = sql + " and c_photo=?";
                list.add(c.getC_photo());
            }
            if (c.getC_type() > 0) {
                sql = sql + " and c_type=?";
                list.add(c.getC_type());
            }

        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }


    public List<Map<String, Object>> listInputClass(PointEntity p) {
        if (p == null || !CommonUtils.has(p.getTp_id()))
            return null;
        List<Object> list = new ArrayList<>();
        String sql = "select in_c_id c_id , c.c_lev from input i " +
                " INNER JOIN t_pint p on p.tp_id = i.tp_id " +
                " LEFT JOIN class c on c.c_id = i.in_c_id " +
                " where 1=1 " +
                " and p.tp_id = ?";

        sql += "GROUP  BY in_mattername";
        list.add(p.getTp_id());
        return jdbcTemplate.queryForList(sql, list.toArray());
    }


    public Map<String, Object> findById(int c_id) {
        String sql = "select * from  class where c_id = " + c_id;
        return jdbcTemplate.queryForMap(sql);
    }
}

