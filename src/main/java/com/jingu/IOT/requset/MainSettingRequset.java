/**  
*   
* 项目名称：IOT  
* 类名称：MainSettingRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月15日 下午3:16:57  
* 修改人：jianghu  
* 修改时间：2017年9月15日 下午3:16:57  
* 修改备注： 下午3:16:57
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.MainSettingEntity;

/**

* @ClassName: MainSettingRequset
* @Description: TODO
* @author jianghu
* @date 2017年9月15日 下午3:16:57

*/
public class MainSettingRequset extends MainSettingEntity {

	private String ckuid;
	private String cksid;
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
