/**  
*   
* 项目名称：IOT  
* 类名称：GatherController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月8日 下午8:04:00  
* 修改人：jianghu  
* 修改时间：2017年9月8日 下午8:04:00  
* 修改备注： 下午8:04:00
* @version   
*   
*/
package com.jingu.IOT.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.entity.MainDeviceEntity;
import com.jingu.IOT.entity.SettingEntity;
import com.jingu.IOT.entity.VAREntity;
import com.jingu.IOT.requset.ParamRequest;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.requset.StaticRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.GatherService;
import com.jingu.IOT.service.MainDeviceService;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.service.UserService;
import com.jingu.IOT.util.Base64;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.SerializeUtil;
import com.jingu.IOT.util.ToolUtil;
import com.thoughtworks.xstream.mapper.Mapper.Null;

/**
 * 
 * @ClassName: GatherController
 * @Description: TODO
 * @author jianghu
 * @date 2017年9月8日 下午8:04:00 
 * 采集
 */
@RestController
public class GatherController {

	private GatherService gatherService;
	private ToolUtil toolUtil;
	private PointService pointService;
	private UserService userService;
	private MainDeviceService mainDeviceService;

	private static HashMap<String, String> dataMap;

	@Autowired
	public GatherController(GatherService gatherService, ToolUtil toolUtil, PointService pointService,
			UserService userService, MainDeviceService mainDeviceService) {
		this.gatherService = gatherService;
		this.toolUtil = toolUtil;
		this.pointService = pointService;
		this.userService = userService;
		this.mainDeviceService = mainDeviceService;
		// 1 CH1 2 CH2 3电池电量 4 二氧化碳 5风速 6 风向 7光照度 8空气湿度 9空气温度 10 土壤湿度 11 土壤水分
		// 12土壤温度 13系统5伏电压 14 雨量
		this.dataMap = new HashMap<>();
		;
		dataMap.put(Base64.encode("光照度".getBytes()), "light");
		dataMap.put(Base64.encode("空气温度".getBytes()), "otemp");
		dataMap.put(Base64.encode("空气湿度".getBytes()), "odump");
		dataMap.put(Base64.encode("土壤温度".getBytes()), "soiltemp");
		dataMap.put(Base64.encode("土壤湿度".getBytes()), "soildump");
		dataMap.put(Base64.encode("CH1".getBytes()), "CH1");
		dataMap.put(Base64.encode("CH2".getBytes()), "CH2");
		dataMap.put(Base64.encode("电池电量".getBytes()), "battery");
		dataMap.put(Base64.encode("二氧化碳".getBytes()), "carbon");
		dataMap.put(Base64.encode("风速".getBytes()), "speed");
		dataMap.put(Base64.encode("系统5伏电压".getBytes()), "voltage");
		dataMap.put(Base64.encode("雨量".getBytes()), "rain");
	}

	// 展示规格文件
	@CrossOrigin
	@RequestMapping(value = "/getGatherSettings", method = RequestMethod.POST)
	public IOTResult getGatherSettings(@RequestBody PointRequest pr) {
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
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(uid);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		VAREntity varEntity = new VAREntity();
		varEntity.setDeviceId(pr.getDeviceId());
		List<Map<String, Object>> gatherSettings = gatherService.getGatherSettings(varEntity);
		if (gatherSettings != null && gatherSettings.size() > 0) {
			return new IOTResult(true, "查看成功", gatherSettings, 0);
		}

		return new IOTResult(false, "暂无相关信息", null, 4);
	}

	// 删除规格文件
	@CrossOrigin
	@RequestMapping(value = "/deleteGatherSettings", method = RequestMethod.POST)
	public IOTResult deleteGatherSettings(@RequestBody PointRequest pr) {
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
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(uid);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		VAREntity varEntity = new VAREntity();
		varEntity.setDeviceId(pr.getDeviceId());
		int deleteSettingByDeviceId = gatherService.deleteSettingById(pr);
		if (deleteSettingByDeviceId > 0) {
			return new IOTResult(true, "删除成功", null, 0);
		}
		return new IOTResult(false, "删除失败", null, 4);
	}

