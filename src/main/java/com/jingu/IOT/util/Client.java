package com.jingu.IOT.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.IpPortBean;
import com.jingu.IOT.entity.IpcBean;
import com.jingu.IOT.entity.IpcProxyBean;
import com.jingu.IOT.entity.MonitorHBM;
import com.jingu.IOT.entity.VRAConfigBean;
import com.jingu.IOT.entity.VRALogBean;
import com.jingu.IOT.switcher.VRASwitchBean;
import com.jingu.IOT.switcher.VRASwitchConfBean;
import com.jingu.IOT.switcher.VRASwitchConfInBean;

public class Client {
    private static Logger logger = Logger.getLogger(Client.class.getName());
    private static long nabtoSleep = 1000;
    private static int reTryTimes = 1;
    private static int nabtoReTryTimes = 10;

    /*	//设置监视点
    public static boolean setVRAMonitor(String ip,int port,byte type,MonitorHBM hbm) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			String deviceId=hbm.getDeviceId();

			DynamicUtil dUtil=new DynamicUtil();

			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}


			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x14;
			byte[] data=new byte[15];
			//监视点类型
			data[0]=type;
			//操作类型
			data[1]=0x1;
			//监视点编号
			data[2]=(byte)hbm.getMonitorId();
			//时间范围
			byte[] beginTime =new byte[3];
			String[] time=hbm.getBeginTime().split(":");
			data[3]=(byte)Integer.parseInt(time[0]);
			data[4]=(byte)Integer.parseInt(time[1]);
			data[5]=(byte)Integer.parseInt(time[2]);
	        byte[] endTime =new byte[3];
			time=hbm.getEndTime().split(":");
			data[6]=(byte)Integer.parseInt(time[0]);
			data[7]=(byte)Integer.parseInt(time[1]);
			data[8]=(byte)Integer.parseInt(time[2]);
	        //采集间隔
	        byte[] a = PublicMethod.int4bytes(hbm.getRateSecond()*60);
	        System.arraycopy(a, 0, data, 9, a.length);
	        //采集周期
	        a = PublicMethod.int2bytes(hbm.getCycleDay());
	        System.arraycopy(a, 0, data, 13, a.length);

			dos.write(AES.EnParseData(Command.getSentCommand(cmd,data,hbm.getDeviceId())));
			dos.flush();
			byte[] logByte = readInputStream(socket.getInputStream());
			boolean isSuccess=PublicMethod.getCommandResult66(AES.DeParseData(logByte));
			dos.close();
			socket.close();

			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
			System.out.println("["+hbm.getDeviceId()+"]配置监视点："+hbm.getMonitorId()+" | "+isSuccess);

			return isSuccess;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();

				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
			} catch (IOException e1) {
			}
			return false;
		}
	}
	//删除监视点
	public static boolean delVRAMonitor(String ip,int port,byte type,MonitorHBM hbm) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {

			String deviceId=hbm.getDeviceId();
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}


			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x14;
			byte[] data=new byte[3];
			//监视点类型
			data[0]=type;
			//操作类型
			data[1]=0x2;
			//监视点
			data[2]=(byte)hbm.getMonitorId();
			dos.write(AES.EnParseData(Command.getSentCommand(cmd,data,hbm.getDeviceId())));
			dos.flush();
			byte[] logByte = readInputStream(socket.getInputStream());
			boolean isSuccess=PublicMethod.getCommandResult66(AES.DeParseData(logByte));
			dos.close();
			socket.close();

			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
			return isSuccess;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();

				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
			} catch (IOException e1) {
			}
			return false;
		}
	}
	//clear监视点
	public static boolean clearVRAMonitor(String ip,int port,byte type,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x14;
			byte[] data=new byte[3];
			//监视点类型
			data[0]=type;
			//操作类型
			data[1]=(byte)0xff;
			dos.write(AES.EnParseData(Command.getSentCommand(cmd,data,deviceId)));
			dos.flush();
			byte[] logByte = readInputStream(socket.getInputStream());
			boolean isSuccess=PublicMethod.getCommandResult66(AES.DeParseData(logByte));
			dos.close();
			socket.close();

			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

			return isSuccess;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}

			} catch (IOException e1) {
			}
			return false;
		}
	}
	//跳至监视点
	public static boolean goVRAMonitor(String ip,int port,byte type,MonitorHBM hbm) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;

		try {

			//for nabto
			String deviceId=hbm.getDeviceId();
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}

			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x14;
			byte[] data=new byte[3];
			//监视点类型
			data[0]=type;
			//操作类型
			data[1]=0x3;
			//监视点
			data[2]=(byte)hbm.getMonitorId();
			dos.write(AES.EnParseData(Command.getSentCommand(cmd,data,hbm.getDeviceId())));
			dos.flush();
			byte[] logByte = readInputStream(socket.getInputStream());
			boolean isSuccess=PublicMethod.getCommandResult66(AES.DeParseData(logByte));
			dos.close();
			socket.close();

			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
			return isSuccess;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();

				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
			} catch (IOException e1) {
			}
			return false;
		}
	}
	public static void getVRAMonitor(String deviceId,byte type,String ip,int port){
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {

			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x14;
			byte[] data=new byte[2];
			//监视点类型
			data[0]=type;
			//操作类型
			data[1]=0x4;
			dos.write(AES.EnParseData(Command.getSentCommand(cmd,data,deviceId)));
			dos.flush();
			data = readInputStream(socket.getInputStream());
			data=AES.DeParseData(data);
			dos.close();
			socket.close();
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

			try {

			  System.out.println();
			  //设备编号
			  deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));
			  //获取除去包括设备ID后的数据
			  data = PublicMethod.getDataResult(data);
			  //6个字节为一条记录
			  int rowCount=data.length/13;
			  MonitorDAO vraMonitorDAO = (MonitorDAO) Global.getBean("vraMonitorDAO");
//			  vraMonitorDAO.update("update MonitorHBM hbm set hbm.success=false where deviceId='"+deviceId+"'");
			  vraMonitorDAO.excuteSQL("update T_VARTRIVER_Monitor set success=0 where deviceId='"+deviceId+"'", null);
			  for(int i=0;i<rowCount;i++){
				  MonitorHBM hbm =new MonitorHBM();
				  hbm.setDeviceId(deviceId);
				  byte[] rec=new byte[13];
				  System.arraycopy(data, i*13, rec, 0, rec.length);
			      //监视点
			      byte[] idByte = new byte[1];
			      System.arraycopy(rec, 0, idByte, 0, idByte.length);
			      hbm.setMonitorId(PublicMethod.byteToInt2(idByte));
			      //时间范围
			      hbm.setBeginTime(String.format("%02d", rec[1])+":"+String.format("%02d", rec[2])+":"+String.format("%02d", rec[3]));
			      hbm.setEndTime(String.format("%02d", rec[4])+":"+String.format("%02d", rec[5])+":"+String.format("%02d", rec[6]));
			      //采集间隔
			      byte[] rateByte = new byte[4];
			      System.arraycopy(rec, 7, rateByte, 0, rateByte.length);
			      hbm.setRateSecond(PublicMethod.byteToInt2(rateByte)/60);
			      //采集周期
			      byte[] cycleByte = new byte[2];
			      System.arraycopy(rec, 11, cycleByte, 0, cycleByte.length);
			      hbm.setCycleDay(PublicMethod.byteToInt2(cycleByte));
			      hbm.setSuccess(true);
			      List ls=vraMonitorDAO.queryHql("from MonitorHBM hbm where deviceId='"+deviceId+"' and monitorId="+hbm.getMonitorId(), null);
			      if(ls.size()>0){
			    	  MonitorHBM m=(MonitorHBM)ls.get(0);
			    	  m.setBeginTime(hbm.getBeginTime());
			    	  m.setEndTime(hbm.getEndTime());
			    	  m.setRateSecond(hbm.getRateSecond());
			    	  m.setCycleDay(hbm.getCycleDay());
			    	  m.setSuccess(true);
			    	  vraMonitorDAO.update(m);
			      }else{
			    	  vraMonitorDAO.insert(hbm);
			      }
			  }
		        System.out.println("获取图片监视点成功:"+deviceId);
		    } catch (Exception e) {
		    	System.out.println("获取图片监视点数据错误");
		    }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

		}
	}*/
    /////////////////IPC监视点
    //设置监视点
    public static boolean setIpcMonitor(String ip, int port, byte type, MonitorHBM hbm) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			String deviceId=hbm.getDeviceId();
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/


            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) 0xd0;
            byte[] data = new byte[15];
            //监视点类型
            data[0] = type;
            //操作类型
            data[1] = 0x1;
            //监视点编号
            data[2] = (byte) hbm.getMonitorId();
            //时间范围
            byte[] beginTime = new byte[3];
            String[] time = hbm.getBeginTime().split(":");
            data[3] = (byte) Integer.parseInt(time[0]);
            data[4] = (byte) Integer.parseInt(time[1]);
            data[5] = (byte) Integer.parseInt(time[2]);
            byte[] endTime = new byte[3];
            time = hbm.getEndTime().split(":");
            data[6] = (byte) Integer.parseInt(time[0]);
            data[7] = (byte) Integer.parseInt(time[1]);
            data[8] = (byte) Integer.parseInt(time[2]);
            //采集间隔
            byte[] a = PublicMethod.int4bytes(hbm.getRateSecond() * 60);
            System.arraycopy(a, 0, data, 9, a.length);
            //采集周期
            a = PublicMethod.int2bytes(hbm.getCycleDay());
            System.arraycopy(a, 0, data, 13, a.length);

            dos.write(AES.EnParseData(Command.getSentCommand(cmd, data, hbm.getDeviceId())));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            boolean isSuccess = PublicMethod.getCommandResult66(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            System.out.println("[" + hbm.getDeviceId() + "]配置监视点：" + hbm.getMonitorId() + " | " + isSuccess);
            return isSuccess;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}


            } catch (IOException e1) {
            }
            return false;
        }
    }

    //删除监视点
    public static boolean delIpcMonitor(String ip, int port, byte type, MonitorHBM hbm) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {

            String deviceId = hbm.getDeviceId();
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/


            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) 0xd0;
            byte[] data = new byte[3];
            //监视点类型
            data[0] = type;
            //操作类型
            data[1] = 0x2;
            //监视点
            data[2] = (byte) hbm.getMonitorId();
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, data, hbm.getDeviceId())));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            boolean isSuccess = PublicMethod.getCommandResult66(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return isSuccess;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }
            return false;
        }
    }

    //clear监视点
    public static boolean clearIpcMonitor(String ip, int port, byte type, String deviceId) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
		/*	DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) 0xd0;
            byte[] data = new byte[3];
            //监视点类型
            data[0] = type;
            //操作类型
            data[1] = (byte) 0xff;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, data, deviceId)));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            boolean isSuccess = PublicMethod.getCommandResult66(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            return isSuccess;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}

            } catch (IOException e1) {
            }
            return false;
        }
    }

    public static void getIpcMonitor(String mapingDevId, String deviceId, byte type, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {

            //for nabto
	/*		DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) 0xd0;
            byte[] data = new byte[2];
            //监视点类型
            data[0] = type;
            //操作类型
            data[1] = 0x4;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, data, deviceId)));
            dos.flush();
            data = readInputStream(socket.getInputStream());
            data = AES.DeParseData(data);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            try {

                System.out.println("getIpcMonitor");
                //设备编号
                deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));//2015 1212 不需要
                //获取除去包括设备ID后的数据
                data = PublicMethod.getDataResult(data);
                //6个字节为一条记录
                int rowCount = data.length / 13;
			/*  MonitorDAO vraMonitorDAO = (MonitorDAO) Global.getBean("vraMonitorDAO");*/
