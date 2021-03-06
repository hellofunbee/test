package com.jingu.IOT.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class VideoShemaBean extends ActionForm implements Serializable{
	
	private VideoChannel mainChannel;//主码流
	private VideoChannel subChannel;//字码流
	public VideoShemaBean(){
		mainChannel=new VideoChannel();
		subChannel=new VideoChannel();
	}
	public void parse(String xml){
       	try{       		
       		InputStream is = new ByteArrayInputStream(xml.getBytes());
    		//parse XML
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        	DocumentBuilder db = dbf.newDocumentBuilder(); 
        	Document document = db.parse(is);
        	
        	
//        	 XPathFactory xpfactory = XPathFactory.newInstance();
//        	 XPath  path = xpfactory.newXPath();//
//        	 Node node = (Node) path.evaluate("/AudioVideoCompressInfo/VideoCompressInfo",document, XPathConstants.NODE);//返回一个节点。//
        	 String xpath="/AudioVideoCompressInfo/VideoCompressInfo/ChannelList/ChannelEntry/MainChannel";
//        	 Node mainCh = (Node) path.evaluate("/AudioVideoCompressInfo/VideoCompressInfo/ChannelList/ChannelEntry/MainChannel",document, XPathConstants.NODE);//返回一个节点。//        	 
//        	 mainChannel=new VideoChannel();
        	 mainChannel.setChannelType("main");
        	 mainChannel.parse(document,xpath);
        	         	 
        	 xpath="/AudioVideoCompressInfo/VideoCompressInfo/ChannelList/ChannelEntry/SubChannelList/SubChannelEntry[1]";
//        	 Node subCh = (Node) path.evaluate(xpath,document, XPathConstants.NODE);//返回一个节点。//        	 
//        	 subChannel=new VideoChannel();
        	 subChannel.setChannelType("sub1");
        	 subChannel.parse(document,xpath);
        	 
        	
//        	
//        	Element root=document.getDocumentElement();
//        	
//        	
//        
//        	NodeList collegeNodes = root.getChildNodes();  
//        	Node nd = collegeNodes.
//
    	}catch(Exception ex){
    		
    	}
	}
//	
//	XPath xpath = XPathFactory.newInstance().newXPath();   
//	 XPathExpression express = null;  
//	Nodelist nodes = (Nodelist) path.evaluate("/gridbag/row",doc,XPathContants.NODESET);//返回的是一组节点。//
//	Node node = (Node) path.evaluate("/gridbag/row[1]",doc,XPathContants.NODE);//返回一个节点。//
//	int count = ((Number) path.evaluate("count(/gridbag/row)",doc,XPathContants.NUMBER)).intValue();//返回节点个数。//
//	如果想要从某个节点，或者节点列计算得到一个节点可以通过：//
//	reusult = path.evaluate(expression,node);来获取。
	public VideoChannel getMainChannel() {
		return mainChannel;
	}
	public void setMainChannel(VideoChannel mainChannel) {
		this.mainChannel = mainChannel;
	}
	public VideoChannel getSubChannel() {
		return subChannel;
	}
	public void setSubChannel(VideoChannel subChannel) {
		this.subChannel = subChannel;
	}

//	 express = xpath.compile("//OS[@name='Linux']");   
//	 // 根据XPathExpression对象查找对应的节点集合   
//	 NodeList nodes = (NodeList) express.evaluate(doc, XPathConstants.NODESET);   
}
