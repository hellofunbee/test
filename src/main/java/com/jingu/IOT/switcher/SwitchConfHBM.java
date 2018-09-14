package com.jingu.IOT.switcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.jingu.IOT.entity.ActionForm;
import com.jingu.IOT.util.PublicMethod;
public class SwitchConfHBM extends ActionForm implements Serializable {
	private String id;
    private String deviceId;
    private String groupId;
    private String switchId;    
    private String ctrlType;
    private String associatedGroupId;
    private String associatedSwitchId;
    private int dateInterval;
    private String levelPulse;
    private int pulseWidth;
    private boolean success;
    private Date createTime;
    private List timeCtrlList;
    private boolean newData;
    public SwitchConfHBM(){
    	deviceId="1";
    	groupId="1";
    	associatedSwitchId="1";
    	associatedGroupId="1";
    	dateInterval=1;
    	levelPulse="0";
    	pulseWidth=1;
    	newData=false;
    	timeCtrlList=new ArrayList();
    	id=UUID.randomUUID().toString();    
    }
    public byte[] toByte()
    {
    	byte[] tmpData=null;
    	byte[] bLen=new byte[2];
    	byte[] buff4B=null;
    	if(ctrlType.equals("1")||ctrlType.equals("2")){
    		tmpData=new byte[9];
    		bLen = PublicMethod.int2bytes(7);
    		System.arraycopy(bLen, 0, tmpData, 0, bLen.length);
    		tmpData[2]=(byte)Integer.parseInt(groupId);
    		tmpData[3]=(byte)Integer.parseInt(switchId);
    		tmpData[4]=(byte)Integer.parseInt(ctrlType);
    		buff4B=new byte[4];
    		int valueInt=256*Integer.parseInt(associatedGroupId)+Integer.parseInt(associatedSwitchId);
    		buff4B=PublicMethod.int4bytes(valueInt);
    		System.arraycopy(buff4B, 0, tmpData, 5, buff4B.length);
    	}
    	if(ctrlType.equals("3")){
    		tmpData=new byte[5+4+4*timeCtrlList.size()];
    		bLen = PublicMethod.int2bytes(3+4+4*timeCtrlList.size());
    		System.arraycopy(bLen, 0, tmpData, 0, bLen.length);
    		tmpData[2]=(byte)Integer.parseInt(groupId);
    		tmpData[3]=(byte)Integer.parseInt(switchId);
    		tmpData[4]=(byte)Integer.parseInt(ctrlType);
    		tmpData[5]=(byte)pulseWidth;
    		tmpData[6]=(byte)Integer.parseInt(levelPulse);
    		byte[] bDTInterval=PublicMethod.int2bytes(getDateInterval()) ;
    		System.arraycopy(bDTInterval, 0, tmpData, 7, 2);
    		SwitchConfTimeCtrlHBM swTime=null;
    		byte[] buff=null;
    		int iPos=9;
    		for(int i=0;i<timeCtrlList.size();i++){
    			swTime=(SwitchConfTimeCtrlHBM)timeCtrlList.get(i);
    			buff=swTime.toByte();
    			System.arraycopy(buff, 0, tmpData, iPos, buff.length);
    			iPos+=4;
    		}
    	}
    	if(ctrlType.equals("4")){
    		tmpData=new byte[5];
    		bLen = PublicMethod.int2bytes(7);
    		System.arraycopy(bLen, 0, tmpData, 0, bLen.length);
    		tmpData[2]=(byte)Integer.parseInt(groupId);
    		tmpData[3]=(byte)Integer.parseInt(switchId);
    		tmpData[4]=(byte)Integer.parseInt(ctrlType);    		
    	}
    	//byte[] resuletData=new byte[tmpData.length+1];
    	//resuletData[0]=0x3;
    	//System.arraycopy(tmpData, 0, resuletData, 1, tmpData.length);
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
	public String getCtrlType() {
		return ctrlType;
	}
	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}
	public String getAssociatedGroupId() {
		return associatedGroupId;
	}
	public void setAssociatedGroupId(String associatedGroupId) {
		this.associatedGroupId = associatedGroupId;
	}
	public String getAssociatedSwitchId() {
		return associatedSwitchId;
	}
	public void setAssociatedSwitchId(String associatedSwitchId) {
		this.associatedSwitchId = associatedSwitchId;
	}
	public int getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(int dateInterval) {
		this.dateInterval = dateInterval;
	}
	public String getLevelPulse() {
		return levelPulse;
	}
	public void setLevelPulse(String levelPulse) {
		this.levelPulse = levelPulse;
	}
	public int getPulseWidth() {
		return pulseWidth;
	}
	public void setPulseWidth(int pulseWidth) {
		this.pulseWidth = pulseWidth;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List getTimeCtrlList() {
		return timeCtrlList;
	}
	/**
	 * 
	 * @param timeCtrlList ����ΪSwitchConfTimeCtrlHBM
	 * @param port
	 * @return
	 */

	public void setTimeCtrlList(List timeCtrlList) {
		this.timeCtrlList = timeCtrlList;
	}
	public boolean isSameSwitch(SwitchConfHBM sw)
	{
		if(sw.getDeviceId().equals(getDeviceId())&&sw.getGroupId().equals(getGroupId())&&sw.getSwitchId().equals(getSwitchId())){
			return true;
		}
		return false;
	}
	public boolean isNewData() {
		return newData;
	}
	public void setNewData(boolean newData) {
		this.newData = newData;
	}
}