//			  vraMonitorDAO.excuteSQL("update T_VARTRIVER_Monitor set success=0 where deviceId='"+mapingDevId+"'", null);
//			  vraMonitorDAO.update("update MonitorHBM hbm set hbm.success=false where deviceId='"+mapingDevId+"'");

			 /* List lsM=vraMonitorDAO.queryHql("from MonitorHBM hbm where deviceId='"+mapingDevId+"'", null);
			  for(int i=0;i<lsM.size();i++){
				  MonitorHBM tmpM=(MonitorHBM)lsM.get(i);
				  tmpM.setSuccess(false);
				  vraMonitorDAO.update(tmpM);
			  }*/

                for (int i = 0; i < rowCount; i++) {
                    MonitorHBM hbm = new MonitorHBM();
                    hbm.setDeviceId(mapingDevId);
                    byte[] rec = new byte[13];
                    System.arraycopy(data, i * 13, rec, 0, rec.length);
                    //监视点
                    byte[] idByte = new byte[1];
                    System.arraycopy(rec, 0, idByte, 0, idByte.length);
                    hbm.setMonitorId(PublicMethod.byteToInt2(idByte));
                    //时间范围
                    hbm.setBeginTime(String.format("%02d", rec[1]) + ":" + String.format("%02d", rec[2]) + ":" + String.format("%02d", rec[3]));
                    hbm.setEndTime(String.format("%02d", rec[4]) + ":" + String.format("%02d", rec[5]) + ":" + String.format("%02d", rec[6]));
                    //采集间隔
                    byte[] rateByte = new byte[4];
                    System.arraycopy(rec, 7, rateByte, 0, rateByte.length);
                    hbm.setRateSecond(PublicMethod.byteToInt2(rateByte) / 60);
                    //采集周期
                    byte[] cycleByte = new byte[2];
                    System.arraycopy(rec, 11, cycleByte, 0, cycleByte.length);
                    hbm.setCycleDay(PublicMethod.byteToInt2(cycleByte));
                    hbm.setSuccess(true);
                    IPCPointEntity ipcPointEntity = new IPCPointEntity(0, deviceId, PublicMethod.byteToInt2(idByte), null, String.format("%02d", rec[1]) + ":" + String.format("%02d", rec[2]) + ":" + String.format("%02d", rec[3]), String.format("%02d", rec[4]) + ":" + String.format("%02d", rec[5]) + ":" + String.format("%02d", rec[6]), PublicMethod.byteToInt2(rateByte) / 60, PublicMethod.byteToInt2(cycleByte), null, null, 1);
			    /*  List ls=.queryHql("from MonitorHBM hbm where deviceId='"+mapingDevId+"' and monitorId="+hbm.getMonitorId(), null);
			      if(ls.size()>0){
			    	  MonitorHBM m=(MonitorHBM)ls.get(0);
			    	  m.setBeginTime(hbm.getBeginTime());
			    	  m.setEndTime(hbm.getEndTime());
			    	  m.setRateSecond(hbm.getRateSecond());
			    	  m.setCycleDay(hbm.getCycleDay());
			    	  m.setSuccess(hbm.isSuccess());

			    	  vraMonitorDAO.update(m);
			      }else{
			    	  vraMonitorDAO.insert(hbm);
			      }*/
                    // List<ipcPointEntity> list = new ArrayList<ipcPointEntity>();

                }
                System.out.println("获取图片监视点成功:" + mapingDevId);
            } catch (Exception e) {
                System.out.println("获取图片监视点数据错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

        }
    }

    public static List<IPCPointEntity> getIpcMonitor2(String mapingDevId, String deviceId, byte type, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {

            //for nabto
	/*		DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) 0xd0;
            byte[] data = new byte[2];
            //监视点类型
            data[0] = type;
            //操作类型
            data[1] = 0x4;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, data, deviceId)));
            dos.flush();
            data = readInputStream(socket.getInputStream());
            data = AES.DeParseData(data);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            try {

                System.out.println("getIpcMonitor");
                //设备编号
                deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));//2015 1212 不需要
                //获取除去包括设备ID后的数据
                data = PublicMethod.getDataResult(data);
                //6个字节为一条记录
                int rowCount = data.length / 13;
			/*  MonitorDAO vraMonitorDAO = (MonitorDAO) Global.getBean("vraMonitorDAO");*/
//			  vraMonitorDAO.excuteSQL("update T_VARTRIVER_Monitor set success=0 where deviceId='"+mapingDevId+"'", null);
//			  vraMonitorDAO.update("update MonitorHBM hbm set hbm.success=false where deviceId='"+mapingDevId+"'");

			 /* List lsM=vraMonitorDAO.queryHql("from MonitorHBM hbm where deviceId='"+mapingDevId+"'", null);
			  for(int i=0;i<lsM.size();i++){
				  MonitorHBM tmpM=(MonitorHBM)lsM.get(i);
				  tmpM.setSuccess(false);
				  vraMonitorDAO.update(tmpM);
			  }*/

                List<IPCPointEntity> list = new ArrayList<IPCPointEntity>();
                for (int i = 0; i < rowCount; i++) {
                    MonitorHBM hbm = new MonitorHBM();
                    hbm.setDeviceId(mapingDevId);
                    byte[] rec = new byte[13];
                    System.arraycopy(data, i * 13, rec, 0, rec.length);
                    //监视点
                    byte[] idByte = new byte[1];
                    System.arraycopy(rec, 0, idByte, 0, idByte.length);
                    hbm.setMonitorId(PublicMethod.byteToInt2(idByte));
                    //时间范围
                    hbm.setBeginTime(String.format("%02d", rec[1]) + ":" + String.format("%02d", rec[2]) + ":" + String.format("%02d", rec[3]));
                    hbm.setEndTime(String.format("%02d", rec[4]) + ":" + String.format("%02d", rec[5]) + ":" + String.format("%02d", rec[6]));
                    //采集间隔
                    byte[] rateByte = new byte[4];
                    System.arraycopy(rec, 7, rateByte, 0, rateByte.length);
                    hbm.setRateSecond(PublicMethod.byteToInt2(rateByte) / 60);
                    //采集周期
                    byte[] cycleByte = new byte[2];
                    System.arraycopy(rec, 11, cycleByte, 0, cycleByte.length);
                    hbm.setCycleDay(PublicMethod.byteToInt2(cycleByte));
                    hbm.setSuccess(true);
	/*		      this.id = id;
					this.deviceId = deviceId;
					this.monitorId = monitorId;
					this.monitorName = monitorName;
					this.beginTime = beginTime;
					this.endTime = endTime;
					this.rateSecond = rateSecond;
					this.cycleDay = cycleDay;
					this.imgUrl = imgUrl;
					this.createTime = createTime;
					this.success = success;*/
                    IPCPointEntity ipcPointEntity = new IPCPointEntity(0, mapingDevId, PublicMethod.byteToInt2(idByte), null, String.format("%02d", rec[1]) + ":" + String.format("%02d", rec[2]) + ":" + String.format("%02d", rec[3]), String.format("%02d", rec[4]) + ":" + String.format("%02d", rec[5]) + ":" + String.format("%02d", rec[6]), PublicMethod.byteToInt2(rateByte) / 60, PublicMethod.byteToInt2(cycleByte), null, null, 1);
                    list.add(ipcPointEntity);
			    /*  List ls=.queryHql("from MonitorHBM hbm where deviceId='"+mapingDevId+"' and monitorId="+hbm.getMonitorId(), null);
			      if(ls.size()>0){
			    	  MonitorHBM m=(MonitorHBM)ls.get(0);
			    	  m.setBeginTime(hbm.getBeginTime());
			    	  m.setEndTime(hbm.getEndTime());
			    	  m.setRateSecond(hbm.getRateSecond());
			    	  m.setCycleDay(hbm.getCycleDay());
			    	  m.setSuccess(hbm.isSuccess());

			    	  vraMonitorDAO.update(m);
			      }else{
			    	  vraMonitorDAO.insert(hbm);
			      }*/
                }
                System.out.println("获取图片监视点成功:" + mapingDevId);
                return list;
            } catch (Exception e) {
                System.out.println("获取图片监视点数据错误");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            return null;
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

        }
    }




	 /*
	 * TODO 获取ZigBee设备参数
	 */
	/*public static VRAZgBeConfigBean getZgBeVRAConfig(String ip,int port,String orderBy) {
		String configStr = "";
		VRAZgBeConfigBean configBean = null;
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(Command.getVRAZgBeConfigData());
			dos.flush();
			byte[] configBytes = readInputStream(socket.getInputStream());
			configBytes=AES.DeParseData(configBytes);
			configStr = new String(configBytes);
			configStr=new String(PublicMethod.getDataResult(configBytes));
			configBean = new VRAZgBeConfigBean(configStr,ip,orderBy);
			dos.close();
			socket.close();
			return configBean;
		} catch (IOException e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {

			}
		}
		return null;
	}*/
	/*public static VRAZgBeConfigBean getZgBeVRAConfig(String ip,int port,String orderBy,String deviceId) {
		String configStr = "";
		VRAZgBeConfigBean configBean = null;
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(Command.getVRAZgBeConfigData());
			dos.flush();
			byte[] configBytes = readInputStream(socket.getInputStream());
			configBytes=AES.DeParseData(configBytes);
			configStr = new String(configBytes);
			configStr=new String(PublicMethod.getDataResult(configBytes));
			configBean = new VRAZgBeConfigBean(configStr,ip,orderBy);
			dos.close();
			socket.close();
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

			return configBean;
		} catch (IOException e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}

			} catch (IOException e1) {

			}
		}
		return null;
	}*/
	/*
	 * TODO 设置设备参数
	 */
	/*public static boolean setVRAZgBeConfig(String config,String DeviceId,String ip,int port) {
		boolean isError =false;
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			//ip="127.0.0.1";
			//port=11223;
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.setVRAZgBeConfigData(config, getDeviceId(DeviceId))));
			dos.flush();

			byte[] logByte = readInputStream(socket.getInputStream());
			isError=PublicMethod.getCommandResult(AES.DeParseData(logByte));
			dos.close();
			socket.close();
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

			System.out.println("设置Zigbee设备参数成功:"+DeviceId);
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}

			} catch (IOException e1) {
			}
		}
		return isError;
	}*/

    /**
     * 写入设备参数
     *
     * @param request
     * @param DeviceId
     * @param ip
     * @param port
     */
	/*public static boolean writeVRAZgBeConfig(HttpServletRequest request,String DeviceId,String ip,int port){
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;

		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}

			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0xd;
			dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
			dos.flush();
			dos.close();
			socket.close();
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
			System.out.println("写入设备参数成功:"+DeviceId);

		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
			} catch (IOException e1) {
			}
			return false;
		}
		return true;
	}*/

    /*
	 * TODO 获取设备参数
	 */
    public static VRAConfigBean getVRAConfig(String ip, int port) {
        String configStr = "";
        VRAConfigBean configBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        try {
//			socket = new Socket(ip, port);
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 10000000);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(Command.getVRAConfigData());
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
            configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            configBean = new VRAConfigBean(configStr, ip);
            dos.close();
            socket.close();
            return configBean;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
        }
        //return configStr;
        return configBean;
    }

    public static VRAConfigBean getVRAConfig(String ip, int port, String deviceId) {

        String configStr = "";
        VRAConfigBean configBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
            int currReTryTimes = reTryTimes;
		/*	DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);	*/
			/*if(connBean.getConnType().equals("3")){
				//TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				//port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(nabtoSleep);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(Command.getVRAConfigData());
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
            configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            configBean = new VRAConfigBean(configStr, ip);
            dos.close();
            socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return configBean;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
        }
        return configBean;
    }

    /*
	 * TODO 设置设备参数
	 */
    public static boolean setVRAConfig(String config, String deviceId, String ip, int port) {
        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
			/*DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
//			socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            //dos.write(Command.setVRAConfigData(config, getDeviceId(DeviceId)));
            //[85, 5, -128, 0, 0, 0, 0, 0, 0, 0, 8, -64, 108, 1, -118, -88, -19, -29, 34, -78, -69, 123, -93, -5, 49, 54, -22, 14, -62, -32, 76, 38, 34, 101, 116, 89, 35, -16, -22, -57, 38, -31, 47, 84, -127, 54, 78, 71, -113, -108, -126, 111, 51, -46, 66, 118, 84, -8, -48, 72, -65, 29, -95, -110, 17, -30, 112, 79, 4, 45, -61, -106, 68, 110, -20, 19, 55, 38, -43, 35, 19, 43, 36, -21, 100, -6, 11, -11, -83, 69, -58, 44, -3, -112, 111, 50, 48, 38, 55, -62, -104, -52, 73, -10, 36, 94, -31, -111, 83, 0, -5, -72, -32, -42, 98, -39, -104, -34, 107, -74, 17, -23, -69, 18, 88, -34, -8, 119, -29, 107, 1, -4, -86, 125, 56, -108, 126, 56, -57, 7, -45, 81, -105, 36, 75, -26, -25, -22, 73, 60, -29, 97, 1, -4, 65, 6, -63, -120, 30, -23, -53, -116, -109, -94, -10, 114, -31, 98, -39, -125, 37, -54, 80, -83, 101, -102, 15, -4, -73, 47, 127, -91, -96, -80, -58, -50, -53, -56, -78, -80, 66, 96, -48, 38, 55, 39, 61, 93, 92, 87, -126, 120, -1, 95, 114, 8, -21, 120, 45, 6, 0, 75, 104, -92, -123, 120, -110, -125, -74, 5, -29, -108, 12, 9, 6, 99, -122, 11, -39, -117, 84, 108, -21, -80, -80, 96, 116, -124, 86, -67, -15, -94, 80, -98, -92, 126, 80, 9, -107, -119, 66, -28, 31, -7, -36, -7, -56, 75, 21, -65, 34, -94, -42, -78, -42, 119, -30, -37, -95, -95, -19, 48, 109, -50, -48, -91, -113, 3, -102, 31, 123, -16, 31, -18, 13, 58, -57, 16, 126, -9, -2, -66, -38, 70, 97, 124, 65, -71, -40, -102, -60, -65, -102, -1, -17, 108, -114, 96, -50, -6, 63, 61, 40, 68, -5, 89, -119, -65, -116, -14, 51, 80, 53, -58, 5, -5, -113, 73, 105, -3, 111, 119, -2, 77, -7, 59, 36, -105, 94, 126, -113, 98, 1, 79, -98, -71, -2, -95, 9, 40, 25, -2, -25, 44, -45, -26, 82, 105, -101, 37, -119, 115, -90, -5, 4, 119, 111, -45, 122, 45, 3, 80, 54, -28, -82, 115, -62, -50, -26, -35, -64, 83, 65, 94, 1, 115, -62, 123, -37, -46, 68, 13, -34, -9, -21, -34, -106, 92, 95, -109, 54, 126, 103, 109, -64, 120, -29, 2, -75, -105, -34, -13, 66, 93, -23, -73, -43, -49, 36, 5, 43, -50, -6, 21, -124, -57, 108, -8, 27, 12, -112, -116, -47, 113, -57, 29, 42, -44, -9, 16, 66, -87, -11, 8, -16, -6, 90, -13, 25, 108, -52, -22, -70, 78, -88, -12, 5, 15, -29, 28, 42, -119, 94, 66, -111, 54, -33, 113, 93, -101, -43, -52, 58, -106, -102, 11, -31, -93, 118, 40, 73, -30, 43, 6, 41, -58, -52, 30, 34, 109, -100, -80, -55, -85, -68, -105, -128, 65, -121, 12, -115, -38, 26, -107, -25, 39, -49, 85, 127, -62, -121, 121, -24, 0, -106, -1, -10, 100, 108, 76, -84, 127, -117, -31, -4, -16, 108, -57, 23, -18, 80, 124, 108, -67, 89, -43, 38, -62, -57, 109, -126, -121, 96, 40, -44, -4, -87, -71, -4, 94, -17, -55, -50, -66, 36, 6, -12, -85, -118, -24, -97, 68, 65, 81, 63, 18, -41, -23, 101, 121, 74, 114, 24, 55, 39, 43, -17, -18, 92, 9, -119, -56, 72, 58, 88, -15, -58, -88, -105, -113, 73, 113, 1, 18, 84, -15, 88, -77, -97, -23, 40, 117, 70, 90, 17, -88, 54, -51, -3, 125, -45, -118, 104, -1, 37, 127, -61, -3, -92, -91, -56, -2, -93, -74, -7, -15, -69, -46, -1, 67, -71, 64, 22, 71, 103, -79, -33, -54, -93, 22, -98, 105, -92, -104, -88, 47, -123, 81, -34, 118, 41, 20, 60, -57, -81, -89, -111, 86, -47, 75, -73, 64, -28, -14, -67, -23, 70, 67, -3, -58, -18, 105, 82, -21, -107, -26, -111, 60, -29, -114, 90, -50, -49, -79, 65, -107, -124, 102, 29, 74, -47, 31, -115, 125, 32, 26, -69, -128, -119, -56, 24, 11, 54, -43, -31, -30, -107, 116, -80, 86, 110, 30, -126, -47, -100, -13, 58, -109, 87, -119, 48, -4, -15, -66, 31, 66, 106, 87, 63, 97, 84, 52, -57, 60, -28, 126, -93, -67, 3, -94, -63, 112, -102, -114, 16, 76, 105, 75, -80, -35, 90, 82, 94, 29, 1, 10, -75, -33, 39, -7, 0, -49, -29, -13, 48, 53, -28, 102, 26, 1, -94, 68, -81, 84, -39, 77, 41, -11, -59, -46, 90, 3, 68, -4, -71, 20, 11, 62, 46, -77, 118, 23, -10, 40, 126, -87, 49, -71, 78, 15, -34, 7, 110, -19, 69, -2, -56, -39, 67, 49, -13, -36, -99, -69, -22, 10, 21, -22, -80, -44, 92, 93, 117, 95, -29, 78, -25, 61, -60, 24, 59, 70, -89, -128, 47, 123, -66, 42, 74, -42, -21, 37, -20, 123, -42, -100, -118, 10, -43, 106, -47, -50, -125, 121, 10, -66, -79, -17, 117, 43, 25, -33, 7, 25, 84, -43, -107, -10, 34, 85, 35, 56, -16, -78, -47, -88, 7, -75, 114, -113, 12, -12, -103, -3, 87, 109, -31, 40, 81, 99, -45, 13, 95, -112, -48, -102, -86, 41, -55, 6, 7, -120, 78, -112, 71, -18, -89, -78, -4, -68, -113, -118, -69, -98, 63, 92, -26, -20, 117, -94, -11, 16, 89, 5, -91, 67, -118, -30, 81, -20, -109, 125, -81, -11, 111, -80, -55, 101, 27, -75, 15, -18, 0, 50, 11, -48, 97, 82, 90, -13, -33, -97, 45, 101, -72, -122, 108, -97, 21, -101, 9, 48, -8, -119, -2, -85, -47, -77, -27, 127, 16, 53, -34, -112, -57, 64, -20, -11, 70, -51, -110, 115, -53, 118, 123, -14, -114, 91, 9, 82, -56, 89, 69, 108, -59, -38, -97, -94, 117, -51, -88, 71, 66, 27, 62, -40, -75, 10, 118, -107, -69, -6, -101, -3, -29, -111, 62, -11, -125, 127, -13, -84, 53, -67, 45, -80, 79, -34, 68, -13, 85, 100, 114, 116, -14, 73, -32, 123, -26, -47, -128, -36, 71, 96, 92, -118, -89, 114, 124, 78, -77, 26, 69, 13, -45, -38, -76, -97, -124, -87, 12, 113, 100, 86, 57, 123, -53, -36, 26, 106, 17, -83, 20, 127, 60, -83, -31, 12, -91, 11, 95, -120, 37, -23, -12, -16, -40, 94, -14, -118, -29, 3, -116, 111, 13, -128, 61, 77, 55, 35, -88, -5, -28, -51, 83, 56, 126, -90, -71, 24, 22, 103, -11, 83, -93, 40, -59, -84, -14, 90, -65, -81, 38, -64, 42, -64, 10, -87, -101, 67, 37, 77, 71, 63, -4, 14, 108, 87, -83, 83, 68, -128, 23, -23, 53, 111, -4, -44, -48, -109, 107, 27, 110, 54, -67, -91, -46, -105, -111, 35, 10, -73, -1, -4, -44, 108, -126, -50, 124, -5, 63, -91, 100, 80, -8, -34, 39, 88, 53, -6, -63, -24, -122, 73, -108, -52, 115, -124, -39, -127, -104, 31, 41, -75, 12, -71, 86, -42, -83, 19, -33, -53, 20, 17, -40, 26, 17, 103, -25, -14, -37, -59, -43, -34, -86, 3, 53, 46, 65, 100, -122, 116, 61, -90, 109, 58, 15, 78, 99, 16, 17, 20, -2, 32, -14, -120, -13, 77, -24, -37, -92, -77, -80, 46, 52, -112, 8, 70, -54, -88, 103, 26, -67, 8, -96, -79, -18, 26, 6, 41, 60, -81, 2, 28, 99, 41, -64, -58, -38, 62, 4, 21, -112, 91, 92, 10, -85, -80, -43, 34, 118, -59, -17, -7, 20, 108, 66, -73, 102, -71, 9, -127, 54, 36, 45, 32, -94, -59, -35, 104, 24, 55, 19, 98, 108, -109, 112, 76, 81, -116, -74, 15, 125, -96, -24, -63, -4, -112, -69, -39, -65, 87, 65, -50, -2, -45, 102, 112, -54, -44, -24, -120, 76, -119, -24, -92, -65, 82, -18, 124, 4, -65, -62, -80, -114, -12, -84, 80, -124, 78, -75, 126, 70, -59, 46, -65, -73, 6, 11, -128, 20, 127, 98, 89, 61, 14, 77, -89, -19, -38, -17, -121, 103, 34, 79, 33, 35, 119, -4, 3, -16, 126, -49, -106, 122, -6, 124, -103, -109, -127, 113, -90, -48, 58, 54, 8, -112, -127, 29, -75, 50, 107, -66, 22, -50, 68, -44, -104, -120, -47, -57, 96, 125, -44, 124, -36, -97, 42, 71, -92, -81, 40, 107, -108, -22, 39, 32, 68, 64, -124, 104, -80, -41, -88, 93, -1, -27, -79, 35, -7, 25, -43, 85, 80, 98, -105, 125, 62, -91, -56, -122, 117, -83, 43, 21, -15, -14, -48, 11, 50, -55, -88, 51, -58, -105, 112, 98, 14, 67, -75, -117, 96, 74, 16, 96, -31, -100, -90, -49, 73, -75, -126, 8, -52, 67, -43, 71, 12, -41, -35, -104, 127, 99, 40, -46, 61, 74, 73, 13, 62, 106, 109, 1, 41, 92, 5, -84, 33, -56, -104, -17, -31, -103, -10, 75, 36, 25, -34, -31, 87, -103, -42, 126, -21, -39, 60, -115, -102, 11, 63, 31, -93, -92, -25, 89, 86, -26, -19, -124, -97, 73, 3, 10, -48, 89, 30, 59, -123, 119, -19, 29, 28, 53, 23, 106, 5, 82, -24, -93, -106, -95, -101, 125, 83, 101, 41, 23, 66, 123, 75, -123, 94, 23, -5, 93, -14, -9, 50, 21, -26, -17, 101, 81, -95, 104, 81, -58, -109, 102, 36, -123, -90, -26, -62, -80, -89, 95, 5, 102, 127, -26, -21, -127, -46, -66, 26, 62, 64, 49, -35, 32, 93, 120, 38, -12, -38, 66, -119, -38, -1, -107, 70, -70, 121, 4, -120, 59, 82, 2, -81, 123, 115, 90, 34, -79, 67, -48, -40, 16, 54, 118, -95, 71, -28, 42, -113, -98, 94, -100, 38, 91, -113, 20, -68, -23, 93, 7, 50, -58, 79, 119, 26, 78, -61, -46, 76, -25, 40, -47, -68, 22, -81, -45, -121, -111, 63, -79, 50, -115, -117, -30, -45, 57, -9, -22, -63, -44, -85, -104, 27, 20, -70, 95, 97, -72, 58, 0, -31, -60, -84, 99, -88, -106, -81, 87, -119, -14, -121, -49, 102, -47, 13, -78, 20, -95, -25, -28, 83, -98, 11, -86, 11, 80, -116, -47, 87, 24, -15, 47, -47, 9, -109, -99, -54, 4, 5, 1, -102, -114, 56, 90, 22, 47, 9, -70, -101, 117, -74, 56, 122, 61, -34, -49, 10, -57, 96, -19, 97, -120, -119, -110, -59, 35, 27, -103, 46, -93, 85, -125, -26, -104, -93, 121, -89, -90, -43, 89, -122, 101, 124, -109, -68, 5, 108, -14, 17, 119, 38, -58, -80, 63, 39, 68, 2, 86, 114, 122, -83, -62, -30, 7, 101, -28, -101, 118, 62, 0, -64, 108, 47, -38, -4, 96, -30, 96, -67, 40, -16, 87, -62, 98, -48, -100, 59, -22, 117, 42, -128, 80, -94, 119, -96, 54, 13, 100, 38, 36, 82, 40, 68, 8, -119, 13, 43, -79, -113, -52, -114, 43, -54, -117, -120, 115, 14, -85, 61, -97, 105, -40, 77, 103, 109, -4, -37, -113, -123, -126, 111, 39, 114, 17, -122, 70, -41, 103, -112, 93, 112, 107, 117, -56, 7, -36, -10, -114, 85, -120, -42, -109, 0, 110, -1, 91, 39, 57, 99, 113, -83, -4, -117, -94, 74, 106, 42, 50, -128, 120, -16, -46, 27, -42, 48, -96, -58, -14, -9, 119, -17, -126, -121, -57, -67, -8, 22, 97, -30, 82, -74, 38, 0, -113, -76, -47, -89, -15, 105, 119, -84, -126, 20, 127, 47, -110, 94, 105, 57, 94, -16, 98, -24, -5, 1, -65, -123, -107, -49, 123, -54, 92, 66, -96, -126, -50, -44, 52, -11, 35, -120, 36, -89, -84, 110, -112, -79, -4, 80, -78, 11, 61, 15, -70, -30, 31, 17, -99, -105, 67, -2, -19, 12, 15, 93, -67, -4, 97, -80, -94, -35, -21, -4, -116, -4, -110, -113, 88, -99, -40, 47, -53, -116, 14, 99, 2, -69, -61, 49, 70, -86, 19, 46, -88, -56, -3, -40, -28, 74, 92, -30, -82, 106, 8, -102, -117, 63, -1, -56, -91, -6, -39, 41, -9, -121, 3, 112, 18, 114, -126, -102, 32, 64, -105, -83, 105, -20, 41, -79, 121, -9, -83, -14, -62, 19, -13, 45, 49, -33, -37, 64, 85, 109, -112, -92, -9, -67, 86, -94, 16, -55, -125, -88, -95, 8, -54, -61, -4, -57, 91, -74, 19, -66, -83, 16, -98, 43, 3, -4, 118, 7, -66, 97, -93, -74, -85, -93, 72, 127, -18, 42, 106, -94, -67, -108, -51, -116, 69, -72, -77, -11, -78, -98, -7, 18, 101, -80, -64, -125, 124, -21, -93, -23, 26, -71, 117, 71, -86, -97, 43, 101, 94, 126, 30, -44, 108, -46, -122, 90, -23, 93, 59, 15,...
//			deviceId="10.00.20.47";//debug
            //10002174
            System.out.println(config);
            byte[] deviceId2 = getDeviceId(deviceId);
            dos.write(AES.EnParseData(Command.setVRAConfigData(config, getDeviceId(deviceId))));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            System.out.println("设置设备参数成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
        return isError;
    }

    /**
     * 写入设备参数
     *
     * @param request
     * @param DeviceId
     * @param ip
     * @param port
     */
    public static boolean writeVRAConfig(HttpServletRequest request, String deviceId, String ip, int port) {

        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				//TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = 0xd;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
            dos.flush();
            dos.close();
            socket.close();

            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            System.out.println("写入设备参数成功:" + deviceId);

        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


            return false;
        }
        return true;
    }

    /**
     * 手动获取设备状态
     *
     * @param request
     * @param DeviceId
     * @param ip
     * @param port
     */
    public static void getDeviceStatus(HttpServletRequest request, String DeviceId, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
			/*DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = 0x03;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
            dos.flush();
            byte[] data = readInputStream(socket.getInputStream());
            data = AES.DeParseData(data);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


            try {
                //System.out.println("告警、状态数据");
                System.out.println();
                //设备编号
                String deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));
                //获取除去包括设备ID后的数据
                data = PublicMethod.getDataResult(data);
                //告警出现时间
                byte[] dt = new byte[14];
                System.arraycopy(data, 0, dt, 0, dt.length);
                Date infoDataTime = (new SimpleDateFormat("yyyyMMddHHmmss")).parse(new String(dt));
                /**
                 * statusDatas
                 * 除去日期及CYC字节后的数据
                 */
                byte[] statusDatas = new byte[data.length - 14];
                System.arraycopy(data, 14, statusDatas, 0, statusDatas.length);
                //6个字节为一条记录
                int rowCount = statusDatas.length / 6;
                //Connection conn = Proxool.getConnection();
                //  PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_DeviceStatus(DeviceId,infoDataTime,infoLevel,infoType,infoNum,infoContent)VALUES(?,?,?,?,?,?)");
                String sql = "insert into wr values()";
                StringBuffer stringBuffer = new StringBuffer("insert into wr values ");
                for (int i = 0; i < rowCount; i++) {
                    byte[] status = new byte[6];
                    System.arraycopy(statusDatas, i * 6, status, 0, status.length);
                    //信息级别
                    byte[] level = new byte[2];
                    stringBuffer.append("(");
                    stringBuffer.append("'");
                    stringBuffer.append(deviceId);
                    stringBuffer.append("'");
                    stringBuffer.append(",");
                    System.arraycopy(status, 0, level, 0, level.length);
                    String infoLevel = PublicMethod.byteToInt2(level) + "";
                    stringBuffer.append(infoLevel);
                    stringBuffer.append(",");
                    //信息类型
                    String infoType = status[2] + "";//Integer.toHexString(status[2]&0xff);
                    stringBuffer.append(infoType);
                    stringBuffer.append(",");
                    //信息编号
                    String infoNum = status[3] + "";//Integer.toHexString(status[3]&0xff);
                    stringBuffer.append(infoNum);
                    stringBuffer.append(",");
                    //信息内容
                    byte[] content = new byte[2];
                    System.arraycopy(status, 4, content, 0, content.length);
                    String infoContent = PublicMethod.byteToInt2(content) + "";
                    stringBuffer.append(infoContent);
                    stringBuffer.append("),");
/*			      stmt.setString(1, deviceId);
				  java.sql.Timestamp cdate = new java.sql.Timestamp(infoDataTime.getTime());
		          stmt.setTimestamp(2,cdate);
		          stmt.setString(3, infoLevel);
		          stmt.setString(4, infoType);
		          stmt.setString(5, infoNum);
		          stmt.setString(6, infoContent);
			      stmt.executeUpdate();*/
                    System.out.println("---");
                }
                System.out.println(stringBuffer.subSequence(0, stringBuffer.length() - 1).toString());
                System.out.println("获取设备状态成功:" + DeviceId);
/*			    stmt.close();
				conn.close();*/

            } catch (Exception e) {
                System.out.println("解析告警、状态数据错误");
                logger.error("解析告警、状态数据错误：" + ip, e);
            }
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


        }
    }


    public static String getDeviceStatus2(HttpServletRequest request, String DeviceId, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
			/*DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = 0x03;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
            dos.flush();
            byte[] data = readInputStream(socket.getInputStream());
            data = AES.DeParseData(data);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


            try {
                //System.out.println("告警、状态数据");
                System.out.println();
                //设备编号
                String deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));
                return deviceId;
                //获取除去包括设备ID后的数据
/*			  data = PublicMethod.getDataResult(data);
			  //告警出现时间
			  byte[] dt = new byte[14];
			  System.arraycopy(data, 0, dt, 0, dt.length);
			  Date infoDataTime=(new SimpleDateFormat("yyyyMMddHHmmss")).parse(new String(dt));
			  *//**
                 * statusDatas
                 * 除去日期及CYC字节后的数据
                 *//*
			  byte[] statusDatas=new byte[data.length-14];
			  System.arraycopy(data, 14, statusDatas, 0, statusDatas.length);
			  //6个字节为一条记录
			  int rowCount=statusDatas.length/6;
			  //Connection conn = Proxool.getConnection();
			//  PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_DeviceStatus(DeviceId,infoDataTime,infoLevel,infoType,infoNum,infoContent)VALUES(?,?,?,?,?,?)");
			  String sql ="insert into wr values()";
			  StringBuffer stringBuffer = new StringBuffer("insert into wr values ");
			  for(int i=0;i<rowCount;i++){
				  byte[] status=new byte[6];
				  System.arraycopy(statusDatas, i*6, status, 0, status.length);
			      //信息级别
			      byte[] level = new byte[2];
			      stringBuffer.append("(");
			      stringBuffer.append("'");
			      stringBuffer.append(deviceId);
			      stringBuffer.append("'");
			      stringBuffer.append(",");
			      System.arraycopy(status, 0, level, 0, level.length);
			      String infoLevel=PublicMethod.byteToInt2(level)+"";
			      stringBuffer.append(infoLevel);
			      stringBuffer.append(",");
			      //信息类型
			      String infoType=status[2]+"";//Integer.toHexString(status[2]&0xff);
			      stringBuffer.append(infoType);
			      stringBuffer.append(",");
			      //信息编号
			      String infoNum=status[3]+"";//Integer.toHexString(status[3]&0xff);
			      stringBuffer.append(infoNum);
			      stringBuffer.append(",");
			      //信息内容
			      byte[] content = new byte[2];
			      System.arraycopy(status, 4, content, 0, content.length);
			      String infoContent=PublicMethod.byteToInt2(content)+"";
			      stringBuffer.append(infoContent);
			      stringBuffer.append("),");
			      stmt.setString(1, deviceId);
				  java.sql.Timestamp cdate = new java.sql.Timestamp(infoDataTime.getTime());
		          stmt.setTimestamp(2,cdate);
		          stmt.setString(3, infoLevel);
		          stmt.setString(4, infoType);
		          stmt.setString(5, infoNum);
		          stmt.setString(6, infoContent);
			      stmt.executeUpdate();
			      System.out.println("---");
			  }*/
                //  System.out.println(stringBuffer.subSequence(0, stringBuffer.length()-1).toString());
                //   System.out.println("获取设备状态成功:"+DeviceId);
/*			    stmt.close();
				conn.close();*/

            } catch (Exception e) {
                System.out.println("解析告警、状态数据错误");
                logger.error("解析告警、状态数据错误：" + ip, e);
                return null;
            }
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            return null;
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


        }
    }

    public static String getDeviceStatus3(HttpServletRequest request, String DeviceId, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
			/*DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = 0x03;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
            dos.flush();
            byte[] data = readInputStream(socket.getInputStream());
            data = AES.DeParseData(data);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


            try {
                //System.out.println("告警、状态数据");
                System.out.println();
                //设备编号
                String deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));
                //获取除去包括设备ID后的数据
                data = PublicMethod.getDataResult(data);
                //告警出现时间
                byte[] dt = new byte[14];
                System.arraycopy(data, 0, dt, 0, dt.length);
                Date infoDataTime = (new SimpleDateFormat("yyyyMMddHHmmss")).parse(new String(dt));
                /**
                 * statusDatas
                 * 除去日期及CYC字节后的数据
                 */
                byte[] statusDatas = new byte[data.length - 14];
                System.arraycopy(data, 14, statusDatas, 0, statusDatas.length);
                //6个字节为一条记录
                int rowCount = statusDatas.length / 6;
                //Connection conn = Proxool.getConnection();
                //  PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_DeviceStatus(DeviceId,infoDataTime,infoLevel,infoType,infoNum,infoContent)VALUES(?,?,?,?,?,?)");
                String sql = "insert into wr values()";
                StringBuffer stringBuffer = new StringBuffer("insert into wr values ");
                for (int i = 0; i < rowCount; i++) {
                    byte[] status = new byte[6];
                    System.arraycopy(statusDatas, i * 6, status, 0, status.length);
                    //信息级别
                    byte[] level = new byte[2];
                    stringBuffer.append("(");
                    stringBuffer.append("'");
                    stringBuffer.append(deviceId);
                    stringBuffer.append("'");
                    stringBuffer.append(",");
                    System.arraycopy(status, 0, level, 0, level.length);
                    String infoLevel = PublicMethod.byteToInt2(level) + "";
                    stringBuffer.append(infoLevel);
                    stringBuffer.append(",");
                    //信息类型
                    String infoType = status[2] + "";//Integer.toHexString(status[2]&0xff);
                    stringBuffer.append(infoType);
                    stringBuffer.append(",");
                    //信息编号
                    String infoNum = status[3] + "";//Integer.toHexString(status[3]&0xff);
                    stringBuffer.append(infoNum);
                    stringBuffer.append(",");
                    //信息内容
                    byte[] content = new byte[2];
                    System.arraycopy(status, 4, content, 0, content.length);
                    String infoContent = PublicMethod.byteToInt2(content) + "";
                    stringBuffer.append(infoContent);
                    stringBuffer.append("), ");
/*			      stmt.setString(1, deviceId);
				  java.sql.Timestamp cdate = new java.sql.Timestamp(infoDataTime.getTime());
		          stmt.setTimestamp(2,cdate);
		          stmt.setString(3, infoLevel);
		          stmt.setString(4, infoType);
		          stmt.setString(5, infoNum);
		          stmt.setString(6, infoContent);
			      stmt.executeUpdate();*/
                    //stringBuffer.append(";");
                    System.out.println(i);
                }
                //return stringBuffer.toString();
                System.out.println(stringBuffer.subSequence(0, stringBuffer.length() - 1).toString());
                System.out.println("获取设备状态成功:" + DeviceId);
/*			    stmt.close();
				conn.close();*/
                return stringBuffer.toString();

            } catch (Exception e) {
                System.out.println("解析告警、状态数据错误");
                logger.error("解析告警、状态数据错误：" + ip, e);
                return null;
            }
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            return null;
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


        }
    }


    /**
     * 手动获取设备状态
     * @param request
     * @param DeviceId
     * @param ip
     * @param port
     */
