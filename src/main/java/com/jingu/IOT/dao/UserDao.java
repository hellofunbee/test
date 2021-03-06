/**
 * 项目名称：IOT
 * 类名称：UserDao
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月6日 上午10:01:03
 * 修改人：jianghu
 * 修改时间：2017年9月6日 上午10:01:03
 * 修改备注： 上午10:01:03
 *
 * @version
 */
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.UserEntity;
import com.jingu.IOT.entity.UserEntity2;
import com.jingu.IOT.requset.UserReq;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: UserDao
 * @Description: TODO
 * @date 2017年9月6日 上午10:01:03
 */
@Component
public class UserDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;


    public List<UserEntity> getUserByPwd(UserEntity userEntity) {
        String sql = " select * from t_user where tu_username =? and tu_pwd =? and tu_state =1";
        return jdbcTemplate.query(sql, new UserEntity(), userEntity.getTu_username(), userEntity.getTu_pwd());
        //return 0;
    }

    public Map findById(UserEntity userEntity) {
        String sql = " select t.*," +
                " (" +
                " select GROUP_CONCAT(c1.c_name) from exp_field e1 " +
                " LEFT JOIN class c1 on c1.c_id = e1.c_id " +
                " where e1.tu_id = t.tu_id " +
                " ) fields " +
                " from t_user t where t.tu_id =?";


        return jdbcTemplate.queryForMap(sql, userEntity.getUid());
        //return 0;
    }

    public List<UserEntity> getUserByName(UserEntity userEntity) {
        String sql = " select * from t_user where tu_username =?";
        return jdbcTemplate.query(sql, new UserEntity(), userEntity.getTu_username());
        //return 0;
    }


    public int addUser(UserEntity userEntity) {
        String sql = " insert into t_user (" +
                "tu_username," +
                "tu_pwd," +
                "tu_state," +
                "tu_type," +
                "tu_name," +
                "tu_phone," +

                "tu_email," +
                "tu_sex," +
                "tu_job," +
                "tu_edu," +
                "c_id" +
                ") value (?,?,?,?,?,?,?,?,?,?,?) ";
        return jdbcTemplate.update(sql,
                userEntity.getTu_username(),
                userEntity.getTu_pwd(),
                1,
                userEntity.getTu_type(),
                userEntity.getTu_name(),
                userEntity.getTu_phone(),
                userEntity.getTu_email(),
                userEntity.getTu_sex(),
                userEntity.getTu_job(),
                userEntity.getTu_edu(),
                userEntity.getC_id()
        );
        //return 0;
    }

    public int updateUser(UserEntity userEntity) {
        String sql = "update t_user set tu_id =? ";
        List<Object> list = new ArrayList<>();
        list.add(userEntity.getUid());
        if (userEntity.getTu_pwd() != null && userEntity.getTu_pwd().trim().length() > 0) {
            sql += " , tu_pwd =? ";
            list.add(userEntity.getTu_pwd());
        }
        if (userEntity.getTu_state() > 0) {
            sql += " , tu_state =? ";
            list.add(userEntity.getTu_state());
        }
        if (userEntity.getTu_username() != null && userEntity.getTu_username().trim().length() > 0) {
            sql += " , tu_username =? ";
            list.add(userEntity.getTu_username());
        }
        if (userEntity.getTu_type() > 0) {
            sql += " , tu_type =? ";
            list.add(userEntity.getTu_type());
        }
        if (userEntity.getTu_name() != null && userEntity.getTu_name().trim().length() > 0) {
            sql += " , tu_name =? ";
            list.add(userEntity.getTu_name());
        }
        if (userEntity.getTu_phone() != null && userEntity.getTu_phone().trim().length() > 0) {
            sql += " , tu_phone =? ";
            list.add(userEntity.getTu_phone());
        }


        if (userEntity.getTu_email() != null && userEntity.getTu_email().trim().length() > 0) {
            sql += " , tu_email =? ";
            list.add(userEntity.getTu_email());
        }


        if (userEntity.getTu_sex() != null && userEntity.getTu_sex().trim().length() > 0) {
            sql += " , tu_sex =? ";
            list.add(userEntity.getTu_sex());
        }
        if (userEntity.getTu_job() != null && userEntity.getTu_job().trim().length() > 0) {
            sql += " , tu_job =? ";
            list.add(userEntity.getTu_job());
        }

        if (userEntity.getTu_edu() != null && userEntity.getTu_edu().trim().length() > 0) {
            sql += " , tu_edu =? ";
            list.add(userEntity.getTu_edu());
        }
        if (userEntity.getTu_logo() != null && userEntity.getTu_logo().trim().length() > 0) {
            sql += " , tu_logo =? ";
            list.add(userEntity.getTu_logo());
        }

        if (userEntity.getTu_info() != null && userEntity.getTu_info().trim().length() > 0) {
            sql += " , tu_info =? ";
            list.add(userEntity.getTu_info());
        }
        if (userEntity.getC_id() > 0) {
            sql += " , c_id =? ";
            list.add(userEntity.getC_id());
        }
        if (userEntity.getTu_age() > 0) {
            sql += " , tu_age =? ";
            list.add(userEntity.getTu_age());
        }
        if (list.size() == 1) {
            return 0;
        }
        sql += " where tu_id = ?";
        list.add(userEntity.getUid());
        return jdbcTemplate.update(sql, list.toArray());
        //return 0;
    }

    public List<Map<String, Object>> listUserForMap(UserEntity userEntity) {
        String sql = " select t.*,c.c_type,c.c_name," +
                " (" +
                " select GROUP_CONCAT(c1.c_name) from exp_field e1 " +
                " LEFT JOIN class c1 on c1.c_id = e1.c_id " +
                " where e1.tu_id = t.tu_id " +
                " ) fields ," +
                " ( " +
                "select count(exp_client_id) from exp_client cl " +
                " where cl.exp_id = t.tu_id " +
                " ) clients " +
                " from t_user t " +
                "LEFT join class c on c.c_id = t.c_id " +
                "where 1 =1";
        List<Object> list = new ArrayList<>();
        if (userEntity.getTu_pwd() != null && userEntity.getTu_pwd().trim().length() > 0) {
            sql += " and tu_pwd =? ";
            list.add(userEntity.getTu_pwd());
        }
        if (userEntity.getTu_state() > 0) {
            sql += " and tu_state =? ";
            list.add(userEntity.getTu_state());
        }
        if (userEntity.getTu_username() != null && userEntity.getTu_username().trim().length() > 0) {
            sql += " and tu_username =? ";
            list.add(userEntity.getTu_username());
        }
        if (userEntity.getTu_type() > 0) {
            sql += " and tu_type =? ";
            list.add(userEntity.getTu_type());
        }
        if (userEntity.getUid() > 0) {
            sql += " and tu_id = ?";
            list.add(userEntity.getUid());
        }

        if (userEntity.getU_search() != null && userEntity.getU_search().trim().length() > 0) {
            sql += " and tu_name like '%" + userEntity.getU_search() + "%'";

        }

        if (userEntity.getStart() > 0) {
            sql += " limit " + (userEntity.getStart() - 1) * userEntity.getPagesize() + "," + userEntity.getPagesize();
        }

        //UserEntity query = jdbcTemplate.query(sql, list.toArray(), new UserEntity());
        return jdbcTemplate.queryForList(sql, list.toArray());
        //return 0;
    }

    public List<UserEntity> listUser(UserEntity userEntity) {
        String sql = " select * from t_user where 1 =1";
        List<Object> list = new ArrayList<>();
        if (userEntity.getTu_pwd() != null && userEntity.getTu_pwd().trim().length() > 0) {
            sql += " and tu_pwd =? ";
            list.add(userEntity.getTu_pwd());
        }
        if (userEntity.getTu_state() > 0) {
            sql += " and tu_state =? ";
            list.add(userEntity.getTu_state());
        }
        if (userEntity.getTu_username() != null && userEntity.getTu_username().trim().length() > 0) {
            sql += " and tu_username =? ";
            list.add(userEntity.getTu_username());
        }
        if (userEntity.getTu_type() > 0) {
            sql += " and tu_type =? ";
            list.add(userEntity.getTu_type());
        }
        if (userEntity.getUid() > 0) {
            sql += " and tu_id = ?";
            list.add(userEntity.getUid());
        }

        if (userEntity.getU_search() != null && userEntity.getU_search().trim().length() > 0) {
            sql += " and tu_name like '%" + userEntity.getU_search() + "%'";

        }


        if (userEntity.getStart() > 0) {
            sql += " limit " + (userEntity.getStart() - 1) * userEntity.getPagesize() + "," + userEntity.getPagesize();
        }


        //UserEntity query = jdbcTemplate.query(sql, list.toArray(), new UserEntity());
        return jdbcTemplate.query(sql, list.toArray(), new UserEntity());
        //return 0;
    }

    public int listUserCount(UserEntity userEntity) {
        String sql = " select count(*) from t_user where 1 =1";
        List<Object> list = new ArrayList<>();
        if (userEntity.getTu_pwd() != null && userEntity.getTu_pwd().trim().length() > 0) {
            sql += " and tu_pwd =? ";
            list.add(userEntity.getTu_pwd());
        }
        if (userEntity.getTu_state() > 0) {
            sql += " and tu_state =? ";
            list.add(userEntity.getTu_state());
        }
        if (userEntity.getTu_username() != null && userEntity.getTu_username().trim().length() > 0) {
            sql += " and tu_username =? ";
            list.add(userEntity.getTu_username());
        }
        if (userEntity.getTu_type() > 0) {
            sql += " and tu_type =? ";
            list.add(userEntity.getTu_type());
        }
        if (userEntity.getUid() > 0) {
            sql += " and tu_id = ?";
            list.add(userEntity.getUid());
        }
        if (userEntity.getU_search() != null && userEntity.getU_search().trim().length() > 0) {
            sql += " and tu_name like '%" + userEntity.getU_search() + "%'";

        }
        //UserEntity query = jdbcTemplate.query(sql, list.toArray(), new UserEntity());
        return jdbcTemplate.queryForObject(sql, Integer.class, list.toArray());
        //return 0;
    }


    public int deleteUserByUid(UserEntity userEntity) {
        String sql = " delete from t_user where tu_id =?";
        return jdbcTemplate.update(sql, userEntity.getUid());
        //return 0;
    }

    public int ckAdmin(long uid) {
        String sql = " select count(*) from t_user where tu_id =? and ( tu_type =1 or tu_type =2)";
        return jdbcTemplate.queryForObject(sql, Integer.class, uid);
        //return 0;
    }

    public int ckSuperAdmin(long uid) {
        String sql = " select count(*) from t_user where tu_id =? and  tu_type =1 ";
        return jdbcTemplate.queryForObject(sql, Integer.class, uid);
        //return 0;
    }


    public List<Map<String, Object>> listUser2(UserEntity2 use) {
        String sql = "select sh.* from shopuser sh  where 1= 1 ";
        if (use.getU_search() != null && use.getU_search().trim().length() > 0) {
            sql += " and (sh.u_uname like '%" + use.getU_search() + "%' or sh.u_phone like '%" + use.getU_search() + "%')";
            return jdbcTemplate2.queryForList(sql);
        }
        List<Object> list = new ArrayList<>();
        if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
            sql += " and sh.class1name = ?";
            list.add(use.getClass1name());
        }
        if (use.getClass2name() != null && use.getClass2name().trim().length() > 0) {
            sql += " and sh.class2name = ?";
            list.add(use.getClass2name());
        }
        if (use.getDetail() != null && use.getDetail().trim().length() > 0) {
            sql += " and sh.detail = ?";
            list.add(use.getDetail());
        }
        if (use.getField() != null && use.getField().trim().length() > 0) {
            sql += " and sh.field = ?";
            list.add(use.getField());
        }
        if (use.getSex() != null && use.getSex().trim().length() > 0) {
            sql += " and sh.sex = ?";
            list.add(use.getSex());
        }
        if (use.getU_name() != null && use.getU_name().trim().length() > 0) {
            sql += " and sh.u_name = ?";
            list.add(use.getU_name());
        }
        if (use.getPostion() != null && use.getPostion().trim().length() > 0) {
            sql += " and sh.postion = ?";
            list.add(use.getPostion());
        }
        if (use.getU_phone() != null && use.getU_phone().trim().length() > 0) {
            sql += " and sh.u_phone = ?";
            list.add(use.getU_phone());
        }
        if (use.getU_phone2() != null && use.getU_phone2().trim().length() > 0) {
            sql += " and sh.u_phone2 = ?";
            list.add(use.getU_phone2());
        }
        if (use.getClass1id() > 0) {
            sql += " and sh.class1id = ?";
            list.add(use.getClass1id());
        }
        if (use.getClass2id() > 0) {
            sql += " and sh.class2id = ?";
            list.add(use.getClass2id());
        }
        if (use.getU_id() > 0) {
            sql += " and sh.u_id =?";
            list.add(use.getU_id());
        }
        if (use.getU_type() != null && use.getU_type().trim().length() > 0) {
            sql += " and sh.u_type =?";
            list.add(use.getU_type());
        }
        if (use.getU_check() > 0) {
            sql += " and sh.u_check = ?";
            list.add(use.getU_check());
        }
        if (use.getU_state() > 0) {
            sql += " and sh.u_state = ?";
            list.add(use.getU_state());
        }
