/**  
*   
* 项目名称：IOT  
* 类名称：ParamRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月9日 上午10:34:57  
* 修改人：jianghu  
* 修改时间：2017年10月9日 上午10:34:57  
* 修改备注： 上午10:34:57
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.SettingEntity;

/**

* @ClassName: ParamRequest
* @Description: TODO
* @author jianghu
* @date 2017年10月9日 上午10:34:57

*/
public class ParamRequest extends PointEntity{

	private String ckuid;
	private String cksid;
	private List<SettingEntity> settingEntity;
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
	public List<SettingEntity> getSettingEntity() {
		return settingEntity;
	}
	public void setSettingEntity(List<SettingEntity> settingEntity) {
		this.settingEntity = settingEntity;
	}


	
}
