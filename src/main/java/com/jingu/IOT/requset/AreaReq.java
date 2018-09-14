/**  
*   
* 项目名称：shop  
* 类名称：AreaReq  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月7日 下午3:18:54  
* 修改人：jianghu  
* 修改时间：2017年9月7日 下午3:18:54  
* 修改备注： 下午3:18:54
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.AreaBean;

/**

* @ClassName: AreaReq
* @Description: TODO
* @author jianghu
* @date 2017年9月7日 下午3:18:54
*
*/
public class AreaReq extends AreaBean {

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
