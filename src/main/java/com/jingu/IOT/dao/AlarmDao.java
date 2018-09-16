/**  
*   
* 项目名称：IOT  
* 类名称：AlarmDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午1:53:32  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午1:53:32  
* 修改备注： 下午1:53:32
* @version   
*   
*/
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.AlarmRuleEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: AlarmDao
 * @Description: TODO
 * @author jianghu
 * @date 2018年4月13日 下午1:53:32
 * 
 */
@Component
public class AlarmDao {
	final static String table = "alarm_rule";

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	// @Autowired
	// public AlarmDao(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }

	public int addAlarmRule(AlarmRuleEntity ae) {
		String sql = "insert into "+table+" (ala_name,ala_channel,deviceid,range,ala_state,ala_up,ala_low,ala_producer,ala_supervisor,ala_content,ala_index,ala_grade) value (?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, ae.getAla_name(), ae.getAla_channel(), ae.getDeviceid(), ae.getRange(),
				ae.getAla_state(), ae.getAla_up(), ae.getAla_low(), ae.getAla_producer(), ae.getAla_supervisor(),
				ae.getAla_content(), ae.getAla_index(), ae.getAla_grade());
	}

	public int deleteAlarmById(AlarmRuleEntity ae) {
		String sql = "delete from "+table+" ala_id = ?";
		return jdbcTemplate.update(sql, ae.getAla_id());
	}

	public int deleteAlarmByDeviceId(String deviceId) {
		String sql = "delete from "+table+" deviceid = ?";
		return jdbcTemplate.update(sql, deviceId);
	}

	public int addAlarmRuleList(List<AlarmRuleEntity> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		String sql = "insert into alarm (ala_name,ala_channel,deviceid,range,ala_state,ala_up,ala_low,ala_producer,ala_supervisor,ala_content,ala_index,ala_grade) value (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, list.get(i).getAla_name());
				ps.setString(2, list.get(i).getAla_channel());
				ps.setString(3, list.get(i).getDeviceid());
				ps.setString(4, list.get(i).getRange());
				ps.setInt(5, list.get(i).getAla_state());
				ps.setString(6, list.get(i).getAla_up());
				ps.setString(7, list.get(i).getAla_low());
				ps.setInt(8, list.get(i).getAla_producer());
				ps.setInt(9, list.get(i).getAla_supervisor());
				ps.setString(10, list.get(i).getAla_content());
				ps.setString(11, list.get(i).getAla_index());
				ps.setString(12, list.get(i).getAla_grade());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		return 1;
	}

	public int updateAlarmRule(AlarmRuleEntity ae) {
		String sql = "update "+table +"  set ala_id = ?";
		if (ae == null) {
			return 0;
		}
		ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.add(ae.getAla_id());
		if (ae.getAla_channel() != null && ae.getAla_channel().trim().length() > 0) {
			sql += " , ala_channel = ?";
			arrayList.add(ae.getAla_channel());
		}
		if (ae.getAla_content() != null && ae.getAla_content().trim().length() > 0) {
			sql += " , ala_content = ?";
			arrayList.add(ae.getAla_content());
		}
		if (ae.getAla_index() != null && ae.getAla_index().trim().length() > 0) {
			sql += " , ala_index = ?";
			arrayList.add(ae.getAla_index());
		}
		if (ae.getAla_low() != null && ae.getAla_low().trim().length() > 0) {
			sql += " , ala_low = ?";
			arrayList.add(ae.getAla_low());
		}
		if (ae.getAla_name() != null && ae.getAla_name().trim().length() > 0) {
			sql += " , ala_name = ?";
			arrayList.add(ae.getAla_name());
		}
		if (ae.getAla_up() != null && ae.getAla_up().trim().length() > 0) {
			sql += " , ala_up = ?";
			arrayList.add(ae.getAla_up());
		}
		if (ae.getAla_producer() != null && ae.getAla_producer().intValue() > 0) {
			sql += " , ala_producer = ?";
			arrayList.add(ae.getAla_producer());
		}
		if (ae.getAla_state() != null && ae.getAla_state().intValue() > 0) {
			sql += " , ala_state = ?";
			arrayList.add(ae.getAla_state());
		}
		if (ae.getAla_supervisor() != null && ae.getAla_supervisor().intValue() > 0) {
			sql += " , ala_supervisor = ?";
			arrayList.add(ae.getAla_supervisor());
		}
		if (ae.getRange() != null && ae.getRange().trim().length() > 0) {
			sql += " , range = ?";
			arrayList.add(ae.getRange());
		}
		if (ae.getDeviceid() != null && ae.getDeviceid().trim().length() > 0) {
			sql += " , deviceid = ?";
			arrayList.add(ae.getDeviceid());
		}
		if (arrayList.size() == 1) {
			return 0;
		}
		sql += " where ala_id = ?";
		arrayList.add(ae.getAla_id());
		return jdbcTemplate.update(sql, arrayList.toArray());
	}

	public List<Map<String, Object>> listAlarmRule(AlarmRuleEntity ae, Integer pageNum, Integer pageSize) {
		String sql = "select * from "+table +"  where 1=1";
		ArrayList<Object> arrayList = new ArrayList<>();
		if (ae.getAla_channel() != null && ae.getAla_channel().trim().length() > 0) {
			sql += " and ala_channel = ?";
			arrayList.add(ae.getAla_channel());
		}
		if (ae.getAla_content() != null && ae.getAla_content().trim().length() > 0) {
			sql += " and ala_content = ?";
			arrayList.add(ae.getAla_content());
		}
		if (ae.getAla_index() != null && ae.getAla_index().trim().length() > 0) {
			sql += " and ala_index = ?";
			arrayList.add(ae.getAla_index());
		}
		if (ae.getAla_low() != null && ae.getAla_low().trim().length() > 0) {
			sql += " and ala_low = ?";
			arrayList.add(ae.getAla_low());
		}
		if (ae.getAla_name() != null && ae.getAla_name().trim().length() > 0) {
			sql += " and ala_name = ?";
			arrayList.add(ae.getAla_name());
		}
		if (ae.getAla_up() != null && ae.getAla_up().trim().length() > 0) {
			sql += " and ala_up = ?";
			arrayList.add(ae.getAla_up());
		}
		if (ae.getAla_producer() != null && ae.getAla_producer().intValue() > 0) {
			sql += " and ala_producer = ?";
			arrayList.add(ae.getAla_producer());
		}
		if (ae.getAla_state() != null && ae.getAla_state().intValue() > 0) {
			sql += " and ala_state = ?";
			arrayList.add(ae.getAla_state());
		}
		if (ae.getAla_supervisor() != null && ae.getAla_supervisor().intValue() > 0) {
			sql += " and ala_supervisor = ?";
			arrayList.add(ae.getAla_supervisor());
		}
		if (ae.getRange() != null && ae.getRange().trim().length() > 0) {
			sql += " and range = ?";
			arrayList.add(ae.getRange());
		}
		if (ae.getDeviceid() != null && ae.getDeviceid().trim().length() > 0) {
			sql += " and deviceid = ?";
			arrayList.add(ae.getDeviceid());
		}
		if (ae.getAla_id() != null && ae.getAla_id().intValue() > 0) {
			sql += " and ala_id = ?";
			arrayList.add(ae.getAla_id());
		}
		if (pageNum != null && pageNum.intValue() > 0) {
			sql += " limit " + (pageNum - 1) * pageSize + "," + pageSize;
		}
		return jdbcTemplate.queryForList(sql, arrayList.toArray());
	}

	public String getFiledByFieldValue(String outFiled, String inField, String value) {
		String sql = " select count(1) from "+table +"  where " + inField + " = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, value);
		if (count == 0) {
			return null;
		}
		sql = " select " + outFiled + " from "+table +"  where " + inField + " = ?";
		return jdbcTemplate.queryForObject(sql, String.class, value);
	}

	public Map<String, Object> getFieldByValue(String inField, String value) {
		String sql = " select count(1) from "+table +"  where " + inField + " = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, value);
		if (count == 0) {
			return null;
		}
		sql = " select * from "+table +"  where " + inField + " = ?";
		return jdbcTemplate.queryForMap(sql, value);
	}

	public int deleteAlarmByIdString(String idString) {
		if (idString == null) {
			return 0;
		}
		String sql = "delete from "+table +"  deviceid in (" + idString + ")";
		return jdbcTemplate.update(sql);
	}

	// 根据设备id和channel分组 获得纪录
	public List<Map<String, Object>> listAlarmGroupByDeviceId(String deviceId) {
		if (deviceId == null) {
			return null;
		}
		String sql = "select * from "+table +"  where deivceid =? group by ala_channel";
		return jdbcTemplate.queryForList(sql, deviceId);
	}

	// 通过设备id 分数 channel 确定等级
	public Map<String, Object> getGrade(String deviceId, String channel, String score) {
		if (deviceId == null) {
			return null;
		}
		String sql = "select count(1) from "+table +"  where deivceid =? and ala_channel = ? and ala_up > ? and ala_low < ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, deviceId, channel, score);
		if (count == 0) {
			return null;
		}
		sql = "select * from "+table +"  where deivceid =? and ala_channel = ? and ala_up > ? and ala_low < ?";
		return jdbcTemplate.queryForMap(sql);
	}

	

}
