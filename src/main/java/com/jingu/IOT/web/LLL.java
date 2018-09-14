///**  
//*   
//* 项目名称：IOT  
//* 类名称：LLL  
//* 类描述：  
//* 创建人：jianghu  
//* 创建时间：2017年9月28日 下午4:11:52  
//* 修改人：jianghu  
//* 修改时间：2017年9月28日 下午4:11:52  
//* 修改备注： 下午4:11:52
//* @version   
//*   
//*/ 
//package com.jingu.IOT.web;
//
//import java.util.Iterator;
//
//import com.jingu.IOT.entity.MotorHBM;
//import com.jingu.IOT.util.Client;
//
//import net.sf.json.JSONObject;
//
///**
//
//* @ClassName: LLL
//* @Description: TODO
//* @author jianghu
//* @date 2017年9月28日 下午4:11:52
//
//*/
//public class LLL {
//	public static void main(String[] args) {
//		String string = "{,1:'ab',2:'b',3:'c'}";
//		String substring = string.substring(2);
//		String string2 = String.valueOf("{"+substring);
//		System.out.println(string2);
//		//JSONObject fromObject = JSONObject.fromObject(string);
//	//	System.out.println(fromObject);
////		MotorHBM hbm=(MotorHBM)motorDAO.loadObj(Integer.parseInt(id));
////		hbm.setCtrlType(1);
////		hbm.setDirection(Integer.parseInt(direction));
////		hbm.setDistanceOrDuration(Integer.parseInt(distance));
////		String deviceId=hbm.getDeviceId();
////		//send
////		
////		boolean isSuc = Client.motorsCtrl("192.168.0.168", 52390, "10.00.74.21", hbm.toByteCmd(),(byte)0x17);
////		System.out.println("电机控制命令："+isSuc+" >>> 方向:"+hbm.getDirection()+" >>> |比例:"+hbm.getDistanceOrDuration());
////		
////		
////		long start = System.currentTimeMillis();
////		for (int a = -100; a < 100; a++) {
////			for (int b = -100; b < 100; b++) {
////				for (int c = -100; c < 100; c++) {
////					for (int d = -100; d < 100; d++) {
////						boolean i = a + b ==80;
////						boolean j = c - d ==60;
////						boolean k = a + c ==130;
////						boolean l = b + d ==80;
////						if(i && j && k && l){
////							System.out.println("a = "+ a);
////							System.out.println("b = "+ b);
////							System.out.println("c = "+ c);
////							System.out.println("d = "+ d);
////							System.out.println("时间(单位毫秒) :"+(System.currentTimeMillis()-start));
////							break;
////						}
////					}
////				}
////			}
////		}
//	}
//
//}
