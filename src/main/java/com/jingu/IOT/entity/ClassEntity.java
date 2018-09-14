package com.jingu.IOT.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class ClassEntity implements Serializable,RowMapper<ClassEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//类目表
	private int c_id,//类目id
	c_lev,//当前级别
	c_rid,//上级 id  0/表示 没有上级
	c_state,//状态 1/正常  2/挂起
	c_posi,//显示在首页的位置
	c_type; // 类型 1 作物 2 农资
	private String c_name,//类目名称
	c_photo;//图片
	private List<ClassEntity> list = new ArrayList<>();
	
	
	
	public List<ClassEntity> getList() {
		return list;
	}


	public void setList(List<ClassEntity> list) {
		this.list = list;
	}


	public int getC_type() {
		return c_type;
	}


	public void setC_type(int c_type) {
		this.c_type = c_type;
	}


	public int getC_id() {
		return c_id;
	}


	public void setC_id(int c_id) {
		this.c_id = c_id;
	}


	public int getC_lev() {
		return c_lev;
	}


	public void setC_lev(int c_lev) {
		this.c_lev = c_lev;
	}


	public int getC_rid() {
		return c_rid;
	}


	public void setC_rid(int c_rid) {
		this.c_rid = c_rid;
	}


	public int getC_state() {
		return c_state;
	}


	public void setC_state(int c_state) {
		this.c_state = c_state;
	}


	public String getC_name() {
		return c_name;
	}


	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public int getC_posi() {
		return c_posi;
	}


	public void setC_posi(int c_posi) {
		this.c_posi = c_posi;
	}

	public String getC_photo() {
		return c_photo;
	}


	public void setC_photo(String c_photo) {
		this.c_photo = c_photo;
	}


	public ClassEntity() {
	}
	
	public ClassEntity(int c_id, int c_lev, int c_rid, int c_state, String c_name,int c_posi,String c_photo,int c_type) {
		super();
		this.c_id = c_id;
		this.c_lev = c_lev;
		this.c_rid = c_rid;
		this.c_state = c_state;
		this.c_name = c_name;
		this.c_posi = c_posi;
		this.c_photo = c_photo;
		this.c_type =c_type;
	}

	@Override
	public String toString(){
		return "ClassEntity [c_id="+c_id+",c_lev="+c_lev+",c_rid="+c_rid+",c_state="+c_state+",c_name="+c_name+",c_posi="+c_posi+",c_photo="+c_photo+"]";
	}

	@Override
	public ClassEntity mapRow(ResultSet rs, int arg1) throws SQLException {
		return new ClassEntity(rs.getInt("c_id"), rs.getInt("c_lev"), rs.getInt("c_rid"), rs.getInt("c_state"), rs.getString("c_name"), rs.getInt("c_posi"), rs.getString("c_photo"),rs.getInt("c_type"));
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
//	@Override
//	public int compareTo(ClassEntity o) {
//		//if(PingYinUtil.getFirstSpell(o.getC_name()))
//		//首先比较长度是否相同
//		//长度不同
//		//在你比较字母
//		String firstSpell = PingYinUtil.getFirstSpell(this.c_name);
//		String ofirstSpell = PingYinUtil.getFirstSpell(o.getC_name());
//		int compareTo = firstSpell.compareTo(ofirstSpell);
//		return compareTo;
//	}

}
