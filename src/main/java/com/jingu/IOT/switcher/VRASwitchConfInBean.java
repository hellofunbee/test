package com.jingu.IOT.switcher;
///负责解析和生成switch字节数组
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jingu.IOT.entity.ActionForm;
import com.jingu.IOT.util.PublicMethod;

public class VRASwitchConfInBean extends ActionForm implements Serializable {
	private String deviceId;
	private List groupList=new ArrayList();

	public VRASwitchConfInBean(){		
	}
	public VRASwitchConfInBean(String deviceId,List groupList){		
		setDeviceId(deviceId);
		//setCtrlType(ctrlType);
		setGroupList(groupList);
	}

	public boolean PaserData(byte[] recData,String deviceId){	
		byte[] disposeData=new byte[recData.length-1];
		System.arraycopy(recData, 1, disposeData, 0, disposeData.length);
		
		byte bGroupId;
		byte nStatus;
		byte bCtrlType;
		bCtrlType=recData[0];
//		setCtrlType(Integer.toString(bCtrlType));
		setDeviceId(deviceId);
		SwitchConfInHBM switchCtrlHBM;
		String sStatus;
		String[] asStatus;
		try
		{
			groupList=new ArrayList();
			for(int i=1;i<recData.length;){				
				bGroupId=recData[i++];
				nStatus=recData[i++];
				switchCtrlHBM=new SwitchConfInHBM();
				switchCtrlHBM.setGroupId(Integer.toString(bGroupId));
				asStatus=PublicMethod.getArrStrForByte(nStatus);
				switchCtrlHBM.setSwitchId1(asStatus[7]);
				switchCtrlHBM.setSwitchId2(asStatus[6]);
				switchCtrlHBM.setSwitchId3(asStatus[5]);
				switchCtrlHBM.setSwitchId4(asStatus[4]);
				switchCtrlHBM.setSwitchId5(asStatus[3]);
				switchCtrlHBM.setSwitchId6(asStatus[2]);
				switchCtrlHBM.setSwitchId7(asStatus[1]);
				switchCtrlHBM.setSwitchId8(asStatus[0]);
				groupList.add(switchCtrlHBM);
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;			
			}
		//System.out.println("");
	}
	public byte[] ToByte()
	{
		SwitchConfInHBM switchCtrlHBM; 
		int groupCount = groupList.size();
		byte[] tmp;
		int dataLen=0;
		byte[] resultByte=null;
		byte[] buffByte=new byte[24*2];
		try{
			for(int i=0;i<groupCount;i++){
				switchCtrlHBM=(SwitchConfInHBM)groupList.get(i);
				tmp=switchCtrlHBM.toByte();		
				if(tmp.length<2) continue;
				System.arraycopy(tmp, 0, buffByte, dataLen, tmp.length);
				dataLen+=tmp.length;
			}
			resultByte=new byte[dataLen];
			System.arraycopy(buffByte, 0, resultByte, 0, dataLen);
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
//	public VRASwitchBean(byte[] recData,String deviceId,String ctrlType){	
//		byte[] disposeData=new byte[recData.length-1];
//		System.arraycopy(recData, 1, disposeData, 0, disposeData.length);
//		
//		byte bGroupId;
//		byte nStatus;
//		byte bCtrlType;
//		bCtrlType=recData[0];
//		setCtrlType(Integer.toString(bCtrlType));
//		setDeviceId(deviceId);
//		setCtrlType(ctrlType);
//		SwitchCtrlHBM switchCtrlHBM;
//		String sStatus;
//		String[] asStatus;
//		try
//		{
//			groupList=new ArrayList();
//			for(int i=0;i<recData.length;){				
//				bGroupId=recData[i++];
//				nStatus=recData[i++];
//				switchCtrlHBM=new SwitchCtrlHBM();
//				switchCtrlHBM.setGroupId(Integer.toString(bGroupId));
//				asStatus=PublicMethod.getArrStrForByte(nStatus);
//				switchCtrlHBM.setSwitchId1(asStatus[0]);
//				switchCtrlHBM.setSwitchId2(asStatus[1]);
//				switchCtrlHBM.setSwitchId3(asStatus[2]);
//				switchCtrlHBM.setSwitchId4(asStatus[3]);
//				switchCtrlHBM.setSwitchId5(asStatus[4]);
//				switchCtrlHBM.setSwitchId6(asStatus[5]);
//				switchCtrlHBM.setSwitchId7(asStatus[6]);
//				switchCtrlHBM.setSwitchId8(asStatus[7]);
//				groupList.add(switchCtrlHBM);
//			}
//		}
//		catch(Exception e){e.printStackTrace();}
//		//System.out.println("");
//	}
}
