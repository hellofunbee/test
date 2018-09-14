/**  
*   
* 项目名称：shop  
* 类名称：AreaBean  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月7日 下午3:15:59  
* 修改人：jianghu  
* 修改时间：2017年9月7日 下午3:15:59  
* 修改备注： 下午3:15:59
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: AreaBean
* @Description: TODO
* @author jianghu
* @date 2017年9月7日 下午3:15:59

*/
public class AreaBean {

	private String ar_id;
	private String a_name;
	private String a_pid;
	public String getAr_id() {
		return ar_id;
	}
	public void setAr_id(String ar_id) {
		if (null == ar_id || ar_id.trim().length() == 0) {
			this.ar_id = "";
		}
		this.ar_id = ar_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		if (null == a_name || a_name.trim().length() == 0) {
			this.a_name = "";
		}
		this.a_name = a_name;
	}
	public String getA_pid() {
		return a_pid;
	}
	public void setA_pid(String a_pid) {
		if (null == a_pid || a_pid.trim().length() == 0) {
			this.a_pid = "";
		}
		this.a_pid = a_pid;
	}
	
	
}
