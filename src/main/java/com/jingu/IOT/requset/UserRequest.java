/**  
*   
* 项目名称：IOT  
* 类名称：UserRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月6日 上午10:31:37  
* 修改人：jianghu  
* 修改时间：2017年9月6日 上午10:31:37  
* 修改备注： 上午10:31:37
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.UserEntity;

/**

* @ClassName: UserRequest
* @Description: TODO
* @author jianghu
* @date 2017年9月6日 上午10:31:37

*/
public class UserRequest extends UserEntity{

	private String ckuid;
	private String cksid;
	private String deviceId;
	

	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		if (null == ckuid || ckuid.trim().length() == 0) {
			this.ckuid = "";
		}
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		if (null == cksid || cksid.trim().length() == 0) {
			this.cksid = "";
		}
		this.cksid = cksid;
	}
	
	
}
