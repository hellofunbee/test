/*package com.jingu.IOT.switcher;

import java.io.Serializable;
import java.util.Date;

import com.jingu.IOT.entity.ActionForm;
import com.jingu.IOT.util.PublicMethod;
public class SwitchStatusHBM extends ActionForm implements Serializable {
	//���뿪��������
	private int id;
    private String deviceId;
    private Date infoDataTime;
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
	public Date getInfoDataTime() {
		return infoDataTime;
	}
	public void setInfoDataTime(Date modifyTime) {
		this.infoDataTime = infoDataTime;
	}
	
	public static void main(String[] args) throws Exception {
		int i=8;
		byte b=(byte)0x80;
		byte result = (byte)(i|b);
		String[] s = PublicMethod.getArrStrForByte(result);
		i=i;
	}

//  public SwitchStatusHBM(){
//	groupId="-";
//	switchId1="-";
//	switchId2="-";
//	switchId3="-";
//	switchId4="-";
//	switchId5="-";
//	switchId6="-";
//	switchId7="-";
//	switchId8="-";
//}
//public byte[] toByte()
//{
//	if(groupId.equals("-")){
//	 return null;
//	}
//	int i=0;
//	int iGroup=Integer.parseInt(groupId);
//	int count=0;
//	byte[] tmp=new byte[16];
//	try{
//    	if(!switchId1.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(1,switchId1);count+=2;	    		
//    	}
//    	i+=2;	 
//    	if(!switchId2.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(2,switchId2);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId3.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(3,switchId3);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId4.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(4,switchId4);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId5.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(5,switchId5);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId6.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(6,switchId6);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId7.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(7,switchId7);count+=2;
//    	}
//    	i+=2;
//    	if(!switchId8.equals("-")){
//    		tmp[i]=(byte)iGroup;
//    		tmp[i+1]=parseByte(8,switchId8);count+=2;
//    	}
//    	byte[] resultB=new byte[count];
//    	System.arraycopy(tmp, 0, resultB, 0, resultB.length);
//    	return resultB;
//	}
//    catch (Exception e) {
//    	//System.out.println("SwitchCtrlHBM-��"+ip);
//    	e.printStackTrace();
//    }
//	return null;
//}
//public byte parseByte(int idx,String switchId){
//	//����ʹ��
//	byte result=0;
//	try{
//    	byte bMask=(byte)0x00;
//    	if(switchId.equals("1"))
//    		bMask=(byte)0x80;
//		result = (byte)(idx|bMask);
//	}
//	catch(Exception e){
//		e.printStackTrace();
//	}
//	return result;
//}
}
*/