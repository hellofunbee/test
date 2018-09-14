/**  
*   
* 项目名称：IOT  
* 类名称：VAREntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月28日 下午3:49:43  
* 修改人：jianghu  
* 修改时间：2017年8月28日 下午3:49:43  
* 修改备注： 下午3:49:43
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: VAREntity
* @Description: TODO
* @author jianghu
* @date 2017年8月28日 下午3:49:43

*/
public class VAREntity {
	
	private String deviceId;
	private String ip;
	private String configStr;
	private int port;
	private int state;
	
	
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public String getConfigStr() {
		return configStr;
	}

	public void setConfigStr(String configStr) {
		if (null == configStr || configStr.trim().length() == 0) {
			this.configStr = "";
		}
		this.configStr = configStr;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		if (null == deviceId || deviceId.trim().length() == 0) {
			this.deviceId = "";
		}
		this.deviceId = deviceId;
	}

	public VAREntity(String deviceId, String ip, String configStr, int port, int state) {
		this.deviceId = deviceId;
		this.ip = ip;
		this.configStr = configStr;
		this.port = port;
		this.state = state;
	}

	public VAREntity() {
		// TODO Auto-generated constructor stub
	}
	

}