/*	public static void getZigbeeDeviceStatus(HttpServletRequest request,String DeviceId,String ip,int port){
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		String zbAddrStr="";
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", DeviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			byte cmd=0x13;
			dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
			dos.flush();
			byte[] data = readInputStream(socket.getInputStream());
			data=AES.DeParseData(data);
			dos.close();
			socket.close();
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


			try {
			  //System.out.println("告警、状态数据");
			  System.out.println();
			  //设备编号
			  String deviceId = PublicMethod.getFormatDeviceID(PublicMethod.getDeviceID(data));
			  //获取除去包括设备ID后的数据
			  data = PublicMethod.getDataResult(data);
			  //告警出现时间
			  byte[] dt = new byte[14];
			  System.arraycopy(data, 0, dt, 0, dt.length);
			  Date infoDataTime=(new SimpleDateFormat("yyyyMMddHHmmss")).parse(new String(dt));
			  *//**
     * statusDatas
     * 除去日期及CYC字节后的数据  X  PublicMethod.getDataResult(data)已经出去CRC
     *//*
			  byte[] statusDatas=new byte[data.length-14];
			  System.arraycopy(data, 14, statusDatas, 0, statusDatas.length);
			  //10个字节为一条记录
			  int rowCount=statusDatas.length/10;
			  Connection conn = Proxool.getConnection();
			  PreparedStatement stmt = conn.prepareStatement("INSERT INTO T_VARTRIVER_DeviceStatus(DeviceId,infoDataTime,infoLevel,infoType,infoNum,infoContent)VALUES(?,?,?,?,?,?)");
			  for(int i=0;i<rowCount;i++){
				  byte[] zbAddr=new byte[4];
				  System.arraycopy(statusDatas, i*10, zbAddr, 0, zbAddr.length);
				  zbAddrStr = PublicMethod.getFormatDeviceID(zbAddr);
				  byte[] status=new byte[6];
				  System.arraycopy(statusDatas, i*10+4, status, 0, status.length);
			      //信息级别
			      byte[] level = new byte[2];
			      System.arraycopy(status, 0, level, 0, level.length);
			      String infoLevel=PublicMethod.byteToInt2(level)+"";
			      //信息类型
			      String infoType=status[2]+"";//Integer.toHexString(status[2]&0xff);
			      //信息编号
			      String infoNum=status[3]+"";//Integer.toHexString(status[3]&0xff);
			      //信息内容
			      byte[] content = new byte[2];
			      System.arraycopy(status, 4, content, 0, content.length);
			      String infoContent=PublicMethod.byteToInt2(content)+"";
			      stmt.setString(1, deviceId+"."+zbAddrStr);
				  java.sql.Timestamp cdate = new java.sql.Timestamp(infoDataTime.getTime());
		          stmt.setTimestamp(2,cdate);
		          stmt.setString(3, infoLevel);
		          stmt.setString(4, infoType);
		          stmt.setString(5, infoNum);
		          stmt.setString(6, infoContent);
			      stmt.executeUpdate();
			  }
		        System.out.println("获取设备状态成功:"+deviceId+"."+zbAddrStr);
			    stmt.close();
				conn.close();
		    } catch (Exception e) {
		    	System.out.println("解析告警、状态数据错误");
		    	logger.error("解析告警、状态数据错误："+ip,e);
		    }
		} catch (Exception e) {
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}


			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
		}
	}*/

    /**
     * 重启设备
     *
     * @param request
     * @param DeviceId
     * @param ip
     * @param port
     */
    public static void startupDevice(HttpServletRequest request, String deviceId, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;

        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = (byte) Integer.parseInt("FF", 16);
            //byte[] data=new byte[1];
            //data[0]=0x00;
            //dos.write(Command.getSentCommand(cmd,data,DeviceId));
            dos.write(AES.EnParseData(Command.getSentCommand(cmd)));
            dos.flush();
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            System.out.println("重启设备:" + deviceId);
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
    }

    /**
     * TODO 获取日志
     *
     * @param DeviceId
     * @param ip
     * @param port
     * @return
     */
    public static VRALogBean getVRALog(HttpServletRequest request, String deviceId, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        VRALogBean logBean = null;
        int index = 0;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/


            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            // 日志文件数
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(AES.EnParseData(Command.getVRALogFileNum()));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            logByte = AES.DeParseData(logByte);
            logBean = new VRALogBean(logByte);
            System.out.println(ip + " 设备日志文件个数为：" + logBean.getLogNum());
            //request.getSession().setAttribute("progressTotal", logBean.getLogNum()+"");
            //request.getSession().setAttribute("progressNum", "0");
            byte[] d = getDeviceId(deviceId);
            byte type = 0x09;
            List<VRALogBean> list = new ArrayList<VRALogBean>();
            for (index = 0; index < logBean.getLogNum(); index++) {
                //request.getSession().setAttribute("progressNum",(index+1)+"");
                // 日志文件描述
                dos = new DataOutputStream(socket.getOutputStream());
                dos.write(AES.EnParseData(Command.getVRASendOK(d, type)));
                dos.flush();

                logByte = readInputStream(socket.getInputStream());
                logByte = AES.DeParseData(logByte);
                int fileSize = logBean.setFileName(index, logByte);
                System.out.println("日志文件:" + logBean.getFileName() + "/" + fileSize);
//			    if(fileSize==0){
//			    	System.out.println("日志文件长度为0");
//			    	continue;
//			    }
                //日志文件内容
                dos = new DataOutputStream(socket.getOutputStream());
                dos.write(AES.EnParseData(Command.getVRASendOK(d, type)));
                dos.flush();

                logByte = readInputStream(socket.getInputStream());
                logByte = AES.DeParseData(logByte);
                logBean.setFileContent(index, logByte);
                //保存文件
                VRALogBean saveLog2 = logBean.saveLog2();
                list.add(saveLog2);
            }
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(AES.EnParseData(Command.getVRASendOK(d, type)));
            dos.flush();

            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            //request.getSession().setAttribute("progressNum", logBean.getLogNum()+"");
            return logBean;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
        return logBean;
    }

    /*
	 * TODO 软件更新
	 */
    public static boolean VRASoftWareUpdate(String deviceId, byte[] data, String ip, int port) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        boolean isOk = false;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
		/*	if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(),20934);// connBean.getPort() //20160412
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            //升级请求
            dos = new DataOutputStream(socket.getOutputStream());
            byte cmd = 0x6;
            byte high = (byte) ((1 & 0xff00) >> 8);
            byte low = (byte) (1 & 0x00ff);
            byte[] fileNum = new byte[2];
            fileNum[0] = high;
            fileNum[1] = low;
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, fileNum, deviceId)));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            isOk = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            if (isOk == false) {
                dos.close();
                socket.close();
                System.out.println("请求更新失败");
                return isOk;
            }
            System.out.println("升级文件数发送成功...");
            //发送文件大小
            cmd = 0x7;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(AES.EnParseData(Command.getSentCommand(cmd, "UpdateFile.dat:" + data.length, deviceId)));
            dos.flush();
            logByte = readInputStream(socket.getInputStream());
            isOk = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            if (isOk == false) {
                dos.close();
                socket.close();
                System.out.println("请求更新：文件大小失败");
                return isOk;
            }
            System.out.println("升级文件大小发送成功...");
            //发送文件内容
//			high = (byte)((1 & 0xff00)>>8);
//			low = (byte)(1 & 0x00ff);

            ByteArrayInputStream bin = new ByteArrayInputStream(data);
            byte[] buffer = new byte[10240];
            int size;
            while ((size = bin.read(buffer)) != -1) {
                dos = new DataOutputStream(socket.getOutputStream());
                byte[] newData = new byte[size + 2];
                newData[0] = high;
                newData[1] = low;
                System.arraycopy(buffer, 0, newData, 2, size);
                byte[] d = Command.getSentCommand(cmd, newData, deviceId);
                dos.write(AES.EnParseData(d));
                dos.flush();
                logByte = readInputStream(socket.getInputStream());
                isOk = PublicMethod.getCommandResult(AES.DeParseData(logByte));
                if (isOk == false) {
                    dos.close();
                    socket.close();
                    System.out.println("请求更新：发送更新包失败");
                    return isOk;
                }
            }
            //升级结束
            cmd = 0x8;
            dos = new DataOutputStream(socket.getOutputStream());
            dos.write(Command.getSentCommand(cmd));
            dos.flush();
            logByte = readInputStream(socket.getInputStream());
            isOk = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            if (isOk == false) {
                dos.close();
                socket.close();
                System.out.println("升级失败");
                return isOk;
            }
            System.out.println("升级文件更新完成...");
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            return isOk;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
                return false;
            }


        }
        return false;
    }

    /**
     * 显示视频菜单
     * @param ip
     * @param port
     * @return
     */
