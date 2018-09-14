package com.jingu.IOT.switcher;
import java.io.Serializable;
import java.util.Date;

import com.jingu.IOT.entity.ActionForm;
public class SwitchConfInHBM extends ActionForm implements Serializable {
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
    private Date createTime;
    private boolean success;
    public SwitchConfInHBM(){
    	groupId="-";
    	switchId1="0";
    	switchId2="0";
    	switchId3="0";
    	switchId4="0";
    	switchId5="0";
    	switchId6="0";
    	switchId7="0";
    	switchId8="0";
    }
    public byte[] toByte()
    {
    	if(groupId.equals("-")){
    	 return null;
    	}
    	int i=0;
    	int iGroup=Integer.parseInt(groupId);
    	int count=0;
    	byte[] tmp=new byte[2];
    	try{
    		tmp[0]=(byte)Integer.parseInt(groupId);
	    	tmp[1]=parseByte();
    	}
	    catch (Exception e) {
	    	//System.out.println("SwitchCtrlHBM-��"+ip);
	    	e.printStackTrace();
	    }
    	return tmp;
    }
    public byte parseByte(){
    	//����ʹ��
    	byte[] arrByte=new byte[8];
    	if(switchId1.equals("1")) arrByte[0]=0x1;
    	if(switchId2.equals("1")) arrByte[1]=0x1<<1;
    	if(switchId3.equals("1")) arrByte[2]=0x1<<2;
    	if(switchId4.equals("1")) arrByte[3]=0x1<<3;
    	if(switchId5.equals("1")) arrByte[4]=0x1<<4;
    	if(switchId6.equals("1")) arrByte[5]=0x1<<5;
    	if(switchId7.equals("1")) arrByte[6]=0x1<<6;
    	if(switchId8.equals("1")) arrByte[7]=(byte)(0x1<<7);
    	
    	byte result=(byte)(arrByte[0]|arrByte[1]|arrByte[2]|arrByte[3]|arrByte[4]|arrByte[5]|arrByte[6]|arrByte[7]);
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
		
		SwitchConfInHBM s=new SwitchConfInHBM();
		s.parseByte();
		int i;
		byte ori=0x0;
		byte b=0x1<<1;
		byte result = (byte)(ori|b);
		
		
//		for(int i=0;i<8;i++){
//			Method method=SwitchConfInHBM.class.getMethod("getSwitchId"+i, String.class);
//			method.invoke(this);
//		}

		
//		int i=8;
//		byte b=(byte)0x80;
//		byte result = (byte)(i|b);
//		String[] s = PublicMethod.getArrStrForByte(result);
		 i=0;
	}
}
