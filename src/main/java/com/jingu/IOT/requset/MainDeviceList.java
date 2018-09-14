/**  
*   
* 项目名称：IOT  
* 类名称：MainDeviceList  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月4日 下午2:32:55  
* 修改人：jianghu  
* 修改时间：2017年11月4日 下午2:32:55  
* 修改备注： 下午2:32:55
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.MainDeviceEntity;

/**

* @ClassName: MainDeviceList
* @Description: TODO
* @author jianghu
* @date 2017年11月4日 下午2:32:55

*/
public class MainDeviceList {
	
	private String ckuid;
	private String cksid;
	private List<MainDeviceEntity> list;
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
	public List<MainDeviceEntity> getList() {
		return list;
	}
	public void setList(List<MainDeviceEntity> list) {
		this.list = list;
	}
	
	
	

}