/*	public static boolean sendCameraMenu(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraMenu()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("显示视频菜单");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraMenu(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraMenu()));
			dos.flush();
			dos.close();
			socket.close();
			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}
			System.out.println("显示视频菜单");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}

		}
		return isError;
	}*/

    /**
     * 视频上移命令
     * @param ip
     * @param port
     * @return
     */
/*	public static boolean sendCameraUp(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraUp()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频上移命令");
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraUp(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraUp()));
			dos.flush();
			dos.close();
			socket.close();

			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}

			System.out.println("视频上移命令");
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}


		}
		return isError;
	}
	*//**
     * 视频下移命令
     * @param ip
     * @param port
     * @return
     *//*
	public static boolean sendCameraDown(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraDown()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频下移命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraDown(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraDown()));
			dos.flush();
			dos.close();
			socket.close();

			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}

			System.out.println("视频下移命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}

			} catch (IOException e1) {
			}


		}
		return isError;
	}
	*//**
     * 视频上翻屏命令
     * @param ip
     * @param port
     * @return
     *//*
	public static boolean sendCameraLeft(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraLeft()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频上翻屏命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraLeft(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;

		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraLeft()));
			dos.flush();
			dos.close();
			socket.close();
			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}


			System.out.println("视频上翻屏命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}



		}
		return isError;
	}
	*//**
     * 视频下翻屏命令
     * @param ip
     * @param port
     * @return
     *//*
	public static boolean sendCameraRight(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraRight()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频下翻屏命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraRight(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}

			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraRight()));
			dos.flush();
			dos.close();
			socket.close();

			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}

			System.out.println("视频下翻屏命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}



		}
		return isError;
	}
	*//**
     * 视频选择命令
     * @param ip
     * @param port
     * @return
     *//*
	public static boolean sendCameraChoose(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraChoose()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频选择命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraChoose(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;

		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraChoose()));
			dos.flush();
			dos.close();
			socket.close();
			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}

			System.out.println("视频选择命令");
			return true;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}


		}
		return isError;
	}
	*/

    /**
     * 视频确定命令
     *
     * @param ip
     * @param port
     * @return
     *//*
	public static boolean sendCameraOk(String ip,int port) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		try {
			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraOk()));
			dos.flush();
			dos.close();
			socket.close();
			System.out.println("视频确定命令");
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
		}
		return isError;
	}
	public static boolean sendCameraOk(String ip,int port,String deviceId) {
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		boolean isError =false;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}

			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(AES.EnParseData(Command.cameraOk()));
			dos.flush();
			dos.close();
			socket.close();
			//for nabto
			if(connBean.getConnType().equals("3")){
				//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
			}
			System.out.println("视频确定命令");
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
				//for nabto
				if(connBean.getConnType().equals("3")){
					//NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getAperturePort());
				}
			} catch (IOException e1) {
			}

		}
		return isError;
	}*/

    //本地服务连接监控用（平台服务 和采集服务通讯）
    public static String sendClientCommand(String ip, int port, String command) {
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        String configStr = "";
        try {
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            //byte cmd=(byte)0xC6;
            byte cmd = (byte) 0x64;
            dos.write(Command.getSentCommand(cmd, command));
            dos.flush();
            byte[] resultBytes = readInputStream(socket.getInputStream());
            if (resultBytes == null)
                configStr = "error";
            else
                configStr = new String(PublicMethod.getUserDataResult(resultBytes));
//			System.out.println(configStr);
            dos.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip + ":" + port);
            logger.error("socket连接失败：" + ip + ":" + port, e);
            configStr = "error";
            //e.printStackTrace();
        }

        return configStr;
    }

    private static byte[] readInputStream(InputStream _in) throws IOException {
        if (_in == null)
            throw new IOException("InputStream can't be null!");

        // /读取包头
        int haveread = 0;
        int readed = 14;
        //
        byte[] head = new byte[14];
        //
        while (haveread < readed) {
            int s = _in.read(head, haveread, readed - haveread);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }
        // 读取数据类型
        int dataType = head[1];
        // 读取加密长度
        byte[] AES = new byte[2];
        AES[0] = head[10];
        AES[1] = head[11];
        int AESLen = PublicMethod.byteToInt2(AES);
        // 数据长度
        int dataLen = AESLen - 2;

        haveread = 0;
        byte[] data = new byte[dataLen];

        // 获取数据
        while (haveread < dataLen) {
            int s = _in.read(data, haveread, dataLen - haveread);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }

        // CRC
        byte[] CRC = new byte[1];
        haveread = 0;
        while (haveread < 1) {
            int s = _in.read(CRC, haveread, 1);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }
        // 返回全部数据
        int allLen = 14 + dataLen + 1;
        byte[] allData = new byte[allLen];
        System.arraycopy(head, 0, allData, 0, head.length);
        System.arraycopy(data, 0, allData, 14, data.length);
        allData[allLen - 1] = CRC[0];
        return allData;
    }

    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    public static byte[] getDeviceId(String deviceId) {
//    	byte[] b=new byte[4];
//    	StringTokenizer token=new StringTokenizer(deviceId,".");
//    	int i=0;
//		while(token.hasMoreTokens()){
//			b[i]=(byte)Integer.parseInt(token.nextToken());
//			i++;
//		}
//    	return b;

        //StringTokenizer token=new StringTokenizer(deviceId,".");
        String[] kens = deviceId.split("\\.");
        //Object[] kens=Unit.tokenToArray(deviceId,".");
        byte[] b = new byte[kens.length];
        for (int i = 0; i < kens.length; i++) {
            byte[] tmp = ((String) kens[i]).getBytes();
            byte _b0 = uniteBytes(tmp[0], tmp[1]);
            ;
            b[i] = _b0;
        }
        return b;

    }

    //////////////////////////////////// 开关量命令开始
    public static VRASwitchBean getSwitchOut(String ip, int port, byte[] dataValue, String deviceId) {
        return getSwitch(ip, port, dataValue, deviceId, false);
    }

    public static VRASwitchBean getSwitchIn(String ip, int port, byte[] dataValue, String deviceId) {
        return getSwitch(ip, port, dataValue, deviceId, true);
    }

    public static VRASwitchBean getSwitch(String ip, int port, byte[] dataValue, String deviceId, boolean isIn) {
        VRASwitchBean switchBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte cmd = 0x15;
        byte switchType = 0x1;
        if (isIn) switchType = 0x5;
        //for nabto
        IpPortBean connBean = null;

        try {

            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 10000);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSwitch(cmd, switchType, dataValue, deviceId));
            dos.write(bSend);
            //[85, 21, -128, 0, 0, 0, 0, 0, 0, 0, 0, 32, -41, -123, -78, 93, -33, -82, -74, 13, -57, 76, 99, 49, 31, 37, -75, -87, 65, 112, 118, 26, -89, -54, -81, -94, 49, 36, -21, -8, 55, 23, -35, 18, 124]
            //[85, 21, -128, 0, 0, 0, 0, 0, 0, 0, 0, 32, -2, -11, 82, 71, 122, -3, 2, -110, 5, -60, -16, -114, -2, -13, 76, -101, 21, 72, 29, 91, -85, -52, -66, 56, -88, 59, -84, -123, 120, 80, 112, 0, 68]
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            recByte = AES.DeParseData(recByte);
            //解析并复制groupList
            //switchBean = new VRASwitchBean(PublicMethod.getDataResult(recByte),deviceId,Integer.toString(switchType));
            switchBean = new VRASwitchBean();
            boolean isOK = switchBean.PaserData(PublicMethod.getDataResult(recByte), deviceId, Integer.toString(switchType));
            if (!isOK) {
                System.out.println("getSwitch参数解析错误：" + ip);
            }
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            return switchBean;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

        }
        return switchBean;
    }

    /*
	 * TODO 控制输入/输出开关量
	 * @param switchCtrlData
	 */
    public static boolean setSwitchCtrlOut(String ip, int port, String deviceId, byte[] sendConfData) {
        byte switchType = 0x2;
        byte[] switchConfData = new byte[sendConfData.length + 1];
        switchConfData[0] = switchType;
        System.arraycopy(sendConfData, 0, switchConfData, 1, sendConfData.length);
        return setSwitch(ip, port, deviceId, switchConfData);
    }

    /*
	 * TODO 控制输入/输出开关量
	 * @param switchCtrlData
	 */
    public static boolean setSwitchConfOut(String ip, int port, String deviceId, byte[] sendConfData) {
        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte switchType = 0x3;
        byte[] switchConfData = new byte[sendConfData.length + 1];
        switchConfData[0] = switchType;
        System.arraycopy(sendConfData, 0, switchConfData, 1, sendConfData.length);
        //for nabto
        IpPortBean connBean = null;

        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.setVRASwitchData(switchConfData, getDeviceId(deviceId)));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult66(AES.DeParseData(recByte));
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            System.out.println("输出开关量配置命令发送成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
        return isError;
    }

    /*
	 * TODO 获取输出开关量配置
	 * @param dataValue 欲获取的GroupID的byte数组
	 */
    public static VRASwitchConfBean getSwitchConfOut(String ip, int port, String deviceId) {
        VRASwitchConfBean switchConfBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte cmd = 0x15;
        byte switchType = 0x4;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId)*/
            ;
		/*	if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 10000);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSwitchConf(cmd, switchType, deviceId));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            recByte = AES.DeParseData(recByte);

            //《《《分为输入配置 输出配置两种情况
            //解析并复制groupList
            switchConfBean = new VRASwitchConfBean(PublicMethod.getDataResult(recByte), deviceId);
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            //return switchConfBean;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
        return switchConfBean;
    }

    public static boolean setSwitchConfIn(String ip, int port, String deviceId, byte[] sendConfData) {
        byte switchType = 0x6;
        byte[] switchConfData = new byte[sendConfData.length + 1];
        switchConfData[0] = switchType;
        System.arraycopy(sendConfData, 0, switchConfData, 1, sendConfData.length);
        return setSwitch(ip, port, deviceId, switchConfData);
    }

    /*
	 * TODO 获取输入/输出开关量
	 * @param dataValue 欲获取的GroupID的byte数组
	 * @param isIn 是否是输入
	 */
    public static VRASwitchConfInBean getSwitchConfIn(String ip, int port, byte[] dataValue, String deviceId) {
        VRASwitchConfInBean switchBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte cmd = 0x15;
        byte switchType = 0x7;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 10000);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSwitch(cmd, switchType, dataValue, deviceId));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            recByte = AES.DeParseData(recByte);
            //解析并复制groupList
            //switchBean = new VRASwitchBean(PublicMethod.getDataResult(recByte),deviceId,Integer.toString(switchType));
            switchBean = new VRASwitchConfInBean();
            boolean isOK = switchBean.PaserData(PublicMethod.getDataResult(recByte), deviceId);
            if (!isOK) {
                System.out.println("getSwitch参数解析错误：" + ip);
            }
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

            return switchBean;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }


        }
        return switchBean;
    }

    public static boolean setSwitch(String ip, int port, String deviceId, byte[] switchCtrlData) {
        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);*/
		/*	if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}
			*/
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.setVRASwitchData(switchCtrlData, getDeviceId(deviceId)));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult66(AES.DeParseData(recByte));
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            System.out.println("开关量控制命令发送成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }

        }
        return isError;
    }

    //直流电机控制命令发送
    public static boolean motorsCtrl(String ip, int port, String deviceId, byte[] data) {
        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);	*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
			}*/

            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSentCommand((byte) 0x17, data, deviceId));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult66(AES.DeParseData(recByte));
            dos.close();
            socket.close();
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            System.out.println("直流电机控制命令发送成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
                //for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
            } catch (IOException e1) {
            }

        }
        return isError;
    }


    //直流电机获得传感器开关信息
    public static byte[] getMotorSensor(String ip, int port, String deviceId) {
//		boolean isSucce =false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte[] data = new byte[1];
        data[0] = 0x1;
        try {
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSentCommand((byte) 0x18, data, deviceId));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            recByte = AES.DeParseData(recByte);
//			System.out.println("传感器数据："+PublicMethod.bytesToHexString(recByte));
            dos.close();
            socket.close();
            System.out.println("直流电机传感器信息获取成功:" + deviceId);
            return recByte;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
        }
        return null;
    }

    ///////////////////////////////////IPC

    public static String getIpc(String deviceId) {

        String ip = "";
        int port = 0;

        String configStr = "";
        IpcBean ipcBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();

			*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(nabtoSleep);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            dos.write(Command.getIpcDev(getDeviceId(deviceId)));
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
//			configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            //ipcBean = new IpcBean(configStr);
            dos.close();
            socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return configStr;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
        }
        return configStr;
    }

    public static String getIpc2(String ip, int port, String deviceId) {

		/*String ip="";
		int port=0;*/

        String configStr = "";
        IpcBean ipcBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();

			*/
			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(nabtoSleep);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);

                    return null;
                }
            }
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            dos.write(Command.getIpcDev(getDeviceId(deviceId)));
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
//			configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            //ipcBean = new IpcBean(configStr);
            dos.close();
            socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return configStr;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
        }
        return configStr;
    }


    /*
	 * TODO 添加设备
	 * type get add delete edit
	 */
    public static boolean setIpc(String type, String config, String deviceId) {

        String ip = "";
        int port = 0;

        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = null;
            if (type.equals("add"))
                cmd = Command.addIpcDev(config, getDeviceId(deviceId));//添加命令
            if (type.equals("delete"))
                cmd = Command.delIpcDev(config, getDeviceId(deviceId));//删除命令
            if (type.equals("edit"))
                cmd = Command.editIpcDev(config, getDeviceId(deviceId));//编辑命令
//			if(type.equals("get"))
//				cmd=Command.getIpcDev(getDeviceId(deviceId));//编辑命令
            dos.write(AES.EnParseData(cmd));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            System.out.println("添加IPC成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

            } catch (IOException e1) {
            }

        }
        return isError;
    }


    /*
	 * TODO 添加设备
	 * type get add delete edit
	 */
    public static boolean setIpc1(String type, String config, String deviceId, String ip, int port) {

	/*	String ip="";
		int port=0;*/

        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			ip=connBean.getIp();
			port=connBean.getPort();
			*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = null;
            if (type.equals("add"))
                cmd = Command.addIpcDev(config, getDeviceId(deviceId));//添加命令
            if (type.equals("delete"))
                cmd = Command.delIpcDev(config, getDeviceId(deviceId));//删除命令
            if (type.equals("edit"))
                cmd = Command.editIpcDev(config, getDeviceId(deviceId));//编辑命令
//			if(type.equals("get"))
//				cmd=Command.getIpcDev(getDeviceId(deviceId));//编辑命令
            dos.write(AES.EnParseData(cmd));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            System.out.println("添加IPC成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

            } catch (IOException e1) {
            }

        }
        return isError;
    }

    public static String getIpcProxy(String deviceId) {

        String ip = "";
        int port = 0;

        String configStr = "";
        IpcProxyBean ipcProxyBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();
			*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(nabtoSleep);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = Command.getIpcProxy("", getDeviceId(deviceId));//删除命令23ring ip="";

            dos.write(cmd);
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
//			configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            //ipcProxyBean = new IpcProxyBean(configStr);
            dos.close();
            socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return configStr;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
        }
        return configStr;
    }

    public static String getIpcProxy1(String deviceId, String ip, int port) {

/*		String ip="";
		int port=0;*/

        String configStr = "";
        IpcProxyBean ipcProxyBean = null;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        IpPortBean connBean = null;
        try {

            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();
			*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(nabtoSleep);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = Command.getIpcProxy("", getDeviceId(deviceId));//删除命令23ring ip="";

            dos.write(cmd);
            dos.flush();
            byte[] configBytes = readInputStream(socket.getInputStream());
            configBytes = AES.DeParseData(configBytes);
//			configStr = new String(configBytes);
            configStr = new String(PublicMethod.getDataResult(configBytes));
            //ipcProxyBean = new IpcProxyBean(configStr);
            dos.close();
            socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
            return configStr;
        } catch (IOException e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
            //for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
        }
        return configStr;
    }


	/*
	 * TODO 设置设备参数
	 *  type get add delete edit
	 */

    public static String getCommandResultStr(byte[] data) {
        // 85 5 0 50 10 0 0 0 85 4 0 8 0 6 16 1 0 19 111 107 4
        byte[] AES = new byte[2];
        AES[0] = data[12];
        AES[1] = data[13];
        int AESLen = PublicMethod.byteToInt2(AES);
        byte[] value = new byte[AESLen - 4];
        System.arraycopy(data, 18, value, 0, value.length);
        String result = new String(value);
        System.out.println("添加代理返回值" + result);
        return result;

    }

    public static String setIpcProxyEx(String type, String config, String deviceId) {

        String ip = "";
        int port = 0;

        String proxyRe = "00";
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = null;
            if (type.equals("add"))
                cmd = Command.addIpcProxy(config, getDeviceId(deviceId));//添加命令
//			if(type.equals("get"))
//

            dos.write(AES.EnParseData(cmd));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
//			isError=PublicMethod.getCommandResult(AES.DeParseData(logByte));
            proxyRe = getCommandResultStr(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            System.out.println("添加IPC成功:" + deviceId);
            return proxyRe;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

            } catch (IOException e1) {
            }

        }
        return proxyRe;
    }

    public static String setIpcProxyEx1(String type, String config, String deviceId, String ip, int port) {

/*		String ip="";
		int port=0;*/

        String proxyRe = "00";
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = null;
            if (type.equals("add"))
                cmd = Command.addIpcProxy(config, getDeviceId(deviceId));//添加命令
//			if(type.equals("get"))
            dos.write(AES.EnParseData(cmd));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
//			isError=PublicMethod.getCommandResult(AES.DeParseData(logByte));
            proxyRe = getCommandResultStr(AES.DeParseData(logByte));
            dos.close();
            socket.close();
            System.out.println("添加IPC成功:" + deviceId);
            return proxyRe;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

            } catch (IOException e1) {
            }

        }
        return proxyRe;
    }


    public static boolean setIpcProxy(String type, String config, String deviceId) {

        String ip = "";
        int port = 0;

        boolean isError = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        //for nabto
        IpPortBean connBean = null;
        try {
            //for nabto
            int currReTryTimes = reTryTimes;
/*			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);

			ip=connBean.getIp();
			port=connBean.getPort();*/

			/*if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}*/
            //for nabto 20150909
            boolean reTry = true;
            int reCot = 0;
            while (reTry && reCot++ < currReTryTimes) {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), 10000);
                    reTry = false;
                } catch (Exception ex) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    socket = null;
                    System.out.println("socket连接失败retry " + ip + "：" + port + "," + reCot);
                }
            }
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] cmd = null;
            if (type.equals("add"))
                cmd = Command.addIpcProxy(config, getDeviceId(deviceId));//添加命令
