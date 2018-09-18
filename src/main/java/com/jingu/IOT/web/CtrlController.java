/**  
*   
* 项目名称：IOT  
* 类名称：CtrlController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月12日 上午9:48:37  
* 修改人：jianghu  
* 修改时间：2017年10月12日 上午9:48:37  
* 修改备注： 上午9:48:37
* @version   
*   
*/
package com.jingu.IOT.web;

import com.jingu.IOT.entity.*;
import com.jingu.IOT.requset.ControlRequset;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.*;
import com.jingu.IOT.switcher.VRASwitchBean;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.PublicMethod;
import com.jingu.IOT.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @ClassName: CtrlController
 * @Description: TODO
 * @author jianghu
 * @date 2017年10月12日 上午9:48:37 控制
 */
@RestController
public class CtrlController {

	private final Logger logger = LoggerFactory.getLogger(CtrlController.class);

	private SettingService settingService;
	private ToolUtil toolUtil;
	private PointService pointService;
	private UserService userService;
	private GatherService gatherService;
	private ControlService controlService;
	private RuleService ruleService;
	private CtrlService ctrlService;

	@Autowired
	public CtrlController(SettingService settingService, ToolUtil toolUtil, PointService pointService,
			UserService userService, GatherService gatherService, RuleService ruleService, CtrlService ctrlService) {
		this.settingService = settingService;
		this.toolUtil = toolUtil;
		this.pointService = pointService;
		this.userService = userService;
		this.gatherService = gatherService;
		this.ruleService = ruleService;
		this.ctrlService = ctrlService;
	}

	// @CrossOrigin
	// @RequestMapping(value = "/", method = RequestMethod.POST)
	// public IOTResult logs(){
	// UserEntity userEntity = new UserEntity();
	// userEntity.setStart(1);
	// userEntity.setTu_username("这个是什么鬼..");
	// userEntity.setTu_phone("123456");
	// logger.debug("为什么不打印名字呢????");
	// logger.debug(""+userEntity.getTu_username()+userEntity.getTu_phone()+"");
	// logger.info("This is an info message");
	// logger.warn("This is a warn message");
	// logger.error("This is an error message");
	// logger.error("为什么不打印名字呢????");
	// logger.error(userEntity.getTu_username()+userEntity.getTu_phone());
	// new SpringLoggingHelper().helpMethod();
	// return null;
	// }

	// 添加控制配置
	@CrossOrigin
	@RequestMapping(value = "/addControlSetting", method = RequestMethod.POST)
	public IOTResult addControlSetting(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		PointEntity pointEntity = cr.getPointEntity();
		pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setUid(uid);
		// pointEntity.setRole(String.valueOf(uid));
		PointEntity point = pointService.getPoint(pointEntity);
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		int addDistributionList = settingService.addControlSetting(cr);
		if (addDistributionList > 0) {
			return new IOTResult(true, "添加成功", null, 0);
		}
		return new IOTResult(false, "添加失败", null, 0);
	}

	// 删除控制配置
	@CrossOrigin
	@RequestMapping(value = "/deleteControlSetting", method = RequestMethod.POST)
	public IOTResult deleteControlSetting(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		// PointEntity pointEntity = cr.getPointEntity();
		// pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setRole(String.valueOf(uid));
		// pointEntity.setUid(uid);
		// pointEntity.setRole(String.valueOf(uid));
		// PointEntity point = pointService.getPoint(pointEntity);
		// if(point ==null ){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		int addDistributionList = settingService.deleteControlSetting(cr);
		if (addDistributionList > 0) {
			return new IOTResult(true, "删除成功", null, 0);
		}
		return new IOTResult(false, "删除失败", null, 0);
	}

	// 修改配置
	@CrossOrigin
	@RequestMapping(value = "/updateControlSetting", method = RequestMethod.POST)
	public IOTResult updateControlSetting(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		PointEntity pointEntity = cr.getPointEntity();
		pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setRole(String.valueOf(uid));
		// pointEntity.setUid(uid);
		// pointEntity.setRole(String.valueOf(uid));
		PointEntity point = pointService.getPoint(pointEntity);
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		int addDistributionList = settingService.updateControlSetting(cr);
		if (addDistributionList > 0) {
			return new IOTResult(true, "修改成功", null, 0);
		}
		return new IOTResult(false, "修改失败", null, 0);
	}

