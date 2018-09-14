/**  
*   
* 项目名称：IOT  
* 类名称：PointController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月8日 下午7:36:47  
* 修改人：jianghu  
* 修改时间：2017年9月8日 下午7:36:47  
* 修改备注： 下午7:36:47
* @version   
*   
*//* 
package com.jingu.IOT.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.IPCService;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.util.ToolUtil;

*//**

* @ClassName: PointController
* @Description: TODO
* @author jianghu
* @date 2017年9月8日 下午7:36:47

*//*
@RestController
public class PointController {

	private PointService pointService;
	private ToolUtil toolUtil;
	private IPCService iPCService;

	@Autowired
	public PointController(PointService pointService,ToolUtil toolUtil,IPCService iPCService) {
		this.pointService = pointService;
		this.toolUtil = toolUtil;
		this.iPCService = iPCService;
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/addPoint",method=RequestMethod.POST)
	public IOTResult addPoint(PointRequest pr) {
		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		if(check ==null ||!pr.getCksid().equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		pr.setUid(uid);
		PointEntity pointEntity = new PointEntity();
		pointEntity.setUid(1);
		pointEntity.setTp_id(pr.getTp_pid());
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint ==null){
			return new IOTResult(false,"父节点不存在",null,3);
		}
		int addPoint = pointService.addPoint(pointEntity);
		if(addPoint <1){
			return new IOTResult(false, "添加失败", null, 4);
		}
		return new IOTResult(true, "添加成功", null, 0);
		
	}
	@CrossOrigin
	@RequestMapping(value="/deletePoint",method=RequestMethod.POST)
	public IOTResult deletePoint(PointRequest pr) {
		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		if(check ==null ||!pr.getCksid().equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		pr.setUid(uid);
		PointEntity pointEntity = new PointEntity();
		pointEntity.setUid(1);
		pointEntity.setTp_id(pr.getTp_pid());
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint ==null){
			return new IOTResult(false,"父节点不存在",null,3);
		}
		int addPoint = pointService.deletePoint(pointEntity);
		if(addPoint <1){
			return new IOTResult(false, "删除失败", null, 4);
		}
		return new IOTResult(true, "删除成功", null, 0);
		
	}
	@CrossOrigin
	@RequestMapping(value="/getPoint",method=RequestMethod.POST)
	public IOTResult getPoint(PointRequest pr) {
		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		if(check ==null ||!pr.getCksid().equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		pr.setUid(uid);
		PointEntity pointEntity = new PointEntity();
		pointEntity.setUid(1);
		pointEntity.setTp_id(pr.getTp_pid());
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint ==null){
			return new IOTResult(false,"父节点不存在",null,3);
		}
		PointEntity point = pointService.getPoint(pointEntity);
		if(point == null){
			return new IOTResult(false, "查看失败", null, 4);
		}
		return new IOTResult(true, "查看成功", null, 0);
		
	}
	@CrossOrigin
	@RequestMapping(value="/listPoint",method=RequestMethod.POST)
	public IOTResult listPoint(PointRequest pr) {
		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		if(check ==null ||!pr.getCksid().equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		pr.setUid(uid);
		PointEntity pointEntity = new PointEntity();
		pointEntity.setUid(1);
		pointEntity.setTp_id(pr.getTp_pid());
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint ==null){
			return new IOTResult(false,"父节点不存在",null,3);
		}
		List<Map<String,Object>> listPoint2 = pointService.listPoint(pointEntity);
		if(listPoint2==null || listPoint2.size() <1){
			return new IOTResult(false, "查看失败", null, 4);
		}
		return new IOTResult(true, "查看成功", null, 0);
		
	}
	@CrossOrigin
	@RequestMapping(value="/updatePoint",method=RequestMethod.POST)
	public IOTResult updatePoint(PointRequest pr) {
		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		if(check ==null ||!pr.getCksid().equals(check)){
			return  new IOTResult(false,"登陆失效",null,2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		pr.setUid(uid);
		PointEntity pointEntity = new PointEntity();
		pointEntity.setUid(1);
		pointEntity.setTp_id(pr.getTp_pid());
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint ==null){
			return new IOTResult(false,"父节点不存在",null,3);
		}
		int addPoint = pointService.updatePoint(pointEntity);
		if(addPoint <1){
			return new IOTResult(false, "删除失败", null, 4);
		}
		return new IOTResult(true, "删除成功", null, 0);
		
	}
	
	
	
	
}
*/