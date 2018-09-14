/**  
*   
* 项目名称：IOT  
* 类名称：IPCPointEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 下午2:54:43  
* 修改人：jianghu  
* 修改时间：2017年9月5日 下午2:54:43  
* 修改备注： 下午2:54:43
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: IPCPointEntity
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 下午2:54:43

*/
public class IPCPointEntity {

	private int id;
	private String deviceId;
	private int monitorId;
	private String monitorName;
	private String beginTime ;
	private String endTime ;
	private int rateSecond ;
	private int cycleDay ;
	private String imgUrl="";
	private String createTime;
	private int success;
	private int start;
	private int pagesize =10;
	private String ip;
	private int port;
	private int app;
	private String apptime;
	
	
	
	
	

	public String getApptime() {
		return apptime;
	}
	public void setApptime(String apptime) {
		this.apptime = apptime;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		
		this.port = port;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPagesize() {
		return pagesize;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		if (null == deviceId || deviceId.trim().length() == 0) {
			this.deviceId = "";
		}
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
		if (null == monitorName || monitorName.trim().length() == 0) {
			this.monitorName = "";
		}
		this.monitorName = monitorName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		if (null == beginTime || beginTime.trim().length() == 0) {
			this.beginTime = "";
		}
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		if (null == endTime || endTime.trim().length() == 0) {
			this.endTime = "";
		}
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
		if (null == imgUrl || imgUrl.trim().length() == 0) {
			this.imgUrl = "";
		}
		this.imgUrl = imgUrl;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		if (null == createTime || createTime.trim().length() == 0) {
			this.createTime = "";
		}
		this.createTime = createTime;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public IPCPointEntity(int id, String deviceId, int monitorId, String monitorName, String beginTime, String endTime,
			int rateSecond, int cycleDay, String imgUrl, String createTime, int success) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.monitorId = monitorId;
		this.monitorName = monitorName;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.rateSecond = rateSecond;
		this.cycleDay = cycleDay;
		this.imgUrl = imgUrl;
		this.createTime = createTime;
		this.success = success;
	}
	public IPCPointEntity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "IPCPointEntity [id=" + id + ", deviceId=" + deviceId + ", monitorId=" + monitorId + ", monitorName="
				+ monitorName + ", beginTime=" + beginTime + ", endTime=" + endTime + ", rateSecond=" + rateSecond
				+ ", cycleDay=" + cycleDay + ", imgUrl=" + imgUrl + ", createTime=" + createTime + ", success="
				+ success + "]";
	}
	
	
	
}
