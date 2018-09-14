/**  
*   
* 项目名称：IOT  
* 类名称：InputRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午7:10:47  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午7:10:47  
* 修改备注： 下午7:10:47
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: InputRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午7:10:47

*/
public class InputRequset extends InputEntity {

	private String ckuid;
	private String cksid;
	private List<InputEntity> inputEntity;
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
	public List<InputEntity> getInputEntity() {
		return inputEntity;
	}
	public void setInputEntity(List<InputEntity> inputEntity) {
		this.inputEntity = inputEntity;
	}
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}

	
	
	
}
