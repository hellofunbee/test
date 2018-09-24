/**
 * 项目名称：IOT
 * 类名称：ProduceDao
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月10日 上午10:43:06
 * 修改人：jianghu
 * 修改时间：2017年10月10日 上午10:43:06
 * 修改备注： 上午10:43:06
 *
 * @version
 */
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.MessageEntity;
import com.jingu.IOT.util.Types;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: ProduceDao
 * @Description: TODO
 * @date 2017年10月10日 上午10:43:06
 */
@Component
public class MessageDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public MessageDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

    public int addMessage(MessageEntity me) {
        String sql = " insert into message (m_type,m_title,m_content,m_province,m_city,m_district,m_class,m_time,m_authorname,m_cover,m_class2) value (?,?,?,?,?,?,?,UNIX_TIMESTAMP(),?,?,?)";
        return jdbcTemplate.update(sql, me.getM_type(), me.getM_title(), me.getM_content(), me.getM_province(), me.getM_city(), me.getM_district(), me.getM_class(), me.getM_authorname(), me.getM_cover(), me.getM_class2());
    }

    public int updateMessage(MessageEntity me) {
        String sql = "update message set m_id= ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(me.getM_id());
        if (me.getM_type() > 0) {
            sql += " , m_type =?";
            list.add(me.getM_type());
        }
        if (me.getM_class() > 0) {
            sql += " , m_class =?";
            list.add(me.getM_class());
        }
        if (me.getM_title() != null && me.getM_title().trim().length() > 0) {
            sql += " , m_title =?";
            list.add(me.getM_title());
        }
        if (me.getM_content() != null && me.getM_content().trim().length() > 0) {
            sql += " , m_content =?";
            list.add(me.getM_content());
        }
        if (me.getM_province() != null && me.getM_province().trim().length() > 0) {
            sql += " , m_province =?";
            list.add(me.getM_province());
        }
        if (me.getM_city() != null && me.getM_city().trim().length() > 0) {
            sql += " , m_city =?";
            list.add(me.getM_city());
        }
        if (me.getM_district() != null && me.getM_district().trim().length() > 0) {
            sql += " , m_district =?";
            list.add(me.getM_district());
        }
        if (me.getM_time() != null && me.getM_time().trim().length() > 0) {
            sql += " , m_time = UNIX_TIMESTAMP()";
        }
        if (me.getM_authorname() != null && me.getM_authorname().trim().length() > 0) {
            sql += " , m_title =?";
            list.add(me.getM_authorname());
        }
        if (me.getM_cover() != null && me.getM_cover().trim().length() > 0) {
            sql += " , m_title =?";
            list.add(me.getM_cover());
        }
        if (me.getM_class2() > 0) {
            sql += " , m_class2 =?";
            list.add(me.getM_class2());
        }
        if (list.size() == 1) {
            return 0;

        }
        sql += " where m_id =?";
        list.add(me.getM_id());
        return jdbcTemplate.update(sql, list.toArray());
    }

    public List<Map<String, Object>> listMessage(MessageEntity me) {
        String sql =
                "select m.*,sa.a_name province, s.a_name city,sar.a_name district " +
                        "from message m " +
                        "left join s_area sa on sa.ar_id=m.m_province " +
                        "LEFT JOIN s_area s on s.ar_id =m.m_city " +
                        "LEFT JOIN s_area sar on sar.ar_id =m.m_district " +
                        "where 1=1 ";
        ArrayList<Object> list = new ArrayList<>();
        if (me.getM_id() > 0) {
            sql += " and m_id =?";
            list.add(me.getM_id());
        }
        if (me.getM_class2() > 0) {
            sql += " and m_class2 =?";
            list.add(me.getM_class2());
        }
        if (me.getM_type() > 0) {
            sql += " and m_type =?";
            list.add(me.getM_type());
        }
        if (me.getM_class() > 0) {
            sql += " and m_class =?";
            list.add(me.getM_class());
        }
        if (me.getM_title() != null && me.getM_title().trim().length() > 0) {
            sql += " and m_title like '%" + me.getM_title() + "%'";
        }
        if (me.getM_content() != null && me.getM_content().trim().length() > 0) {
            sql += " and m_content =?";
            list.add(me.getM_content());
        }
        if (me.getM_province() != null && me.getM_province().trim().length() > 0) {
            sql += " and m_province =?";
            list.add(me.getM_province());
        }
        if (me.getM_city() != null && me.getM_city().trim().length() > 0) {
            sql += " and m_city =?";
            list.add(me.getM_city());
        }
        if (me.getM_district() != null && me.getM_district().trim().length() > 0) {
            sql += " and m_district =?";
            list.add(me.getM_district());
        }
        if (me.getM_time() != null && me.getM_time().trim().length() > 0) {
            if (me.getM_type() == 3) {
                sql += " and m_time < UNIX_TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 7 DAY))  and m_time < UNIX_TIMESTAMP()";
            } else {
                sql += " and m_time =?";
                list.add(me.getM_time());
            }
        }
        if (me.getM_authorname() != null && me.getM_authorname().trim().length() > 0) {
            sql += " and m_authorname =?";
            list.add(me.getM_authorname());
        }
        if (me.getM_cover() != null && me.getM_cover().trim().length() > 0) {
            sql += " and m_cover =?";
            list.add(me.getM_cover());
        }
        sql += " order by m_time desc";
        if (me.getM_type() == 1 && me.getStart() > 0) {
            sql += " limit " + (me.getStart() - 1) * me.getPageSize() + "," + me.getPageSize();
        }
//		if(me.getM_type()==2){
//			sql +=" limit 0,1";
//		}
        return jdbcTemplate.queryForList(sql, list.toArray());
    }

    public int deleteMessage(MessageEntity me) {
        String sql = "delete from message where m_id =?";
        return jdbcTemplate.update(sql, me.getM_id());
    }

    /**
     * 即时消息
     *
     * @return
     */
    public List<Map<String, Object>> getInstanceMessage() {

        String sql = "select * from message where m_type = " + Types.MT_JISHI + " order by m_time desc limit 0,3";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 2017年12月6日
     * jianghu
     *
     * @param c_id
     * @return TODO
     */
    public int ckClass(int c_id) {
        // TODO Auto-generated method stub
        String sql = " select count(*) from message where m_state =1 and m_class =?";
        return jdbcTemplate.queryForObject(sql, Integer.class, c_id);
    }

    /**
     * 2018年4月2日
     * jianghu
     *
     * @param me
     * @param string
     * @return TODO
     */
    public List<Map<String, Object>> listMessage4(MessageEntity me, String string) {
        String sql =
                "select m.m_title,m.m_time,m.m_province,m.m_city,m.m_district,sa.a_name province, s.a_name city,sar.a_name district " +
                        "from message m " +
                        "left join s_area sa on sa.ar_id=m.m_province " +
                        "LEFT JOIN s_area s on s.ar_id =m.m_city " +
                        "LEFT JOIN s_area sar on sar.ar_id =m.m_district where 1=1 ";

        ArrayList<Object> list = new ArrayList<>();
        if (me.getM_id() > 0) {
            sql += " and m_id =?";
            list.add(me.getM_id());
        }
        if (me.getM_class2() > 0) {
            sql += " and m_class2 =?";
            list.add(me.getM_class2());
        }
        if (me.getM_type() > 0) {
            sql += " and m_type =?";
            list.add(me.getM_type());
        }
        if (me.getM_class() > 0) {
            sql += " and m_class =?";
            list.add(me.getM_class());
        }
        if (me.getM_title() != null && me.getM_title().trim().length() > 0) {
            sql += " and m_title like '%" + me.getM_title() + "%'";
        }
        if (me.getM_content() != null && me.getM_content().trim().length() > 0) {
            sql += " and m_content =?";
            list.add(me.getM_content());
        }
        if (me.getM_province() != null && me.getM_province().trim().length() > 0) {
            sql += " and m_province =?";
            list.add(me.getM_province());
        }
        if (me.getM_city() != null && me.getM_city().trim().length() > 0) {
            sql += " and m_city =?";
            list.add(me.getM_city());
        }
        if (me.getM_district() != null && me.getM_district().trim().length() > 0) {
            sql += " and m_district =?";
            list.add(me.getM_district());
        }
        if (me.getM_time() != null && me.getM_time().trim().length() > 0) {
            if (me.getM_type() == 3) {
                sql += " and m_time < UNIX_TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 7 DAY))  and m_time < UNIX_TIMESTAMP()";
            } else {
                sql += " and m_time =?";
                list.add(me.getM_time());
            }
        }
        if (me.getM_authorname() != null && me.getM_authorname().trim().length() > 0) {
            sql += " and m_authorname =?";
            list.add(me.getM_authorname());
        }
        if (me.getM_cover() != null && me.getM_cover().trim().length() > 0) {
            sql += " and m_cover =?";
            list.add(me.getM_cover());
        }
        sql += " group by " + string + " order by m_time desc  ";
        if (me.getM_type() == 1 && me.getStart() > 0) {
            sql += " limit " + (me.getStart() - 1) * me.getPageSize() + "," + me.getPageSize();
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }


    public List<Map<String, Object>> listMessage5(MessageEntity me, String string) {
        String sql = "select m.m_id,m_content from message m left join s_area sa on sa.ar_id=m.m_province LEFT JOIN s_area s on s.ar_id =m.m_city LEFT JOIN s_area sar on sar.ar_id =m.m_district where 1=1 ";
        ArrayList<Object> list = new ArrayList<>();
        if (me.getM_id() > 0) {
            sql += " and m_id =?";
            list.add(me.getM_id());
        }
        if (me.getM_class2() > 0) {
            sql += " and m_class2 =?";
            list.add(me.getM_class2());
        }
        if (me.getM_type() > 0) {
            sql += " and m_type =?";
            list.add(me.getM_type());
        }
        if (me.getM_class() > 0) {
            sql += " and m_class =?";
            list.add(me.getM_class());
        }
        if (me.getM_title() != null && me.getM_title().trim().length() > 0) {
            sql += " and m_title like '%" + me.getM_title() + "%'";
        }
        if (me.getM_content() != null && me.getM_content().trim().length() > 0) {
            sql += " and m_content =?";
            list.add(me.getM_content());
        }
        if (me.getM_province() != null && me.getM_province().trim().length() > 0) {
            sql += " and m_province =?";
            list.add(me.getM_province());
        }
        if (me.getM_city() != null && me.getM_city().trim().length() > 0) {
            sql += " and m_city =?";
            list.add(me.getM_city());
        }
        if (me.getM_district() != null && me.getM_district().trim().length() > 0) {
            sql += " and m_district =?";
            list.add(me.getM_district());
        }
        if (me.getM_time() != null && me.getM_time().trim().length() > 0) {
            if (me.getM_type() == 3) {
                sql += " and m_time < UNIX_TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 7 DAY))  and m_time < UNIX_TIMESTAMP()";
            } else {
                sql += " and m_time =?";
                list.add(me.getM_time());
            }
        }
        if (me.getM_authorname() != null && me.getM_authorname().trim().length() > 0) {
            sql += " and m_authorname =?";
            list.add(me.getM_authorname());
        }
        if (me.getM_cover() != null && me.getM_cover().trim().length() > 0) {
            sql += " and m_cover =?";
            list.add(me.getM_cover());
        }
        sql += " order by m_time desc ";
        if (me.getM_type() == 1 && me.getStart() > 0) {
            sql += " limit " + (me.getStart() - 1) * me.getPageSize() + "," + me.getPageSize();
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }

    public List<Map<String, Object>> messageGroupByPlace(MessageEntity me) {
        String sql =
                "select concat(m_province,m_city,m_district) place,m.m_id,m.m_title,m.m_content,m.m_time,m.m_province,m.m_city,m.m_district,sa.a_name province, s.a_name city,sar.a_name district " +
                        "                        from message m " +
                        "                        left join s_area sa on sa.ar_id=m.m_province " +
                        "                        LEFT JOIN s_area s on s.ar_id =m.m_city " +
                        "                        LEFT JOIN s_area sar on sar.ar_id =m.m_district where concat(m_province,m_city,m_district) in " +
                        "(select concat(m_province,m_city,m_district) place from message where  concat(m_province,m_city,m_district) != '' GROUP BY place )";


        ArrayList<Object> list = new ArrayList<>();
        if (me.getM_id() > 0) {
            sql += " and m_id =?";
            list.add(me.getM_id());
        }
        if (me.getM_class2() > 0) {
            sql += " and m_class2 =?";
            list.add(me.getM_class2());
        }
        if (me.getM_type() > 0) {
            sql += " and m_type =?";
            list.add(me.getM_type());
        }
        if (me.getM_class() > 0) {
            sql += " and m_class =?";
            list.add(me.getM_class());
        }
        if (me.getM_title() != null && me.getM_title().trim().length() > 0) {
            sql += " and m_title like '%" + me.getM_title() + "%'";
        }
        if (me.getM_content() != null && me.getM_content().trim().length() > 0) {
            sql += " and m_content =?";
            list.add(me.getM_content());
        }
        if (me.getM_province() != null && me.getM_province().trim().length() > 0) {
            sql += " and m_province =?";
            list.add(me.getM_province());
        }
        if (me.getM_city() != null && me.getM_city().trim().length() > 0) {
            sql += " and m_city =?";
            list.add(me.getM_city());
        }
        if (me.getM_district() != null && me.getM_district().trim().length() > 0) {
            sql += " and m_district =?";
            list.add(me.getM_district());
        }
        if (me.getM_time() != null && me.getM_time().trim().length() > 0) {
            if (me.getM_type() == 3) {
                sql += " and m_time < UNIX_TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 7 DAY))  and m_time < UNIX_TIMESTAMP()";
            } else {
                sql += " and m_time =?";
                list.add(me.getM_time());
            }
        }
        if (me.getM_authorname() != null && me.getM_authorname().trim().length() > 0) {
            sql += " and m_authorname =?";
            list.add(me.getM_authorname());
        }
        if (me.getM_cover() != null && me.getM_cover().trim().length() > 0) {
            sql += " and m_cover =?";
            list.add(me.getM_cover());
        }
        sql += " order by m_time desc ";
        if (me.getM_type() == 1 && me.getStart() > 0) {
            sql += " limit " + (me.getStart() - 1) * me.getPageSize() + "," + me.getPageSize();
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }


}
