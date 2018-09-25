/**  
*   
* 项目名称：IOT  
* 类名称：ProduceEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 上午10:45:32  
* 修改人：jianghu  
* 修改时间：2017年10月10日 上午10:45:32  
* 修改备注： 上午10:45:32
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: ProduceEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 上午10:45:32

*/
public class MessageEntity {

	private int m_id,
	m_type,
	m_class,
	m_state,
	start,
	m_class2;
	private String  m_title="",
	m_content="",
	m_province="",
	m_city="",
	m_district="",
	m_time="",
	m_authorname="",
	m_cover="";
	
	private int pageSize,c_id;
	private List<Integer> towho;
	
	
	
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getM_class2() {
		return m_class2;
	}
	public void setM_class2(int m_class2) {
		this.m_class2 = m_class2;
	}
	public int getM_state() {
		return m_state;
	}
	public void setM_state(int m_state) {
		this.m_state = m_state;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getM_type() {
		return m_type;
	}
	public void setM_type(int m_type) {
		this.m_type = m_type;
	}
	public int getM_class() {
		return m_class;
	}
	public void setM_class(int m_class) {
		this.m_class = m_class;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		if (null == m_title || m_title.trim().length() == 0) {
			this.m_title = "";
		}
		this.m_title = m_title;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		if (null == m_content || m_content.trim().length() == 0) {
			this.m_content = "";
		}
		this.m_content = m_content;
	}
	public String getM_province() {
		return m_province;
	}
	public void setM_province(String m_province) {
		if (null == m_province || m_province.trim().length() == 0) {
			this.m_province = "";
		}
		this.m_province = m_province;
	}
	public String getM_city() {
		return m_city;
	}
	public void setM_city(String m_city) {
		if (null == m_city || m_city.trim().length() == 0) {
			this.m_city = "";
		}
		this.m_city = m_city;
	}
	public String getM_district() {
		return m_district;
	}
	public void setM_district(String m_district) {
		if (null == m_district || m_district.trim().length() == 0) {
			this.m_district = "";
		}
		this.m_district = m_district;
	}
	public String getM_time() {
		return m_time;
	}
	public void setM_time(String m_time) {
		if (null == m_time || m_time.trim().length() == 0) {
			this.m_time = "";
		}
		this.m_time = m_time;
	}
	public String getM_authorname() {
		return m_authorname;
	}
	public void setM_authorname(String m_authorname) {
		if (null == m_authorname || m_authorname.trim().length() == 0) {
			this.m_authorname = "";
		}
		this.m_authorname = m_authorname;
	}
	public String getM_cover() {
		return m_cover;
	}
	public void setM_cover(String m_cover) {
		if (null == m_cover || m_cover.trim().length() == 0) {
			this.m_cover = "";
		}
		this.m_cover = m_cover;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public List<Integer> getTowho() {
		return towho;
	}

	public void setTowho(List<Integer> towho) {
		this.towho = towho;
	}
}