//			if(type.equals("get"))
//

            dos.write(AES.EnParseData(cmd));
            dos.flush();
            byte[] logByte = readInputStream(socket.getInputStream());
            isError = PublicMethod.getCommandResult(AES.DeParseData(logByte));
            dos.close();
            socket.close();

            System.out.println("添加IPC成功:" + deviceId);
            return isError;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();

            } catch (IOException e1) {
            }

        }
        return isError;
    }

/*	///DMA---------------------
	public static DMAParamsBean getDMAConfig(String ip,int port,String deviceId) {

		String configStr = "";
		DMAParamsBean paramsBean = null;
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		IpPortBean connBean=null;
		try {

			//for nabto
			int currReTryTimes=reTryTimes;
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}
			//for nabto 20150909
			boolean reTry=true;
			int reCot=0;
			while(reTry&&reCot++<currReTryTimes){
				try{
					socket = new Socket();
					socket.connect(new InetSocketAddress( ip, port ), 10000);
					reTry=false;
				}catch(Exception ex){
					try{Thread.sleep(nabtoSleep);}catch(Exception e){}
					socket=null;
					System.out.println("socket连接失败retry "+ip+"："+port+","+reCot);
				}
			}
//			socket = new Socket(ip, port);
//			socket.connect(new InetSocketAddress( ip, port ), 10000);

			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.write(Command.getDMAConfigData());
			dos.flush();
			byte[] configBytes = readInputStream(socket.getInputStream());
			configBytes=AES.DeParseData(configBytes);
			configStr = new String(configBytes);
			configStr=new String(PublicMethod.getDataResult(configBytes));

			paramsBean=new DMAParamsBean(deviceId,configStr);


			dos.close();
			socket.close();
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
			return paramsBean;
		} catch (IOException e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();
			} catch (IOException e1) {
			}
			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}
		}
		return paramsBean;
	}*/

	/*
	 * TODO 设置设备参数
	 */
