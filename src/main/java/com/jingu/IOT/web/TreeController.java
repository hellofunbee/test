/**  
*   
* 项目名称：IOT  
* 类名称：TreeController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 下午4:34:51  
* 修改人：jianghu  
* 修改时间：2017年9月5日 下午4:34:51  
* 修改备注： 下午4:34:51
* @version   
*   
*/
package com.jingu.IOT.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.MainDeviceService;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.service.SettingService;
import com.jingu.IOT.service.UserService;
import com.jingu.IOT.util.ToolUtil;

/**
 * 
 * @ClassName: TreeController
 * @Description: TODO
 * @author jianghu
 * @date 2017年9月5日 下午4:34:51
 * 
 */
@RestController
public class TreeController {

	private PointService pointService;
	private ToolUtil toolUtil;
	private MainDeviceService mainDeviceService;
	private SettingService settingService;
	private UserService userService;

	@Autowired
	public TreeController(PointService pointService, ToolUtil toolUtil, MainDeviceService mainDeviceService,
			SettingService settingService, UserService userService) {
		this.pointService = pointService;
		this.toolUtil = toolUtil;
		this.mainDeviceService = mainDeviceService;
		this.settingService = settingService;
		this.userService = userService;
	};

	// 添加节点
	@CrossOrigin
	@RequestMapping(value = "/addPoint", method = RequestMethod.POST)
	public IOTResult addPoint(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());

