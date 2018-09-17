/**  
*   
* 项目名称：IOT  
* 类名称：AlarmRuleRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午3:31:35  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午3:31:35  
* 修改备注： 下午3:31:35
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.AlarmRuleEntity;

import java.util.List;

/**

* @ClassName: AlarmRuleRequest
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午3:31:35

*/
public class AlarmRuleRequest {

	private List<AlarmRuleEntity> aList;
	private String ckuid;
	private String cksid;
	private Integer o_type;
	private String deviceid;
	private String idString;
	
	
	
	
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getCkuid() {
		return ckuid;
	}

	public void setCkuid(String ckuid) {
		this.ckuid = ckuid;
	}

	public String getCksid() {
		return cksid;
	}

	public void setCksid(String cksid) {
		this.cksid = cksid;
	}

	public List<AlarmRuleEntity> getaList() {
		return aList;
	}
	public void setaList(List<AlarmRuleEntity> aList) {
		this.aList = aList;
	}
	public Integer getO_type() {
		return o_type;
	}
	public void setO_type(Integer o_type) {
		this.o_type = o_type;
	}
	
	
	
}
