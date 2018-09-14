/**  
*   
* 项目名称：nxy  
* 类名称：VRAConfigBean  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月3日 下午3:55:06  
* 修改人：jianghu  
* 修改时间：2017年8月3日 下午3:55:06  
* 修改备注： 下午3:55:06
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: VRAConfigBean
* @Description: TODO
* @author jianghu
* @date 2017年8月3日 下午3:55:06

*/
public class VRAConfigBean {

	
	
	public VRAConfigBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VRAConfigBean(String configstr, String ip) {
		super();
		this.configstr = configstr;
		this.ip = ip;
	}
	private String configstr;
	private  String ip;
	public String getConfigstr() {
		return configstr;
	}
	public void setConfigstr(String configstr) {
		if (null == configstr || configstr.trim().length() == 0) {
			this.configstr = "";
		}
		this.configstr = configstr;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		if (null == ip || ip.trim().length() == 0) {
			this.ip = "";
		}
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "VRAConfigBean [configstr=" + configstr + ", ip=" + ip + "]";
	}

	
	
}
