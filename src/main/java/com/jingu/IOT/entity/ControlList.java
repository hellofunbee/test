/**  
*   
* 项目名称：IOT  
* 类名称：ControlList  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月4日 下午4:20:57  
* 修改人：jianghu  
* 修改时间：2017年11月4日 下午4:20:57  
* 修改备注： 下午4:20:57
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: ControlList
* @Description: TODO
* @author jianghu
* @date 2017年11月4日 下午4:20:57

*/
public class ControlList {

	private String ckuid;
	private String cksid;
	private List<ControlEntity> list;
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
	public List<ControlEntity> getList() {
		return list;
	}
	public void setList(List<ControlEntity> list) {
		this.list = list;
	}
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	
	
}
