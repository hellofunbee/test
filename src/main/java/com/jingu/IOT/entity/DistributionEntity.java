/**  
*   
* 项目名称：IOT  
* 类名称：Distribution  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午6:02:49  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午6:02:49  
* 修改备注： 下午6:02:49
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: Distribution
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午6:02:49

*/
public class DistributionEntity {

	private int d_id;
	private int d_type;
	private int d_state;
	private String d_province;
	private String d_city;
	private String d_district;
	private String d_content;
	private String d_value;
	private int d_index;
	private String d_time;
	private String p_province;
	private String p_city;
	private String p_district;
	private List<ContentValueEntity> content;


	private String orginalName;
	private String fileName;
	private int d_procedure;
	
	
	public DistributionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DistributionEntity(int d_id, int d_type, int d_state, String d_province, String d_city, String d_district,
			String d_content, String d_value, int d_index, String d_time, String p_province, String p_city,
			String p_district, List<ContentValueEntity> content,String orginalName,String fileName,int d_procedure) {
		super();
		this.d_id = d_id;
		this.d_type = d_type;
		this.d_state = d_state;
		this.d_province = d_province;
		this.d_city = d_city;
		this.d_district = d_district;
		this.d_content = d_content;
		this.d_value = d_value;
		this.d_index = d_index;
		this.d_time = d_time;
		this.p_province = p_province;
		this.p_city = p_city;
		this.p_district = p_district;
		this.content = content;
		this.orginalName = orginalName;
		this.fileName = fileName;
		this.d_procedure = d_procedure;
	}

	public DistributionEntity(int d_id, int d_type, int d_state, String d_province, String d_city, String d_district,
							  String d_content, String d_value, int d_index, String d_time, String p_province, String p_city,
							  String p_district, List<ContentValueEntity> content) {
		super();
		this.d_id = d_id;
		this.d_type = d_type;
		this.d_state = d_state;
		this.d_province = d_province;
		this.d_city = d_city;
		this.d_district = d_district;
		this.d_content = d_content;
		this.d_value = d_value;
		this.d_index = d_index;
		this.d_time = d_time;
		this.p_province = p_province;
		this.p_city = p_city;
		this.p_district = p_district;
		this.content = content;

	}
	public String getP_province() {
		return p_province;
	}
	public void setP_province(String p_province) {
		this.p_province = p_province;
	}
	public String getP_city() {
		return p_city;
	}
	public void setP_city(String p_city) {
		this.p_city = p_city;
	}
	public String getP_district() {
		return p_district;
	}
	public void setP_district(String p_district) {
		this.p_district = p_district;
	}
	public List<ContentValueEntity> getContent() {
		return content;
	}
	public void setContent(List<ContentValueEntity> content) {
		this.content = content;
	}
	public String getD_time() {
		return d_time;
	}
	public void setD_time(String d_time) {
		if (null == d_time || d_time.trim().length() == 0) {
			this.d_time = "";
		}
		this.d_time = d_time;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getD_type() {
		return d_type;
	}
	public void setD_type(int d_type) {
		this.d_type = d_type;
	}
	public int getD_state() {
		return d_state;
	}
	public void setD_state(int d_state) {
		this.d_state = d_state;
	}
	public String getD_province() {
		return d_province;
	}
	public void setD_province(String d_province) {
		if (null == d_province || d_province.trim().length() == 0) {
			this.d_province = "";
		}
		this.d_province = d_province;
	}
	public String getD_city() {
		return d_city;
	}
	public void setD_city(String d_city) {
		if (null == d_city || d_city.trim().length() == 0) {
			this.d_city = "";
		}
		this.d_city = d_city;
	}
	public String getD_district() {
		return d_district;
	}
	public void setD_district(String d_district) {
		if (null == d_district || d_district.trim().length() == 0) {
			this.d_district = "";
		}
		this.d_district = d_district;
	}
	public String getD_content() {
		return d_content;
	}
	public void setD_content(String d_content) {
		if (null == d_content || d_content.trim().length() == 0) {
			this.d_content = "";
		}
		this.d_content = d_content;
	}
	public String getD_value() {
		return d_value;
	}
	public void setD_value(String d_value) {
		if (null == d_value || d_value.trim().length() == 0) {
			this.d_value = "";
		}
		this.d_value = d_value;
	}
	public int getD_index() {
		return d_index;
	}
	public void setD_index(int d_index) {
		this.d_index = d_index;
	}


	public String getOrginalName() {
		return orginalName;
	}

	public void setOrginalName(String orginalName) {
		this.orginalName = orginalName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getD_procedure() {
		return d_procedure;
	}

	public void setD_procedure(int d_procedure) {
		this.d_procedure = d_procedure;
	}
}
