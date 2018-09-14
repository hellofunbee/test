/**  
*   
* 项目名称：nxy  
* 类名称：A  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月3日 下午3:13:33  
* 修改人：jianghu  
* 修改时间：2017年8月3日 下午3:13:33  
* 修改备注： 下午3:13:33
* @version   
*   
*/ 
package com.jingu.IOT.entity;


import java.util.Date;

public class MonitorHBM {
	private String id;
	private String deviceId;
	private int monitorId; // 监视点编号
	private String monitorName;
	private String beginTime;
	private String endTime;
	private int rateSecond; //采集间隔
	private int cycleDay;
	private String imgUrl;
	private Date createTime;
	private boolean success;
	private boolean edit;
	
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}
	public String getMonitorName() {
		return monitorName;
	}
	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getRateSecond() {
		return rateSecond;
	}
	public void setRateSecond(int rateSecond) {
		this.rateSecond = rateSecond;
	}
	public int getCycleDay() {
		return cycleDay;
	}
	public void setCycleDay(int cycleDay) {
		this.cycleDay = cycleDay;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
