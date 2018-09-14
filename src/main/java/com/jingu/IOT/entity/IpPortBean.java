/**  
*   
* 项目名称：nxy  
* 类名称：A  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月3日 下午3:13:49  
* 修改人：jianghu  
* 修改时间：2017年8月3日 下午3:13:49  
* 修改备注： 下午3:13:49
* @version   
*   
*/ 
package com.jingu.IOT.entity;


public class IpPortBean {
	public String ip="";
	public int port=0;
	public int ctrPort=4738;
	public int videoPort=554;
	public int aperturePort=4736;
	public String connType="-1";//0.外网 1内网 2.路由 3.IPC虚拟 4.IPC虚拟+路由
	public String nabtoId="";
	public String getNabtoId() {
		return nabtoId;
	}
	public void setNabtoId(String nabtoId) {
		this.nabtoId = nabtoId;
	}
	public String getConnType() {
		return connType;
	}
	public void setConnType(String connType) {
		this.connType = connType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getCtrPort() {
		return ctrPort;
	}
	public void setCtrPort(int ctrPort) {
		this.ctrPort = ctrPort;
	}
	public int getVideoPort() {
		return videoPort;
	}
	public void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}
	public int getAperturePort() {
		return aperturePort;
	}
	public void setAperturePort(int aperturePort) {
		this.aperturePort = aperturePort;
	}

}
