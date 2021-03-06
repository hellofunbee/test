package com.jingu.IOT.entity;

import java.io.Serializable;


public class VideoParamsBean extends ActionForm implements Serializable{	
	private VideoChParamsBean mainStream;//主码流
	private VideoChParamsBean sub1Stream;//字码流1
	public VideoChParamsBean getMainStream() {
		return mainStream;
	}
	public void setMainStream(VideoChParamsBean mainStream) {
		this.mainStream = mainStream;
	}
	public VideoChParamsBean getSub1Stream() {
		return sub1Stream;
	}
	public void setSub1Stream(VideoChParamsBean sub1Stream) {
		this.sub1Stream = sub1Stream;
	}
	public HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 toCompressionCfg(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		m_struCompressionCfg = toCompressionCfgMainStream(m_struCompressionCfg);
		m_struCompressionCfg = toCompressionCfgSub1Stream(m_struCompressionCfg);
		return m_struCompressionCfg;
	}
	private HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 toCompressionCfgMainStream(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		if(mainStream ==null ){
			return m_struCompressionCfg;
		}
		try{m_struCompressionCfg.struNormHighRecordPara.byVideoEncType=(byte)Integer.parseInt(mainStream.getVideoEncodeType());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNormHighRecordPara.byVideoEncComplexity=(byte)Integer.parseInt(mainStream.getVideoEncodeEfficiency());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNormHighRecordPara.byResolution=(byte)Integer.parseInt(mainStream.getResolution());}catch(Exception ex){}        	 
    	try{m_struCompressionCfg.struNormHighRecordPara.dwVideoFrameRate=Integer.parseInt(mainStream.getVideoFrameRate());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNormHighRecordPara.dwVideoBitrate=Integer.parseInt(mainStream.getVideoBitrateRaw());}catch(Exception ex){}
		return m_struCompressionCfg;
		
	}
	private HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 toCompressionCfgSub1Stream(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		//m_struCompressionCfg.
		if(sub1Stream ==null ){
			return m_struCompressionCfg;
		}
		try{m_struCompressionCfg.struNetPara.byVideoEncType=(byte)Integer.parseInt(sub1Stream.getVideoEncodeType());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNetPara.byVideoEncComplexity=(byte)Integer.parseInt(sub1Stream.getVideoEncodeEfficiency());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNetPara.byResolution=(byte)Integer.parseInt(sub1Stream.getResolution());}catch(Exception ex){}        	 
    	try{m_struCompressionCfg.struNetPara.dwVideoFrameRate=Integer.parseInt(sub1Stream.getVideoFrameRate());}catch(Exception ex){}
    	try{m_struCompressionCfg.struNetPara.dwVideoBitrate=Integer.parseInt(sub1Stream.getVideoBitrateRaw());}catch(Exception ex){}
		return m_struCompressionCfg;
	}
	public void parse(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		parseMainStream(m_struCompressionCfg);
		parseSub1Stream(m_struCompressionCfg);
	}
	private void parseMainStream(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		mainStream=new VideoChParamsBean();
    	int videoEncodeType=(int)m_struCompressionCfg.struNormHighRecordPara.byVideoEncType;
    	int videoEncComplexity  =(int)m_struCompressionCfg.struNormHighRecordPara.byVideoEncComplexity ;
    	int resolution =(int)m_struCompressionCfg.struNormHighRecordPara.byResolution;	        	 
    	int videoFrameRate =(int)m_struCompressionCfg.struNormHighRecordPara.dwVideoFrameRate ;
    	int videoBitrate =(int)m_struCompressionCfg.struNormHighRecordPara.dwVideoBitrate ;
    	mainStream.setVideoEncodeType(videoEncodeType+"");
    	mainStream.setVideoEncodeEfficiency(videoEncComplexity+"");
    	mainStream.setResolution(resolution+"");
    	mainStream.setVideoFrameRate(videoFrameRate+"");
    	mainStream.setVideoBitrateRaw(videoBitrate+"");
	}
	private void parseSub1Stream(HCNetSDK.NET_DVR_COMPRESSIONCFG_V30 m_struCompressionCfg){
		sub1Stream=new VideoChParamsBean();
    	int videoEncodeType=(int)m_struCompressionCfg.struNetPara.byVideoEncType;
    	int videoEncComplexity  =(int)m_struCompressionCfg.struNetPara.byVideoEncComplexity ;
    	int resolution =(int)m_struCompressionCfg.struNetPara.byResolution;	        	 
    	int videoFrameRate =(int)m_struCompressionCfg.struNetPara.dwVideoFrameRate ;
    	int videoBitrate =(int)m_struCompressionCfg.struNetPara.dwVideoBitrate ;
    	sub1Stream.setVideoEncodeType(videoEncodeType+"");
    	sub1Stream.setVideoEncodeEfficiency(videoEncComplexity+"");
    	sub1Stream.setResolution(resolution+"");
    	sub1Stream.setVideoFrameRate(videoFrameRate+"");
    	sub1Stream.setVideoBitrateRaw(videoBitrate+"");
	}
	
}