/*	public static boolean setDMAConfig(String config,String deviceId,String ip,int port) {
		boolean isError =false;
		Socket socket=null;
		BufferedReader in=null;
		DataOutputStream dos=null;
		//for nabto
		IpPortBean connBean=null;
		try {
			//for nabto
			int currReTryTimes=reTryTimes;
			DynamicUtil dUtil=new DynamicUtil();
			connBean = dUtil.getConnInfoEx("", deviceId);
			if(connBean.getConnType().equals("3")){
				TunnelBean tunnelBean = NabtoClient.openTuennel(connBean.getNabtoId(), connBean.getPort());
				ip="127.0.0.1";
				port=tunnelBean.getLocalPort();
				currReTryTimes=nabtoReTryTimes;
			}
			//for nabto 20150909
			boolean reTry=true;
			int reCot=0;
			while(reTry&&reCot++<currReTryTimes){
				try{
					socket = new Socket();
					socket.connect(new InetSocketAddress( ip, port ), 10000);
					reTry=false;
				}catch(Exception ex){
					try{Thread.sleep(1000);}catch(Exception e){}
					socket=null;
					System.out.println("socket连接失败retry "+ip+"："+port+","+reCot);
				}
			}
//			socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			dos = new DataOutputStream(socket.getOutputStream());
			//dos.write(Command.setVRAConfigData(config, getDeviceId(DeviceId)));
			//[85, 5, -128, 0, 0, 0, 0, 0, 0, 0, 8, -64, 108, 1, -118, -88, -19, -29, 34, -78, -69, 123, -93, -5, 49, 54, -22, 14, -62, -32, 76, 38, 34, 101, 116, 89, 35, -16, -22, -57, 38, -31, 47, 84, -127, 54, 78, 71, -113, -108, -126, 111, 51, -46, 66, 118, 84, -8, -48, 72, -65, 29, -95, -110, 17, -30, 112, 79, 4, 45, -61, -106, 68, 110, -20, 19, 55, 38, -43, 35, 19, 43, 36, -21, 100, -6, 11, -11, -83, 69, -58, 44, -3, -112, 111, 50, 48, 38, 55, -62, -104, -52, 73, -10, 36, 94, -31, -111, 83, 0, -5, -72, -32, -42, 98, -39, -104, -34, 107, -74, 17, -23, -69, 18, 88, -34, -8, 119, -29, 107, 1, -4, -86, 125, 56, -108, 126, 56, -57, 7, -45, 81, -105, 36, 75, -26, -25, -22, 73, 60, -29, 97, 1, -4, 65, 6, -63, -120, 30, -23, -53, -116, -109, -94, -10, 114, -31, 98, -39, -125, 37, -54, 80, -83, 101, -102, 15, -4, -73, 47, 127, -91, -96, -80, -58, -50, -53, -56, -78, -80, 66, 96, -48, 38, 55, 39, 61, 93, 92, 87, -126, 120, -1, 95, 114, 8, -21, 120, 45, 6, 0, 75, 104, -92, -123, 120, -110, -125, -74, 5, -29, -108, 12, 9, 6, 99, -122, 11, -39, -117, 84, 108, -21, -80, -80, 96, 116, -124, 86, -67, -15, -94, 80, -98, -92, 126, 80, 9, -107, -119, 66, -28, 31, -7, -36, -7, -56, 75, 21, -65, 34, -94, -42, -78, -42, 119, -30, -37, -95, -95, -19, 48, 109, -50, -48, -91, -113, 3, -102, 31, 123, -16, 31, -18, 13, 58, -57, 16, 126, -9, -2, -66, -38, 70, 97, 124, 65, -71, -40, -102, -60, -65, -102, -1, -17, 108, -114, 96, -50, -6, 63, 61, 40, 68, -5, 89, -119, -65, -116, -14, 51, 80, 53, -58, 5, -5, -113, 73, 105, -3, 111, 119, -2, 77, -7, 59, 36, -105, 94, 126, -113, 98, 1, 79, -98, -71, -2, -95, 9, 40, 25, -2, -25, 44, -45, -26, 82, 105, -101, 37, -119, 115, -90, -5, 4, 119, 111, -45, 122, 45, 3, 80, 54, -28, -82, 115, -62, -50, -26, -35, -64, 83, 65, 94, 1, 115, -62, 123, -37, -46, 68, 13, -34, -9, -21, -34, -106, 92, 95, -109, 54, 126, 103, 109, -64, 120, -29, 2, -75, -105, -34, -13, 66, 93, -23, -73, -43, -49, 36, 5, 43, -50, -6, 21, -124, -57, 108, -8, 27, 12, -112, -116, -47, 113, -57, 29, 42, -44, -9, 16, 66, -87, -11, 8, -16, -6, 90, -13, 25, 108, -52, -22, -70, 78, -88, -12, 5, 15, -29, 28, 42, -119, 94, 66, -111, 54, -33, 113, 93, -101, -43, -52, 58, -106, -102, 11, -31, -93, 118, 40, 73, -30, 43, 6, 41, -58, -52, 30, 34, 109, -100, -80, -55, -85, -68, -105, -128, 65, -121, 12, -115, -38, 26, -107, -25, 39, -49, 85, 127, -62, -121, 121, -24, 0, -106, -1, -10, 100, 108, 76, -84, 127, -117, -31, -4, -16, 108, -57, 23, -18, 80, 124, 108, -67, 89, -43, 38, -62, -57, 109, -126, -121, 96, 40, -44, -4, -87, -71, -4, 94, -17, -55, -50, -66, 36, 6, -12, -85, -118, -24, -97, 68, 65, 81, 63, 18, -41, -23, 101, 121, 74, 114, 24, 55, 39, 43, -17, -18, 92, 9, -119, -56, 72, 58, 88, -15, -58, -88, -105, -113, 73, 113, 1, 18, 84, -15, 88, -77, -97, -23, 40, 117, 70, 90, 17, -88, 54, -51, -3, 125, -45, -118, 104, -1, 37, 127, -61, -3, -92, -91, -56, -2, -93, -74, -7, -15, -69, -46, -1, 67, -71, 64, 22, 71, 103, -79, -33, -54, -93, 22, -98, 105, -92, -104, -88, 47, -123, 81, -34, 118, 41, 20, 60, -57, -81, -89, -111, 86, -47, 75, -73, 64, -28, -14, -67, -23, 70, 67, -3, -58, -18, 105, 82, -21, -107, -26, -111, 60, -29, -114, 90, -50, -49, -79, 65, -107, -124, 102, 29, 74, -47, 31, -115, 125, 32, 26, -69, -128, -119, -56, 24, 11, 54, -43, -31, -30, -107, 116, -80, 86, 110, 30, -126, -47, -100, -13, 58, -109, 87, -119, 48, -4, -15, -66, 31, 66, 106, 87, 63, 97, 84, 52, -57, 60, -28, 126, -93, -67, 3, -94, -63, 112, -102, -114, 16, 76, 105, 75, -80, -35, 90, 82, 94, 29, 1, 10, -75, -33, 39, -7, 0, -49, -29, -13, 48, 53, -28, 102, 26, 1, -94, 68, -81, 84, -39, 77, 41, -11, -59, -46, 90, 3, 68, -4, -71, 20, 11, 62, 46, -77, 118, 23, -10, 40, 126, -87, 49, -71, 78, 15, -34, 7, 110, -19, 69, -2, -56, -39, 67, 49, -13, -36, -99, -69, -22, 10, 21, -22, -80, -44, 92, 93, 117, 95, -29, 78, -25, 61, -60, 24, 59, 70, -89, -128, 47, 123, -66, 42, 74, -42, -21, 37, -20, 123, -42, -100, -118, 10, -43, 106, -47, -50, -125, 121, 10, -66, -79, -17, 117, 43, 25, -33, 7, 25, 84, -43, -107, -10, 34, 85, 35, 56, -16, -78, -47, -88, 7, -75, 114, -113, 12, -12, -103, -3, 87, 109, -31, 40, 81, 99, -45, 13, 95, -112, -48, -102, -86, 41, -55, 6, 7, -120, 78, -112, 71, -18, -89, -78, -4, -68, -113, -118, -69, -98, 63, 92, -26, -20, 117, -94, -11, 16, 89, 5, -91, 67, -118, -30, 81, -20, -109, 125, -81, -11, 111, -80, -55, 101, 27, -75, 15, -18, 0, 50, 11, -48, 97, 82, 90, -13, -33, -97, 45, 101, -72, -122, 108, -97, 21, -101, 9, 48, -8, -119, -2, -85, -47, -77, -27, 127, 16, 53, -34, -112, -57, 64, -20, -11, 70, -51, -110, 115, -53, 118, 123, -14, -114, 91, 9, 82, -56, 89, 69, 108, -59, -38, -97, -94, 117, -51, -88, 71, 66, 27, 62, -40, -75, 10, 118, -107, -69, -6, -101, -3, -29, -111, 62, -11, -125, 127, -13, -84, 53, -67, 45, -80, 79, -34, 68, -13, 85, 100, 114, 116, -14, 73, -32, 123, -26, -47, -128, -36, 71, 96, 92, -118, -89, 114, 124, 78, -77, 26, 69, 13, -45, -38, -76, -97, -124, -87, 12, 113, 100, 86, 57, 123, -53, -36, 26, 106, 17, -83, 20, 127, 60, -83, -31, 12, -91, 11, 95, -120, 37, -23, -12, -16, -40, 94, -14, -118, -29, 3, -116, 111, 13, -128, 61, 77, 55, 35, -88, -5, -28, -51, 83, 56, 126, -90, -71, 24, 22, 103, -11, 83, -93, 40, -59, -84, -14, 90, -65, -81, 38, -64, 42, -64, 10, -87, -101, 67, 37, 77, 71, 63, -4, 14, 108, 87, -83, 83, 68, -128, 23, -23, 53, 111, -4, -44, -48, -109, 107, 27, 110, 54, -67, -91, -46, -105, -111, 35, 10, -73, -1, -4, -44, 108, -126, -50, 124, -5, 63, -91, 100, 80, -8, -34, 39, 88, 53, -6, -63, -24, -122, 73, -108, -52, 115, -124, -39, -127, -104, 31, 41, -75, 12, -71, 86, -42, -83, 19, -33, -53, 20, 17, -40, 26, 17, 103, -25, -14, -37, -59, -43, -34, -86, 3, 53, 46, 65, 100, -122, 116, 61, -90, 109, 58, 15, 78, 99, 16, 17, 20, -2, 32, -14, -120, -13, 77, -24, -37, -92, -77, -80, 46, 52, -112, 8, 70, -54, -88, 103, 26, -67, 8, -96, -79, -18, 26, 6, 41, 60, -81, 2, 28, 99, 41, -64, -58, -38, 62, 4, 21, -112, 91, 92, 10, -85, -80, -43, 34, 118, -59, -17, -7, 20, 108, 66, -73, 102, -71, 9, -127, 54, 36, 45, 32, -94, -59, -35, 104, 24, 55, 19, 98, 108, -109, 112, 76, 81, -116, -74, 15, 125, -96, -24, -63, -4, -112, -69, -39, -65, 87, 65, -50, -2, -45, 102, 112, -54, -44, -24, -120, 76, -119, -24, -92, -65, 82, -18, 124, 4, -65, -62, -80, -114, -12, -84, 80, -124, 78, -75, 126, 70, -59, 46, -65, -73, 6, 11, -128, 20, 127, 98, 89, 61, 14, 77, -89, -19, -38, -17, -121, 103, 34, 79, 33, 35, 119, -4, 3, -16, 126, -49, -106, 122, -6, 124, -103, -109, -127, 113, -90, -48, 58, 54, 8, -112, -127, 29, -75, 50, 107, -66, 22, -50, 68, -44, -104, -120, -47, -57, 96, 125, -44, 124, -36, -97, 42, 71, -92, -81, 40, 107, -108, -22, 39, 32, 68, 64, -124, 104, -80, -41, -88, 93, -1, -27, -79, 35, -7, 25, -43, 85, 80, 98, -105, 125, 62, -91, -56, -122, 117, -83, 43, 21, -15, -14, -48, 11, 50, -55, -88, 51, -58, -105, 112, 98, 14, 67, -75, -117, 96, 74, 16, 96, -31, -100, -90, -49, 73, -75, -126, 8, -52, 67, -43, 71, 12, -41, -35, -104, 127, 99, 40, -46, 61, 74, 73, 13, 62, 106, 109, 1, 41, 92, 5, -84, 33, -56, -104, -17, -31, -103, -10, 75, 36, 25, -34, -31, 87, -103, -42, 126, -21, -39, 60, -115, -102, 11, 63, 31, -93, -92, -25, 89, 86, -26, -19, -124, -97, 73, 3, 10, -48, 89, 30, 59, -123, 119, -19, 29, 28, 53, 23, 106, 5, 82, -24, -93, -106, -95, -101, 125, 83, 101, 41, 23, 66, 123, 75, -123, 94, 23, -5, 93, -14, -9, 50, 21, -26, -17, 101, 81, -95, 104, 81, -58, -109, 102, 36, -123, -90, -26, -62, -80, -89, 95, 5, 102, 127, -26, -21, -127, -46, -66, 26, 62, 64, 49, -35, 32, 93, 120, 38, -12, -38, 66, -119, -38, -1, -107, 70, -70, 121, 4, -120, 59, 82, 2, -81, 123, 115, 90, 34, -79, 67, -48, -40, 16, 54, 118, -95, 71, -28, 42, -113, -98, 94, -100, 38, 91, -113, 20, -68, -23, 93, 7, 50, -58, 79, 119, 26, 78, -61, -46, 76, -25, 40, -47, -68, 22, -81, -45, -121, -111, 63, -79, 50, -115, -117, -30, -45, 57, -9, -22, -63, -44, -85, -104, 27, 20, -70, 95, 97, -72, 58, 0, -31, -60, -84, 99, -88, -106, -81, 87, -119, -14, -121, -49, 102, -47, 13, -78, 20, -95, -25, -28, 83, -98, 11, -86, 11, 80, -116, -47, 87, 24, -15, 47, -47, 9, -109, -99, -54, 4, 5, 1, -102, -114, 56, 90, 22, 47, 9, -70, -101, 117, -74, 56, 122, 61, -34, -49, 10, -57, 96, -19, 97, -120, -119, -110, -59, 35, 27, -103, 46, -93, 85, -125, -26, -104, -93, 121, -89, -90, -43, 89, -122, 101, 124, -109, -68, 5, 108, -14, 17, 119, 38, -58, -80, 63, 39, 68, 2, 86, 114, 122, -83, -62, -30, 7, 101, -28, -101, 118, 62, 0, -64, 108, 47, -38, -4, 96, -30, 96, -67, 40, -16, 87, -62, 98, -48, -100, 59, -22, 117, 42, -128, 80, -94, 119, -96, 54, 13, 100, 38, 36, 82, 40, 68, 8, -119, 13, 43, -79, -113, -52, -114, 43, -54, -117, -120, 115, 14, -85, 61, -97, 105, -40, 77, 103, 109, -4, -37, -113, -123, -126, 111, 39, 114, 17, -122, 70, -41, 103, -112, 93, 112, 107, 117, -56, 7, -36, -10, -114, 85, -120, -42, -109, 0, 110, -1, 91, 39, 57, 99, 113, -83, -4, -117, -94, 74, 106, 42, 50, -128, 120, -16, -46, 27, -42, 48, -96, -58, -14, -9, 119, -17, -126, -121, -57, -67, -8, 22, 97, -30, 82, -74, 38, 0, -113, -76, -47, -89, -15, 105, 119, -84, -126, 20, 127, 47, -110, 94, 105, 57, 94, -16, 98, -24, -5, 1, -65, -123, -107, -49, 123, -54, 92, 66, -96, -126, -50, -44, 52, -11, 35, -120, 36, -89, -84, 110, -112, -79, -4, 80, -78, 11, 61, 15, -70, -30, 31, 17, -99, -105, 67, -2, -19, 12, 15, 93, -67, -4, 97, -80, -94, -35, -21, -4, -116, -4, -110, -113, 88, -99, -40, 47, -53, -116, 14, 99, 2, -69, -61, 49, 70, -86, 19, 46, -88, -56, -3, -40, -28, 74, 92, -30, -82, 106, 8, -102, -117, 63, -1, -56, -91, -6, -39, 41, -9, -121, 3, 112, 18, 114, -126, -102, 32, 64, -105, -83, 105, -20, 41, -79, 121, -9, -83, -14, -62, 19, -13, 45, 49, -33, -37, 64, 85, 109, -112, -92, -9, -67, 86, -94, 16, -55, -125, -88, -95, 8, -54, -61, -4, -57, 91, -74, 19, -66, -83, 16, -98, 43, 3, -4, 118, 7, -66, 97, -93, -74, -85, -93, 72, 127, -18, 42, 106, -94, -67, -108, -51, -116, 69, -72, -77, -11, -78, -98, -7, 18, 101, -80, -64, -125, 124, -21, -93, -23, 26, -71, 117, 71, -86, -97, 43, 101, 94, 126, 30, -44, 108, -46, -122, 90, -23, 93, 59, 15,...
//			deviceId="10.00.20.47";//debug
			dos.write(AES.EnParseData(Command.setDMAConfigData(config, getDeviceId(deviceId))));
			dos.flush();
			byte[] logByte = readInputStream(socket.getInputStream());
			isError=PublicMethod.getCommandResult(AES.DeParseData(logByte));
			dos.close();
			socket.close();

			//for nabto
//			if(connBean.getConnType().equals("3")){
//				NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//			}

			System.out.println("设置设备参数成功:"+deviceId);
			return isError;
		} catch (Exception e) {
			System.out.println("socket连接失败："+ip);
			logger.error("socket连接失败："+ip,e);
			try {
				if(socket!=null)
				    socket.close();

				//for nabto
//				if(connBean.getConnType().equals("3")){
//					NabtoClient.closeTuennel(connBean.getNabtoId(), connBean.getPort());
//				}
			} catch (IOException e1) {
			}



		}
		return isError;
	}*/

    public static void main(String[] args) throws Exception {

        byte type = 0x1;
        String s_nod = "1";
//		Integer.parseInt(s_nod)
        byte rb = (byte) (Byte.valueOf(s_nod) << 2 | type);

        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
//		socket = new Socket("127.0.0.1", 8889);
        socket = new Socket("127.0.0.1", 8889);
        int pr = socket.getLocalPort();
        for (int i = 0; i < 10000; i++) {
            dos = new DataOutputStream(socket.getOutputStream());
//		   dos.write(data);
            dos.flush();
            byte[] resultBytes = readInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
//		   dos.write(okData);
            dos.flush();
//		   Thread.sleep(100);
        }
        dos.close();
        socket.close();

//		System.out.println((int)((int)3000/(70000*1.00)*100));
//		85 2 -128 0 0 0 0 0 0 0 0 64 97 109 20 90 -76 75 -96 52 -70 22 -48 -102 -53 -44
//		46 101 -26 107 83 -73 -3 12 98 79 38 0 29 107 93 -9 123 44 68 -112 -93 -15 -61 1
//		 -3 -21 85 62 55 -45 -96 65 78 109 85 6 -77 65 72 69 -25 -31 56 79 -43 110 -103
//		126 23 59 93
//		返回结果(10.01.00.13)：85 2 -128 0 0 0 0 0 0 0 0 16 -60 44 -18 -26 15 113 80 -51
//		 -25 114 -35 -84 -31 -96 -73 -45 78
//		String[] a="85 2 -128 0 0 0 0 0 0 0 0 64 97 109 20 90 -76 75 -96 52 -70 22 -48 -102 -53 -44 46 101 -26 107 83 -73 -3 12 98 79 38 0 29 107 93 -9 123 44 68 -112 -93 -15 -61 1 -3 -21 85 62 55 -45 -96 65 78 109 85 6 -77 65 72 69 -25 -31 56 79 -43 110 -103 126 23 59 93".split(" ");
//		//	            85  3  -128  0  0  0  0  0  0  0  0  32  38  -102  -35  25  -15  -97  70  -91  -99  96  36  12  -111  45  -39  101  -95  -127  -44  100  21  79  123  -58  -15  24  -28  92  74  61  -83  -77  -119
//			//                                                           [-8,  -124, 23, 73, -20, 48, 110, -70, 34, -59, -51, -38, -45, -71, -44, -53, 45, -93,  11, -103, 50, -30, -53, 108, -126, 24, 75, 90, -65, 15, -88, 117]
////			String[] a="85, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 6, 16, 1, 0, 19, 111, 107, 4".split(",");
//			            //  85 9 -128 0 0 0 0 0 0 0 0 8 0 6 16 1 0 19 111 107 4
//			            //85  3  -128  0  0  0  0  0  0  0  0  32  38  -102  -35  25  -15 - 97  70  -91  -99  96  36  12  -111  45  -39  101 - 95  -127  -44  100  21  79  123  -58  -15  24  -28  92  74  61 - 83  -77  -119
//		byte[] data = new byte[a.length];
//		for(int i=0;i<a.length;i++){
//			data[i]=(byte)Integer.parseInt(a[i].trim());
//		}
//		String[] ok="85 2 -128 0 0 0 0 0 0 0 0 16 -60 44 -18 -26 15 113 80 -51 -25 114 -35 -84 -31 -96 -73 -45 78".split(" ");
//		byte[] okData = new byte[ok.length];
//		for(int i=0;i<ok.length;i++){
//			okData[i]=(byte)Integer.parseInt(ok[i].trim());
//		}
//		Socket socket=null;
//		DataOutputStream dos=null;
//		long t=System.currentTimeMillis();
//		int i=0;
//		try {
//
//			socket = new Socket("127.0.0.1", 8889);
//			for(i=0;i<10000;i++){
//			   dos = new DataOutputStream(socket.getOutputStream());
//			   dos.write(data);
//			   dos.flush();
//			   byte[] resultBytes = readInputStream(socket.getInputStream());
//			   dos = new DataOutputStream(socket.getOutputStream());
//			   dos.write(okData);
//			   dos.flush();
////			   Thread.sleep(100);
//			}
//			dos.close();
//			socket.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        //System.out.println(Integer.toBinaryString(86400));
        byte[] cmd = PublicMethod.int4bytes(86400);
        int t = PublicMethod.byteToInt2(cmd);
        System.out.println(t);
//		System.out.println("时间："+(System.currentTimeMillis()-t)+"/"+i);
        //byte cmd=(byte)Integer.parseInt("FF",16);
//		byte[] cmd=new byte[4];
//		cmd[0]=(byte)0x10;
//		cmd[1]=(byte)0x01;
//		cmd[2]=(byte)0xC6;
//		cmd[3]=(byte)0x27;
////		int t=PublicMethod.byteToInt2(cmd);
////		System.out.println(t);
////        System.out.println(cmd[0]);
//		//System.out.println(PublicMethod.byteToInt2(cmd));
//        String d=PublicMethod.getFormatDeviceID(cmd);
//		System.out.println(d);
//		System.out.println(PublicMethod.getFormatTable(d));
//		System.out.println(getDeviceId(d));
//			long t=System.currentTimeMillis();//123.127.240.42
//		    Client.sendClientCommand("127.0.0.1",9999,"status");
//		    System.out.println("所需时间："+(System.currentTimeMillis()-t));
//		    Client.sendClientCommand("127.0.0.1",9999,"isStarted");
//		    System.out.println("所需时间："+(System.currentTimeMillis()-t));
//		//Client.sendClientCommand("127.0.0.1",9999,"startServer");
//		//Client.sendClientCommand("127.0.0.1",9999,"stopServer");
    }

    //直流电机控制命令发送
    public static boolean motorsCtrl2(String ip, int port, String deviceId, byte[] data, byte cmd) {
        boolean isSucce = false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSentCommand(cmd, data, deviceId));
