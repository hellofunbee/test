/**  
*   
* 项目名称：IOT  
* 类名称：MainDeviceRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月6日 下午2:25:50  
* 修改人：jianghu  
* 修改时间：2017年9月6日 下午2:25:50  
* 修改备注： 下午2:25:50
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.MainDeviceEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.RelationShipEntity;

/**

* @ClassName: MainDeviceRequset
* @Description: TODO
* @author jianghu
* @date 2017年9月6日 下午2:25:50

*/
public class MainDeviceRequset extends MainDeviceEntity {

	private String cksid;
	private String ckuid;
	private PointEntity pointEntity;
	private long uid;
	private RelationShipEntity relationShipEntity;
	
	
	public RelationShipEntity getRelationShipEntity() {
		return relationShipEntity;
	}
	public void setRelationShipEntity(RelationShipEntity relationShipEntity) {
		this.relationShipEntity = relationShipEntity;
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
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		if (null == ckuid || ckuid.trim().length() == 0) {
			this.ckuid = "";
		}
		this.ckuid = ckuid;
	}

	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}

	
}
