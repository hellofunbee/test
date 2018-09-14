/**  
*   
* 项目名称：IOT  
* 类名称：Monitor  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月21日 下午2:32:21  
* 修改人：jianghu  
* 修改时间：2017年11月21日 下午2:32:21  
* 修改备注： 下午2:32:21
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**

* @ClassName: Monitor
* @Description: TODO
* @author jianghu
* @date 2017年11月21日 下午2:32:21

*/
public class MonitorEntity implements Serializable,RowMapper<MonitorEntity> {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	private int mo_id;
	private String mo_name;
	private String mo_deviceId;
	private String mo_time;
	private String mo_channel;
	private int mo_type;
	private int mo_state;
	private double mo_high;
	private double mo_lower;
	private int ctrl_id;
	
	
	
	
	
	public MonitorEntity(int mo_id, String mo_name, String mo_deviceId, String mo_time, String mo_channel, int mo_type,
			int mo_state, double mo_high, double mo_lower, int ctrl_id) {
		this.mo_id = mo_id;
		this.mo_name = mo_name;
		this.mo_deviceId = mo_deviceId;
		this.mo_time = mo_time;
		this.mo_channel = mo_channel;
		this.mo_type = mo_type;
		this.mo_state = mo_state;
		this.mo_high = mo_high;
		this.mo_lower = mo_lower;
		this.ctrl_id = ctrl_id;
	}
	
	
	public MonitorEntity() {
		super();
	}


	public int getMo_id() {
		return mo_id;
	}
	public void setMo_id(int mo_id) {
		this.mo_id = mo_id;
	}
	public String getMo_name() {
		return mo_name;
	}
	public void setMo_name(String mo_name) {
		if (null == mo_name || mo_name.trim().length() == 0) {
			this.mo_name = "";
		}
		this.mo_name = mo_name;
	}
	public String getMo_deviceId() {
		return mo_deviceId;
	}
	public void setMo_deviceId(String mo_deviceId) {
		if (null == mo_deviceId || mo_deviceId.trim().length() == 0) {
			this.mo_deviceId = "";
		}
		this.mo_deviceId = mo_deviceId;
	}
	public String getMo_time() {
		return mo_time;
	}
	public void setMo_time(String mo_time) {
		if (null == mo_time || mo_time.trim().length() == 0) {
			this.mo_time = "";
		}
		this.mo_time = mo_time;
	}
	public String getMo_channel() {
		return mo_channel;
	}
	public void setMo_channel(String mo_channel) {
		if (null == mo_channel || mo_channel.trim().length() == 0) {
			this.mo_channel = "";
		}
		this.mo_channel = mo_channel;
	}

	
	
	public int getMo_type() {
		return mo_type;
	}
	public void setMo_type(int mo_type) {
		this.mo_type = mo_type;
	}
	public int getMo_state() {
		return mo_state;
	}
	public void setMo_state(int mo_state) {
		this.mo_state = mo_state;
	}
	public double getMo_high() {
		return mo_high;
	}
	public void setMo_high(double mo_high) {
		this.mo_high = mo_high;
	}
	public double getMo_lower() {
		return mo_lower;
	}
	public void setMo_lower(double mo_lower) {
		this.mo_lower = mo_lower;
	}
	public int getCtrl_id() {
		return ctrl_id;
	}
	public void setCtrl_id(int ctrl_id) {
		this.ctrl_id = ctrl_id;
	}

	
//	nt mo_id, String mo_name, String mo_deviceId, String mo_time, String mo_channel, int mo_type,
//	int mo_state, double mo_high, double mo_lower, int ctrl_id
	@Override
	public MonitorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new MonitorEntity(rs.getInt("mo_id"),rs.getString("mo_name"),rs.getString("mo_deviceId"),rs.getString("mo_time"),rs.getString("mo_channel"),rs.getInt("mo_type"),rs.getInt("mo_state"),rs.getDouble("mo_high"),rs.getDouble("mo_lower"),rs.getInt("ctrl_id"));
	}
	
	
}