//		if(use.getSerproject() !=null){
//			sql += " and serproject = ?";
//			list.add(use.getSerproject());
//		}
//		if(use.getSeredproject() !=null){
//			sql += " and seredproject = ?";
//			list.add(use.getSeredproject());
//		}
//		if(use.getSeruser() !=null){
//			sql += " and seruser = ?";
//			list.add(use.getSeruser());
//		}
//		if(use.getU_id() >0){
//			sql += " and u_id = ?";
//			list.add(use.getU_id());
//		}
        return jdbcTemplate2.queryForList(sql, list.toArray());
    }

    public int listUserCount2(UserReq use) {
        String sql = "select count(*) from shopuser  where 1= 1";
        List<Object> list = new ArrayList<>();
        if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
            sql += " and class1name = ?";
            list.add(use.getClass1name());
        }
        if (use.getClass2name() != null && use.getClass2name().trim().length() > 0) {
            sql += " and classname = ?";
            list.add(use.getClass2name());
        }
        if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
            sql += " and class2name = ?";
            list.add(use.getClass2name());
        }
        if (use.getDetail() != null && use.getDetail().trim().length() > 0) {
            sql += " and detail = ?";
            list.add(use.getDetail());
        }
        if (use.getField() != null && use.getField().trim().length() > 0) {
            sql += " and field = ?";
            list.add(use.getField());
        }
        if (use.getSex() != null && use.getSex().trim().length() > 0) {
            sql += " and sex = ?";
            list.add(use.getSex());
        }
        if (use.getU_name() != null && use.getU_name().trim().length() > 0) {
            sql += " and u_name = ?";
            list.add(use.getU_name());
        }
        if (use.getPostion() != null && use.getPostion().trim().length() > 0) {
            sql += " and postion = ?";
            list.add(use.getPostion());
        }
        if (use.getU_phone() != null && use.getU_phone().trim().length() > 0) {
            sql += " and u_phone = ?";
            list.add(use.getU_phone());
        }
        if (use.getU_phone2() != null && use.getU_phone2().trim().length() > 0) {
            sql += " and u_phone2 = ?";
            list.add(use.getU_phone2());
        }
        if (use.getClass1id() > 0) {
            sql += " and class1id = ?";
            list.add(use.getClass1id());
        }
        if (use.getClass2id() > 0) {
            sql += " and class2id = ?";
            list.add(use.getClass2id());
        }
        if (use.getU_id() > 0) {
            sql += " and u_id =?";
            list.add(use.getU_id());
        }
        if (use.getU_type() != null && use.getU_type().trim().length() > 0) {
            sql += " and u_type =?";
            list.add(use.getU_type());
        }
        if (use.getU_check() > 0) {
            sql += " and u_check = ?";
            list.add(use.getU_check());
        }
        if (use.getU_state() > 0) {
            sql += " and u_state = ?";
            list.add(use.getU_state());
        }
        if (use.getSerproject() != null) {
            sql += " and serproject = ?";
            list.add(use.getSerproject());
        }
        if (use.getSeredproject() != null) {
            sql += " and seredproject = ?";
            list.add(use.getSeredproject());
        }
        if (use.getSeruser() != null) {
            sql += " and seruser = ?";
            list.add(use.getSeruser());
        }
        if (use.getU_id() > 0) {
            sql += " and u_id = ?";
            list.add(use.getU_id());
        }
        return jdbcTemplate2.queryForObject(sql, Integer.class, list.toArray());
    }

    /**
     * 2017年12月18日
     * jianghu
     *
     * @param u_uname
     * @param password TODO
     */
    public int ckAuthor(String u_uname, String password) {
        // TODO Auto-generated method stub
        String sql = "select count(*) from author where username = ? and password = ? and type = 2";
        return jdbcTemplate.queryForObject(sql, Integer.class, u_uname, password);

    }

    /**
     * 2017年12月18日
     * jianghu
     *
     * @param uid
     * @return TODO
     */
    public int getMsgCount(long uid) {
        // TODO Auto-generated method stub
        String sql = " select tu_read from t_user where tu_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, uid);
    }


    public List<Map<String, Object>> listUserInType(List<Integer> userType) {
        StringBuffer sb = new StringBuffer();
        for (int i : userType) {
            sb.append(i);
            sb.append(",");
        }
        String types = sb.substring(0, sb.length());
        String sql = "select * from t_user where tu_type in (?)";
        return jdbcTemplate.queryForList(sql, types);

    }
}
