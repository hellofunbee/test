package com.jingu.IOT.entity;



import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

public class HkSdkEx {
	
	private static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
//	private static NET_DVR_MULTI_STREAM_COMPRESSIONCFG_COND_4 m_struMultiStreamCompressionCond = new NET_DVR_MULTI_STREAM_COMPRESSIONCFG_COND_4();
//	private static NET_DVR_MULTI_STREAM_COMPRESSIONCFG_4 m_struMultiStreamCompressionCfg = new NET_DVR_MULTI_STREAM_COMPRESSIONCFG_4();
//	private static STATUS_LIST_4 m_struStatusList = new STATUS_LIST_4();
//	private final Logger logger =LoggerFactory.getLogger(CtrlController.class);

	public static VideoShemaBean getIpcAbility(String uName,String pwd,String sip,String sport){
		VideoShemaBean shema=null;
		String ip=sip;
		int port = 8000;
		try{port = Integer.parseInt(sport);}catch(Exception ex){}
		try{
			NativeLong lUserID = new NativeLong(-1);//用户句柄
			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
			//初始化SDK
			System.out.println("初始化获取能力集");
			boolean initSuc = hCNetSDK.NET_DVR_Init();
	        if (initSuc != true)
	        {
	            System.out.println("初始化失败");
	            System.out.print("faild");
	            return null;
	        }		
	        System.out.println("校验登录状态");
	        //如果已经登录先注销（应该不会执行）
	        if (lUserID.longValue() > -1){
	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
	        	lUserID = new NativeLong(-1);
	        }
	        //初始化设备信息
	        System.out.println("初始化设备信息");
	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	        //登录球机
	        System.out.println("登录摄像机");
        	System.out.println(ip);
        	System.out.println(port);
        	System.out.println(uName);
        	System.out.println(pwd);
	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
	        
	        long userID = lUserID.longValue();
	        if (userID == -1)
	        {
	        	System.out.println("登录球机失败");
	        	System.out.print("faild");
	            return null;
	        }
	        else
	        {
	        	System.out.println("获取能力集");
	        	String  xmlInput = "<?xml version=\"1.0\" encoding=\"utf-8\"?><AudioVideoCompressInfo><AudioChannelNumber>1</AudioChannelNumber><VoiceTalkChannelNumber>1</VoiceTalkChannelNumber><VideoChannelNumber>1</VideoChannelNumber></AudioVideoCompressInfo>";
	        	int XML_ABILITY_OUT_LEN = 3 * 1024 * 1024;
	        	Pointer memory = new Memory(XML_ABILITY_OUT_LEN);
	        	System.out.println(memory.toString());
	        	ByteByReference byteRef=new ByteByReference();
	        	System.out.println("这里...");
	        	System.out.println(xmlInput);
	        	System.out.println("用户句柄:"+lUserID);
	        	boolean net_DVR_GetDeviceAbility = hCNetSDK.NET_DVR_GetDeviceAbility(lUserID, hCNetSDK.DEVICE_ENCODE_ALL_ABILITY_V20 , xmlInput, xmlInput.getBytes().length, memory, XML_ABILITY_OUT_LEN);//ok
	        	int iErr = hCNetSDK.NET_DVR_GetLastError();
	        	System.out.print(iErr);
	        	System.out.println("---");
	        	hCNetSDK.NET_DVR_Logout(lUserID);
	        	hCNetSDK.NET_DVR_Cleanup();
	        	System.out.println(memory.getString(0));
//	        	hCNetSDK.NET_DVR_GetDeviceAbility(lUserID, hCNetSDK.DEVICE_ENCODE_ALL_ABILITY_V20 , xmlInput, xmlInput.getBytes().length, byteRef, XML_ABILITY_OUT_LEN);
	        	String xmlOutPut=memory.getString(0);
	        	System.out.println(xmlOutPut);
//	        	InputStream is = new ByteArrayInputStream(xmlOutPut.getBytes());
	        	shema=new VideoShemaBean();
	        	shema.parse(xmlOutPut);
	        	
	        }
		}catch(Exception e){
			System.out.println("获取能力集出错...");
			System.out.println(hCNetSDK.NET_DVR_GetLastError());
		}
		return shema;
	}
	public static HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 getCompressInfo(String uName,String pwd,String sip,String sport){
		HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg=null;
		String ip=sip;
		int port = 8000;
		try{port = Integer.parseInt(sport);}catch(Exception ex){}
		try{
			NativeLong lUserID = new NativeLong(-1);//用户句柄
			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
			//初始化SDK
			System.out.println("初始化获取视频压缩参数");
			boolean initSuc = hCNetSDK.NET_DVR_Init();
	        if (initSuc != true)
	        {
	            System.out.println("初始化失败");
	            System.out.print("faild");
	            return null;
	        }		
	        System.out.println("校验登录状态");
	        //如果已经登录先注销（应该不会执行）
	        if (lUserID.longValue() > -1){
	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
	        	lUserID = new NativeLong(-1);
	        }
	        //初始化设备信息
	        System.out.println("初始化设备信息");
	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	        //登录球机
	        System.out.println("登录摄像机");
	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
	        
	        long userID = lUserID.longValue();
	        if (userID == -1)
	        {
	        	System.out.println("登录球机失败");
	        	System.out.print("faild");
	        	return null;
	        }
	        else
	        {
	        	IntByReference lpBytesReturned = new IntByReference(0);
	        	m_struCompressionCfg=new HCNetSDK.NET_DVR_COMPRESSIONCFG_V30();
	        	m_struCompressionCfg.write();
	        	Pointer compressPoint = m_struCompressionCfg.getPointer();
	        	hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_COMPRESSCFG_V30, new NativeLong(1), compressPoint, m_struCompressionCfg.size(), lpBytesReturned);
	        	m_struCompressionCfg.read();

//	        	System.out.println("byVideoEncType:" + m_struCompressionCfg.struNormHighRecordPara.byVideoEncType);
	        	hCNetSDK.NET_DVR_Logout(lUserID);
	        	hCNetSDK.NET_DVR_Cleanup();
	        }
		}catch(Exception e){
			System.out.println("获取视频压缩参数出错...");
			System.out.println(hCNetSDK.NET_DVR_GetLastError());
			return null;
		}   	
		return m_struCompressionCfg;
	}
	public static boolean setCompressInfo(String uName,String pwd,String sip,String sport,HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		boolean isOk=false;
		String ip=sip;
		int port = 8000;
		try{port = Integer.parseInt(sport);}catch(Exception ex){}
		try{
			NativeLong lUserID = new NativeLong(-1);//用户句柄
			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
			//初始化SDK
			System.out.println("初始化获取视频压缩参数");
			boolean initSuc = hCNetSDK.NET_DVR_Init();
	        if (initSuc != true)
	        {
	            System.out.println("初始化失败");
	            System.out.print("faild");
	            return isOk;
	        }		
	        System.out.println("校验登录状态");
	        //如果已经登录先注销（应该不会执行）
	        if (lUserID.longValue() > -1){
	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
	        	lUserID = new NativeLong(-1);
	        }
	        //初始化设备信息
	        System.out.println("初始化设备信息");
	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	        //登录球机
	        System.out.println("登录摄像机");
	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
	        
	        long userID = lUserID.longValue();
	        if (userID == -1)
	        {
	        	System.out.println("登录球机失败");
	        	System.out.print("faild");
	        	return isOk;
	        }
	        else
	        {
	        	
	        	IntByReference lpBytesReturned = new IntByReference(0);	        	
	        	m_struCompressionCfg.write();
	        	Pointer compressPoint = m_struCompressionCfg.getPointer();
	        	isOk = hCNetSDK.NET_DVR_SetDVRConfig(lUserID,  HCNetSDK.NET_DVR_SET_COMPRESSCFG_V30, new NativeLong(1), compressPoint, m_struCompressionCfg.size());
	        	m_struCompressionCfg.read();
	        	
	        	hCNetSDK.NET_DVR_Logout(lUserID);
	        	hCNetSDK.NET_DVR_Cleanup();
	        }
		}catch(Exception e){
			System.out.println("配置视频压缩参数出错...");
			System.out.println(hCNetSDK.NET_DVR_GetLastError());
			return false;
		}
		return isOk;
     
	}
	
	public static boolean setSetDateTime(String uName,String pwd,String sip,String sport,HCNetSDK.NET_DVR_TIME m_struTime){
		boolean isOk=false;
		String ip=sip;
		int port = 8000;
		try{port = Integer.parseInt(sport);}catch(Exception ex){}
		try{
			NativeLong lUserID = new NativeLong(-1);//用户句柄
			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
			//初始化SDK
			System.out.println("初始化获取视频压缩参数");
			boolean initSuc = hCNetSDK.NET_DVR_Init();
	        if (initSuc != true)
	        {
	            System.out.println("初始化失败");
	            System.out.print("faild");
	            return isOk;
	        }		
	        System.out.println("校验登录状态");
	        //如果已经登录先注销（应该不会执行）
	        if (lUserID.longValue() > -1){
	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
	        	lUserID = new NativeLong(-1);
	        }
	        //初始化设备信息
	        System.out.println("初始化设备信息");
	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	        //登录球机
	        System.out.println("登录摄像机");
	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
	        
	        long userID = lUserID.longValue();
	        if (userID == -1)
	        {
	        	System.out.println("登录球机失败");
	        	System.out.print("faild");
	        	return isOk;
	        }
	        else
	        {
	        	
	        	IntByReference lpBytesReturned = new IntByReference(0);	        	
	        	m_struTime.write();
	        	Pointer compressPoint = m_struTime.getPointer();
//	        	isOk = hCNetSDK.NET_DVR_SetDVRConfig(lUserID,  HCNetSDK.NET_DVR_SET_COMPRESSCFG_V30, new NativeLong(1), compressPoint, m_struCompressionCfg.size());
	        	
	        	isOk = hCNetSDK.NET_DVR_SetDVRConfig(lUserID,  HCNetSDK.NET_DVR_SET_TIMECFG, new NativeLong(1), compressPoint, m_struTime.size());
	        	
	        	m_struTime.read();
	        	
	        	hCNetSDK.NET_DVR_Logout(lUserID);
	        	hCNetSDK.NET_DVR_Cleanup();
	        }
		}catch(Exception e){
			System.out.println("校时出错...");
			System.out.println(hCNetSDK.NET_DVR_GetLastError());
			return false;
		}
		return isOk;
	}
	
	
	public static boolean setIPC(String uName,String pwd,String sip,String sport,HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		boolean isOk=false;
		String ip=sip;
		int port = 8000;
		try{port = Integer.parseInt(sport);}catch(Exception ex){}
		try{
			NativeLong lUserID = new NativeLong(-1);//用户句柄
			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
			//初始化SDK
			System.out.println("初始化获取视频压缩参数");
			boolean initSuc = hCNetSDK.NET_DVR_Init();
	        if (initSuc != true)
	        {
	            System.out.println("初始化失败");
	            System.out.print("faild");
	            return isOk;
	        }		
	        System.out.println("校验登录状态");
	        //如果已经登录先注销（应该不会执行）
	        if (lUserID.longValue() > -1){
	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
	        	lUserID = new NativeLong(-1);
	        }
	        //初始化设备信息
	        System.out.println("初始化设备信息");
	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
	        //登录球机
	        System.out.println("登录摄像机");
	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
	        
	        long userID = lUserID.longValue();
	        if (userID == -1)
	        {
	        	System.out.println("登录球机失败");
	        	System.out.print("faild");
	        	return isOk;
	        }
	        else
	        {
	        	
	        	IntByReference lpBytesReturned = new IntByReference(0);	        	
	        	m_struCompressionCfg.write();
	        	Pointer compressPoint = m_struCompressionCfg.getPointer();
	        	isOk = hCNetSDK.NET_DVR_SetDVRConfig(lUserID,  HCNetSDK.NET_DVR_SET_COMPRESSCFG_V30, new NativeLong(1), compressPoint, m_struCompressionCfg.size());
	        	m_struCompressionCfg.read();
	        	
	        	hCNetSDK.NET_DVR_Logout(lUserID);
	        	hCNetSDK.NET_DVR_Cleanup();
	        }
		}catch(Exception e){
			System.out.println("配置视频压缩参数出错...");
			System.out.println(hCNetSDK.NET_DVR_GetLastError());
			return false;
		}
		return isOk;
     
	}
