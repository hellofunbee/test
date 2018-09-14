/**  
*   
* 项目名称：IOT  
* 类名称：JKL  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月26日 下午5:47:52  
* 修改人：jianghu  
* 修改时间：2017年9月26日 下午5:47:52  
* 修改备注： 下午5:47:52
* @version   
*   
*/ 
package com.jingu.IOT.web;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.jingu.IOT.entity.HCNetSDK.NET_DVR_COMPRESSIONCFG_V30;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.entity.HkSdkEx;
import com.jingu.IOT.entity.VideoChannel;
import com.jingu.IOT.entity.VideoEncodeType;
import com.jingu.IOT.entity.VideoParamsBean;
import com.jingu.IOT.entity.VideoResolution;
import com.jingu.IOT.entity.VideoShemaBean;

/**

* @ClassName: JKL
* @Description: TODO
* @author jianghu
* @date 2017年9月26日 下午5:47:52

*/
public class JKL {

	public static void main(String[] args) {
		String ip = "192.168.0.168";
		int port = 9002;
		String deviceId = "";
//		String ipcProxy1 = Client.getIpcProxy1("10.00.21.74", ip, 52390);
//		System.out.println(ipcProxy1);
		
														//admin     vr123456  111.53.182.34	   9021
//		VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility("admin", "vr123456", "111.53.182.34", "9003");
		VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility("admin", "12345", "192.168.0.234", "8000");
		VideoChannel subChannel = ipcAbility.getSubChannel();
		String channelType = subChannel.getChannelType();
		List<VideoResolution> solutions = subChannel.getSolutions();
		System.out.println("子码流");
		System.out.println(channelType);
		List<VideoEncodeType> videoEncodeTypes = subChannel.getVideoEncodeTypes();
		for (VideoResolution videoResolution : solutions) {
			
			System.out.println("videoResolution index :"+videoResolution.getIndex());
			System.out.println("videoResolution name"+videoResolution.getName());
			System.out.println("videoResolution Resolution"+videoResolution.getResolution());
			System.out.println("videoResolution VideoBitrate"+videoResolution.getVideoBitrate());
			System.out.println("videoResolution VideoFrameRate"+videoResolution.getVideoFrameRate());
			
		}
		for (VideoEncodeType videoEncodeType : videoEncodeTypes) {
			System.out.println( "videoEncodeType VideoEncodeEfficiency :"+videoEncodeType.getVideoEncodeEfficiency());
			System.out.println("videoEncodeType EncodeType :"+videoEncodeType.getVideoEncodeType());
		}
		VideoChannel mainChannel = ipcAbility.getMainChannel();
		String channelType2 = mainChannel.getChannelType();
		System.out.println(channelType2);
		List<VideoResolution> solutions2 = mainChannel.getSolutions();
		System.out.println("主码流");
		List<VideoEncodeType> videoEncodeTypes2 = mainChannel.getVideoEncodeTypes();
		for (VideoResolution videoResolution : solutions2) {
			
			System.out.println("videoResolution index :"+videoResolution.getIndex());
			System.out.println("videoResolution name"+videoResolution.getName());
			System.out.println("videoResolution Resolution"+videoResolution.getResolution());
			System.out.println("videoResolution VideoBitrate"+videoResolution.getVideoBitrate());
			System.out.println("videoResolution VideoFrameRate"+videoResolution.getVideoFrameRate());
			
		}
		for (VideoEncodeType videoEncodeType : videoEncodeTypes2) {
			System.out.println( "videoEncodeType VideoEncodeEfficiency :"+videoEncodeType.getVideoEncodeEfficiency());
			System.out.println("videoEncodeType EncodeType :"+videoEncodeType.getVideoEncodeType());
		}
		// EncodeType 编码类型范围 0-私有264，1-标准h264，2-标准mpeg4，7-M-JPEG，8-MPEG2，10-H.265
		// VideoEncodeEfficiency 视频编码复杂度：0-低,1-中,2高 
		// Resolution 显示分辨率
		//	VideoFrameRate 0,1,2,3,4,5,6,7,8,9,10,11,14,12,15,13,16,17
		//	帧率：0-全帧率;1-1/16; 2-1/8; 3-1/4; 4-1/2; 5-1; 6-2; 7-4; 8-6; 9-8; 10-10; 11-12;12-16; 13-20; 14-15; 15-18;
		//	16-22; 17-25; 18-30; 19-35; 20-40; 21-45; 22-50; 23-55; 24-60; 25-3;26-5; 27-7; 28-9; 29-100; 30-120; 31-24; 32-48 
        //VideoBitrate(单位kb) 编码率32,16384 最小是32 最大是16384 参数范围 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27
		//1-16K,2-32K,3-48k,4-64K,5-80K,6-96K,7-128K,8-160k,9-192K,10-224K, 11-256K,12-320K,13-384K,14-448K,15-512K,16-640K,17-768K,18-896K,19-1024K,20-1280K,21-1536K,22-1792K,23-2048K,24-3072K,25-4096K,26-8192K,27-16384
		//System.out.println();
		
		
//		NET_DVR_COMPRESSIONCFG_V30 compressInfo = HkSdkEx.getCompressInfo("admin","12345", "192.168.0.168", "9001");
//		VideoParamsBean videoParamsBean = new VideoParamsBean();
//		videoParamsBean.parse(compressInfo);
//		System.out.println(videoParamsBean);
//		System.out.println(compressInfo);
//		byte byAudioEncType1 = compressInfo.struNormHighRecordPara.byAudioEncType;
//		byte byAudioEncType2 = compressInfo.struNormHighRecordPara.byBitrateType;
//		byte byAudioEncType3 = compressInfo.struNormHighRecordPara.byENumber;
//		byte byAudioEncType4 = compressInfo.struNormHighRecordPara.byIntervalBPFrame;
//		byte byAudioEncType5 = compressInfo.struNormHighRecordPara.byPicQuality;
//		byte byAudioEncType6 = compressInfo.struNormHighRecordPara.byResolution;
//		byte byAudioEncType7 = compressInfo.struNormHighRecordPara.byStreamType;
//		byte byAudioEncType8 = compressInfo.struNormHighRecordPara.byVideoEncComplexity;
//		byte[] byAudioEncType9 = compressInfo.struNormHighRecordPara.byres;
//		int byAudioEncType10 = compressInfo.struNormHighRecordPara.dwVideoBitrate;
//		int byAudioEncType11 = compressInfo.struNormHighRecordPara.dwVideoFrameRate;
//		int byAudioEncType12 = compressInfo.struNormHighRecordPara.wIntervalFrameI;
//		System.out.println(byAudioEncType1);
//		System.out.println(byAudioEncType2);
//		System.out.println(byAudioEncType3);
//		System.out.println(byAudioEncType4);
//		System.out.println(byAudioEncType5);
//		System.out.println(byAudioEncType6);
//		System.out.println(byAudioEncType7);
//		System.out.println(byAudioEncType8);
//		System.out.println(byAudioEncType9);
//		System.out.println(byAudioEncType10);
//		System.out.println(byAudioEncType11);
//		System.out.println(byAudioEncType12);

	}
}
