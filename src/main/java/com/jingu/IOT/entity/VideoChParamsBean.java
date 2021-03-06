package com.jingu.IOT.entity;

import java.io.Serializable;


public class VideoChParamsBean extends ActionForm implements Serializable{	
	private String videoEncodeType;//编码类型范围 H264 H265
	private String videoEncodeEfficiency;//编码复杂度范围 高，中，低
	private String resolution;//分辨率
	private String videoFrameRate;//帧率
	private String videoBitrate;//码率
	public String getVideoEncodeType() {
		return videoEncodeType;
	}
	public void setVideoEncodeType(String videoEncodeType) {
		this.videoEncodeType = videoEncodeType;
	}
	public String getVideoEncodeEfficiency() {
		return videoEncodeEfficiency;
	}
	public void setVideoEncodeEfficiency(String videoEncodeEfficiency) {
		this.videoEncodeEfficiency = videoEncodeEfficiency;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getVideoFrameRate() {
		return videoFrameRate;
	}
	public void setVideoFrameRate(String videoFrameRate) {
		this.videoFrameRate = videoFrameRate;
	}
	public String getVideoBitrate() {
		return videoBitrate;
	}
	public void setVideoBitrate(String videoBitrate) {
		this.videoBitrate = videoBitrate;
	}
	public String[] rawBitrate={"0","16","32","48","64","80","96","128","160","192","224","256","320","384","448","512","640","768","896","1024","1280","1536","1792","2048","3072","4096","8192","16384"};
	public String getVideoBitrateRaw() {
		for(int i=0;i<rawBitrate.length;i++){
			if(rawBitrate[i].equals(videoBitrate))
				return i+"";
		}
		int rate = Integer.parseInt(videoBitrate);
		rate=rate*1024;
		rate=rate | 0x80000000;
		return rate+"";
	}
	public void setVideoBitrateRaw(String videoBitrateRaw) {
		try{
			int rate = Integer.parseInt(videoBitrateRaw);
			if(rate>=0&&rate<=27){
				this.videoBitrate = rawBitrate[rate];
			}else if(rate<0){
				rate = rate&0x7fffffff;
				rate = rate/1024;
				this.videoBitrate = rate+"";
			}
		}catch(Exception ex){
			System.out.println("setVideoBitrateRaw错误");
		}
	}
	public static void main(String[] args) {
		String num="-2147142656";
		int rate = Integer.parseInt(num);
		System.out.println(rate);
//		rate =  ~rate+1;
		rate =  rate&0x7fffffff;
		System.out.println(rate);
	}
}
