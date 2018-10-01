package com.jingu.IOT.entity;

import com.jingu.IOT.util.PublicMethod;

import java.io.Serializable;
//薇甘菊踏查记录
public class MotorHBM extends ActionForm implements Serializable {
		
		private long id;
		private String name;
		private String title;
		private String deviceId;
		private int ctrlType;//控制类型是 卷帘1 还是电磁阀2
		private String smartCtrlType;//0手动 1智能 2预约
	 	private String raiseGroupId;//组号，或者上升组号	
	 	private String raiseSwitchId;//开关号，或者上升开关号		
	 	private String skinGroupId;	 	
	    private String skinSwitchId;	    
	    private int direction=0;//控制方向 控制运动方向，0x1：上升，0x2：下降，0x3:停止
	    private int distanceOrDuration=0;//行程1-100,时间 0关  -1 常开
	    private int posSensorCH=0; //点击位置传感器，通道号1-16
	    private String maxValue="0";
	    private String minValue="65535";
	    private String devType;
	    private String smartTempCtrl;//0 手动 1 智能
	    private String smartWaterCtrl;//0 手动 1 智能
	    private String tempCH;
	    private String waterCH;
	    private String tempCHName;
	    private String waterCHName;
	    private String paramValue="0";
	    private String videoDeviceId;
	    private String statusImg;//标识状态的图名称  全称 "on_"+[名称]/"off_" + [名称]+"
	    private String statusTitle;
		
		public String getStatusTitle() {
			return statusTitle;
		}
		public void setStatusTitle(String statusTitle) {
			this.statusTitle = statusTitle;
		}
		public String getDevType() {
			return devType;
		}
		public void setDevType(String devType) {
			this.devType = devType;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		
		public int getCtrlType() {
			return ctrlType;
		}
		public void setCtrlType(int ctrlType) {
			this.ctrlType = ctrlType;
		}
		public String getRaiseGroupId() {
			return raiseGroupId;
		}
		public void setRaiseGroupId(String raiseGroupId) {
			this.raiseGroupId = raiseGroupId;
		}
		public String getRaiseSwitchId() {
			return raiseSwitchId;
		}
		public void setRaiseSwitchId(String raiseSwitchId) {
			this.raiseSwitchId = raiseSwitchId;
		}
		public String getSkinGroupId() {
			return skinGroupId;
		}
		public void setSkinGroupId(String skinGroupId) {
			this.skinGroupId = skinGroupId;
		}
		public String getSkinSwitchId() {
			return skinSwitchId;
		}
		public int getDirection() {
			return direction;
		}
		public void setDirection(int direction) {
			this.direction = direction;
		}

		public int getDistanceOrDuration() {
			return distanceOrDuration;
		}
		public void setDistanceOrDuration(int distanceOrDuration) {
			this.distanceOrDuration = distanceOrDuration;
		}
		public void setSkinSwitchId(String skinSwitchId) {
			this.skinSwitchId = skinSwitchId;
		}

		public int getPosSensorCH() {
			return posSensorCH;
		}
		public void setPosSensorCH(int posSensorCH) {
			this.posSensorCH = posSensorCH;
		}
		public String getSmartTempCtrl() {
			return smartTempCtrl;
		}
		public void setSmartTempCtrl(String smartTempCtrl) {
//			if(smartTempCtrl!=null&&smartTempCtrl.equals("1")) this.setSmartCtrlType("1");
			this.smartTempCtrl = smartTempCtrl;
		}
	    /*public byte[] toByteCmd(){
	     byte[] b=new byte[9];
	     b[0]=(byte)this.getCtrlType();
	     if(b[0]==0x1){
	    	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		     b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
		     b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
		     b[5]=(byte)this.getDirection();
		     b[6]=(byte)this.getDistanceOrDuration();
		     b[7]=(byte)this.getPosSensorCH();
	     }
	     if(b[0]==0x2){
	    	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		     byte[] durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
		     System.arraycopy(durationByte, 0, b, 3, 4);
	     }
	     return b;
	    }*/
	    
	    public byte[] toByteCmd(){
	    	byte[] b=null;
		     if((byte)this.getCtrlType()==0x1){
		    	 b=new byte[12];
			     b[0]=(byte)this.getCtrlType();
		    	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
			     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
			     b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
			     b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
			     b[5]=(byte)this.getDirection();
			     b[6]=(byte)this.getDistanceOrDuration();
			     b[7]=(byte)this.getPosSensorCH();
			     byte[] maxB = PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
			     System.arraycopy(maxB, 0, b, 8, 2);
			     byte[] minB = PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
			     System.arraycopy(minB, 0, b, 10, 2);
		     }
		     if((byte)this.getCtrlType()==0x2){
		    	 b=new byte[9];
		    	 b[0]=(byte)this.getCtrlType();
		    	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
			     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
			     byte[] durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
			     System.arraycopy(durationByte, 0, b, 3, 4);
		     }
		     return b;
		    }
	    
		public String getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}
		public String getMinValue() {
			return minValue;
		}
		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSmartWaterCtrl() {
			return smartWaterCtrl;
		}
		public void setSmartWaterCtrl(String smartWaterCtrl) {
//			if(smartWaterCtrl!=null&&smartWaterCtrl.equals("1")) this.setSmartCtrlType("1");
			this.smartWaterCtrl = smartWaterCtrl;
		}
		public String getTempCH() {
			return tempCH;
		}
		public void setTempCH(String tempCH) {
			this.tempCH = tempCH;
		}
		public String getWaterCH() {
			return waterCH;
		}
		public void setWaterCH(String waterCH) {
			this.waterCH = waterCH;
		}
		public String getTempCHName() {
			return tempCHName;
		}
		public void setTempCHName(String tempCHName) {
			this.tempCHName = tempCHName;
		}
		public String getWaterCHName() {
			return waterCHName;
		}
		public void setWaterCHName(String waterCHName) {
			this.waterCHName = waterCHName;
		}
		public String getSmartCtrlType() {
			return smartCtrlType;
		}
		public void setSmartCtrlType(String smartCtrlType) {
			if(smartCtrlType!=null){
				if(smartCtrlType.equals("0")) {
					this.smartTempCtrl="0";
					this.smartWaterCtrl="0";
				}
				if(smartCtrlType.equals("1")) {
					this.smartTempCtrl="1";
					this.smartWaterCtrl="1";
				}
				if(!smartCtrlType.equals("0")&&!smartCtrlType.equals("1")) {
					this.smartTempCtrl="0";
					this.smartWaterCtrl="0";
				}
			}
			this.smartCtrlType = smartCtrlType;
		}
		public String getParamValue() {
			return paramValue;
		}
		public void setParamValue(String paramValue) {
			this.paramValue = paramValue;
		}
		public String getVideoDeviceId() {
			if(videoDeviceId==null||videoDeviceId.equals("")) return this.deviceId;
			return videoDeviceId;
		}
		public void setVideoDeviceId(String videoDeviceId) {
			this.videoDeviceId = videoDeviceId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStatusImg() {
			if(statusImg==null) return "";
			return statusImg;
		}
		public void setStatusImg(String statusImg) {
			this.statusImg = statusImg;
		}
	    
	    
//		public void setSurveyDateStr(String surveyDateStr) {
//			try {
//				if(surveyDateStr.trim().length()>0){
//					if(surveyDateStr.trim().length()==19)
//				        setSurveyDate(Unit.stringToDate("yyyy-MM-dd HH:mm:ss",surveyDateStr));
//					else
//						setSurveyDate(Unit.stringToDate("yyyy-MM-dd",surveyDateStr));
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}	
}