	// 保存规格文件
	@CrossOrigin
	@RequestMapping(value = "/setGatherSettings", method = RequestMethod.POST)
	public IOTResult setGatherSettings(@RequestBody ParamRequest pr) {
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
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(uid);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		int ckAdmin = userService.ckAdmin(uid);
		if (ckAdmin == 0) {
			return new IOTResult(false, "权限不足", null, 111);
		}
		int settingCountByDeviceId = gatherService.getSettingCountByDeviceId(pr);
		int gatherSettings = 0;
		if (settingCountByDeviceId == 0) {
			List<SettingEntity> asList = pr.getSettingEntity();
			gatherSettings = gatherService.addGatherSettings(asList);
		} else {
			int deleteSettingByDeviceId = gatherService.deleteSettingByDeviceId(pr);
			if (deleteSettingByDeviceId > 0) {

				List<SettingEntity> asList = pr.getSettingEntity();
				gatherSettings = gatherService.addGatherSettings(asList);
			}
		}
		if (gatherSettings > 0) {
			return new IOTResult(true, "保存成功", gatherSettings, 0);
		}
		return new IOTResult(false, "保存失败", null, 4);
	}

	// 展示采集数据
	@CrossOrigin
	@RequestMapping(value = "/listSensorInfo", method = RequestMethod.POST)
	public IOTResult listSensorInfo(@RequestBody PointRequest pr) {
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
		pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		if (pr.getApp() == 1) {
			pr.setPagesize(30);
		}
		List<Map<String, Object>> listSensorInfo = gatherService.listSensorInfo(pr);
		List<Map<String, Object>> listSensorInfoUnit = gatherService.listSensorInfoListUnit(pr);
		if (listSensorInfo == null || listSensorInfo.size() < 1) {
			return new IOTResult(false, "暂无相关信息", null, 4);
		}
		int totalpage = 0;
		int listSensorInfoCount = gatherService.listSensorInfoCount(pr);
		if (listSensorInfoCount % pr.getPagesize() > 0) {
			totalpage = listSensorInfoCount / pr.getPagesize() + 1;
		} else {
			totalpage = listSensorInfoCount / pr.getPagesize();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("unit", listSensorInfoUnit);
		map.put("data", listSensorInfo);
		map.put("count", listSensorInfoCount);
		map.put("totalpage", totalpage);
		return new IOTResult(true, "查询成功", map, 0);
	}

	// 图标展示采集数据
	@CrossOrigin
	@RequestMapping(value = "/listSensorChartInfo", method = RequestMethod.POST)
	public IOTResult listSensorChartInfo(@RequestBody PointRequest pr) {
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
		pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		List<Map<String, Object>> listSensorInfo = gatherService.listSensorInfoFirst(pr);
		List<Map<String, Object>> listSensorInfoUnit = gatherService.listSensorInfoChartUnit(pr);
		MainDeviceEntity mainDeviceEntity = new MainDeviceEntity();
		mainDeviceEntity.setId(pr.getTp_id());
		Map<String, Object> mainDeviceByDeviceId = mainDeviceService.getMainDeviceId(mainDeviceEntity);
		if (listSensorInfo == null || listSensorInfo.size() < 1) {
			return new IOTResult(false, "暂无相关信息", null, 4);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("unit", listSensorInfoUnit);
		map.put("data", listSensorInfo);
		map.put("info", mainDeviceByDeviceId);
		return new IOTResult(true, "查询成功", map, 0);
	}

	// 获得警告信息
	@CrossOrigin
	@RequestMapping(value = "/listWaring", method = RequestMethod.POST)
	public IOTResult listWaring(@RequestBody PointRequest pr) {
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
		pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		List<Map<String, Object>> listwaring = gatherService.listwaring(pr);
		// List<Map<String,Object>> listSensorInfoUnit =
		// gatherService.listSensorInfoUnit(pr);
		if (listwaring == null || listwaring.size() < 1) {
			return new IOTResult(false, "暂无相关信息", null, 4);
		}
		return new IOTResult(true, "查询成功", listwaring, 0);
	}

	// 停止采集服务
	@CrossOrigin
	@RequestMapping(value = "stopGather", method = RequestMethod.POST)
	public IOTResult stopGather(@RequestBody PointRequest pr) {
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
		pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		if (pr.getIp() == null || pr.getIp().trim().length() < 1 || pr.getPort() > 0) {
			return new IOTResult(false, "信息不完善", null, 3);
		}
		String sendClientCommand = Client.sendClientCommand("127.0.0.1", 9999, "stopServer");
		System.out.println(sendClientCommand);
		if (sendClientCommand.equals("true")) {
			return new IOTResult(true, "采集服务停止成功", null, 3);
		}
		// List<Map<String,Object>> listwaring = gatherService.listwaring(pr);
		// List<Map<String,Object>> listSensorInfoUnit =
		// gatherService.listSensorInfoUnit(pr);
		// if(listwaring==null || listwaring.size()<1){
		// return new IOTResult(false,"暂无相关信息",null,4);
		// }
		// return new IOTResult(true,"查询成功",listwaring,0);
		return new IOTResult(false, "采集服务停止失败", null, 3);
	}

	// 开启采集服务设备
	@CrossOrigin
	@RequestMapping(value = "startGather", method = RequestMethod.POST)
	public IOTResult startGather(@RequestBody PointRequest pr) {
		// if(pr.getCksid()==null ||
		// pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// //注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		// if(check ==null ||!pr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		// pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		// if(pr.getIp()==null || pr.getIp().trim().length()<1 ||
		// pr.getPort()>0){
		// return new IOTResult(false,"信息不完善",null,3);
		// }
		String sendClientCommand = Client.sendClientCommand("127.0.0.1", 9999, "startServer");
		System.out.println(sendClientCommand);
		if (sendClientCommand.equals("true")) {
			return new IOTResult(true, "采集服务开启成功", null, 3);
		}
		// List<Map<String,Object>> listwaring = gatherService.listwaring(pr);
		// List<Map<String,Object>> listSensorInfoUnit =
		// gatherService.listSensorInfoUnit(pr);
		// if(listwaring==null || listwaring.size()<1){
		// return new IOTResult(false,"暂无相关信息",null,4);
		// }
		// return new IOTResult(true,"查询成功",listwaring,0);
		return new IOTResult(false, "采集服务开启失败", null, 3);
	}

	// 获得采集服务状态状态
	@CrossOrigin
	@RequestMapping(value = "getDevStatus", method = RequestMethod.POST)
	public IOTResult getDevStatus(@RequestBody PointRequest pr) {
		// if(pr.getCksid()==null ||
		// pr.getCksid().trim().length()<1||pr.getCkuid()==null||pr.getCkuid()==null){
		// return new IOTResult(false,"信息不规范",null,1);
		// }
		// //注册登陆按照什么来????
		// String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
		// if(check ==null ||!pr.getCksid().equals(check)){
		// return new IOTResult(false,"登陆失效",null,2);
		// }
		// long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		// pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		// if(pr.getIp()==null || pr.getIp().trim().length()<1 ||
		// pr.getPort()>0){
		// return new IOTResult(false,"信息不完善",null,3);
		// }
		String sendClientCommand = Client.sendClientCommand("127.0.0.1", 9999, "isStarted");
		System.out.println(sendClientCommand);
		if (sendClientCommand.equals("true")) {
			return new IOTResult(true, "采集服务在开启的状态", null, 0);
		}
		// List<Map<String,Object>> listwaring = gatherService.listwaring(pr);
		// List<Map<String,Object>> listSensorInfoUnit =
		// gatherService.listSensorInfoUnit(pr);
		// if(listwaring==null || listwaring.size()<1){
		// return new IOTResult(false,"暂无相关信息",null,4);
		// }
		// return new IOTResult(true,"查询成功",listwaring,0);
		return new IOTResult(false, "采集服务在关闭的状态", null, 0);
	}

	// 重启采集服务
	@CrossOrigin
	@RequestMapping(value = "restartGather", method = RequestMethod.POST)
	public IOTResult restart(@RequestBody PointRequest pr) throws InterruptedException {
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
		pr.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		// if(pr.getIp()==null || pr.getIp().trim().length()<1 ||
		// pr.getPort()>0){
		// return new IOTResult(false,"信息不完善",null,3);
		// }
		String sendClientCommand = Client.sendClientCommand("127.0.0.1", 9999, "stopServer");
		System.out.println(sendClientCommand);
		if (sendClientCommand.equals("true")) {
			System.out.println("停止成功");
		}
		Thread.sleep(3);
		String sendClientCommand2 = Client.sendClientCommand("127.0.0.1", 9999, "isStarted");
		System.out.println(sendClientCommand2);
		if (sendClientCommand2.equals("false")) {
			// return new IOTResult(true,"采集服务在关闭的状态",null,0);
			System.out.println("采集服务在关闭的状态");
		}
		String sendClientCommand3 = Client.sendClientCommand("127.0.0.1", 9999, "startServer");
		System.out.println(sendClientCommand3);
		if (sendClientCommand3.equals("true")) {
			// return new IOTResult(true,"",null,0);
			System.out.println("采集服务开启成功");
		}
		Thread.sleep(3);
		String sendClientCommand4 = Client.sendClientCommand("127.0.0.1", 9999, "isStarted");
		if (sendClientCommand4.equals("true")) {
			// return new IOTResult(true,"",null,0);
			return new IOTResult(true, "采集服务重启成功", null, 0);
		}
		return new IOTResult(false, "采集服务重启失败", null, 0);
	}

	// 查找大数据分析的数据(根据deviceId和相应的channel)
	@CrossOrigin
	@RequestMapping(value = "getStaticData", method = RequestMethod.POST)
	public IOTResult startGather(@RequestBody StaticRequest sRequest) throws UnsupportedEncodingException {
		if (sRequest.getCkdata() == 1) {
			if (sRequest.getBeginTime().compareTo(sRequest.getEndTime()) > 0) {
				return new IOTResult(false, "输入的日期有误", null, 3);
			}
			return new IOTResult(true, "日期输入正确", null, 0);
		}
		if (sRequest.getCksid() == null || sRequest.getCksid().trim().length() < 1 || sRequest.getCkuid() == null
				|| sRequest.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + sRequest.getCkuid());
		if (check == null || !sRequest.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(sRequest.getCkuid(), sRequest.getCksid());

		if (sRequest.getBeginTime().compareTo(sRequest.getEndTime()) > 0) {
			return new IOTResult(false, "输入的日期有误", null, 3);
		}

		byte[] serialize = SerializeUtil.serialize(sRequest);
		toolUtil.setCheck(ToolUtil.ANALYSIS + sRequest.getType() + ToolUtil.DATA + uid, Base64.encode(serialize));

		List<Object> dataList = new ArrayList<>();
		List<String> deviceList = sRequest.getDeviceList();

		List<String> channelList = sRequest.getChannelList();
		// 首先去找设备配置文件如果有
		for (String string : deviceList) {
			channelList.add(0, string);

			HashMap<Object, Object> hashMap4 = new HashMap<>();

			List<Map<String, Object>> listPointByDeviceId = pointService.listPointByDeviceId(string);

			Map<String, Object> map = listPointByDeviceId.get(0);

			List<Map<String, Object>> listStaticData = gatherService.listStaticData(channelList);

			String string2 = listStaticData.get(0).get("field").toString();// 要找的栏目

			if (string2.trim().length() < 1) {

			}
			List<Map<String, Object>> listStaticInfo = gatherService.listStaticInfo(string2, string,
					sRequest.getBeginTime(), sRequest.getEndTime());// 要找的数据
			// 查找规格文件
			List<Map<String, Object>> listChannelByDeviceId = gatherService.listChannelByDeviceId(string);

			ArrayList<Object> arrayList = new ArrayList<>();

			HashMap<Object, Object> hashMap = new HashMap<>();

			for (int i = 0; i < listChannelByDeviceId.size(); i++) {
				Map<String, Object> map1 = listChannelByDeviceId.get(i);
				String name = map1.get("name").toString();
				boolean contains = channelList.contains(name);
				if (contains) {
					HashMap<Object, Object> hashMap3 = new HashMap<>();
					String field = map1.get("fieldName").toString();
					// List<Map<String,Object>> collect =
					// listStaticInfo.stream().filter(x ->
					// x.get("fieldName").equals(map1.get("fieldName").toString())).collect(Collectors.toList());
					List<Map<String, Object>> collect2 = listStaticInfo.stream().filter(x -> x.get(field) != null)
							.collect(Collectors.toList());
					List<String> collect = collect2.stream().map(x -> x.get(field).toString())
							.collect(Collectors.toList());
					// System.out.println(collect);
					// hashMap.put(name, field);
					// hashMap.put("channel",field);
					// hashMap.put(field, collect);
					hashMap3.put("name", name);
					hashMap3.put("data", collect);
					hashMap3.put("channel", field);
					arrayList.add(hashMap3);
					// hashMap3.put(field, collect);
					// String encode = Base64.encode(name.getBytes());
					// System.out.println(encode);
					// //5LqM5rCn5YyW56Kz
					// //5LqM5rCn5YyW56KzIA==
					// String string4 = dataMap.get(encode);
					// System.out.println(string4);
					// hashMap3.put(string4, field);
					// hashMap.put(string4, hashMap3);

				}
				System.out.println(hashMap);
				if (i == listChannelByDeviceId.size() - 1) {
					HashMap<Object, Object> hashMap5 = new HashMap<>();
					// 2017-12-07 17:44:25.0
					List<String> collect = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(0, 19)).collect(Collectors.toList());
					List<String> yearMonthDate = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(0, 10)).collect(Collectors.toList());
					List<String> hourMinuteSecond = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(11, 19)).collect(Collectors.toList());
					// hashMap.put("infoDataTime", collect);
					// hashMap2.put("deviceId", string);
					// hashMap2.put("info", hashMap);
					// dataList.add(hashMap2);
					hashMap5.put("name1", "infoDataTime");
					hashMap5.put("data1", collect);
					hashMap5.put("channel1", "infoDataTime");
					hashMap5.put("name2", "yearMonthDate");
					hashMap5.put("data2", yearMonthDate);
					hashMap5.put("channel2", "yearMonthDate");

					hashMap5.put("name3", "hourMinuteSecond");
					hashMap5.put("data3", hourMinuteSecond);
					hashMap5.put("channel3", "hourMinuteSecond");
					arrayList.add(hashMap5);
				}

			}
			hashMap4.put("deviceId", string);
			hashMap4.put("deviceName", map.get("tp_name").toString());
			hashMap4.put("info", arrayList);
			dataList.add(hashMap4);
		}
		if (dataList.isEmpty()) {
			return new IOTResult(false, "暂无相关信息", null, 0);
		}
		return new IOTResult(true, "查看成功", dataList, 0);

		// sRequest.setUid(uid);
		// PointEntity pointEntity = new PointEntity();
		// pointEntity.setUid(1);
		// pointEntity.setTp_id(pr.getTp_id());
		// List<Map<String,Object>> listPoint =
		// pointService.listPoint(pointEntity);
		// if(listPoint ==null){
		// return new IOTResult(false,"节点不存在",null,3);
		// }
		// if(pr.getIp()==null || pr.getIp().trim().length()<1 ||
		// pr.getPort()>0){
		// return new IOTResult(false,"信息不完善",null,3);
		// }
		// String sendClientCommand = Client.sendClientCommand("127.0.0.1",
		// 9999, "startServer");
		// System.out.println(sendClientCommand);
		// if(sendClientCommand.equals("true")){
		// return new IOTResult(true,"采集服务开启成功",null,3);
		// }
		// List<Map<String,Object>> listwaring = gatherService.listwaring(pr);
		// List<Map<String,Object>> listSensorInfoUnit =
		// gatherService.listSensorInfoUnit(pr);
		// if(listwaring==null || listwaring.size()<1){
		// return new IOTResult(false,"暂无相关信息",null,4);
		// }
		// return new IOTResult(true,"查询成功",listwaring,0);

	}

