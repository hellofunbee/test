/**  
*   
* 项目名称：IOT  
* 类名称：IPCPoint  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月12日 下午2:54:17  
* 修改人：jianghu  
* 修改时间：2017年9月12日 下午2:54:17  
* 修改备注： 下午2:54:17
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.PointEntity;

/**

* @ClassName: IPCPoint
* @Description: TODO
* @author jianghu
* @date 2017年9月12日 下午2:54:17

*/
public class IPCPointRequset extends IPCPointEntity {
	private PointEntity pointEntity;
	private String ckuid;
	private String cksid;

	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
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
