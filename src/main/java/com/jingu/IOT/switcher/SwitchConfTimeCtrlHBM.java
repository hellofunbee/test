package com.jingu.IOT.switcher;

import java.io.Serializable;

import com.jingu.IOT.entity.ActionForm;

public class SwitchConfTimeCtrlHBM extends ActionForm implements Serializable {
	private String id;
    private String deviceId;
    private String groupId;
    private String switchId;    
    private String ctrlTime;
    private String ctrlTimeOnOff;    
    private String hour;
    private String minite;
    private String second;
    public byte[] toByte(){
    	byte[] tmpData=new byte[4];
    	tmpData[0]=(byte)Integer.parseInt(ctrlTimeOnOff);
    	tmpData[1]=(byte)Integer.parseInt(second);
    	tmpData[2]=(byte)Integer.parseInt(minite);
    	tmpData[3]=(byte)Integer.parseInt(hour);
    	return tmpData;
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSwitchId() {
		return switchId;
	}
	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}
	public String getCtrlTime() {
		return ctrlTime;
	}
	public void setCtrlTime(String ctrlTime) {
		this.ctrlTime = ctrlTime;
		String[] str = ctrlTime.split(":");
		this.hour=str[0];
		this.minite=str[1];
		this.second=str[2];
	}
	public String getCtrlTimeOnOff() {
		return ctrlTimeOnOff;
	}
	public void setCtrlTimeOnOff(String ctrlTimeOnOff) {
		this.ctrlTimeOnOff = ctrlTimeOnOff;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour=hour;
		this.ctrlTime = hour+":"+minite+":"+second;
	}
	public String getMinite() {
		return minite;
	}
	public void setMinite(String minite) {
		this.minite=minite;
		this.ctrlTime = hour+":"+minite+":"+second;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second=second;
		this.ctrlTime = hour+":"+minite+":"+second;
	}
    
}
