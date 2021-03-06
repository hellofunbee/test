package com.jingu.IOT.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VideoChannel {
	private String channelType;//main，sub1，sub2
//	private VideoEncodeType videoEncodeType;//
	private List<VideoEncodeType> videoEncodeTypes=new ArrayList();//
	private List<VideoResolution> solutions=new ArrayList();//
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public List<VideoEncodeType> getVideoEncodeTypes() {
		return videoEncodeTypes;
	}
	public void setVideoEncodeTypes(List<VideoEncodeType> videoEncodeTypes) {
		this.videoEncodeTypes = videoEncodeTypes;
	}
	public List<VideoResolution> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<VideoResolution> solutions) {
		this.solutions = solutions;
	}
	public void parse(Document document,String parentXpath){
		try{
			 XPathFactory xpfactory = XPathFactory.newInstance();
	    	 XPath  path = xpfactory.newXPath();//
	    	 //解析编码类型范围
	    	 String encodeTypeXpath=parentXpath + "/VideoEncodeType/Range";
	    	 Node encodeTypeNode = (Node) path.evaluate(encodeTypeXpath,document, XPathConstants.NODE);//返回一个节点。//    
	    	 String enTypeStr = encodeTypeNode.getTextContent();
	    	 String[] enTypes=enTypeStr.split(",");
	    	 //解析编码复杂度范围
	    	 String efficiencyXpath=parentXpath + "/VideoEncodeEfficiency/Range";
	    	 Node efficiencyNode = (Node) path.evaluate(efficiencyXpath,document, XPathConstants.NODE);//返回一个节点。//  
	    	 String efficiencyStr = efficiencyNode.getTextContent();
	    	 //关联编码类型和复杂度限制
	    	 for(int i=0;i<enTypes.length;i++){
	    		 VideoEncodeType encodeType=new VideoEncodeType(); 
	    		 encodeType.setVideoEncodeEfficiency(efficiencyStr);
	    		 encodeType.parse(document,parentXpath,enTypes[i]);
	    		 videoEncodeTypes.add(encodeType);
	    	 }
	    	 //解析分辨率方案
	    	 String solutionsXpath=parentXpath + "/VideoResolutionList/VideoResolutionEntry";
	    	 NodeList solutionNodes = (NodeList) path.evaluate(solutionsXpath,document, XPathConstants.NODESET);//返回一个节点。//     
	    	 int len = solutionNodes.getLength();
	    	 for(int i=0;i<len;i++){
	    		 Node nd = solutionNodes.item(i);
	    		 
	    		 if(nd.getNodeName().toLowerCase().equals("videoresolutionentry")){
	    			 VideoResolution solution=new VideoResolution();	    		 
	    			 solution.parse(nd);
		    		 solutions.add(solution);
	    		 }
	    	 }
		}catch(Exception ex){
			System.out.println("VideoChannel解析错误...");
		}

	}
}
