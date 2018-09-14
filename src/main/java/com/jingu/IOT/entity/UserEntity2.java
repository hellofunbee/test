package com.jingu.IOT.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserEntity2 implements Serializable,RowMapper<UserEntity2>{
	
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	private int u_state,emp_id,rank,class1id,class2id,check,u_check,u_age;
				
	
	private String u_name,//用户名
	openid,//联系方式
	access_token,
	refresh_token,
	sex,
	city,
	headimgurl,
	u_phone,
	password,
	u_uname,
	u_nickname,
	u_email,
	points,
	say,
	u_type,
	field,
	postion,
	class1name,
	class2name,
	detail,
	u_phone2,
	serproject,
	seredproject,
	seruser,
	u_search;
	private long u_id,vid,lastVid;
	
	private double money,fcommision,scommision,commision,totalmoney;
	
	

	

	
	public int getU_age() {
		return u_age;
	}
	public void setU_age(int u_age) {
		this.u_age = u_age;
	}
	public int getU_check() {
		return u_check;
	}
	public void setU_check(int u_check) {
		this.u_check = u_check;
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
	public String getSerproject() {
		return serproject;
	}
	public void setSerproject(String serproject) {
		if (null == serproject || serproject.trim().length() == 0) {
			this.serproject = "";
		}
		this.serproject = serproject;
	}
	public String getSeredproject() {
		return seredproject;
	}
	public void setSeredproject(String seredproject) {
		if (null == seredproject || seredproject.trim().length() == 0) {
			this.seredproject = "";
		}
		this.seredproject = seredproject;
	}
	public String getSeruser() {
		return seruser;
	}
	public void setSeruser(String seruser) {
		if (null == seruser || seruser.trim().length() == 0) {
			this.seruser = "";
		}
		this.seruser = seruser;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public String getU_phone2() {
		return u_phone2;
	}
	public void setU_phone2(String u_phone2) {
		if (null == u_phone2 || u_phone2.trim().length() == 0) {
			this.u_phone2 = "";
		}
		this.u_phone2 = u_phone2;
	}
	public int getClass1id() {
		return class1id;
	}
	public void setClass1id(int class1id) {
		this.class1id = class1id;
	}
	public int getClass2id() {
		return class2id;
	}
	public void setClass2id(int class2id) {
		this.class2id =class2id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		if (null == field || field.trim().length() == 0) {
			this.field = "";
		}
		this.field = field;
	}
	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		if (null == postion || postion.trim().length() == 0) {
			this.postion = "";
		}
		this.postion = postion;
	}
	public String getClass1name() {
		return class1name;
	}
	public void setClass1name(String class1name) {
		if (null == class1name || class1name.trim().length() == 0) {
			this.class1name = "";
		}
		this.class1name = class1name;
	}
	public String getClass2name() {
		return class2name;
	}
	public void setClass2name(String class2name) {
		if (null == class2name || class2name.trim().length() == 0) {
			this.class2name = "";
		}
		this.class2name = class2name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		if (null == detail || detail.trim().length() == 0) {
			this.detail = "";
		}
		this.detail = detail;
	}
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		if (null == u_type || u_type.trim().length() == 0) {
			this.u_type = "";
		}
		this.u_type = u_type;
	}
	public String getSay() {
		return say;
	}
	public void setSay(String say) {
		if (null == say || say.trim().length() == 0) {
			this.say = "";
		}
		this.say = say;
	}
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public double getCommision() {
		return commision;
	}
	public void setCommision(double commision) {
		this.commision = commision;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getFcommision() {
		return fcommision;
	}
	public void setFcommision(double fcommision) {
		this.fcommision = fcommision;
	}
	public double getScommision() {
		return scommision;
	}
	public void setScommision(double scommision) {
		this.scommision = scommision;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getRank() {
		return rank;
	}
	public void rank(int rank) {
		this.rank = rank;
	}

	public long getLastVid() {
		return lastVid;
	}
	public void setLastVid(long lastVid) {
		this.lastVid = lastVid;
	}
	public long getVid() {
		return vid;
	}
	public void setVid(long vid) {
		this.vid = vid;
	}
	public String getU_uname() {
		return u_uname;
	}
	public void setU_uname(String u_uname) {
		if (null == u_uname || u_uname.trim().length() == 0) {
			this.u_uname = "";
		}
		this.u_uname = u_uname;
	}
	public String getU_nickname() {
		return u_nickname;
	}
	public void setU_nickname(String u_nickname) {
		if (null == u_nickname || u_nickname.trim().length() == 0) {
			this.u_nickname = "";
		}
		this.u_nickname = u_nickname;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		if (null == u_email || u_email.trim().length() == 0) {
			this.u_email = "";
		}
		this.u_email = u_email;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (null == password || password.trim().length() == 0) {
			this.password = "";
		}
		this.password = password;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public int getU_state() {
		return u_state;
	}
	public void setU_state(int u_state) {
		this.u_state = u_state;
	}



	public long getU_id() {
		return u_id;
	}
	public void setU_id(long u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public UserEntity2() {
	}
	public UserEntity2(Long u_id,int u_state,  String u_name,String openid,String sex,String city,String headimgurl,String u_phone,String access_token,String refresh_token,String password,String u_uname,String u_nickname,String u_email,long vid,long lastVid,int rank,double money,double fcommision,double scommision,double commision,double totalmoney,String say,String u_type) {
		super();
		this.u_id = u_id;
		this.u_name = u_name;
		this.openid = openid;
		this.sex = sex;
		this.city = city;
		this.headimgurl=headimgurl;
		this.u_state=u_state;
		this.u_phone=u_phone;
		this.access_token=access_token;
		this.refresh_token=refresh_token;
		this.password = password;
		this.u_uname = u_uname;
		this.u_nickname = u_nickname;
		this.u_email = u_email;
		this.vid = vid;
		this.lastVid = lastVid;
		this.rank = rank;
		this.money =money;
		this.fcommision =fcommision;
		this.scommision =scommision;
		this.commision =commision;
		this.totalmoney =totalmoney;
		this.say =say;
		this.u_type = u_type;
		
	}

	@Override
	public String toString(){
		return "UserEntity [u_id="+u_id+",u_state="+u_state+",u_name="+u_name+",openid="+openid+",sex="+sex+",city="+city+",headimgurl="+headimgurl+",u_phone="+u_phone+",access_token="+access_token+"refresh_token="+refresh_token+"]";
	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UserEntity2 mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
//		int columnCount = rs.getMetaData().getColumnCount();
//		if(columnCount<20){
//			return new UserEntity2(rs.getLong("u_id"),rs.getInt("u_state"), rs.getString("u_name"), rs.getString("openid"), rs.getString("sex"), rs.getString("city"),
//					rs.getString("headimgurl"), rs.getString("u_phone"),rs.getString("access_token"), rs.getString("refresh_token"),"",rs.getString("u_uname"),rs.getString("u_nickname"),rs.getString("u_email"),0,0,0,rs.getDouble("money"),rs.getDouble("fcommision"),rs.getDouble("scommision"),
//					rs.getDouble("commision"));
//		}
		return new UserEntity2(rs.getLong("u_id"),rs.getInt("u_state"), rs.getString("u_name"), rs.getString("openid"), rs.getString("sex"), rs.getString("city"),
				rs.getString("headimgurl"), rs.getString("u_phone"),rs.getString("access_token"), rs.getString("refresh_token"),"",rs.getString("u_uname"),rs.getString("u_nickname"),rs.getString("u_email"),rs.getLong("vid"),rs.getLong("lastVid"),rs.getInt("rank"),rs.getDouble("money"),rs.getDouble("fcommision"),rs.getDouble("scommision"),rs.getDouble("commision"),rs.getDouble("totalmoney"),rs.getString("say"),rs.getString("u_type"));
	}
	
/*	public UserEntity2 mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserEntity2(rs.getLong("u_id"),rs.getInt("u_state"), rs.getString("u_name"), rs.getString("openid"), rs.getString("sex"), rs.getString("city"),
				rs.getString("headimgurl"), rs.getString("u_phone"),rs.getString("access_token"), rs.getString("refresh_token"),"",rs.getString("u_uname"),rs.getString("u_nickname"),rs.getString("u_email"),rs.getLong("vid"),rs.getLong("lastVid"),rs.getInt("rank"));
	}*/
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
//	@Override
//	public UserEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
//		// TODO Auto-generated method stub
//		if(rs.next()){
//			return new UserEntity(rs.getLong("u_id"),rs.getInt("u_state"), rs.getString("u_name"), rs.getString("openid"), rs.getString("sex"), rs.getString("city"),
//					rs.getString("headimgurl"), rs.getString("u_phone"),rs.getString("access_token"), rs.getString("refresh_token"),"",rs.getString("u_uname"),rs.getString("u_nickname"),rs.getString("u_email"));
//		}
//		return null;
//		
//	}


}
