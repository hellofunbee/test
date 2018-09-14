package com.jingu.IOT.switcher;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.jingu.IOT.entity.ActionForm;
import com.jingu.IOT.util.PublicMethod;;
public class VRASwitchConfBean extends ActionForm implements Serializable {
	private String deviceId;
	private List groupList=new ArrayList();//每个开关量对象
	private String CtrlType;
	public VRASwitchConfBean(){		
	}
	/**
	 * Data<数据长度><开关量组号><开关量编号><控制方式>
	 * @param ConfData
	 * @param deviceId
	 * @return
	 */
	public VRASwitchConfBean(byte[] recConfData,String deviceId){	
		setCtrlType(Integer.toString((byte)recConfData[0]));
		byte[] ConfData=new byte[recConfData.length-1];
		System.arraycopy(recConfData, 1, ConfData, 0, recConfData.length-1);
		setDeviceId(deviceId);
		int iPos=0;
		byte[] tmpData=null;
		byte[] bLen=null;
		int len=0;
		try
		{
			while(iPos<ConfData.length){				
				bLen=new byte[2];			
				System.arraycopy(ConfData, iPos, bLen, 0, 2);
				len = PublicMethod.byteToInt2(bLen);				
				iPos+=2;//数据指针后移2位	
				//将数据拷贝出到tmp
				tmpData=new byte[len];
				System.arraycopy(ConfData, iPos, tmpData, 0, len);
				
				switch(tmpData[2]){// C
				case 0x1 : groupList.add(ParseAssociate(tmpData)); iPos+=len;continue;
				case 0x2 : groupList.add(ParseAssociate(tmpData)); iPos+=len;continue;
				case 0x3 : groupList.add(ParseTime(tmpData)); iPos+=len;continue;
				case 0x4 : groupList.add(ParseForbid(tmpData)); iPos+=len;continue;
				}		
				
//				if(tmpData[2]==0x1||tmpData[2]==0x2){
//					groupList.add(ParseAssociate(tmpData));
//					iPos+=len;
//					continue;
//				}
//				if(ConfData[2]==0x3){
//					groupList.add(ParseTime(tmpData));
//					iPos+=len;
//					continue;
//				}
//				if(ConfData[2]==0x4){
//					 ParseForbid(tmpData);
//					iPos+=len;
//					continue;
//				}
			}
		}
		catch(Exception e){e.printStackTrace();}
		//System.out.println("");
	}
	public SwitchConfHBM ParseAssociate(byte[] dataBlock)
	{
		SwitchConfHBM scHBM=new SwitchConfHBM();
		try
		{
			byte groupId=dataBlock[0];
			byte switchId=dataBlock[1];
			byte ctrlType=dataBlock[2];	
			scHBM.setDeviceId(deviceId);
			scHBM.setGroupId(String.valueOf(groupId));
			scHBM.setSwitchId(String.valueOf(switchId));
			scHBM.setCtrlType(String.valueOf(ctrlType));
			byte[] associateValue=new byte[4];
			System.arraycopy(dataBlock, 3, associateValue, 0, associateValue.length);
			BigInteger value = new BigInteger(associateValue);
			int intValue = value.intValue();
			int associateGroupId=intValue/256;
			int associateSwitchId=intValue%256;
			scHBM.setAssociatedGroupId(String.valueOf(associateGroupId));
			scHBM.setAssociatedSwitchId(String.valueOf(associateSwitchId));
		}
		catch(Exception e){e.printStackTrace();}
		return scHBM;
	}
	public SwitchConfHBM ParseTime(byte[] dataBlock)
	{
		int iPos=0;
		List timeCtrlList=new ArrayList();
		SwitchConfHBM scHBM=new SwitchConfHBM();
		SwitchConfTimeCtrlHBM swTimeCtrl=null;
		try
		{
			byte[] bDateInterval=new byte[2];
			byte groupId=dataBlock[0];
			byte switchId=dataBlock[1];
			byte ctrlType=dataBlock[2];			
			byte width=dataBlock[3];
			byte level=dataBlock[4];
			System.arraycopy(dataBlock, 5, bDateInterval, 0, 2);
			
			scHBM.setDeviceId(deviceId);
			scHBM.setGroupId(String.valueOf(groupId));
			scHBM.setSwitchId(String.valueOf(switchId));
			scHBM.setCtrlType(String.valueOf(ctrlType));
			scHBM.setDateInterval(PublicMethod.byteToInt2(bDateInterval));
			scHBM.setLevelPulse(String.valueOf(level));
			scHBM.setPulseWidth(width);
			iPos+=7;
			while(iPos<dataBlock.length){
				swTimeCtrl=new SwitchConfTimeCtrlHBM();
				swTimeCtrl.setDeviceId(deviceId);
				swTimeCtrl.setGroupId(String.valueOf(groupId));
				swTimeCtrl.setSwitchId(String.valueOf(switchId));
				swTimeCtrl.setCtrlTimeOnOff(String.valueOf(dataBlock[iPos]));
				swTimeCtrl.setSecond(String.valueOf(dataBlock[iPos+1]));
				swTimeCtrl.setMinite(String.valueOf(dataBlock[iPos+2]));
				swTimeCtrl.setHour(String.valueOf(dataBlock[iPos+3]));
				timeCtrlList.add(swTimeCtrl);
				iPos+=4;
			}
			scHBM.setTimeCtrlList(timeCtrlList);
		}
		catch(Exception e){e.printStackTrace();}
		return scHBM;
	}
	public SwitchConfHBM ParseForbid(byte[] dataBlock){
		SwitchConfHBM scHBM=new SwitchConfHBM();
		try
		{
			byte groupId=dataBlock[0];
			byte switchId=dataBlock[1];
			byte ctrlType=dataBlock[2];	
			scHBM.setDeviceId(deviceId);
			scHBM.setGroupId(String.valueOf(groupId));
			scHBM.setSwitchId(String.valueOf(switchId));
			scHBM.setCtrlType(String.valueOf(ctrlType));
		}
		catch(Exception e){e.printStackTrace();}
		return scHBM;
	}
	public byte[] toByte(List myList)
	{
		setGroupList(myList);
		SwitchConfHBM switchConfHBM; 
		int groupCount = groupList.size();
		
		byte[] tmp;
		int dataLen=0;
		byte[] resultByte=null;
		byte[] buffByte=null;
		try{
			for(int i=0;i<groupCount;i++){
				switchConfHBM=(SwitchConfHBM)groupList.get(i);
				tmp=switchConfHBM.toByte();						
				resultByte=new byte[dataLen+tmp.length];
				System.arraycopy(buffByte, 0, resultByte, 0, dataLen);//已经写入的byte数组 写入新数组
				System.arraycopy(tmp, 0, resultByte, dataLen, tmp.length);//新获取的byte数组 写入新数组
				buffByte=new byte[dataLen+tmp.length];
				System.arraycopy(resultByte, 0, buffByte, dataLen, buffByte.length);
				dataLen+=tmp.length;
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return resultByte;
	}
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}
	public String getCtrlType() {
		return CtrlType;
	}
	public void setCtrlType(String ctrlType) {
		CtrlType = ctrlType;
	}
}
