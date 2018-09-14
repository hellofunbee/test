/**  
*   
* 项目名称：IOT  
* 类名称：IPCProxyRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月18日 下午6:48:09  
* 修改人：jianghu  
* 修改时间：2017年9月18日 下午6:48:09  
* 修改备注： 下午6:48:09
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.IPCProxyEntity;
import com.jingu.IOT.entity.PointEntity;

/**

* @ClassName: IPCProxyRequest
* @Description: TODO
* @author jianghu
* @date 2017年9月18日 下午6:48:09

*/
public class IPCProxyRequest extends IPCProxyEntity {

	private String ckuid;
	private String cksid;
	private PointEntity pointEntity;
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
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	
	
	
}
