/**  
*   
* 项目名称：IOT  
* 类名称：ContentValueEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月20日 下午2:14:42  
* 修改人：jianghu  
* 修改时间：2017年10月20日 下午2:14:42  
* 修改备注： 下午2:14:42
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ContentValueEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月20日 下午2:14:42

*/
public class ContentValueEntity {

	private String d_content;
	private String d_value;
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


	
	
}
