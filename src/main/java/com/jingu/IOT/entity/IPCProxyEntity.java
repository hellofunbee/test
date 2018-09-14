/**  
*   
* 项目名称：IOT  
* 类名称：IPCProxyEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月18日 下午5:05:50  
* 修改人：jianghu  
* 修改时间：2017年9月18日 下午5:05:50  
* 修改备注： 下午5:05:50
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: IPCProxyEntity
* @Description: TODO
* @author jianghu
* @date 2017年9月18日 下午5:05:50

*/
public class IPCProxyEntity {
	//deviceId,s_host,s_hostport,s_proxy,s_proxyport,s_pwr,s_pwral,s_timeout,status) valus(?,?,?,?,?,?,?,?,?)
	//s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;
	private int id;
	private String deviceId;
	private String s_host;
	private int s_hostport;
	private String s_proxy;
	private int s_proxyport;
	private int s_pwr;
	private int s_pwrval;
	private int status;
	private String s_timeout;
	private String mapingDeviceId;
	private String username;
	private String password;
	private int type; // 代理类型 1 视频代理 2 控制代理
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	public String getMapingDeviceId() {
		return mapingDeviceId;
	}
	public void setMapingDeviceId(String mapingDeviceId) {
		if (null == mapingDeviceId || mapingDeviceId.trim().length() == 0) {
			this.mapingDeviceId = "";
		}
		this.mapingDeviceId = mapingDeviceId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if (null == username || username.trim().length() == 0) {
			this.username = "";
		}
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if (null == password || password.trim().length() == 0) {
			this.password = "";
		}
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setS_pwrval(int s_pwrval) {
		this.s_pwrval = s_pwrval;
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
	public String getS_host() {
		return s_host;
	}
	public void setS_host(String s_host) {
		if (null == s_host || s_host.trim().length() == 0) {
			this.s_host = "";
		}
		this.s_host = s_host;
	}
	public int getS_hostport() {
		return s_hostport;
	}
	public void setS_hostport(int s_hostport) {
		this.s_hostport = s_hostport;
	}
	public String getS_proxy() {
		return s_proxy;
	}
	public void setS_proxy(String s_proxy) {
		if (null == s_proxy || s_proxy.trim().length() == 0) {
			this.s_proxy = "";
		}
		this.s_proxy = s_proxy;
	}
	public int getS_proxyport() {
		return s_proxyport;
	}
	public void setS_proxyport(int s_proxyport) {
		this.s_proxyport = s_proxyport;
	}
	public int getS_pwr() {
		return s_pwr;
	}
	public void setS_pwr(int s_pwr) {
		this.s_pwr = s_pwr;
	}
	public int getS_pwrval() {
		return s_pwrval;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getS_timeout() {
		return s_timeout;
	}
	public void setS_timeout(String s_timeout) {
		if (null == s_timeout || s_timeout.trim().length() == 0) {
			this.s_timeout = "";
		}
		this.s_timeout = s_timeout;
	}
	
}