	// 查看配置
	@CrossOrigin
	@RequestMapping(value = "/listControlSetting", method = RequestMethod.POST)
	public IOTResult listControlSetting(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		PointEntity pointEntity = cr.getPointEntity();
		// pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setUid(uid);
		// pointEntity.setRole(String.valueOf(uid));
		PointEntity point = pointService.getPoint(pointEntity);
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}
		cr.setCtrl_deviceId(cr.getPointEntity().getDeviceId());
		List<Map<String, Object>> listControlSetting = settingService.listControlSetting(cr);
		if (listControlSetting != null && !listControlSetting.isEmpty()) {
			return new IOTResult(true, "查看成功", listControlSetting, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	/**
	 * 规格文件 channel 以及该设备下的ipc
	 * @param pr
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/listChannel", method = RequestMethod.POST)
	public IOTResult listChannel(@RequestBody PointRequest pr) {
		if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
				|| pr.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
		if (check == null || !pr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}

		List<Map<String, Object>> listChannel = gatherService.listChannel(pr);
		if (listChannel != null && !listChannel.isEmpty()) {
			// int lastIndexOf = pr.getDeviceId().lastIndexOf(".");
			// pr.setDeviceId(pr.getDeviceId().substring(0, lastIndexOf));
			List<Map<String, Object>> listPoint2 = pointService.listPoint2(pr);
			Map<String, Object> map = new HashMap<>();
			map.put("listChannel", listChannel);
			map.put("listPoint", listPoint2);
			return new IOTResult(true, "查看成功", map, 0);
		}
		return new IOTResult(false, "查看失败", null, 0);
	}

	// 获得控制设备配置
	@CrossOrigin
	@RequestMapping(value = "/getControlSetting", method = RequestMethod.POST)
	public IOTResult getControlSetting(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		PointEntity pointEntity = cr.getPointEntity();
		pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setRole(String.valueOf(uid));
		// pointEntity.setUid(uid);
		PointEntity point = pointService.getPoint(pointEntity);
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}

		Map<String, Object> controlSetting = settingService.getControlSetting(cr);
		if (controlSetting != null && !controlSetting.isEmpty()) {
			return new IOTResult(true, "查看成功", controlSetting, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	// 暂时无用
	@CrossOrigin
	@RequestMapping(value = "/listControlDevByUid", method = RequestMethod.POST)
	public IOTResult listControlDevByDeviceId(@RequestBody ControlRequset cr) {

		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		// 检测是否有这个点
		long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		PointEntity pointEntity = cr.getPointEntity();
		pointEntity.setUid(uid);
		pointEntity.setRole(String.valueOf(uid));
		pointEntity.setRole(String.valueOf(uid));
		pointEntity.setTp_type(3);// 标识主设备
		List<Map<String, Object>> listPoint = pointService.listPoint(pointEntity);
		if (listPoint == null || listPoint.isEmpty()) {
			return new IOTResult(false, "节点不存在", null, 3);
		}
		for (Map<String, Object> map : listPoint) {
			String deviceId = map.get("deviceId").toString();
			pointEntity.setDeviceId(deviceId);
			List<Map<String, Object>> listControlDevByDeviceId = settingService.listControlDevByDeviceId(pointEntity);
			map.put("list", listControlDevByDeviceId);
		}
		if (listPoint != null && !listPoint.isEmpty()) {
			return new IOTResult(true, "查看成功", listPoint, 0);
		}
		return new IOTResult(false, "暂无相关信息", null, 0);
	}

	// 开启远程远程控制
	@CrossOrigin
	@RequestMapping(value = "/controlMode", method = RequestMethod.POST)
	public IOTResult ControlMode(@RequestBody ControlRequset cr) {
		if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
				|| cr.getCkuid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
		if (check == null || !cr.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		cr.setState_type(1);
		int updateControlSetting = settingService.updateControlSetting(cr);
		if (updateControlSetting > 0) {
			// if(cr.getState_type()==1){
			return new IOTResult(true, "自动控制启用成功", null, 0);
			// }
			// if(cr.getState_type()==0){
			// return new IOTResult(true,"模式关闭成功",null,0);
			// }
		}
		return new IOTResult(false, "自动控制启用失败", null, 0);

	}

	// 控制设备
	@CrossOrigin
	@RequestMapping(value = "/controlDev", method = RequestMethod.POST)
	public IOTResult ControlDev(@RequestBody ControlRequset cr) {
		PointEntity point = null;
		ControlEntity controlEntity = new ControlEntity();
		controlEntity.setCtrl_id(cr.getCtrl_id());
		if (cr.getRuleEntity().getAdmin() != null && cr.getRuleEntity().getAdmin().equals("IOTDevice")) {
			System.out.println("控制中...");
			point = cr.getRuleEntity().getPointEntity();
			controlEntity.setState_type(2);
		} else {
			if (cr.getCksid() == null || cr.getCksid().trim().length() < 1 || cr.getCkuid() == null
					|| cr.getCkuid().trim().length() < 1) {
				return new IOTResult(false, "信息不规范", null, 1);
			}
			// 注册登陆按照什么来????
			String check = toolUtil.getCheck(ToolUtil.IOT + cr.getCkuid());
			if (check == null || !cr.getCksid().equals(check)) {
				return new IOTResult(false, "登陆失效", null, 2);
			}
			if (cr.getModel() == 1) {
				controlEntity.setState_type(1);
				int updateControlSetting = settingService.updateControlSetting(controlEntity);
				if (updateControlSetting > 0) {
					return new IOTResult(true, "控制模式开启成功", null, 0);
				}
				return new IOTResult(false, "控制模式开启失败", null, 0);
			}
			controlEntity.setState_type(1);
			// 检测是否有这个点
			long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
			PointEntity pointEntity = cr.getPointEntity();
			// pointEntity.setDeviceId(cr.getCtrl_deviceId());
			// pointEntity.setUid(uid);
			// pointEntity.setRole(String.valueOf(uid));
			point = pointService.getPoint(pointEntity);
			if (point == null) {
				return new IOTResult(false, "节点不存在", null, 3);
			}
		}
		Map<String, Object> controlSetting = settingService.getControlSetting(cr);
		if (controlSetting == null || controlSetting.isEmpty()) {
			return new IOTResult(false, "控制设备不存在", null, 0);
		}
		// controlEntity.setState_type(1);
		// b=new byte[12];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
		// b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
		// b[5]=(byte)this.getDirection();
		// b[6]=(byte)this.getDistanceOrDuration();
		// b[7]=(byte)this.getPosSensorCH();
		// byte[] maxB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
		// System.arraycopy(maxB, 0, b, 8, 2);
		// byte[] minB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
		// System.arraycopy(minB, 0, b, 10, 2);
		// }
		// if((byte)this.getCtrlType()==0x2){
		// b=new byte[9];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// byte[]
		// durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
		// System.arraycopy(durationByte, 0, b, 3, 4);
		// }
		// return b;
		// }
		point = cr.getPointEntity();
		MotorHBM hbm = cr.getHbm();
		if (hbm.getDistanceOrDuration() == 0) {
			hbm.setDistanceOrDuration(cr.getDistanceOrDuration());
		}
		hbm.setCtrlType(Integer.parseInt(controlSetting.get("ctrl_type").toString()));
		hbm.setRaiseGroupId((controlSetting.get("ctrl_raise_groupId").toString()));
		hbm.setRaiseSwitchId((controlSetting.get("ctrl_raise_switchId").toString()));
		hbm.setSkinGroupId((controlSetting.get("ctrl_down_groupId").toString()));
		hbm.setSkinSwitchId((controlSetting.get("ctrl_down_switchId").toString()));

		if (controlSetting.get("ctrl_type").toString().equals("1")) {
			hbm.setPosSensorCH(Integer.parseInt(controlSetting.get("ctrl_channel").toString()));
			if (hbm.getDirection() == 1 && hbm.getDistanceOrDuration() == 100) {
				controlEntity.setMontor_state(1);
			} else if (hbm.getDirection() == 2 && hbm.getDistanceOrDuration() == 0) {
				controlEntity.setMontor_state(2);
			} else if (hbm.getDirection() == 3 && hbm.getDistanceOrDuration() == 0) {
				controlEntity.setMontor_state(3);
			} else if (hbm.getDirection() == 0) {
				// controlEntity.setMontor_state(3);
				// 计算当前开启度
			}

		}
		// 分成了几种情况 // 1电机一直升上 一直下降 停止 并且保存状态
		// 2输入了开启度点击执行

		System.out.println(point.getIp());
		System.out.println(point.getPort());
		System.out.println(point.getDeviceId());
		System.out.println(hbm.toByteCmd());
		boolean motorsCtrl2 = Client.motorsCtrl2(point.getIp(), point.getPort(), point.getDeviceId(), hbm.toByteCmd(),
				(byte) 0x17);
		System.out.println(motorsCtrl2);
		// [85, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 5, 16, 0, 33, 116, 102, 16]
		// [85, 23, 0, -100, 0, 0, 0, 0, 0, 0, 0, 7, 0, 5, 16, 0, 33, 116, -103,
		// 67]
		// [85, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 5, 16, 0, 34, -104, -103,
		// 104]
		if (motorsCtrl2) {
			System.out.println("------------------------------");
			if (hbm.getDistanceOrDuration() == -1) {
				controlEntity.setS_state(1);
			}
			if (hbm.getDistanceOrDuration() == 0) {
				controlEntity.setS_state(2);
			}
			if (hbm.getDistanceOrDuration() > 0) {
				hbm.setDistanceOrDuration(-1);
				;
			}
			int updateControlSetting = settingService.updateControlSetting(controlEntity);
			return new IOTResult(true, "命令发送成功", hbm.getDistanceOrDuration(), 0);
		}
		return new IOTResult(false, "命令发送失败", null, 10);
	}

	// 只能控制
	@CrossOrigin
	@RequestMapping(value = "/testing", method = RequestMethod.POST)
	public @ResponseBody IOTResult testing(@RequestParam("str") String string,
			@RequestParam("deviceId") String deviceId) throws UnsupportedEncodingException {
		System.out.println(string);
		String string2 = "43595.0;-37.6;null;8.2;735.1;null;-37.7;8.2;null;-39.3;null;8.7;-39.2;8.8;11.7;45.0;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null;null";
		String[] split = string.split(";");
		ctrlService.upSwitch(deviceId, split);
		System.out.println("test方法运行了");
		return null;
	}

	// @CrossOrigin
	// @RequestMapping(value = "/testing", method = RequestMethod.POST)
	// public IOTResult testing(@RequestParam("str") String string){
	// System.out.println(string);
	// return null;
	// PointEntity point =null;
	// ControlEntity controlEntity = new ControlEntity();
	// controlEntity.setCtrl_id(cr.getCtrl_id());
	// if(cr.getRuleEntity().getAdmin()!=null &&
	// cr.getRuleEntity().getAdmin().equals("IOTDevice")){
	// System.out.println("控制中...");
	// point = cr.getRuleEntity().getPointEntity();
	// controlEntity.setState_type(2);
	// }else{
	// if(cr.getCksid()==null ||
	// cr.getCksid().trim().length()<1||cr.getCkuid()==null||cr.getCkuid().trim().length()<1){
	// return new IOTResult(false,"信息不规范",null,1);
	// }
	// // 注册登陆按照什么来????
	// String check = toolUtil.getCheck(ToolUtil.IOT+cr.getCkuid());
	// if(check ==null ||!cr.getCksid().equals(check)){
	// return new IOTResult(false,"登陆失效",null,2);
	// }
	// controlEntity.setState_type(1);
	// // 检测是否有这个点
	// long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
	// PointEntity pointEntity = cr.getPointEntity();
	// pointEntity.setDeviceId(cr.getCtrl_deviceId());
	//// pointEntity.setUid(uid);
	//// pointEntity.setRole(String.valueOf(uid));
	// point = pointService.getPoint(pointEntity);
	// if(point ==null ){
	// return new IOTResult(false,"节点不存在",null,3);
	// }
	// }
	// Map<String, Object> controlSetting =
	// settingService.getControlSetting(cr);
	// if(controlSetting ==null || controlSetting.isEmpty()){
	// return new IOTResult(false,"控制设备不存在",null,0);
	// }
	//// controlEntity.setState_type(1);
	//// b=new byte[12];
	//// b[0]=(byte)this.getCtrlType();
	//// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
	//// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
	//// b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
	//// b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
	//// b[5]=(byte)this.getDirection();
	//// b[6]=(byte)this.getDistanceOrDuration();
	//// b[7]=(byte)this.getPosSensorCH();
	//// byte[] maxB =
	// PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
	//// System.arraycopy(maxB, 0, b, 8, 2);
	//// byte[] minB =
	// PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
	//// System.arraycopy(minB, 0, b, 10, 2);
	//// }
	//// if((byte)this.getCtrlType()==0x2){
	//// b=new byte[9];
	//// b[0]=(byte)this.getCtrlType();
	//// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
	//// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
	//// byte[]
	// durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
	//// System.arraycopy(durationByte, 0, b, 3, 4);
	//// }
	//// return b;
	//// }
	// MotorHBM hbm = cr.getHbm();
	// if(hbm.getDistanceOrDuration()==0){
	// hbm.setDistanceOrDuration(cr.getDistanceOrDuration());
	// }
	// hbm.setCtrlType(Integer.parseInt(controlSetting.get("ctrl_type").toString()));
	// hbm.setRaiseGroupId((controlSetting.get("ctrl_raise_groupId").toString()));
	// hbm.setRaiseSwitchId((controlSetting.get("ctrl_raise_switchId").toString()));
	// hbm.setSkinGroupId((controlSetting.get("ctrl_down_groupId").toString()));
	// hbm.setSkinSwitchId((controlSetting.get("ctrl_down_switchId").toString()));
	// if(controlSetting.get("ctrl_type").toString().equals("1")){
	// hbm.setPosSensorCH(Integer.parseInt(controlSetting.get("ctrl_channel").toString()));
	// }
	//
	//
	// System.out.println(point.getIp());
	// System.out.println(point.getPort());
	// System.out.println(point.getDeviceId());
	// System.out.println(hbm.toByteCmd());
	// boolean motorsCtrl2 = Client.motorsCtrl2(point.getIp(), point.getPort(),
	// point.getDeviceId(), hbm.toByteCmd(), (byte)0x17);
	// System.out.println(motorsCtrl2);
	// //[85, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 5, 16, 0, 33, 116, 102, 16]
	// //[85, 23, 0, -100, 0, 0, 0, 0, 0, 0, 0, 7, 0, 5, 16, 0, 33, 116, -103,
	// 67]
	// if(motorsCtrl2){
	// System.out.println("------------------------------");
	// if(hbm.getDistanceOrDuration()==-1){
	// controlEntity.setS_state(1);
	// }
	// if(hbm.getDistanceOrDuration()==0){
	// controlEntity.setS_state(2);
	// }
	// int updateControlSetting =
	// settingService.updateControlSetting(controlEntity);
	// return new IOTResult(true,"命令发送成功",null,0);
	// }
	// return new IOTResult(false,"命令发送失败",null,10);
	// }

	// 批量验证开关能否使用
	@CrossOrigin
	@RequestMapping(value = "/controlDevList", method = RequestMethod.POST)
	public IOTResult controlDevList(@RequestBody ControlList cr) {
		// PointEntity point =null;
		System.out.println("控制中...");
		// if(cr.getCksid()==null ||
		// cr.getCksid().trim().length()<1||cr.getCkuid()==null||cr.getCkuid().trim().length()<1){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// // 注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+cr.getCkuid());
		// if(check ==null ||!cr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// 检测是否有这个点
		// b=new byte[12];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
		// b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
		// b[5]=(byte)this.getDirection();
		// b[6]=(byte)this.getDistanceOrDuration();
		// b[7]=(byte)this.getPosSensorCH();
		// byte[] maxB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
		// System.arraycopy(maxB, 0, b, 8, 2);
		// byte[] minB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
		// System.arraycopy(minB, 0, b, 10, 2);
		// }
		// if((byte)this.getCtrlType()==0x2){
		// b=new byte[9];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// byte[]
		// durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
		// System.arraycopy(durationByte, 0, b, 3, 4);
		// }
		// return b;
		// }
		PointEntity point = cr.getPointEntity();
		System.out.println(point.getIp());
		System.out.println(point.getPort());
		System.out.println(point.getDeviceId());
		List<ControlEntity> list = cr.getList();
		MotorHBM hbm = new MotorHBM();
		IOTResult iotResult = new IOTResult();
		List<Object> result = new ArrayList<>();
		Map<String, Object> map = null;
		for (ControlEntity controlEntity : list) {
			map = new HashMap<>();
			hbm.setCtrlType(controlEntity.getCtrl_type());
			hbm.setRaiseGroupId(String.valueOf(controlEntity.getCtrl_raise_groupId()));
			hbm.setRaiseSwitchId(String.valueOf(controlEntity.getCtrl_raise_switchId()));
			hbm.setSkinGroupId(String.valueOf(controlEntity.getCtrl_down_groupId()));
			hbm.setSkinSwitchId(String.valueOf(controlEntity.getCtrl_down_switchId()));
			hbm.setPosSensorCH(Integer.parseInt(controlEntity.getCtrl_channel()));
			hbm.setDistanceOrDuration(controlEntity.getDistanceOrDuration());
			System.out.println(hbm.toByteCmd());
			boolean motorsCtrl2 = Client.motorsCtrl2(point.getIp(), point.getPort(), point.getDeviceId(),
					hbm.toByteCmd(), (byte) 0x17);
			if (motorsCtrl2) {
				// iotResult.setSuccess(true);
				map.put("success", true);
				// iotResult.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送成功");
				map.put("msg", "(" + controlEntity.getCtrl_raise_groupId() + ","
						+ controlEntity.getCtrl_raise_switchId() + "),命令发送成功");
				// result.add(iotResult);
				result.add(map);
				controlEntity.setSuccess(true);
				controlEntity.setMsg("(" + controlEntity.getCtrl_raise_groupId() + ","
						+ controlEntity.getCtrl_raise_switchId() + "),命令发送成功");
			} else {
				map.put("success", false);
				map.put("msg", "(" + controlEntity.getCtrl_raise_groupId() + ","
						+ controlEntity.getCtrl_raise_switchId() + "),命令发送成功");
				// iotResult.setSuccess(false);
				// iotResult.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送失败");
				result.add(map);
				controlEntity.setSuccess(false);
				controlEntity.setMsg("(" + controlEntity.getCtrl_raise_groupId() + ","
						+ controlEntity.getCtrl_raise_switchId() + "),命令发送失败");
				// result.add(map);
			}
		}
		//
		// System.out.println(motorsCtrl2);
		// if(motorsCtrl2){
		// System.out.println("------------------------------");
		// return new IOTResult(true,"命令发送成功",null,0);
		// }
		return new IOTResult(true, "执行成功", result, 0);
	}

	@CrossOrigin
	@RequestMapping(value = "/getControlDevStatus", method = RequestMethod.POST)
	public IOTResult getControlDevStatus(@RequestBody ControlRequset cr) throws UnsupportedEncodingException {
		// if(cr.getCksid()==null ||
		// cr.getCksid().trim().length()<1||cr.getCkuid()==null||cr.getCkuid().trim().length()<1){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// // 注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+cr.getCkuid());
		// if(check ==null ||!cr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// 检测是否有这个点
		// long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
		HashMap<Object, Object> hashMap3 = new HashMap<>();
		PointEntity pointEntity = cr.getPointEntity();
		// pointEntity.setDeviceId(cr.getCtrl_deviceId());
		// pointEntity.setUid(uid);
		// pointEntity.setRole(String.valueOf(uid));
		PointEntity point = pointService.getPoint(pointEntity);
		if (point == null) {
			return new IOTResult(false, "节点不存在", null, 3);
		}

		Map<String, Object> controlSetting = settingService.getControlSetting(cr);
		String string = controlSetting.get("s_state").toString();

		if (controlSetting == null || controlSetting.isEmpty()) {
			return new IOTResult(false, "控制设备不存在", null, 0);
		}
		MotorHBM hbm = cr.getHbm();
		hbm.setCtrlType(Integer.parseInt(controlSetting.get("ctrl_type").toString()));
		hbm.setRaiseGroupId((controlSetting.get("ctrl_raise_groupId").toString()));
		hbm.setRaiseSwitchId((controlSetting.get("ctrl_raise_switchId").toString()));
		hbm.setSkinGroupId((controlSetting.get("ctrl_down_groupId").toString()));
		hbm.setSkinSwitchId((controlSetting.get("ctrl_down_switchId").toString()));
		hbm.setPosSensorCH(Integer.parseInt(controlSetting.get("ctrl_channel").toString()));
		int parseInt = Integer.parseInt(controlSetting.get("ctrl_type").toString());
		if (parseInt == 1) {
			int result = -1;
			byte[] recData = Client.getMotorSensor2(point.getIp(), point.getPort(), point.getDeviceId());
			int posSensorSh = Integer.parseInt(controlSetting.get("ctrl_channel").toString());
			String maxVal = controlSetting.get("ctrl_max").toString();
			String minVal = controlSetting.get("ctrl_min").toString();
			if (recData == null)
				System.out.println("没收到结果...");
			else {
				// 获取相应通道传感器数据
				// int result=-1;
				try {
					byte[] value = new byte[2];

					System.arraycopy(recData, 14 + 4 + 1 + (posSensorSh - 1) * 2, value, 0, 2);// 数据头+id+数据类型+...

					int max = 65535;
					int min = 0;

					try {
						max = Integer.parseInt(maxVal);
					} catch (Exception e) {
					}
					try {
						min = Integer.parseInt(minVal);
					} catch (Exception e) {
					}

					result = PublicMethod.byteToInt2(value);
					if (result < min)
						result = min;
					else if (result > max)
						result = max;
					// result = (int)(result/65535.00*5-1)*25;
					result = 100 - (int) (result - min) * 100 / (max - min);
					System.out.println("开启度：" + result);
				} catch (Exception e) {
					// out.print("开启度获取错误");
				}
				hashMap3.put("s_state", result);
				System.out.println(result);
			}

		}
		if (parseInt == 2) {
			// byte[] dataValue =null;
			// VRASwitchBean switch1 = Client.getSwitch(point.getIp(),
			// point.getPort(), dataValue, point.getDeviceId(), false);
			// System.out.println(switch1);
			byte[] bs2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
			try {
				VRASwitchBean switch1 = Client.getSwitch(point.getIp(), point.getPort(), bs2, point.getDeviceId(),
						false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("获得开关报错");
				e.printStackTrace();
			}
			// System.out.println(switch1);
			// Object object =
			// switch1.getGroupList().get(Integer.parseInt((controlSetting.get("ctrl_raise_groupId").toString())));
			// JSONArray fromObject = JSONArray.fromObject(object);
			// Object object2 =
			// fromObject.get(Integer.parseInt((controlSetting.get("ctrl_raise_switchId").toString())));
			hashMap3.put("s_state", string);
		}
		Map<Object, Object> map = new HashMap<>();
		map.put("state", 2);
		RuleEntity ruleEntity = new RuleEntity();
		ruleEntity.setCtrl_id(cr.getCtrl_id());
		HashMap<Object, Object> hashMap = new HashMap<>();
		HashMap<Object, Object> hashMap2 = new HashMap<>();
		ruleEntity.setType(1);
		// List<RuleEntity> timing = ruleService.listRule(ruleEntity);
		List<RuleEntity> timing = toolUtil.getRuleList(ToolUtil.RULE);
		List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR + cr.getPointEntity().getDeviceId());
		List<MonitorEntity> monitorCollect = null;
		List<RuleEntity> collect2 = null;
		List<RuleEntity> collect = null;
		if (timing != null) {
			collect = timing.stream().filter(x -> x.getCtrl_id() == cr.getCtrl_id()).collect(Collectors.toList());
			if (cr.getApp() == 1) {
				Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				System.out.println(hour + "-" + minute + "-" + second);
				String cktime = toolUtil.cktimeHour(hour + ":" + minute + ":" + second);
				collect2 = collect.stream().filter(x -> x.getExecTime().compareTo(cktime) > 0)
						.collect(Collectors.toList());
				Comparator<RuleEntity> byTime = (m1, m2) -> m2.getExecTime().toString()
						.compareTo(m1.getExecTime().toString());
				Collections.sort(collect2, byTime);
				// collect = collect2;
			}
		}

		if (monitorList != null) {
			monitorCollect = monitorList.stream().filter(x -> x.getCtrl_id() == cr.getCtrl_id())
					.collect(Collectors.toList());
		}

		List<RuleEntity> auto = new ArrayList<>();
		hashMap.put("state", 2);
		hashMap2.put("state", 2);
		ArrayList<Object> arrayList = new ArrayList<>();
		// 1 开 2 关
		// 1 开 2关
		if (controlSetting.get("state_type").toString()
				.equals("1")/* && 开关处于开启状态 */) {
			map.put("state", 1);
		}
		if (controlSetting.get("state_type").toString()
				.equals("2")/* && 开关处于开启状态 */) {
			hashMap.put("state", 1);
		}
		if (controlSetting.get("state_type").toString()
				.equals("3")/* && 开关处于开启状态 */) {
			hashMap2.put("state", 1);
		}
		if (cr.getApp() == 1) {
			hashMap.put("rule", collect2);
		} else {
			hashMap.put("rule", collect);
		}
		hashMap2.put("rule", monitorCollect);
		arrayList.add(map);
		arrayList.add(hashMap);
		arrayList.add(hashMap2);

		hashMap3.put("list", arrayList);

		// boolean motorsCtrl2 = Client.getMotorSensor2(ip, port, deviceId)(,
		// hbm.toByteCmd(), (byte)0x17);
		// System.out.println(motorsCtrl2);
		// Client.motorsCtrl2(, data)
		return new IOTResult(true, "查看成功", hashMap3, 0);
	}

