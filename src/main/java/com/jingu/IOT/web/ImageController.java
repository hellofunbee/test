/**  
*   
* 项目名称：shop  
* 类名称：ImgController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月7日 下午5:37:27  
* 修改人：jianghu  
* 修改时间：2017年9月7日 下午5:37:27  
* 修改备注： 下午5:37:27
* @version   
*   
*/ 
package com.jingu.IOT.web;

import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**

* @ClassName: ImgController
* @Description: TODO
* @author jianghu
* @date 2017年9月7日 下午5:37:27
* 图片
*/
@RestController
public class ImageController {
	
	private ToolUtil toolUtil;
	
	@Autowired
	public ImageController(ToolUtil toolUtil) {
		this.toolUtil = toolUtil;
	}


//	// 下载文件
//	@CrossOrigin
//	@RequestMapping(value = "/getActImage", method = RequestMethod.GET)
//	public @ResponseBody FileSystemResource getFile(@RequestParam("file_name") String fileName
//	 @RequestParam("sid") String sid, @RequestParam("uid") String uid ) throws UnsupportedEncodingException {
//		
//		 if (null == sid || sid.length() < 1 || null == uid || uid.length() <
//		 1) { return null; // return new StoryResult(false,"信息不规范",null,0); }
//		 if (!uid.equals(toolUtil.getWxUser(sid).getSu_id())) { return null;
//		 // return new StoryResult(false,"您已在其他地方上线",null,1); }
//		 
//		if (fileName != null && !"".equals(fileName)) {
//			File file = new File(ToolUtil.FILEPATH + fileName);
//			System.out.println(fileName);
//			System.out.println(file.exists());
//			if (file.exists()) {
//				return new FileSystemResource(file);
//				
//				 * Map<String, String> map = new HashMap<>(); map.put("picture",
//				 * "http://192.168.0.124:8080/getActImage?file_name=111111111_0_image.jpg"
//				 * ); return map;
//				 
//				// return new StoryResult(true,"成功",new
//				// FileSystemResource(file),0);
//			}
//		}
//		System.out.println("...........");
//		return null;
//	}
	public static void main(String[] args) {
		
		String name = "/vraImage/10.00.21.28.01/10.00.21.28.01_4_20171106_135513.jpg";
		String substring = name.substring(10);
		System.out.println(substring);
	}
	
	// 下载文件
	@CrossOrigin
	@RequestMapping(value = "/getReportImage", method = RequestMethod.GET)
	public @ResponseBody FileSystemResource getReportImage(@RequestParam("file_name") String fileName
	/* @RequestParam("sid") String sid, @RequestParam("uid") String uid */) throws UnsupportedEncodingException {
		/*
		 * if (null == sid || sid.length() < 1 || null == uid || uid.length() <
		 * 1) { return null; // return new StoryResult(false,"信息不规范",null,0); }
		 * if (!uid.equals(toolUtil.getWxUser(sid).getSu_id())) { return null;
		 * // return new StoryResult(false,"您已在其他地方上线",null,1); }
		 */
		if (fileName != null && !"".equals(fileName)) {
			if(ToolUtil.IMG.startsWith("/")){
				fileName = fileName.substring(10);
				System.out.println(ToolUtil.IMG);
			}else{
				fileName = fileName.replace("/", "\\");
			}
			//\data\IOTImage/vraImage/10.00.21.27.01/10.00.21.27.01_2_20171118_193612.jpg
			File file = new File(ToolUtil.IMG + fileName);
			System.out.println(fileName);
			System.out.println(ToolUtil.IMG + fileName);
			System.out.println(file.exists());
			if (file.exists()) {
				return new FileSystemResource(file);
				/*
				 * Map<String, String> map = new HashMap<>(); map.put("picture",
				 * "http://192.168.0.124:8080/getActImage?file_name=111111111_0_image.jpg"
				 * ); return map;
				 */
				// return new StoryResult(true,"成功",new
				// FileSystemResource(file),0);
			}
		}
		System.out.println("...........");
		return null;
	}
	
	
	// 下载文件
	@CrossOrigin
	@RequestMapping(value = "/getActImage", method = RequestMethod.GET)
	public @ResponseBody FileSystemResource getActImage(@RequestParam("file_name") String fileName
	/* @RequestParam("sid") String sid, @RequestParam("uid") String uid */) throws UnsupportedEncodingException {
		/*
		 * if (null == sid || sid.length() < 1 || null == uid || uid.length() <
		 * 1) { return null; // return new StoryResult(false,"信息不规范",null,0); }
		 * if (!uid.equals(toolUtil.getWxUser(sid).getSu_id())) { return null;
		 * // return new StoryResult(false,"您已在其他地方上线",null,1); }
		 */
		
		if (fileName != null && !"".equals(fileName)) {
			if(fileName.startsWith("CMS")){
				File file = new File(ToolUtil.CMSIMG + fileName);
				System.out.println(fileName);
				System.out.println(file.exists());
				if (file.exists()) {
					return new FileSystemResource(file);
				}
			}else{
				File file1 = new File(ToolUtil.FILEPATH + fileName);
				System.out.println(fileName);
				System.out.println(file1.exists());
				if (file1.exists()) {
					return new FileSystemResource(file1);
			}
				/*
				 * Map<String, String> map = new HashMap<>(); map.put("picture",
				 * "http://192.168.0.124:8080/getActImage?file_name=111111111_0_image.jpg"
				 * ); return map;
				 */
				// return new StoryResult(true,"成功",new
				// FileSystemResource(file),0);
			}
		}
		System.out.println("...........");
		return null;
	}
}
