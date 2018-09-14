package com.jingu.IOT.switcher;

import java.io.Serializable;
import java.util.Date;

import com.jingu.IOT.entity.ActionForm;
import com.jingu.IOT.util.PublicMethod;
public class SwitchCtrlHBM extends ActionForm implements Serializable {
	//���뿪��������
	private String id;
    private String deviceId;
    private String groupId;
    private String switchId1;    
    private String switchId2;
    private String switchId3;
    private String switchId4;
    private String switchId5;
    private String switchId6;
    private String switchId7;
    private String switchId8;   
    private boolean sw1;
    private boolean sw2;
    private boolean sw3;
    private boolean sw4;
    private boolean sw5;
    private boolean sw6;
    private boolean sw7;
    private boolean sw8;
    private Date createTime;
    private boolean success;
    public SwitchCtrlHBM(){
    	groupId="-";
    	switchId1="-";
    	switchId2="-";
    	switchId3="-";
    	switchId4="-";
    	switchId5="-";
    	switchId6="-";
    	switchId7="-";
    	switchId8="-";
    	sw1=sw2=sw3=sw4=sw5=sw6=sw7=sw8=true;
    }
    public SwitchCtrlHBM(boolean isTrue){
    	groupId="1";
    	switchId1="0";
    	switchId2="0";
    	switchId3="0";
    	switchId4="0";
    	switchId5="0";
    	switchId6="0";
    	switchId7="0";
    	switchId8="0";
    	sw1=sw2=sw3=sw4=sw5=sw6=sw7=sw8=isTrue;
    }
    public byte[] toByte()
    {
    	if(groupId.equals("-")){
    	 return null;
    	}
    	int i=0;
    	int iGroup=Integer.parseInt(groupId);
    	int count=0;
    	byte[] tmp=new byte[16];
    	try{
	    	if(!switchId1.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(1,switchId1);count+=2;	    		
	    	}
	    	i+=2;	 
	    	if(!switchId2.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(2,switchId2);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId3.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(3,switchId3);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId4.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(4,switchId4);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId5.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(5,switchId5);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId6.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(6,switchId6);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId7.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(7,switchId7);count+=2;
	    	}
	    	i+=2;
	    	if(!switchId8.equals("-")){
	    		tmp[count]=(byte)iGroup;
	    		tmp[count+1]=parseByte(8,switchId8);count+=2;
	    	}
	    	byte[] resultB=new byte[count];
	    	System.arraycopy(tmp, 0, resultB, 0, resultB.length);
	    	return resultB;
    	}
	    catch (Exception e) {
	    	//System.out.println("SwitchCtrlHBM-��"+ip);
	    	e.printStackTrace();
	    }
    	return null;
    }
    public byte parseByte(int idx,String switchId){
    	//����ʹ��
    	byte result=0;
    	try{
	    	byte bMask=(byte)0x00;
	    	if(switchId.equals("1"))
	    		bMask=(byte)0x80;
			result = (byte)(idx|bMask);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
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
	public String getSwitchId1() {
		return switchId1;
	}
	public void setSwitchId1(String switchId1) {
		this.switchId1 = switchId1;
	}
	public String getSwitchId2() {
		return switchId2;
	}
	public void setSwitchId2(String switchId2) {
		this.switchId2 = switchId2;
	}
	public String getSwitchId3() {
		return switchId3;
	}
	public void setSwitchId3(String switchId3) {
		this.switchId3 = switchId3;
	}
	public String getSwitchId4() {
		return switchId4;
	}
	public void setSwitchId4(String switchId4) {
		this.switchId4 = switchId4;
	}
	public String getSwitchId5() {
		return switchId5;
	}
	public void setSwitchId5(String switchId5) {
		this.switchId5 = switchId5;
	}
	public String getSwitchId6() {
		return switchId6;
	}
	public void setSwitchId6(String switchId6) {
		this.switchId6 = switchId6;
	}
	public String getSwitchId7() {
		return switchId7;
	}
	public void setSwitchId7(String switchId7) {
		this.switchId7 = switchId7;
	}
	public String getSwitchId8() {
		return switchId8;
	}
	public void setSwitchId8(String switchId8) {
		this.switchId8 = switchId8;
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
	
	
	public static void main(String[] args) throws Exception {
		int i=8;
		byte b=(byte)0x80;
		byte result = (byte)(i|b);
		String[] s = PublicMethod.getArrStrForByte(result);
		i=i;
	}
	public boolean isSw1() {
		return sw1;
	}
	public void setSw1(boolean sw1) {
		this.sw1 = sw1;
	}
	public boolean isSw2() {
		return sw2;
	}
	public void setSw2(boolean sw2) {
		this.sw2 = sw2;
	}
	public boolean isSw3() {
		return sw3;
	}
	public void setSw3(boolean sw3) {
		this.sw3 = sw3;
	}
	public boolean isSw4() {
		return sw4;
	}
	public void setSw4(boolean sw4) {
		this.sw4 = sw4;
	}
	public boolean isSw5() {
		return sw5;
	}
	public void setSw5(boolean sw5) {
		this.sw5 = sw5;
	}
	public boolean isSw6() {
		return sw6;
	}
	public void setSw6(boolean sw6) {
		this.sw6 = sw6;
	}
	public boolean isSw7() {
		return sw7;
	}
	public void setSw7(boolean sw7) {
		this.sw7 = sw7;
	}
	public boolean isSw8() {
		return sw8;
	}
	public void setSw8(boolean sw8) {
		this.sw8 = sw8;
	}
}
