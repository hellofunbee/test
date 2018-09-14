/**  
*   
* 项目名称：IOT  
* 类名称：MonitorRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月21日 下午3:46:06  
* 修改人：jianghu  
* 修改时间：2017年11月21日 下午3:46:06  
* 修改备注： 下午3:46:06
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.MonitorEntity;

/**

* @ClassName: MonitorRequest
* @Description: TODO
* @author jianghu
* @date 2017年11月21日 下午3:46:06

*/
public class MonitorRequest extends MonitorEntity {

	private String ckuid;
	private String cksid;
	private int type ;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
