/**  
*   
* 项目名称：IOT  
* 类名称：RoleEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月13日 下午1:12:26  
* 修改人：jianghu  
* 修改时间：2017年10月13日 下午1:12:26  
* 修改备注： 下午1:12:26
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: RoleEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月13日 下午1:12:26

*/
public class MenuEntity {

	private int id;
	private String r_name;
	private String r_value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		if (null == r_name || r_name.trim().length() == 0) {
			this.r_name = "";
		}
		this.r_name = r_name;
	}
	public String getR_value() {
		return r_value;
	}
	public void setR_value(String r_value) {
		if (null == r_value || r_value.trim().length() == 0) {
			this.r_value = "";
		}
		this.r_value = r_value;
	}
	
	
}
