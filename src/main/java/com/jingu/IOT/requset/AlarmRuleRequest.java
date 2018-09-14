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

import java.util.List;

import com.jingu.IOT.entity.AlarmRuleEntity;

/**

* @ClassName: AlarmRuleRequest
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午3:31:35

*/
public class AlarmRuleRequest {

	private List<AlarmRuleEntity> aList;
	private String uid;
	private String sid;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
