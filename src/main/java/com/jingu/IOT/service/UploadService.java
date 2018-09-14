package com.jingu.IOT.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.Base64;
import com.jingu.IOT.util.ToolUtil;
@Component
public class UploadService {
	//o_id(任意对象主键)fix文件格式
	public IOTResult uploadFile(String type,int o_id ,MultipartFile file){
		IOTResult result = new IOTResult();
		String fileName = type+"_"+o_id+"_"+file.getOriginalFilename();
			try { 
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH+fileName)));
                stream.write(bytes);
                stream.close();
                result.setSuccess(true);
                result.setMsg("上传成功！");
                result.setObject(ToolUtil.IOTURL+"/getActImage?file_name="+fileName);
                return result;
            } catch (Exception e) {
            	e.printStackTrace();
                result.setSuccess(false);
                result.setMsg("上传失败！");
                result.setObject(null);
	}
	result.setSuccess(false);
	result.setMsg("上传失败！");
	result.setObject(null);
	return result;
	}
	
	
	public IOTResult uploadFilebyte(String type,int o_id ,byte[] bytes,String filename){
		IOTResult result = new IOTResult();
		String fileName = type+"_"+o_id+"_"+filename+".jpg";
			try { 
            
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH+fileName)));
                stream.write(bytes);
                stream.close();
                result.setSuccess(true);
                result.setMsg("上传成功！");
                result.setObject(ToolUtil.IOTURL+"/getActImage?file_name="+fileName);
                return result;
            } catch (Exception e) {
            	e.printStackTrace();
                result.setSuccess(false);
                result.setMsg("上传失败！");
                result.setObject(null);
	}
	result.setSuccess(false);
	result.setMsg("上传失败！");
	result.setObject(null);
	return result;
	}
	
	public IOTResult uploadFile2(String type,String o_id ,MultipartFile file){
		IOTResult result = new IOTResult();
		String name = file.getName();
		String split = file.getOriginalFilename();
		String fileName = type+"_"+o_id+"_"+System.currentTimeMillis()+".jpg";
		BufferedOutputStream stream =null;
		//String filename = type+"_"+o_id+"_"+split;
			try { 
                byte[] bytes = file.getBytes();
                
                stream = new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH+fileName)));
                stream.write(bytes);
                stream.close();
               //Thumbnails.of(ToolUtil.FILEPATH+fileName).scale(1f).toFile(ToolUtil.FILEPATH+fileName);
               //Thumbnails.of(ToolUtil.FILEPATH+filename).scale(0.25f).
                result.setSuccess(true);
                result.setMsg("上传成功！");
                result.setObject("/getActImage?file_name="+fileName);
                return result;
            } catch (Exception e) {
            	e.printStackTrace();
            	try {
					stream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                result.setSuccess(false);
                result.setMsg("上传失败！");
                result.setObject(null);
            }
	result.setSuccess(false);
	result.setMsg("上传失败！");
	result.setObject(null);
	return result;
	}
	
	public IOTResult uploadFile3(String type,int o_id ,MultipartFile file){
		IOTResult result = new IOTResult();
		String originalFilename = file.getOriginalFilename();
		String encode = Base64.encode(originalFilename.getBytes());
		String fileName = type+"_"+o_id+"_"+encode;
			try { 
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH+fileName)));
                stream.write(bytes);
                stream.close();
                result.setSuccess(true);
                result.setMsg("上传成功！");
                result.setObject(ToolUtil.IOTURL+"/getActImage?file_name="+fileName);
                return result;
            } catch (Exception e) {
            	e.printStackTrace();
                result.setSuccess(false);
                result.setMsg("上传失败！");
                result.setObject(null);
	}
	result.setSuccess(false);
	result.setMsg("上传失败！");
	result.setObject(null);
	return result;
	}
	
	public boolean deleteFile(String filename){
		IOTResult result = new IOTResult();
		File file = new File(filename);
		if(file.exists() && file.isFile()){
			boolean delete = file.delete();
			return delete;
		}
		return true;
	}

}
