/**  
*   
* 项目名称：IOT  
* 类名称：IPCRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月8日 下午3:42:19  
* 修改人：jianghu  
* 修改时间：2017年9月8日 下午3:42:19  
* 修改备注： 下午3:42:19
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.IPCEntity;
import com.jingu.IOT.entity.IPCProxyEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.VideoParamsBean;

/**

* @ClassName: IPCRequest
* @Description: TODO
* @author jianghu
* @date 2017年9月8日 下午3:42:19

*/
public class IPCRequest extends IPCEntity {
	private String ckuid;
	private String cksid;
	private PointEntity pointEntity;
	private VideoParamsBean videoParamsBean;
	private IPCProxyEntity ipc;
	private int type;
	private int cktime;
	
	
	
	
	public int getCktime() {
		return cktime;
	}
	public void setCktime(int cktime) {
		this.cktime = cktime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public VideoParamsBean getVideoParamsBean() {
		return videoParamsBean;
	}
	public void setVideoParamsBean(VideoParamsBean videoParamsBean) {
		this.videoParamsBean = videoParamsBean;
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
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	public IPCProxyEntity getIpc() {
		return ipc;
	}
	public void setIpc(IPCProxyEntity ipc) {
		this.ipc = ipc;
	}
	
	
	

}
