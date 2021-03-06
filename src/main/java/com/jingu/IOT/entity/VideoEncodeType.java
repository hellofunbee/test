package com.jingu.IOT.entity;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VideoEncodeType {
	private String videoEncodeType;//编码类型范围 H264 H265
	private String videoEncodeEfficiency;//编码复杂度范围 高，中，低
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
	public void parse(Document document,String parentXpath,String encodeType){
		try{
			videoEncodeType=encodeType;
			XPathFactory xpfactory = XPathFactory.newInstance();
	   	 	XPath  path = xpfactory.newXPath();//
			String efficiencyXpath=parentXpath + "/VideoEncodeEfficiency/EncodeTypeList/EncodeType";
			NodeList nodes = (NodeList) path.evaluate(efficiencyXpath,document, XPathConstants.NODESET);//返回一个节点。// 
			for(int i=0;i<nodes.getLength();i++){
				nodes.item(i).getNodeName();
				
				NodeList subNodeList=nodes.item(i).getChildNodes();
				for(int j=0;j<subNodeList.getLength();j++){
					Node subNode=subNodeList.item(j);
					String subNodeName = subNode.getNodeName();
					if(subNodeName.toLowerCase().equals("index")){//是index节点
						if(subNode.getTextContent().endsWith(encodeType)){//index节点对应编码类型
							for(j++;j<subNodeList.getLength();j++){
								subNode=subNodeList.item(j);
								subNodeName = subNode.getNodeName();
								if(subNodeName.toLowerCase().equals("index")) continue;
								if(subNodeName.toLowerCase().equals("range")){
									videoEncodeEfficiency=subNode.getTextContent();									
								}
							}
						}
					}
				}
				
//				System.out.println("VideoEncodeType.getNodeName()：" + nodes.item(i).getNodeName());
			}
			
		}catch(Exception ex){
//			ex.printStackTrace();
			System.out.println("VideoEncodeType解析错误：" + encodeType);
		}
//		System.out.println("videoEncodeType：" + videoEncodeType);
//		System.out.println("videoEncodeEfficiency：" + videoEncodeEfficiency);
	}

}
