/**  
*   
* 项目名称：nxy  
* 类名称：A  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月8日 上午11:12:07  
* 修改人：jianghu  
* 修改时间：2017年8月8日 上午11:12:07  
* 修改备注： 上午11:12:07
* @version   
*   
*/ 
package com.jingu.IOT.entity;


import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.jingu.IOT.util.PublicMethod;
import com.jingu.IOT.util.ToolUtil;


public class VRALogBean {
    public VRALogBean(byte[] data){
    	System.arraycopy(data,14,DeviceIdBtye,0,DeviceIdBtye.length);
    	deviceId=PublicMethod.getFormatDeviceID(DeviceIdBtye);
    	logNum=data[18]-0x30;
    }
    public int setFileName(int index,byte[] data){
		byte[] AES=new byte[2];
		AES[0]=data[12];
		AES[1]=data[13];
		int AESLen=PublicMethod.byteToInt2(AES);
    	byte[] value=new byte[AESLen-4];
    	System.arraycopy(data,18,value,0,value.length);
    	fileName=new String(value);
    	String[] file=fileName.split(":");
    	fileName=file[0];
    	fileSize=file[1];
    	return Integer.parseInt(file[1]); 
    }
    public void setFileContent(int index,byte[] data){
		byte[] AES=new byte[2];
		AES[0]=data[12];
		AES[1]=data[13];
		int AESLen=PublicMethod.byteToInt2(AES);
    	byte[] value=new byte[AESLen-4];
    	System.arraycopy(data,18,value,0,value.length);
    	String content=new String(value);
    	fileContent=content;
    	//System.out.println(content);
    }
    public void saveLog(){
         try {
        	 //if(fileName==null)
        		//return;
     		 String logFilePath=VRALogBean.class.getResource("/").getPath();
     		 logFilePath=logFilePath.substring(0,logFilePath.indexOf("WEB-INF"));
     		 logFilePath=logFilePath+"logs/";
     		 FileOutputStream fos=new FileOutputStream(logFilePath+fileName+".log");
			 //FileOutputStream fos=new FileOutputStream(ConfigFile.logFilePath+fileName+".log");
			 PrintWriter pw=new PrintWriter(fos);
			 pw.print(fileContent);
			 pw.flush();
			 pw.close();
			  //保存日志
			 //Connection conn = Database.getConnection();
			/* Connection conn = Proxool.getConnection();
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_Log(DeviceId,fileName,fileSize,filepath)VALUES(?,?,?,?)");
	         stmt.setString(1, this.getDeviceId());
	         stmt.setString(2, this.getFileName()+".log");
	         stmt.setString(3, this.getFileSize());
	         //stmt.setString(4, ConfigFile.logFilePath);
	         stmt.setString(4, "logs/");
	         stmt.executeUpdate();
		     stmt.close();
			 conn.close();*/
			  //DBUtils.saveLog(this);
			 
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	//logNum=0;
    	fileName="";
    	fileSize="";
    	fileContent="";
		
    }
    
    public VRALogBean saveLog2(){
        try {
       	 //if(fileName==null)
       		//return;
    		/* String logFilePath=VRALogBean.class.getResource("/").getPath();
    		 logFilePath=logFilePath.substring(0,logFilePath.indexOf("WEB-INF"));
    		 logFilePath=logFilePath+"logs/";*/
        	 String logFilePath =ToolUtil.DEVICELOG;
    		 FileOutputStream fos=new FileOutputStream(logFilePath+fileName+".log");
			 //FileOutputStream fos=new FileOutputStream(ConfigFile.logFilePath+fileName+".log");
			 PrintWriter pw=new PrintWriter(fos);
			 pw.print(fileContent);
			 pw.flush();
			 pw.close();
			  //保存日志
			 //Connection conn = Database.getConnection();
			/* Connection conn = Proxool.getConnection();
			 PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_Log(DeviceId,fileName,fileSize,filepath)VALUES(?,?,?,?)");
	         stmt.setString(1, this.getDeviceId());
	         stmt.setString(2, this.getFileName()+".log");
	         stmt.setString(3, this.getFileSize());
	         //stmt.setString(4, ConfigFile.logFilePath);
	         stmt.setString(4, "logs/");
	         stmt.executeUpdate();
		     stmt.close();
			 conn.close();*/
			  //DBUtils.saveLog(this);
			 return this;
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		 }
   	//logNum=0;
   	fileName="";
   	fileSize="";
   	fileContent="";
   	return null;
   }
	private String deviceId;
	private byte[] DeviceIdBtye=new byte[4];
    private int logNum;
    private String fileName;
    private String fileSize;
    private String fileContent;
    
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public byte[] getDeviceIdBtye() {
		return DeviceIdBtye;
	}
	public void setDeviceIdBtye(byte[] deviceIdBtye) {
		DeviceIdBtye = deviceIdBtye;
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public int getLogNum() {
		return logNum;
	}
	public void setLogNum(int logNum) {
		this.logNum = logNum;
	}
	//55:09:00:00:00:00:00:00:00:00:00:00
	//55:09:00:00:00:00:00:00:00:00:00:07:00:05:10:01:00:13:32:5b
	
	//55:09:00:00:00:00:00:00:00:00:00:08:00:06:10:01:00:13:6f:6b:04	
   //55:09:00:00:00:00:00:00:00:00:00:21:00:1f:10:01:00:13:75:70:64:61:74:65:5f:73:65:72:76:65:72:5f:32:30:31:31:31:31:30:33:3a:31:35:31:33:a8
	
	//55:09:00:00:00:00:00:00:00:00:00:08:00:06:10:01:00:13:6f:6b:04
	
	//55:09:00:00:00:00:00:00:00:00:05:ef:05:ed:10:01:00:13:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:34:33:3a:32:35:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:32:33:36:2e:31:35:30:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:34:33:3a:34:32:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:34:34:2e:32:34:32:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:34:34:3a:33:36:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:32:38:2e:31:34:30:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:34:35:3a:32:36:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:37:36:2e:32:34:32:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:34:38:3a:34:34:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:34:34:2e:32:30:38:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:35:32:3a:30:35:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:32:38:2e:32:33:33:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:35:33:3a:30:32:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:39:32:2e:31:38:35:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:35:33:3a:31:36:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:32:32:30:2e:32:34:39:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:31:3a:35:39:3a:33:36:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:31:38:38:2e:32:32:38:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:30:37:3a:35:33:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:31:35:36:2e:31:35:32:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:30:3a:30:34:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:33:20:74:69:6d:65:73:20:66:69:6c:65:20:6e:75:6d:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:30:3a:30:34:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:6c:6f:67:20:66:69:6c:65:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:30:3a:30:34:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:36:2e:31:35:36:2e:31:35:32:2e:31:39:30:2c:20:70:6f:72:74:3a:36:35:35:33:35:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:32:31:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:39:32:2e:31:36:38:2e:31:2e:31:37:31:2c:20:70:6f:72:74:3a:33:38:34:30:36:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:34:38:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:33:20:74:69:6d:65:73:20:66:69:6c:65:20:6e:75:6d:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:34:38:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:6c:6f:67:20:66:69:6c:65:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:34:38:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:39:32:2e:31:36:38:2e:31:2e:31:37:31:2c:20:70:6f:72:74:3a:33:38:34:30:36:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:35:36:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:39:32:2e:31:36:38:2e:31:2e:31:37:31:2c:20:70:6f:72:74:3a:34:30:31:39:38:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:35:39:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:33:20:74:69:6d:65:73:20:66:69:6c:65:20:6e:75:6d:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:35:39:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:73:65:6e:64:20:6c:6f:67:20:66:69:6c:65:20:66:61:69:6c:65:64:0a:32:30:31:31:2d:31:31:2d:30:33:20:31:32:3a:31:32:3a:35:39:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:39:32:2e:31:36:38:2e:31:2e:31:37:31:2c:20:70:6f:72:74:3a:34:30:31:39:38:0a:32:30:31:31:2d:31:32:2d:32:32:20:31:31:3a:32:39:3a:33:30:3a:20:5b:75:70:64:61:74:65:5f:73:65:72:76:65:72:5d:20:66:72:6f:6d:20:69:70:3a:31:39:32:2e:31:36:38:2e:31:2e:31:37:31:2c:20:70:6f:72:74:3a:34:31:37:33:34:0a:43
	//55:09:00:00:00:00:00:00:00:00:00:08:00:06:10:01:00:13:6f:6b:04
	
	
	//55 09 00 00 00 00 00 00 00 00 00 00
	//55:09:00:00:00:00:00:00:00:00:00:07:00:05:10:01:00:13:32:5b
	
	//55 09 00 00 00 00 00 00 00 00 00 08 00 06 10 01 00 13 6f 6b 04
	//55 09 00 00 00 00 00 00 00 00 08 00 06 00 10 01 00 13 6f 6b 04
	
	//55:09:00:00:00:00:00:00:00:00:00:07:00:05:10:01:00:13:32:5b
	public static void main(String[] args){
		String path=VRALogBean.class.getResource("/").getPath();
		path=path.substring(0,path.indexOf("WEB-INF"));
		System.out.println(path);
		
		byte[] data=new byte[21];
		data[0]=0x55;
		data[1]=0x09;
		data[2]=0x00;
		data[3]=0x00;
		data[4]=0x00;
		data[5]=0x00;
		data[6]=0x00;
		data[7]=0x00;
		data[8]=0x00;
		data[9]=0x00;
		data[10]=0x00;
		data[11]=0x08;
		data[12]=0x00;
		data[13]=0x06;
		data[14]=0x10;
		data[15]=0x01;
		data[16]=0x00;
		data[17]=0x13;
		data[18]=0x6f;
		data[19]=0x6b;
		data[20]=0x04;
		System.out.println(data);
	}
}
