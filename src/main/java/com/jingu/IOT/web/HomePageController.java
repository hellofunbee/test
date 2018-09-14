/**  
*   
* 项目名称：sxcms  
* 类名称：HomePageController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月19日 下午5:23:07  
* 修改人：jianghu  
* 修改时间：2017年10月19日 下午5:23:07  
* 修改备注： 下午5:23:07
* @version   
*   
*/ 
package com.jingu.IOT.web;

import com.jingu.IOT.entity.HomePageEntity;
import com.jingu.IOT.requset.HomePageRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.HomePageService;
import com.jingu.IOT.service.UploadService;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**

* @ClassName: HomePageController
* @Description: TODO
* @author jianghu
* @date 2017年10月19日 下午5:23:07
* 首页
*/
@RestController
public class HomePageController {

	private HomePageService homePageService;
	private ToolUtil toolUtil;
	private UploadService uploadService;
	@Autowired
	public HomePageController(HomePageService homePageService, ToolUtil toolUtil,UploadService uploadService) {
		this.homePageService = homePageService;
		this.toolUtil = toolUtil;
		this.uploadService = uploadService;
	}
	@CrossOrigin
	@RequestMapping(value="/updateHomePage",method=RequestMethod.POST)
	public IOTResult updateHomePage(@RequestBody  HomePageRequset hr) {
		if(hr.getCksid()==null ||hr.getCksid().trim().length()<1||hr.getCkuid()==null || hr.getCkuid().trim().length()<1){
			return new IOTResult(false,"信息不规范",null,1);
		}
		boolean flag = false;
		String check = toolUtil.getCheck(ToolUtil.IOT+hr.getCkuid());
		if(check ==null || !check.equals(hr.getCksid())){
			return new IOTResult(false,"登陆失效",null,2);
		}
//		if(hr.getH_cover()==null || hr.getH_cover().trim().length()<1 || hr.getH_url()==null || hr.getH_url().trim().length()<1){
//			hr.setH_state(2);
//		}
		int addHomePagePicture = homePageService.updateHomePagePicture(hr);
		if(addHomePagePicture >0){
			return new IOTResult(true,"保存成功",null,0);
		}
		return new IOTResult(false,"保存失败",null,0);
		
	}
	
	// 2 显示  1 是默认 
	@CrossOrigin
	@RequestMapping(value="/listHomePage",method=RequestMethod.POST)
	public IOTResult listHomePage(@RequestBody HomePageRequset hr) {
//		if(hr.getCksid()==null ||hr.getCksid().trim().length()<1||hr.getCkuid()==null || hr.getCkuid().trim().length()<1){
//			return new CMSResult(false,"信息不规范",null,1);
//		}
//		boolean flag = false;
//		String check = toolUtil.getCheck(ToolUtil.CMS+hr.getCkuid());
//		if(check !=null || check.equals(hr.getCksid())){
//			return new CMSResult(false,"登陆失效",null,2);
//		}
//		hr.setH_state(1);
		List<Map<String,Object>> listHomePagePicture = homePageService.listHomePagePicture(hr);
		if(listHomePagePicture!=null && !listHomePagePicture.isEmpty()){
			return new IOTResult(true,"查看成功",listHomePagePicture,0);
		}
		return new IOTResult(false,"暂无相关信息",null,0);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/listPic",method=RequestMethod.POST)
	public IOTResult listpic(@RequestBody HomePageRequset hr) {
//		if(hr.getCksid()==null ||hr.getCksid().trim().length()<1||hr.getCkuid()==null || hr.getCkuid().trim().length()<1){
//			return new CMSResult(false,"信息不规范",null,1);
//		}
//		boolean flag = false;
//		String check = toolUtil.getCheck(ToolUtil.CMS+hr.getCkuid());
//		if(check !=null || check.equals(hr.getCksid())){
//			return new CMSResult(false,"登陆失效",null,2);
//		}
		hr.setH_state(1);
		List<Map<String,Object>> listHomePagePicture = homePageService.listHomePagePicture(hr);
		if(listHomePagePicture!=null && !listHomePagePicture.isEmpty()){
			return new IOTResult(true,"查看成功",listHomePagePicture,0);
		}
		return new IOTResult(false,"暂无相关信息",null,0);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addHomePagePicture", method = RequestMethod.POST)
	public @ResponseBody IOTResult addPicture(@RequestParam("picture") MultipartFile picture,@RequestParam("cksid") String cksid,@RequestParam("ckuid") String ckuid,@RequestParam("oldfile") String oldfile,@RequestParam("h_id") String h_id){
		String check = toolUtil.getCheck(ToolUtil.ADMIN+ckuid);
		if(check==null || !check.equals(cksid)){
			return new IOTResult(false,"登陆失效",null,0);
		}
		if(!oldfile.equals("")){
		String[] split = oldfile.split("file_name=");
		System.out.println(split[1]);
			boolean deleteFile = uploadService.deleteFile(ToolUtil.FILEPATH+split[1]);
			System.out.println(deleteFile);
		}
		HomePageEntity homePageEntity = new HomePageEntity();
		homePageEntity.setH_id(Integer.parseInt(h_id));
		IOTResult uploadFile2 = uploadService.uploadFile2(ToolUtil.CMS,ckuid,picture);
		if(uploadFile2.isSuccess()){
			homePageEntity.setH_cover(uploadFile2.getObject().toString());
			int updateHomePagePicture = homePageService.updateHomePagePicture(homePageEntity);
			if(updateHomePagePicture >0){
				return new IOTResult(true,"保存成功",uploadFile2.getObject().toString(),0);
			}
		}
		return new IOTResult(false,"保存失败",null,0);
	}
	
}