//			System.out.println("发出数据："+PublicMethod.bytesToHexString(bSend));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            System.out.println("命令发送之后的返回:" + Arrays.toString(recByte));
//			System.out.println("收到数据："+PublicMethod.bytesToHexString(recByte));
            isSucce = PublicMethod.getCommandResult66(AES.DeParseData(recByte));
//			System.out.println("解密后数据："+PublicMethod.bytesToHexString(recByte));
            dos.close();
            socket.close();
            if (isSucce)
                System.out.println("直流电机控制命令执行成功:" + deviceId);
            else
                System.out.println("直流电机控制命令执行失败:" + deviceId);
            return isSucce;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip + ":" + port);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
        }
        return isSucce;
    }


    /**
     * 直流电机控制命令发送
     * @param ip
     * @param port
     * @param deviceId
     * @return
     */
    public static byte[] getMotorSensor2(String ip, int port, String deviceId) {
//		boolean isSucce =false;
        Socket socket = null;
        BufferedReader in = null;
        DataOutputStream dos = null;
        byte[] data = new byte[1];
        data[0] = 0x1;
        try {
            socket = new Socket(ip, port);
            socket.setTcpNoDelay(true);
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] bSend = AES.EnParseData(Command.getSentCommand((byte) 0x18, data, deviceId));
            dos.write(bSend);
            dos.flush();
            byte[] recByte = readInputStream(socket.getInputStream());
            recByte = AES.DeParseData(recByte);
//			System.out.println("传感器数据："+PublicMethod.bytesToHexString(recByte));
            dos.close();
            socket.close();
            System.out.println("直流电机传感器信息获取成功:" + deviceId);
            return recByte;
        } catch (Exception e) {
            System.out.println("socket连接失败：" + ip);
            logger.error("socket连接失败：" + ip, e);
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e1) {
            }
        }
        return null;
    }

    private static byte[] readInputStream2(InputStream _in) throws IOException {
        if (_in == null)
            throw new IOException("InputStream can't be null!");

        // /读取包头
        int haveread = 0;
        int readed = 14;
        //
        byte[] head = new byte[14];
        //
        while (haveread < readed) {
            int s = _in.read(head, haveread, readed - haveread);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }
        // 读取数据类型
        int dataType = head[1];
        // 读取加密长度
        byte[] AES = new byte[2];
        AES[0] = head[10];
        AES[1] = head[11];
        int AESLen = PublicMethod.byteToInt2(AES);
        // 数据长度
        int dataLen = AESLen - 2;

        haveread = 0;
        byte[] data = new byte[dataLen];

        // 获取数据
        while (haveread < dataLen) {
            int s = _in.read(data, haveread, dataLen - haveread);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }

        // CRC
        byte[] CRC = new byte[1];
        haveread = 0;
        while (haveread < 1) {
            int s = _in.read(CRC, haveread, 1);
            if (s == -1) {
                return null; // Connection lost
            }
            haveread = haveread + s;
        }
        // 返回全部数据
        int allLen = 14 + dataLen + 1;
        byte[] allData = new byte[allLen];
        System.arraycopy(head, 0, allData, 0, head.length);
        System.arraycopy(data, 0, allData, 14, data.length);
        allData[allLen - 1] = CRC[0];
        return allData;
    }

}
