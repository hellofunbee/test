/**  
*   
* 项目名称：IOT  
* 类名称：DistributionEntity2  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年3月29日 下午12:05:06  
* 修改人：jianghu  
* 修改时间：2018年3月29日 下午12:05:06  
* 修改备注： 下午12:05:06
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: DistributionEntity2
* @Description: TODO
* @author jianghu
* @date 2018年3月29日 下午12:05:06

*/
public class DistributionEntity2 extends DistributionEntity {

	private String orginalName;
	private String fileName;
	private Integer d_procedure;


	public DistributionEntity2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public DistributionEntity2(int d_id, int d_type, int d_state, String d_province, String d_city, String d_district,
			String d_content, String d_value, int d_index, String d_time, String p_province, String p_city,
			String p_district, List<ContentValueEntity> content, String orginalName, String fileName,Integer d_procedure) {
		super(d_id, d_type, d_state, d_province, d_city, d_district, d_content, d_value, d_index, d_time, p_province,
				p_city, p_district, content);
		this.orginalName = orginalName;
		this.fileName = fileName;
		this.d_procedure = d_procedure;
		
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



	public Integer getD_procedure() {
		return d_procedure;
	}



	public void setD_procedure(Integer d_procedure) {
		this.d_procedure = d_procedure;
	}
	
	
}