		if (pr.getTp_pid() != 0) {
			if (pr.getTp_type() == 1) {
				return new IOTResult(false, "请确认节点类型", null, 4);
			}
			PointEntity pointEntity = new PointEntity();
			pointEntity.setTp_id(pr.getTp_pid());
			// pointEntity.setUid(uid);
			// pointEntity.setRole(String.valueOf(uid));
			PointEntity point = pointService.getPoint(pointEntity);
			if (point == null) {
				return new IOTResult(false, "父节点不存在", null, 3);
			}
		}
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		/*
		 * if(!point.get("uid").equals(uid)){ return new
		 * IOTResult(false,"您无权添加到该节点下",null,4); }
		 */
		if (pr.getTp_type() < 0 && pr.getTp_type() > 3) {
			return new IOTResult(false, "类型不正确", null, 5);
		}
		String maxId = toolUtil.getMaxId(ToolUtil.TREEID);
		if (maxId == null) {
			int listMaxId = pointService.listMaxId();
			toolUtil.setMaxId(ToolUtil.TREEID, listMaxId);
		}
		Long maxIdInc = toolUtil.MaxIdInc(ToolUtil.TREEID);
		pr.setTp_id(maxIdInc.intValue());
		int addPoint = pointService.addPoint(pr);
		// 您无权添加到该节点下
		// 节点不存在
		if (addPoint > 0) {
			return new IOTResult(true, "节点添加成功", null, 0);
		}
		return new IOTResult(false, "节点添加失败", null, 6);
	}

	// 查看节点
	@CrossOrigin
	@RequestMapping(value = "/listPoint", method = RequestMethod.POST)
	public IOTResult listPoint(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCksid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// System.out.println(pr.getCkuid() + "---" + pr.getCksid());
		// long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		long uid = Long.valueOf(pr.getCkuid());
		// PointEntity pointEntity = new PointEntity();
		// 如果是管理员的话,把uid置为零
		if (pr.getU_type() == 2) {
			pr.setRole(String.valueOf(pr.getCkuid()));
		} else {
			int ckAdmin = userService.ckAdmin(uid);
			if (ckAdmin == 1) {
				uid = 0;
			} else {
				pr.setRole(String.valueOf(uid));
			}
		}
		List<Map<String, Object>> listPoint = pointService.listPoint(pr);
		/*
		 * if(listPoint ==null){ return new IOTResult(false,"父节点不存在",null,3); }
		 */
		/*
		 * if(!point.get("uid").equals(uid)){ return new
		 * IOTResult(false,"您无权添加到该节点下",null,4); }
		 */
		/*
		 * if(pr.getTp_type() >0&&pr.getTp_type()<5){ return new
		 * IOTResult(false,"类型不正确",null,5); }
		 */
		// int addPoint = pointService.addPoint(pr);
		// 您无权添加到该节点下
		// 节点不存在

		if (listPoint != null && listPoint.size() > 0) {
			return new IOTResult(true, "查看成功", listPoint, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	// 查看节点
	@CrossOrigin
	@RequestMapping(value = "/listPoint2", method = RequestMethod.POST)
	public IOTResult listPoint2(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCksid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		// 如果是管理员的话,把uid置为零
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 1) {
			uid = 0;
		} else {
			pr.setRole(String.valueOf(uid));
		}
		List<Map<String, Object>> listPoint2 = pointService.listPoint2(pr);

		if (listPoint2 != null && listPoint2.size() > 0) {
			return new IOTResult(true, "查看成功", listPoint2, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	// 查看节点
	@CrossOrigin
	@RequestMapping(value = "/listPoint3", method = RequestMethod.POST)
	public IOTResult listPoint3(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCksid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		// 如果是管理员的话,把uid置为零
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 1) {
			uid = 0;
		} else {
			pr.setRole(String.valueOf(uid));
		}
		Map<String, Object> listPoint3 = pointService.listPoint3(pr);

		if (listPoint3 != null && listPoint3.size() > 0) {
			return new IOTResult(true, "查看成功", listPoint3, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	// 删除节点
	@CrossOrigin
	@RequestMapping(value = "/deletePoint", method = RequestMethod.POST)
	public IOTResult deletePoint(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		PointEntity pointEntity = new PointEntity();
		pointEntity.setTp_id(pr.getTp_id());
		PointEntity point = pointService.getPoint(pointEntity);// 已经包括了节点的权限问题
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		pointEntity.setTp_id(0);
		pointEntity.setTp_pid(pr.getTp_id());
		List<Map<String, Object>> listPoint = pointService.listPoint(pointEntity);
		if (listPoint != null && listPoint.size() > 0) {
			return new IOTResult(false, "该节点下有子节点,不能删除", null, 4);
		}
		/*
		 * if(!point.get("uid").equals(uid)){ return new
		 * IOTResult(false,"您无权删除该节点下",null,5); }
		 */
		/*
		 * if(pr.getTp_type() >0&&pr.getTp_type()<5){ return new
		 * IOTResult(false,"类型不正确",null,6); }
		 */
		int addPoint = pointService.deletePoint(pr);
		// 您无权添加到该节点下
		// 节点不存在
		// 该节点有子节点不能删除
		if (addPoint > 0) {
			return new IOTResult(true, "节点删除成功", null, 0);
		}
		return new IOTResult(false, "节点删除失败", null, 7);
	}

	// 获得节点相管信息
	@CrossOrigin
	@RequestMapping(value = "/getPoint", method = RequestMethod.POST)
	public IOTResult getPoint(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		PointEntity pointEntity = new PointEntity();
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 1) {
			uid = 0;
		} else {
			pr.setRole(String.valueOf(uid));
		}
		pointEntity.setUid(uid);
		pointEntity.setRole(String.valueOf(uid));
		pointEntity.setTp_id(pr.getTp_pid());
		PointEntity point = pointService.getPoint(pointEntity);

		if (pointEntity.getTp_pid() != 0) {
			if (point == null) {
				return new IOTResult(false, "父节点不存在", null, 3);
			}
		}

		// if(!point.get("uid").equals(uid)){
		// return new IOTResult(false,"您无权添加到该节点下",null,4);
		// }
		if (pr.getTp_type() > 0 && pr.getTp_type() < 5) {
			return new IOTResult(false, "类型不正确", null, 5);
		}
		// int addPoint = pointService.addPoint(pr);
		// 您无权添加到该节点下
		// 节点不存在
		if (point != null) {
			return new IOTResult(true, "查看成功", point, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 6);
	}

	// 查看节点
	@CrossOrigin
	@RequestMapping(value = "/listPointGroup", method = RequestMethod.POST)
	public IOTResult listPointGroup(@RequestBody PointRequest pr) {
		// if(pr.getCksid()==null ||
		// pr.getCksid().trim().length()<1||pr.getCkuid()==null||
		// pr.getCksid().trim().length()<1){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// // 注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		// if(check ==null ||!pr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		// // 如果是管理员的话,把uid置为零
		// int ckAdmin = userService.ckAdmin(uid);
		// if(ckAdmin ==1 ){
		// uid =0;
		// }
		// pr.setUid(uid);
		//// pointEntity.setTp_type(1);
		// pr.setRole(String.valueOf(uid));

		List<Map<String, Object>> listPointGroup = pointService.listPointGroup(pr);
		if (listPointGroup != null && listPointGroup.size() > 0) {
			return new IOTResult(true, "查看成功", listPointGroup, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

}
