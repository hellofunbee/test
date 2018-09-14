package com.jingu.IOT.util;
/*package com.jingu.nxy.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jingu.nxy.entity.IpPortBean;

public class DynamicUtil {
	//private VRAIPDAO vraipDAO = (VRAIPDAO) Global.getBean("vraipDAO");
	
	//20150812 增加Nabto类型识别
	public IpPortBean getConnInfoEx(String channelId,String deviceId){
		IpPortBean ipPort=new IpPortBean();
		try{			
			VRAIPHBM iphbm=null;
			if(channelId==null||channelId.equals("")){
				String hql="From VRAIPHBM hbm where hbm.DeviceId='"+deviceId+"'";
				List list  = vraipDAO.queryHql(hql, null);
				if(list.size()<=0) return null;
				else iphbm = (VRAIPHBM)list.get(0);
			}else{
				iphbm = (VRAIPHBM) vraipDAO.load(channelId);
			}

			String ip = "";
			int port =52390;
			
			String NIP = "";
			int NPort = 52390;
			
			
			
			ip = iphbm.getIP();

			try{port = Integer.parseInt(iphbm.getPort());}catch(Exception ex){}
			NIP = iphbm.getNIP();
			try{NPort = Integer.parseInt(iphbm.getNPort());}catch(Exception ex){}
			String proxyIp=iphbm.getProxyIp();
			int proxyPort=52390;
			try{proxyPort=Integer.parseInt(iphbm.getProxyPort());}catch(Exception ex){}
			
			if (iphbm.getUseIPConnect() != null && iphbm.getUseIPConnect().equals("1")) {//内网
				ip = NIP;
				port = NPort;
			}
			if (iphbm.getUseIPConnect() != null && iphbm.getUseIPConnect().equals("2")) {//代理
				ip = proxyIp;
				port = proxyPort;
				
			}
			if (iphbm.getUseIPConnect() != null && iphbm.getUseIPConnect().equals("3")) {//路由
				ip = "127.0.0.1";//nabto使用本机地址
				port = NPort;//nabto使用默认端口
				ipPort.setNabtoId(iphbm.getNabtoDevId());
			}
			
			if (ip==null||ip.trim().length()==0 ||ip.equals("0.0.0.0")) {
				
				//测试外网地址
				Socket socket=null;
				try{
					socket = new Socket();
					socket.connect(new InetSocketAddress( ip, port ), 10000);  
					iphbm.setUseIPConnect("0");
				}catch(Exception ex){
					ip = NIP;
					port = NPort;
					iphbm.setUseIPConnect("1");
					System.out.println(deviceId + "外网优先模式Ex: 外网IP无法连接，尝试内网IP");
				}finally{					
					socket.close();
					socket=null;
				}
			}
			
			if(iphbm.getUseIPConnect().equals("0")||iphbm.getUseIPConnect().equals("2")){
				//视频等端口
				int videoPlayPort=554;
				try{videoPlayPort = Integer.parseInt(iphbm.getVideoPlayPort());}catch(Exception ex){}
				int videoCtrlPort=4738;
				try{videoCtrlPort = Integer.parseInt(iphbm.getCtrPort());}catch(Exception ex){}
				int aperturePort=4736;
				try{aperturePort = Integer.parseInt(iphbm.getVideoPort());}catch(Exception ex){}
				ipPort.setVideoPort(videoPlayPort);
				ipPort.setCtrPort(videoCtrlPort);
				ipPort.setAperturePort(aperturePort);
			}
			
			ipPort.setIp(ip);
			ipPort.setPort(port);
			ipPort.setConnType( iphbm.getUseIPConnect());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ipPort;
	}
	
	public IpPortBean getConnInfo(String channelId,String deviceId){
		IpPortBean ipPort=new IpPortBean();
		try{			
			VRAIPHBM iphbm=null;
			if(channelId==null||channelId.equals("")){
				String hql="From VRAIPHBM hbm where hbm.DeviceId='"+deviceId+"'";
				List list  = vraipDAO.queryHql(hql, null);
				if(list.size()<=0) return null;
				else iphbm = (VRAIPHBM)list.get(0);
			}else{
				iphbm = (VRAIPHBM) vraipDAO.load(channelId);
			}

			String ip = "";
			int port =52390;
			
			String NIP = "";
			int NPort = 52390;
			
			ip = iphbm.getIP();

			try{port = Integer.parseInt(iphbm.getPort());}catch(Exception ex){}
			NIP = iphbm.getNIP();
			try{NPort = Integer.parseInt(iphbm.getNPort());}catch(Exception ex){}
			String proxyIp=iphbm.getProxyIp();
			int proxyPort=52390;
			try{proxyPort=Integer.parseInt(iphbm.getProxyPort());}catch(Exception ex){}
			
			if (iphbm.getUseIPConnect() != null && iphbm.getUseIPConnect().equals("1")) {
				ip = NIP;
				port = NPort;
			}
			if (iphbm.getUseIPConnect() != null && iphbm.getUseIPConnect().equals("2")) {
				ip = proxyIp;
				port = proxyPort;
			}
			if (ip==null||ip.trim().length()==0 ||ip.equals("0.0.0.0")) {
				//测试外网地址
				Socket socket=null;
				try{
					socket = new Socket();
					socket.connect(new InetSocketAddress( ip, port ), 10000);  
					iphbm.setUseIPConnect("0");
				}catch(Exception ex){
					ip = NIP;
					port = NPort;
					iphbm.setUseIPConnect("1");
					System.out.println(deviceId + "外网优先模式: 外网IP无法连接，尝试内网IP");
				}finally{					
					socket.close();
					socket=null;
				}
			}
			ipPort.setIp(ip);
			ipPort.setPort(port);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ipPort;
	}
	
	//new version full function 自动判别IP
	public IpPortBean getConnInfo(String channelId,String deviceId ,HttpServletRequest request){
		IpPortBean ipPort=null;
		try{
			if(ConfigFile.useIpMark.toLowerCase().equals("true")){
				//获取URL中IP地址
				String url=request.getRequestURL().toString();
				String[] uArr=url.toLowerCase().split("/");
				url=uArr[2];
				String[] uArr2 = url.split(":");
				if(uArr2.length>0) url=uArr2[0];
				System.out.println("vistor url:" + url);
				//获取IP地址对应配置的IP类型 1内网IP 2外网IP 3代理IP 0获取失败
				int ipType = getIpType(url);
				if(ipType!=0){
					//ipPort = getConnInfo(channelId,deviceId,ipType);
					ipPort = getConnInfoEx(channelId,deviceId);//20151118
					System.out.println("ipBean:" + ipPort.getIp());
					return ipPort;
				}
			}
		}catch(Exception e){
//			e.printStackTrace();
		}
		//出问题使用默认方式
		//ipPort = getConnInfo(channelId,deviceId);
		ipPort = getConnInfoEx(channelId,deviceId);//20151118
		return ipPort;
	}
	
	private IpPortBean getConnInfo(String channelId,String deviceId,int conType){
		IpPortBean ipPort=new IpPortBean();
		try{			
			VRAIPHBM iphbm=null;
			if(channelId==null||channelId.equals("")){
				String hql="From VRAIPHBM hbm where hbm.DeviceId='"+deviceId+"'";
				List list  = vraipDAO.queryHql(hql, null);
				if(list.size()<=0) return null;
				else iphbm = (VRAIPHBM)list.get(0);
			}else{
				iphbm = (VRAIPHBM) vraipDAO.load(channelId);
			}

			String ip = "";
			int port =52390;
			
			String NIP = "";
			int NPort = 52390;
			
			ip = iphbm.getIP();

			try{port = Integer.parseInt(iphbm.getPort());}catch(Exception ex){}
			NIP = iphbm.getNIP();
			try{NPort = Integer.parseInt(iphbm.getNPort());}catch(Exception ex){}
			String proxyIp=iphbm.getProxyIp();
			int proxyPort=52390;
			try{proxyPort=Integer.parseInt(iphbm.getProxyPort());}catch(Exception ex){}
			
			if (conType == 1) {
				ip = NIP;
				port = NPort;
			}
			if (conType == 3) {
				ip = proxyIp;
				port = proxyPort;
			}
			if (ip==null||ip.trim().length()==0 ||ip.equals("0.0.0.0")) {
				//测试外网地址
				Socket socket=null;
				try{
					socket = new Socket();
					socket.connect(new InetSocketAddress( ip, port ), 10000);  
					iphbm.setUseIPConnect("0");
				}catch(Exception ex){
					ip = NIP;
					port = NPort;
					iphbm.setUseIPConnect("1");
					System.out.println(deviceId + "外网优先模式: 外网IP无法连接，尝试内网IP");
				}finally{					
					socket.close();
					socket=null;
				}
			}
			
			ipPort.setIp(ip);
			ipPort.setPort(port);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ipPort;
	}

	private int getIpType(String url){
		try{
//			String[] uArr=url.toLowerCase().split("/");
//			url=uArr[2];
			InetAddress address=InetAddress.getByName(url);
			url=address.getHostAddress();
			
			if(url.equals(ConfigFile.innerIpMark)) return 1;
			if(url.equals(ConfigFile.outerIpMark)) return 2;	
			if(url.equals(ConfigFile.proxyIpMark)) return 3;	
			if(PublicMethod.isInnerIP(address.getHostAddress())) return 1;
			else return 3;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;

	}
	public static void main(String[] args){
	
		try{
			DynamicUtil d=new DynamicUtil();
			String url="http://172.32.5.60/vraMonitorDevice.do?deviceId=10.00.20.53&channelId=0004&orderBy=000100010002A&random=0.6178228355700965";
			String[] uArr=url.toLowerCase().split("/");
			url=uArr[2];
			InetAddress address=InetAddress.getByName(url);
			url=address.getHostAddress();
			
			PublicMethod.isInnerIP(address.getHostAddress());
			System.out.println(ConfigFile.innerIpMark);
			System.out.println(ConfigFile.outerIpMark);
			System.out.println(ConfigFile.transmitPort);
			System.out.print(url+"|"+url.equals(ConfigFile.innerIpMark)+"|"+url.equals(ConfigFile.outerIpMark)+"|"+PublicMethod.isInnerIP(address.getHostAddress()));
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
*/