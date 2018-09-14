/**  
*   
* 项目名称：IOT  
* 类名称：RelationShipRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月20日 下午2:53:51  
* 修改人：jianghu  
* 修改时间：2017年11月20日 下午2:53:51  
* 修改备注： 下午2:53:51
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.RelationShipEntity;

/**

* @ClassName: RelationShipRequset
* @Description: TODO
* @author jianghu
* @date 2017年11月20日 下午2:53:51

*/
public class RelationShipRequset extends RelationShipEntity {

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
