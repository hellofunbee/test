///**  
//*   
//* 项目名称：IOT  
//* 类名称：LK  
//* 类描述：  
//* 创建人：jianghu  
//* 创建时间：2017年9月19日 下午5:51:30  
//* 修改人：jianghu  
//* 修改时间：2017年9月19日 下午5:51:30  
//* 修改备注： 下午5:51:30
//* @version   
//*   
//*/ 
//package com.jingu.IOT.web;
//
//import com.jingu.IOT.util.Client;
//import com.jingu.IOT.util.PublicMethod;
//
///**
//
//* @ClassName: LK
//* @Description: TODO
//* @author jianghu
//* @date 2017年9月19日 下午5:51:30
//
//*/
//public class LK {
//
//	public static void main(String[] args) {
//		String ip = "192.168.0.168";
//		int port =52390;
//		String deviceId = "10.00.21.74";
//		byte[] recData = Client.getMotorSensor(ip, port, deviceId);
//		//85, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 39, 0, 37, 1, 0, 33, 116, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -100
//		
//		//System.out.println(motorSensor);
//		
//		//boolean commandResult = PublicMethod.getCommandResult(motorSensor);
//		
//		if(recData==null)
//			//return -1;
//			System.out.println(-1);
//		else{
//		//获取相应通道传感器数据
//			int result=-1;
//			try{
//			byte[] value = new byte[2];
//			
//			System.arraycopy(recData, 14 + 4 + 1 + (10-1)*2 , value, 0, 2);//数据头+id+数据类型+...
//			
//			int max = 65535;
//			int min = 0;
//			
///*			try{
//				max=Integer.parseInt(maxVal);
//			}catch(Exception e){}
//			try{
//				min=Integer.parseInt(minVal);
//			}catch(Exception e){}*/
//			
//			
//			result = PublicMethod.byteToInt2(value);
//			if(result<min) result=min;
//			else if(result >max) result = max;
////			result = (int)(result/65535.00*5-1)*25;
//			result = 100-(int)(result - min)*100/(max-min);
//			System.out.println("开启度："+result);
//			}catch(Exception e){
//				//out.print("开启度获取错误");
//			}
//			System.out.println(result);
//			//return result;
//		}						
//	}
//	
//}