	@CrossOrigin
	@RequestMapping(value = "/controlDevListDefault", method = RequestMethod.POST)
	public IOTResult controlDevListDefault(@RequestBody ControlList cr) {
		// PointEntity point =null;
		System.out.println("控制中...");
		// if(cr.getCksid()==null ||
		// cr.getCksid().trim().length()<1||cr.getCkuid()==null||cr.getCkuid().trim().length()<1){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// // 注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+cr.getCkuid());
		// if(check ==null ||!cr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// 检测是否有这个点
		// b=new byte[12];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
		// b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
		// b[5]=(byte)this.getDirection();
		// b[6]=(byte)this.getDistanceOrDuration();
		// b[7]=(byte)this.getPosSensorCH();
		// byte[] maxB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
		// System.arraycopy(maxB, 0, b, 8, 2);
		// byte[] minB =
		// PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
		// System.arraycopy(minB, 0, b, 10, 2);
		// }
		// if((byte)this.getCtrlType()==0x2){
		// b=new byte[9];
		// b[0]=(byte)this.getCtrlType();
		// b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
		// b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
		// byte[]
		// durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
		// System.arraycopy(durationByte, 0, b, 3, 4);
		// }
		// return b;
		// }
		PointEntity point = cr.getPointEntity();
		System.out.println(point.getIp());
		System.out.println(point.getPort());
		System.out.println(point.getDeviceId());
		List<ControlEntity> list = cr.getList();
		MotorHBM hbm = new MotorHBM();
		IOTResult iotResult = new IOTResult();
		List<Object> result = new ArrayList<>();
		// ArrayList<Object> arrayList = new ArrayList<>();
		Map<String, Object> map = null;
		for (int i = 1; i < 25; i++) {
			for (int j = 1; j < 9; j++) {
				map = new HashMap<>();
				hbm.setCtrlType(2);
				hbm.setRaiseGroupId(String.valueOf(i));
				hbm.setRaiseSwitchId(String.valueOf(j));
				hbm.setPosSensorCH(1);
				hbm.setDistanceOrDuration(-1);
				boolean motorsCtrl2;
				try {
					motorsCtrl2 = Client.motorsCtrl2(point.getIp(), point.getPort(), point.getDeviceId(),
							hbm.toByteCmd(), (byte) 0x17);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				if (motorsCtrl2) {
					map.put("success", true);
					map.put("msg", "(" + i + "," + j + "),命令发送成功");
				} else {
					map.put("success", false);
					map.put("msg", "(" + i + "," + j + "),命令发送失败");
				}
				result.add(map);
			}
			// arrayList.add(result);
		}
		return new IOTResult(true, "测试成功", result, 0);
		// for (ControlEntity controlEntity : list) {
		// map = new HashMap<>();
		// hbm.setCtrlType(controlEntity.getCtrl_type());
		// hbm.setRaiseGroupId(String.valueOf(controlEntity.getCtrl_raise_groupId()));
		// hbm.setRaiseSwitchId(String.valueOf(controlEntity.getCtrl_raise_switchId()));
		// hbm.setSkinGroupId(String.valueOf(controlEntity.getCtrl_down_groupId()));
		// hbm.setSkinSwitchId(String.valueOf(controlEntity.getCtrl_down_switchId()));
		// hbm.setPosSensorCH(Integer.parseInt(controlEntity.getCtrl_channel()));
		// hbm.setDistanceOrDuration(controlEntity.getDistanceOrDuration());
		// System.out.println(hbm.toByteCmd());
		// boolean motorsCtrl2 = Client.motorsCtrl2(point.getIp(),
		// point.getPort(), point.getDeviceId(), hbm.toByteCmd(), (byte)0x17);
		// if(motorsCtrl2){
		//// iotResult.setSuccess(true);
		// map.put("success", true);
		//// iotResult.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送成功");
		// map.put("msg",
		// "("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送成功");
		//// result.add(iotResult);
		// result.add(map);
		// controlEntity.setSuccess(true);
		// controlEntity.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送成功");
		// }else{
		// map.put("success", false);
		// map.put("msg",
		// "("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送成功");
		//// iotResult.setSuccess(false);
		//// iotResult.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送失败");
		// result.add(map);
		// controlEntity.setSuccess(false);
		// controlEntity.setMsg("("+controlEntity.getCtrl_raise_groupId()+","+controlEntity.getCtrl_raise_switchId()+"),命令发送失败");
		//// result.add(map);
		// }
		// }
	}
}
