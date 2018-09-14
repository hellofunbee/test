/**  
*   
* 项目名称：IOT  
* 类名称：LL  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月1日 上午11:34:38  
* 修改人：jianghu  
* 修改时间：2017年9月1日 上午11:34:38  
* 修改备注： 上午11:34:38
* @version   
*   
*/ 
package com.jingu.IOT.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingu.IOT.entity.HCNetSDK;
import com.jingu.IOT.entity.HCNetSDK.NET_DVR_COMPRESSIONCFG_V30;
import com.jingu.IOT.entity.HCNetSDK.NET_DVR_TIME;
import com.jingu.IOT.entity.HkSdkEx;
import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.MonitorHBM;
import com.jingu.IOT.entity.VideoShemaBean;
import com.jingu.IOT.util.Client;

/**

* @ClassName: LL
* @Description: TODO
* @author jianghu
* @date 2017年9月1日 上午11:34:38

*/
public class LL {

	public static void main(String[] args) {
		String deviceId = "10.00.22.98";
		String ip = "192.168.0.198";
		int port = 52390;
		Calendar instance = Calendar.getInstance();
		HCNetSDK.NET_DVR_TIME m_struTime = new HCNetSDK.NET_DVR_TIME();
		m_struTime.dwYear=instance.get(Calendar.YEAR);
		m_struTime.dwMonth=instance.get(Calendar.MONTH);
		m_struTime.dwDay=instance.get(Calendar.DATE);
		m_struTime.dwHour=instance.get(Calendar.HOUR_OF_DAY);
		m_struTime.dwMinute=instance.get(Calendar.MINUTE);
		m_struTime.dwSecond=instance.get(Calendar.SECOND);
//		boolean setSetDateTime = HkSdkEx.setSetDateTime(ipc.getS_username(), ipc.getS_password(), ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString(), m_struTime);
		boolean setSetDateTime = HkSdkEx.setSetDateTime("admin", "12345", "192.168.0.234", "8000", m_struTime);
		System.out.println(setSetDateTime);

//		s_ipcnum:1;s_nod:0;s_power:1;s_ip:192.168.0.170;s_port:80;s_username:admin;s_password:vr12345;s_online:255;s_stream:0;
//		String deviceId = "10.00.21.27";
//		String ip = "111.53.182.34";
//		int port = 52400;
	
		
		
//		int port = 52390;
//		String deviceId="10.00.22.98";
//		String ip = "192.168.0.198";
		//获得vr1000下的节点个数
//		String ipc2 = Client.getIpc2(ip, port, deviceId);
//		System.out.println(ipc2);
//		String ipcProxy1 = Client.getIpcProxy1(deviceId, ip, port);
//		System.out.println(ipcProxy1);
//		if(ipc2.length()>11){
//			int indexOf = ipc2.indexOf(";");
//			String substring = ipc2.substring(indexOf+1);
//			System.out.println(substring);
//			String[] split2 = substring.split(";");
//			Map<String,String> map = new HashMap<>();
//			for (int i = 0; i < split2.length; i++) {
//				String[] split3 = split2[i].split(":");
//				map.put(split3[0], split3[1]);
//				if(map.containsKey("s_stream")){
//					map.remove("s_stream", map.get("s_stream"));
//				}
//			}
//		}
		
		//s_ipcnum:1;s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:1;s_stream:0;
//////		//获得代理情况
//		String ipcProxy1 = Client.getIpcProxy1(deviceId, ip, port);
//		System.out.println(ipcProxy1);
//		byte b = 1;
//		boolean clearIpcMonitor = Client.clearIpcMonitor("192.168.0.168", 52390, b, deviceId);
//		System.out.println(clearIpcMonitor);
		//设置代理
//		String config = "s_host:192.168.0.234;s_rport:8000;s_lport:9100;s_pwr:1;s_pwrval:0;s_timeout:600;";
//		String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, deviceId, ip, port);
//		System.out.println(setIpcProxyEx1);
		
		
//		String config = "s_host:192.168.0.234;s_rport:8000;s_lport:9002;s_pwr:1;s_pwrval:0;s_timeout:600;";
//		String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, deviceId, ip, port);
//		System.out.println(setIpcProxyEx1);
		//我要配置监视点
//		byte b =1;
//		List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2("10.00.22.98", deviceId, b, ip, port);
//		System.out.println(ipcMonitor2);
//		System.out.println("--");
//		MonitorHBM hbm = new MonitorHBM();
//		hbm.setBeginTime("12:00:00");
//		hbm.setEndTime("18:00:00");
//		hbm.setCycleDay(1);
//		hbm.setDeviceId(deviceId);
//		hbm.setMonitorName("新设置的监视点");
//		hbm.setMonitorId(2);
//		hbm.setRateSecond(5);
//		hbm.setId("4");
//		boolean setIpcMonitor = Client.setIpcMonitor(ip, port, b, hbm);
//		System.out.println(setIpcMonitor);
//		String uName ="admin";
//		String pwd = "12345";
//		String sport = "9090";
//		VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility(uName, pwd, ip, sport);
//		String channelType = ipcAbility.getMainChannel().getChannelType();
//		System.out.println(channelType);
//		NET_DVR_COMPRESSIONCFG_V30 compressInfo = HkSdkEx.getCompressInfo(uName, pwd, ip, sport);
//		System.out.println(compressInfo);
//		NET_DVR_TIME net_DVR_TIME = new HCNetSDK.NET_DVR_TIME();
//		net_DVR_TIME.dwDay =1;
//		net_DVR_TIME.dwHour =1;
//		net_DVR_TIME.dwMinute =1;
//		net_DVR_TIME.dwSecond =1;
//		net_DVR_TIME.dwYear =2017;
//		HkSdkEx.setSetDateTime(uName, pwd, ip, sport, net_DVR_TIME);
		//s_nod:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;
//		1;s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:1;s_stream:0
//		boolean setIpc1 = Client.setIpc1("add", "s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:0;s_stream:1;", deviceId, ip, port);
//		System.out.println(setIpc1);
		/*String ipc22 = Client.getIpc2(ip, port, deviceId);
		System.out.println(ipc22);*/
//		List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2(deviceId+".01", deviceId, b, ip, port);
//		System.out.println(ipcMonitor2);
		//String deviceStatus2 = Client.getDeviceStatus2(null, null, ip, port);
		//Client.getDeviceStatus(null, null, ip, port);
	//	System.out.println(deviceStatus2);
//		String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, deviceId, ip, port);
//		List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2("10.00.21.74.01", "10.00.21.74.01", b, "192.168.0.168", 52390);
//		System.out.println(ipcMonitor2);
//		String ipc2 = Client.getIpc2(ip, port, "10.00.21.74");
//		System.out.println(ipc2);
//		boolean setIpc1 = Client.setIpc1("delete", "s_nod:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;", deviceId, ip, port);
//		System.out.println(setIpc1);
		//s_ipcnum:1;s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:0;s_stream:0;
		//String deviceStatus3 = Client.getDeviceStatus3(null, deviceId, ip, port);
		//System.out.println(deviceStatus3);
		//VRALogBean vraLog = Client.getVRALog(null, deviceId, ip, port);
		//System.out.println(vraLog);
//		boolean clearIpcMonitor = Client.clearIpcMonitor(ip, port, b, deviceId);
//		System.out.println(clearIpcMonitor);
/*		MonitorHBM hbm = new MonitorHBM();
		hbm.setBeginTime("9:00:00");
		hbm.setEndTime("18:00:00");
		hbm.setCycleDay(1);
		hbm.setDeviceId(deviceId);
		hbm.setMonitorName("新设置的监视点");
		hbm.setMonitorId(1);
		hbm.setRateSecond(10);
		//hbm.setId("1");
		boolean setIpcMonitor = Client.setIpcMonitor(ip, port, b, hbm);
		System.out.println(setIpcMonitor);*/
//		List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2("", deviceId, b, ip, port);
//		System.out.println(ipcMonitor2);
//		
//		int a1 =0;
//		int a2 =0;
//		int a3 =0;
//		int a4 =0;
//		int a5 =0;
//		int a6 =0;
//		int a7 =0;
//		int a8 =0;
//		int a9 =0;
//		int a =a2;
//		for (int i = 0; i < 100; i++) {
//			int c =ai;
//			System.out.println(c);
//		}
	}
}
