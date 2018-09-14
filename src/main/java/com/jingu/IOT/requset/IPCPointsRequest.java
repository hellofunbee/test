/**  
*   
* 项目名称：IOT  
* 类名称：IPCPointsRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月12日 下午1:09:28  
* 修改人：jianghu  
* 修改时间：2018年4月12日 下午1:09:28  
* 修改备注： 下午1:09:28
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.PointEntity;

/**

* @ClassName: IPCPointsRequest
* @Description: TODO
* @author jianghu
* @date 2018年4月12日 下午1:09:28

*/
public class IPCPointsRequest {
	
	private String ckuid;
	private String cksid;
	private PointEntity pointEntity;
	private List<IPCPointEntity> ipcPointList;
	private String idString;
	
	
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		this.cksid = cksid;
	}
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	public List<IPCPointEntity> getIpcPointList() {
		return ipcPointList;
	}
	public void setIpcPointList(List<IPCPointEntity> ipcPointList) {
		this.ipcPointList = ipcPointList;
	}
	
	// ]v7m((d4_Z
	// ]v7m^^d4}Z

}