	// 查找大数据分析的数据(根据deviceId和相应的channel)
	@CrossOrigin
	@RequestMapping(value = "getStaticData2", method = RequestMethod.POST)
	public IOTResult getStaticData2(@RequestBody StaticRequest sRequest) throws UnsupportedEncodingException {

		if (sRequest.getCkdata() == 1) {
			if (sRequest.getBeginTime().compareTo(sRequest.getEndTime()) > 0) {
				return new IOTResult(false, "输入的日期有误", null, 3);
			}
			return new IOTResult(true, "日期输入正确", null, 0);
		}

		if (sRequest.getCksid() == null || sRequest.getCksid().trim().length() < 1 || sRequest.getCkuid() == null
				|| sRequest.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + sRequest.getCkuid());
		if (check == null || !sRequest.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(sRequest.getCkuid(), sRequest.getCksid());

		if (sRequest.getBeginTime().compareTo(sRequest.getEndTime()) <= 0) {
			return new IOTResult(false, "输入的日期有误", null, 3);
		}
		byte[] serialize = SerializeUtil.serialize(sRequest);
		toolUtil.setCheck(ToolUtil.ANALYSIS + sRequest.getType() + ToolUtil.DATA + uid, Base64.encode(serialize));

		List<Object> dataList = new ArrayList<>();

		List<String> deviceList = sRequest.getDeviceList();

		ArrayList<Object> arrayList = new ArrayList<>();

		List<String> channelList = sRequest.getChannelList();
		// 首先去找设备配置文件如果有
		for (String string : deviceList) {
			channelList.add(0, string);

			HashMap<Object, Object> hashMap4 = new HashMap<>();

			List<Map<String, Object>> listPointByDeviceId = pointService.listPointByDeviceId(string);

			Map<String, Object> map = listPointByDeviceId.get(0);

			List<Map<String, Object>> listStaticData = gatherService.listStaticData(channelList);

			String string2 = listStaticData.get(0).get("field").toString();// 要找的栏目

			if (string2.trim().length() < 1) {

			}
			List<Map<String, Object>> listStaticInfo = gatherService.listStaticInfo(string2, string,
					sRequest.getBeginTime(), sRequest.getEndTime());// 要找的数据
			// 查找规格文件
			List<Map<String, Object>> listChannelByDeviceId = gatherService.listChannelByDeviceId(string);

			HashMap<Object, Object> hashMap = new HashMap<>();

			for (int i = 0; i < listChannelByDeviceId.size(); i++) {
				Map<String, Object> map1 = listChannelByDeviceId.get(i);
				String name = map1.get("name").toString();
				boolean contains = channelList.contains(name);
				if (contains) {
					HashMap<Object, Object> hashMap3 = new HashMap<>();
					String field = map1.get("fieldName").toString();
					// List<Map<String,Object>> collect =
					// listStaticInfo.stream().filter(x ->
					// x.get("fieldName").equals(map1.get("fieldName").toString())).collect(Collectors.toList());
					List<String> collect = listStaticInfo.stream().map(x -> x.get(field).toString())
							.collect(Collectors.toList());
					// System.out.println(collect);
					// hashMap.put(name, field);
					// hashMap.put("channel",field);
					// hashMap.put(field, collect);
					hashMap3.put("name", name);
					hashMap3.put("data", collect);
					hashMap3.put("channel", field);
					hashMap3.put("deviceId", string);
					hashMap3.put("deviceName", map.get("tp_name").toString());
					// hashMap3.put("info", arrayList);
					arrayList.add(hashMap3);
					// hashMap3.put(field, collect);
					// String encode = Base64.encode(name.getBytes());
					// System.out.println(encode);
					// //5LqM5rCn5YyW56Kz
					// //5LqM5rCn5YyW56KzIA==
					// String string4 = dataMap.get(encode);
					// System.out.println(string4);
					// hashMap3.put(string4, field);
					// hashMap.put(string4, hashMap3);

				}
				System.out.println(hashMap);
				if (i == listChannelByDeviceId.size() - 1) {
					HashMap<Object, Object> hashMap5 = new HashMap<>();
					List<String> collect = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(0, 19)).collect(Collectors.toList());
					List<String> yearMonthDate = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(0, 10)).collect(Collectors.toList());
					List<String> hourMinuteSecond = listStaticInfo.stream()
							.map(x -> x.get("infoDataTime").toString().substring(11, 19)).collect(Collectors.toList());
					// hashMap.put("infoDataTime", collect);
					// hashMap2.put("deviceId", string);
					// hashMap2.put("info", hashMap);
					// dataList.add(hashMap2);
					hashMap5.put("name1", "infoDataTime");
					hashMap5.put("data1", collect);
					hashMap5.put("channel1", "infoDataTime");

					hashMap5.put("name2", "yearMonthDate");
					hashMap5.put("data2", yearMonthDate);
					hashMap5.put("channel2", "yearMonthDate");

					hashMap5.put("name3", "hourMinuteSecond");
					hashMap5.put("data3", hourMinuteSecond);
					hashMap5.put("channel3", "hourMinuteSecond");
					hashMap5.put("deviceId", string);
					hashMap5.put("deviceName", map.get("tp_name").toString());
					// hashMap5.put("info", arrayList);
					arrayList.add(hashMap5);
				}

			}
			dataList.add(hashMap4);
		}
		if (arrayList.isEmpty()) {
			return new IOTResult(false, "暂无相关信息", null, 0);
		}
		return new IOTResult(true, "查看成功", arrayList, 0);
	}

	@CrossOrigin
	@RequestMapping(value = "switchAnalysis", method = RequestMethod.POST)
	public IOTResult switchAnylise(@RequestBody StaticRequest sRequest)
			throws InterruptedException, UnsupportedEncodingException {
		if (sRequest.getCksid() == null || sRequest.getCksid().trim().length() < 1 || sRequest.getCkuid() == null
				|| sRequest.getCkuid() == null) {
			return new IOTResult(false, "信息不规范", null, 1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT + sRequest.getCkuid());
		if (check == null || !sRequest.getCksid().equals(check)) {
			return new IOTResult(false, "登陆失效", null, 2);
		}
		long uid = toolUtil.getbase_uidSid(sRequest.getCkuid(), sRequest.getCksid());

		String check2 = toolUtil.getCheck(ToolUtil.ANALYSIS + sRequest.getType() + ToolUtil.DATA + uid);

		if (check2 == null || check2.trim().length() > 0) {
			return new IOTResult(false, "暂无相关信息", null, 0);
		}
		byte[] decode = Base64.decode(check2);
		Object unserialize = SerializeUtil.unserialize(decode);

		return new IOTResult(false, "采集服务重启失败", unserialize, 0);
	}

	public static void main(String[] args) {

		String string = "2017-12-07 17:44:25.0";
		String substring = string.substring(0, 10);
		System.out.println(substring);
		String substring1 = string.substring(11, 19);
		System.out.println(substring1);
		String substring2 = string.substring(0, 19);
		System.out.println(substring2);
	}

}
