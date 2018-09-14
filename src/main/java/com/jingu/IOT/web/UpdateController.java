/**  
*   
* 项目名称：IOT  
* 类名称：UpdateController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月23日 下午12:42:29  
* 修改人：jianghu  
* 修改时间：2017年10月23日 下午12:42:29  
* 修改备注： 下午12:42:29
* @version   
*   
*/ 
package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.service.UserService;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**

* @ClassName: UpdateController
* @Description: TODO
* @author jianghu
* @date 2017年10月23日 下午12:42:29

*/
@RestController
public class UpdateController {

	private ToolUtil toolUtil;
	private UserService userService;
	private PointService pointService;

	@Autowired
	public UpdateController(ToolUtil toolUtil,UserService userService,PointService pointService) {
		this.toolUtil = toolUtil;
		this.userService =userService;
		this.pointService =pointService;
	}
	
	// 软件更新
	@CrossOrigin
	@RequestMapping(value="/updateSoftWare",method=RequestMethod.POST)
	public IOTResult updateSoftWare(@RequestParam("ckuid") String ckuid,@RequestParam("cksid") String cksid,@RequestParam("deviceId") String deviceId,@RequestParam("ip") String ip,@RequestParam("port") String port,@RequestParam("file") MultipartFile file) throws NumberFormatException, IOException{
		if(cksid==null || cksid.trim().length()<1||ckuid==null||ckuid==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+ckuid);
		if(check ==null ||!cksid.equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(ckuid, cksid);
		int ckAdmin = userService.ckAdmin(uid);
		if(ckAdmin ==0 ){
			return new IOTResult(false,"权限不足",null,111);
		}
		if(deviceId==null || deviceId.trim().length()<1||ip==null || ip.trim().length()<1||port==null || port.trim().length()<1){
			return new IOTResult(false,"信息不完善",null,111);
		}
//		boolean vraSoftWareUpdate =true;
		boolean vraSoftWareUpdate = Client.VRASoftWareUpdate(deviceId, file.getBytes(),ip,Integer.parseInt(port));
		if(vraSoftWareUpdate){
			return new IOTResult(true,"升级成功",null,0);
		}
		return new IOTResult(false,"升级失败",null,0);
		
	}
	
}