//	public static VideoShemaBean getIpcAbility2(String uName,String pwd,String sip,String sport){
//		VideoShemaBean shema=null;
//		String ip=sip;
//		int port = 8000;
//		try{port = Integer.parseInt(sport);}catch(Exception ex){}
//		try{
//			NativeLong lUserID = new NativeLong(-1);//用户句柄
//			HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
//			//初始化SDK
//			System.out.println("初始化获取能力集");
//			boolean initSuc = hCNetSDK.NET_DVR_Init();
//	        if (initSuc != true)
//	        {
//	            System.out.println("初始化失败");
//	            System.out.print("faild");
//	            return null;
//	        }		
//	        System.out.println("校验登录状态");
//	        //如果已经登录先注销（应该不会执行）
//	        if (lUserID.longValue() > -1){
//	        	hCNetSDK.NET_DVR_Logout_V30(lUserID);
//	        	lUserID = new NativeLong(-1);
//	        }
//	        //初始化设备信息
//	        System.out.println("初始化设备信息");
//	        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
//	        //登录球机
//	        System.out.println("登录摄像机");
//	        lUserID = hCNetSDK.NET_DVR_Login_V30(ip,(short) port, uName , pwd , m_strDeviceInfo);
//	        long userID = lUserID.longValue();
//	        if (userID == -1)
//	        {
//	        	System.out.println("登录球机失败");
//	        	System.out.print("faild");
//	            return null;
//	        }
//	        
//			Pointer lpMultiStreamCompressionCond = m_struMultiStreamCompressionCond.getPointer();
//			Pointer lpMultiStreamCompressionCfg = m_struMultiStreamCompressionCfg.getPointer();
//			Pointer lpStatusList = m_struStatusList.getPointer();
////			
//			m_struMultiStreamCompressionCond.write();
//			m_struMultiStreamCompressionCfg.write();
//			m_struStatusList.write();
//			if (false == hCNetSDK.NET_DVR_GetDeviceConfig(lUserID, HCNetSDK.NET_DVR_GET_MULTI_STREAM_COMPRESSIONCFG, 4, lpMultiStreamCompressionCond, m_struMultiStreamCompressionCond.size(), lpStatusList, lpMultiStreamCompressionCfg, m_struMultiStreamCompressionCfg.size()))
//			{
//				int iErr = hCNetSDK.NET_DVR_GetLastError();
//				DialogMessage dlg = new DialogMessage("get failed,error code is: "+iErr);
//				dlg.setBounds(dlg.getX()+dlg.getWidth()/2,dlg.getY()+dlg.getHeight() , 370, 200);
//				dlg.setVisible(true);
////				return;
//			}
//			else
//			{
//				DialogMessage dlg = new DialogMessage("get succeed!");
//				dlg.setBounds(dlg.getX()+dlg.getWidth()/2,dlg.getY()+dlg.getHeight() , 370, 200);
//				dlg.setVisible(true);
//			}
////			m_struMultiStreamCompressionCond.read();
////			m_struMultiStreamCompressionCfg.read();
////			m_struStatusList.read();
////			
////			m_sCurStreamType = m_sPreStreamType = (String)m_cmbStreamType.getSelectedItem();
////			StreamTypeChanged(m_sCurStreamType);
////		}
////	});
////	btnGet.setBounds(111, 441, 82, 23);
////	getContentPane().add(btnGet);
//			
///*	        long userID = lUserID.longValue();
//	        if (userID == -1)
//	        {
//	        	System.out.println("登录球机失败");
//	        	System.out.print("faild");
//	            return null;
//	        }
//	        else
//	        {
//	        	System.out.println("获取能力集");
//	        	String  xmlInput = "<AudioVideoCompressInfo><AudioChannelNumber>1</AudioChannelNumber><VoiceTalkChannelNumber>1</VoiceTalkChannelNumber><VideoChannelNumber>1</VideoChannelNumber></AudioVideoCompressInfo>";
//	        	int XML_ABILITY_OUT_LEN = 3 * 1024 * 1024;
//	        	Pointer memory = new Memory(XML_ABILITY_OUT_LEN);
//	        	System.out.println(memory.toString());
//	        	ByteByReference byteRef=new ByteByReference();
//	        	System.out.println("这里...");
//	        	hCNetSDK.NET_DVR_GetDeviceAbility(lUserID, hCNetSDK.DEVICE_ENCODE_ALL_ABILITY_V20 , xmlInput, xmlInput.getBytes().length, memory, XML_ABILITY_OUT_LEN);//ok
//	        	System.out.println("---");
//	        	hCNetSDK.NET_DVR_Logout(lUserID);
//	        	hCNetSDK.NET_DVR_Cleanup();
//	        	System.out.println(memory.getString(0));
////	        	hCNetSDK.NET_DVR_GetDeviceAbility(lUserID, hCNetSDK.DEVICE_ENCODE_ALL_ABILITY_V20 , xmlInput, xmlInput.getBytes().length, byteRef, XML_ABILITY_OUT_LEN);
//	        	String xmlOutPut=memory.getString(0);
//	        	System.out.println(xmlOutPut);
////	        	InputStream is = new ByteArrayInputStream(xmlOutPut.getBytes());
//	        	shema=new VideoShemaBean();
//	        	shema.parse(xmlOutPut);
//	        	
//	        }
//		}catch(Exception e){
//			System.out.println("获取能力集出错...");
//			System.out.println(hCNetSDK.NET_DVR_GetLastError());
//		}
//		return shema;*/
//	}
//		finally {
//			}
//		return shema;
//		}
//	
	
}
