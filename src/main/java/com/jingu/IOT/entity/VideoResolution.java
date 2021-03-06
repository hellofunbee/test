package com.jingu.IOT.entity;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VideoResolution {
	private String index;
	private String resolution;//
	private String name;//
	private String videoFrameRate;//帧率范围
	private String videoBitrate;//码率范围
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void parse(Node nd){
		NodeList ls = nd.getChildNodes();
		for(int i=0;i<ls.getLength();i++){
		String nName=ls.item(i).getNodeName();
		if(nName.toLowerCase().equals("index"))
			index = nd.getChildNodes().item(i).getTextContent();//index
		if(nName.toLowerCase().equals("name"))
			name = nd.getChildNodes().item(i).getTextContent();//index
		if(nName.toLowerCase().equals("resolution"))
			resolution = nd.getChildNodes().item(i).getTextContent();//index
		if(nName.toLowerCase().equals("videoframerate"))
			videoFrameRate = nd.getChildNodes().item(i).getTextContent();//index
		if(nName.toLowerCase().equals("videobitrate")){
			videoBitrate="";
			NodeList ls2 =nd.getChildNodes().item(i).getChildNodes();
			for(int j=0;j<ls2.getLength();j++){
				String subnName=ls2.item(j).getNodeName();
				if(subnName.toLowerCase().equals("min"))
					videoBitrate+=ls2.item(j).getTextContent();
				if(subnName.toLowerCase().equals("max"))
					videoBitrate+=","+ls2.item(j).getTextContent();
			}
		}
//		System.out.println(nName);
		}
	}
}
