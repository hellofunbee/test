/**
*
* 项目名称：IOT
* 类名称：UserEntity
* 类描述：
* 创建人：jianghu
* 创建时间：2017年9月6日 上午10:06:02
* 修改人：jianghu
* 修改时间：2017年9月6日 上午10:06:02
* 修改备注： 上午10:06:02
* @version
*
*/
package com.jingu.IOT.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**

* @ClassName: UserEntity
* @Description: TODO
* @author jianghu
* @date 2017年9月6日 上午10:06:02

*/
public class UserEntity implements RowMapper<UserEntity> {

	private int uid;
	private String tu_username;
	private String tu_pwd;
	private int tu_state;
	private int tu_type;   // 1 超级管理员  2 管理员  3 用户
	private String tu_phone="";
	private String tu_name="";
	private int start;
	private int pagesize =20;
	private String u_search;


	private String power;

	private int unbind;

	private int r_id;

	private String tu_email;
	private String tu_sex;// '0:1:2保密，男，女',
	private String tu_job;//'职务',
	private String tu_edu;// '学位',

	private String device_name;
	private String device_ip;

	private int c_id;//专家分类





	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getUnbind() {
		return unbind;
	}
	public void setUnbind(int unbind) {
		this.unbind = unbind;
	}
	public String getU_search() {
		return u_search;
	}
	public void setU_search(String u_search) {
		if (null == u_search || u_search.trim().length() == 0) {
			this.u_search = "";
		}
		this.u_search = u_search;
	}
	public String getTu_name() {
		return tu_name;
	}
	public void setTu_name(String tu_name) {
		if (null == tu_name || tu_name.trim().length() == 0) {
			this.tu_name = "";
		}
		this.tu_name = tu_name;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPagesize() {
		return pagesize;
	}
	public String getTu_phone() {
		return tu_phone;
	}
	public void setTu_phone(String tu_phone) {
		if (null == tu_phone || tu_phone.trim().length() == 0) {
			this.tu_phone = "";
		}
		this.tu_phone = tu_phone;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTu_username() {
		return tu_username;
	}
	public void setTu_username(String tu_username) {
		if (null == tu_username || tu_username.trim().length() == 0) {
			this.tu_username = "";
		}
		this.tu_username = tu_username;
	}
	public String getTu_pwd() {
		return tu_pwd;
	}
	public void setTu_pwd(String tu_pwd) {
		if (null == tu_pwd || tu_pwd.trim().length() == 0) {
			this.tu_pwd = "";
		}
		this.tu_pwd = tu_pwd;
	}
	public int getTu_state() {
		return tu_state;
	}
	public void setTu_state(int tu_state) {
		this.tu_state = tu_state;
	}
	public int getTu_type() {
		return tu_type;
	}
	public void setTu_type(int tu_type) {
		this.tu_type = tu_type;
	}


	public UserEntity(int uid, String tu_username, String tu_pwd, int tu_state, int tu_type,String tu_phone,String tu_name) {
		super();
		this.uid = uid;
		this.tu_username = tu_username;
		this.tu_pwd = tu_pwd;
		this.tu_state = tu_state;
		this.tu_type = tu_type;
		this.tu_phone =tu_phone;
		this.tu_name =tu_name;
	}


	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
//	@Override
//	public UserEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
//		// TODO Auto-generated method stub
//		if(rs.next()){
//			return new UserEntity(rs.getInt("tu_id"),rs.getString("tu_username"),"",rs.getInt("tu_state"),rs.getInt("tu_type"));
//		}
//		return null;
//	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		 return new UserEntity(rs.getInt("tu_id"),rs.getString("tu_username"),"",rs.getInt("tu_state"),rs.getInt("tu_type"),rs.getString("tu_phone"),rs.getString("tu_name"));
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getTu_email() {
		return tu_email;
	}

	public void setTu_email(String tu_email) {
		this.tu_email = tu_email;
	}

	public String getTu_sex() {
		return tu_sex;
	}

	public void setTu_sex(String tu_sex) {
		this.tu_sex = tu_sex;
	}

	public String getTu_job() {
		return tu_job;
	}

	public void setTu_job(String tu_job) {
		this.tu_job = tu_job;
	}

	public String getTu_edu() {
		return tu_edu;
	}

	public void setTu_edu(String tu_edu) {
		this.tu_edu = tu_edu;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
}
