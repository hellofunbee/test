/**
 * 项目名称：IOT
 * 类名称：SettingController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月1日 下午3:31:20
 * 修改人：jianghu
 * 修改时间：2017年9月1日 下午3:31:20
 * 修改备注： 下午3:31:20
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.*;
import com.jingu.IOT.requset.MainDeviceList;
import com.jingu.IOT.requset.MainDeviceRequset;
import com.jingu.IOT.requset.MainSettingRequset;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.*;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.JsonToString;
import com.jingu.IOT.util.ToolUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * @author jianghu
 * @ClassName: SettingController
 * @Description: TODO
 * @date 2017年9月1日 下午3:31:20
 * 设置和配置操作接口类
 */
@RestController
public class SettingController {

    private SettingService settingService;
    private ToolUtil toolUtil;
    private PointService pointService;
    private MainDeviceService mainDeviceService;
    private IPCService iPCService;
    private RoleService roleService;
    private UserService userService;
    private JsonToString jsonToString;


    @Autowired
    public SettingController(SettingService settingService, ToolUtil toolUtil, PointService pointService, MainDeviceService mainDeviceService, IPCService iPCService, RoleService roleService, UserService userService) {
        this.settingService = settingService;
        this.toolUtil = toolUtil;
        this.pointService = pointService;
        this.mainDeviceService = mainDeviceService;
        this.iPCService = iPCService;
        this.roleService = roleService;
        this.userService = userService;
    }


    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Integer create(@RequestBody Map<String, String> map) {
        settingService.createTable(map.get("name"));
        return 0;
    }

    // 获得配置
    @CrossOrigin
    @RequestMapping(value = "/getSetting", method = RequestMethod.POST)
    public IOTResult getSetting(@RequestBody PointRequest pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
        PointEntity pointEntity = new PointEntity();
//		pointEntity.setUid(uid);
//		pointEntity.setRole(String.valueOf(uid));
        pointEntity.setTp_id(pr.getTp_id());
        pointEntity.setTp_type(pr.getTp_type());
        if (pr.getTp_type() < 1 /*或者大于多少度的时候*/) {
            return new IOTResult(false, "节点类型错误", null, 4);
        }
        PointEntity point = pointService.getPoint(pointEntity);
        if (point == null) {
            return new IOTResult(false, "节点不存在", null, 3);
        }
        if (pr.getTp_type() == 1) {
            return new IOTResult(true, "查看站点配置成功", point, 0);
        }
        if (pr.getTp_type() == 2) {
            return new IOTResult(true, "查看分类点配置成功", point, 0);
        }
        if (pr.getTp_type() == 3) {
// 权限问题
//			String role = point.getRole();
//			String rolestr = role.substring(2);
//			JSONObject fromObject = JSONObject.fromObject(String.valueOf("{"+rolestr));
//			Object object = fromObject.get(String.valueOf(uid));
//			RoleEntity roleEntity = new RoleEntity();
//			roleEntity.setR_name(object.toString());
//			Map<String, Object> role2 = roleService.getRole(roleEntity);
//			String string = role2.get("r_value").toString();
//比较是否相等
//因为角色底层权限不考虑,所以权限都一样,那么只检查是否是管理员和专家
            int ckAdmin = userService.ckAdmin(uid);
            if (ckAdmin == 0) {
                return new IOTResult(false, "权限不足", null, 111);
            }
            MainDeviceEntity mainDeviceEntity = new MainDeviceEntity();
            mainDeviceEntity.setId(pr.getTp_id());
            MainDeviceEntity mainDeviceById = mainDeviceService.getMainDeviceById(mainDeviceEntity);
            if (mainDeviceById == null || mainDeviceById.getId() > 0) {
                return new IOTResult(true, "查看配置成功", mainDeviceById, 0);
            }
        }
        if (pr.getTp_type() == 4) {
            int ckAdmin = userService.ckAdmin(uid);
            if (ckAdmin == 0) {
                return new IOTResult(false, "权限不足", null, 111);
            }
            IPCEntity ipcEntity = new IPCEntity();
            ipcEntity.setId(pr.getTp_id());
            IPCEntity ipc = iPCService.getIPC(ipcEntity);
            if (ipc != null) {
                return new IOTResult(true, "查看配置成功", ipc, 0);

            }
        }
        return new IOTResult(false, "查看配置失败", null, 0);
    }

    // 添加和修改配置
    @CrossOrigin
    @RequestMapping(value = "/saveAndUpdateMainDevice", method = RequestMethod.POST)
    public IOTResult addMainDevice(@RequestBody MainDeviceRequset mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }

        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        if (mdr.getPointEntity() == null) {
            return new IOTResult(false, "请填写节点参数", null, 2);
        }
        PointEntity pointEntity = new PointEntity();
        pointEntity.setUid(uid);
        pointEntity.setRole(String.valueOf(uid));
        // 修改
        if (mdr.getId() > 0) {

//			pointEntity.setTp_id(mdr.getId());
//			PointEntity point = pointService.getPoint(pointEntity);
//			if( pointEntity.getTp_pid()!=0){
//				if(point==null){
//					return new IOTResult(false,"节点不存在",null,3);
//				}
//			}
//			mdr.getGroupid();
//			if(mdr.getPointEntity().getTp_type()!=3){
//				return new IOTResult(false,"类型不正确",null,5);
//			}
            String deviceId = mdr.getDeviceId();
            if (deviceId == null || mdr.getDeviceId().trim().length() < 1) {
                return new IOTResult(false, "请输入设备号", null, 7);
            }
            int updateMainDevice = 0;
            try {
                updateMainDevice = mainDeviceService.updateMainDevice(mdr);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new IOTResult(false, "节点修改失败", null, 8);
            }
            switch (updateMainDevice) {
                case 1:
                    return new IOTResult(true, "节点修改成功", null, 6);
                case 2:
                    return new IOTResult(false, "请输入正确的链接方式", null, 9);
                case 3:
                    return new IOTResult(false, "请输入正确的ip port和链接方式", null, 10);
                default:
                    return new IOTResult(false, "节点修改失败", null, 8);
            }
        }
//		pointEntity.setTp_id(mdr.getPointEntity().getTp_id());
//		PointEntity point = pointService.getPoint(pointEntity);
//		if( pointEntity.getTp_pid()!=0){
//			if(point==null){
//				return new IOTResult(false,"父节点不存在",null,3);
//			}
//		}
        /*if(!point.get("uid").equals(uid)){
            return new IOTResult(false,"您无权添加到该节点下",null,4);
		}*/
//		if(mdr.getPointEntity().getTp_type()!=3){
//			return new IOTResult(false,"类型不正确",null,5);
//		}
        String maxId = toolUtil.getMaxId(ToolUtil.TREEID);
        if (maxId == null) {
            int listMaxId = pointService.listMaxId();
            toolUtil.setMaxId(ToolUtil.TREEID, listMaxId);
        }
        Long maxIdInc = toolUtil.MaxIdInc(ToolUtil.TREEID);
        mdr.setId(maxIdInc.intValue());
        mdr.setUid(uid);
        String deviceId = mdr.getDeviceId();
        if (deviceId == null || mdr.getDeviceId().trim().length() < 1) {
            return new IOTResult(false, "请输入设备号", null, 7);
        }
        int addPoint;
        try {
            addPoint = mainDeviceService.addMainDevice(mdr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new IOTResult(false, "节点添加失败", null, 8);
        }
        // 您无权添加到该节点下
        // 节点不存在
		/*if(addPoint ==1){
			return new IOTResult(true,"节点添加成功",null,6);
		}*/
        switch (addPoint) {
            case 1:
                return new IOTResult(true, "节点添加成功", null, 0);
            case 2:
                return new IOTResult(false, "请输入正确的链接方式", null, 9);
            case 3:
                return new IOTResult(false, "请输入正确的ip port和链接方式", null, 10);
            default:
                return new IOTResult(false, "节点添加失败", null, 12);
        }
    }

    // 删除主设备
    @CrossOrigin
    @RequestMapping(value = "/deleteMainDevice", method = RequestMethod.POST)
    public IOTResult deleteMainDevice(@RequestBody PointRequest mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        if (mdr.getTp_id() > 0) {
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getTp_id());
//			pointEntity.setRole(String.valueOf(uid));
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
        }
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        int deleteMainDevice = mainDeviceService.deleteMainDevice(mdr);
        if (deleteMainDevice == 1) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(true, "删除失败", null, 0);

    }

    // 获得主设备设置
    @CrossOrigin
    @RequestMapping(value = "/getMainDeviceInfo", method = RequestMethod.POST)
    public IOTResult getMainDeviceInfo(@RequestBody PointRequest mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        if (mdr.getTp_id() > 0) {
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getTp_id());
//			pointEntity.setRole(String.valueOf(uid));
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
        }
        MainDeviceEntity mainDeviceEntity = new MainDeviceEntity();
        mainDeviceEntity.setDeviceId(mdr.getDeviceId());
        Map<String, Object> mainDeviceByDeviceId = mainDeviceService.getMainDeviceByDeviceId(mainDeviceEntity);
        if (mainDeviceByDeviceId != null && !mainDeviceByDeviceId.isEmpty()) {
            return new IOTResult(true, "查看成功", mainDeviceByDeviceId, 0);
        }
        return new IOTResult(false, "暂无相关信息", null, 0);
    }


    // 获得主设备设置
    @CrossOrigin
    @RequestMapping(value = "/getMainDeviceSetting", method = RequestMethod.POST)
    public IOTResult getMainDeviceSetting(@RequestBody PointRequest mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        if (mdr.getTp_id() > 0) {
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getTp_id());
//			pointEntity.setRole(String.valueOf(uid));
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
        }
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        if (mdr.getDeviceId() == null || mdr.getDeviceId() == "") {
            return new IOTResult(false, "设备不存在！", null, 111);
        }
        String ConfigStr = toolUtil.getCheck(ToolUtil.CONFIG + mdr.getDeviceId());
        if (ConfigStr == null || ConfigStr.trim().length() < 1) {
            // 去查找数据库
            //如果 数据库中没有
            VAREntity varEntity = new VAREntity();
            varEntity.setDeviceId(mdr.getDeviceId());

            ConfigStr = settingService.getSettings(varEntity);

            if (ConfigStr == null || ConfigStr.trim().length() < 1) {
                //去找设备
                VRAConfigBean vraConfig = Client.getVRAConfig(mdr.getIp(), mdr.getPort());

                if(vraConfig == null)
                    return new IOTResult(true, "暂无相关信息！", null, 2);

                toolUtil.setCheck(ToolUtil.CONFIG + mdr.getDeviceId(), vraConfig.getConfigstr());
                ConfigStr = vraConfig.getConfigstr();
                // 把设置保存到数据库中
                varEntity.setConfigStr(ConfigStr);
                varEntity.setIp(mdr.getIp());
                varEntity.setPort(mdr.getPort());
                varEntity.setState(1);
                int addSettings = settingService.addSettings(varEntity);
            }
            toolUtil.setCheck(ToolUtil.CONFIG + mdr.getDeviceId(), ConfigStr);
        }
        JSONObject stringToJson = JsonToString.StringToJson(ConfigStr);
        return new IOTResult(true, "查找成功", stringToJson, 0);
    }


    // 设置主设备配置
    @CrossOrigin
    @RequestMapping(value = "/setMainDeviceSetting", method = RequestMethod.POST)
    public IOTResult setMainDeviceSetting(@RequestBody MainSettingRequset mdr) {

        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        if (mdr.getTp_id() > 0) {
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getTp_id());
//			pointEntity.setTp_type(mdr.getTp_type());
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
//		String config = toolUtil.getCheck(ToolUtil.CONFIG+mdr.getDeviceId());
        Map<String, String> oldconfig = mdr.getOldconfig();
//		System.out.println(oldconfig);
        String configstr = toolUtil.getCheck(ToolUtil.CONFIG + mdr.getDeviceId());
//		System.out.println(configstr);
        // 找到没有显示改变的位置 把config拼好
//		String config2 = mdr.getConfig();
//		JSONObject fromObject = JSONObject.fromObject(config2);
//		System.out.println(fromObject);
        Map<String, String> config2 = mdr.getConfig();
        String string = config2.get("s_repIpServIp1");
        System.out.println(string);
//		System.out.println(config2);
        JSONObject fromObject = JSONObject.fromObject(config2);
        Set newentrySet = fromObject.entrySet();
        JSONObject fromObject2 = JSONObject.fromObject(oldconfig);
        Set oldentrySet = fromObject2.entrySet();
        for (Object object : oldentrySet) {
            String olds = object.toString().replace("=", ":");
            if (!object.toString().equals(object.toString().split("=")[0] + "=" + config2.get(object.toString().split("=")[0]))) {
                String s = object.toString().split("=")[0] + ":" + config2.get(object.toString().split("=")[0]);
                configstr = configstr.replace(olds, s);

            }
        }
        System.out.println(configstr);
        toolUtil.setCheck(ToolUtil.CONFIG + mdr.getDeviceId(), configstr);
        // 去设置设备
        boolean setVRAConfig = Client.setVRAConfig(configstr, mdr.getDeviceId(), mdr.getIp(), mdr.getPort());

        if (!setVRAConfig) {
            toolUtil.setCheck(ToolUtil.CONFIG + mdr.getDeviceId(), configstr);
            return new IOTResult(false, "设置失败", null, 0);
        }
        return new IOTResult(true, "设置成功", null, 0);
    }

    /**
     *
     * 先更新 后写入
     *
     * 写入主设备配置
     * */
    @CrossOrigin
    @RequestMapping(value = "/writeMainDeviceSetting", method = RequestMethod.POST)
    public IOTResult writeMainDeviceSetting(@RequestBody MainDeviceRequset mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        PointEntity pointEntity = new PointEntity();
//		pointEntity.setUid(mdr.getUid());
        pointEntity.setDeviceId(mdr.getDeviceId());
//		pointEntity.setRole(String.valueOf(uid));
//		PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//		if(point==null){
//			return new IOTResult(false,"节点不存在",null,3);
//		}
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        String config = toolUtil.getCheck(ToolUtil.CONFIG + mdr.getDeviceId());
        VAREntity varEntity = new VAREntity(mdr.getDeviceId(), mdr.getIp(), config, mdr.getPort(), 0);
        String settings = settingService.getSettings(varEntity);

//		if(settings == null || settings.trim().length()<1){
//			return new IOTResult(false,"该设备尚未获得设置,请刷新页面",null,0);
//		}

        int updateSettings = settingService.updateSettings(varEntity);
        if (updateSettings > 0) {
            boolean writeVRAConfig = Client.writeVRAConfig(null, mdr.getDeviceId(), mdr.getIp(), mdr.getPort());
            if (writeVRAConfig) {
                return new IOTResult(true, "写入成功", null, 0);
            }
            varEntity.setConfigStr(settings);
            updateSettings = settingService.updateSettings(varEntity);
            if (updateSettings < 1) {
                return new IOTResult(false, "未知错误", null, 0);
            }
        }
        return new IOTResult(false, "写入失败", null, 0);
    }

    // 重启主设备
    @CrossOrigin
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public IOTResult restart(@RequestBody MainDeviceRequset mdr) {
        if (mdr.getCksid() == null || mdr.getCksid().trim().length() < 1 || mdr.getCkuid() == null || mdr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mdr.getCkuid());
        if (check == null || !mdr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mdr.getCkuid(), mdr.getCksid());
        PointEntity pointEntity = new PointEntity();
        pointEntity.setDeviceId(mdr.getDeviceId());
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getPointEntity().getTp_id());
//			pointEntity.setTp_type(mdr.getPointEntity().getTp_type());
//			pointEntity.setRole(String.valueOf(uid));
//		PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//		if(point==null){
//			return new IOTResult(false,"节点不存在",null,3);
//		}
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        Client.startupDevice(null, mdr.getDeviceId(), mdr.getIp(), mdr.getPort());
        toolUtil.setCheck(ToolUtil.CONFIG + mdr.getDeviceId(), "");
        return new IOTResult(true, "设备重启中,请稍后...", null, 0);
    }


//	@CrossOrigin
//	@RequestMapping(value="/listLocate",method=RequestMethod.POST)
//	public IOTResult listPoint(@RequestBody PointRequest pr){
//		if(pr.getCksid()==null || pr.getCksid().trim().length()<1||pr.getCkuid()==null|| pr.getCksid().trim().length()<1){
//			return  new IOTResult(false,"信息不规范",null,1);
//		}
//		// 注册登陆按照什么来????
//		String check = toolUtil.getCheck(ToolUtil.IOT+pr.getCkuid());
//		if(check ==null ||!pr.getCksid().equals(check)){
//			return  new IOTResult(false,"登陆失效",null,2);
//		}
//		long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
//		PointEntity pointEntity = new PointEntity();
//		// 如果是管理员的话,把uid置为零
//		int ckAdmin = userService.ckAdmin(uid);
//		if(ckAdmin ==1 ){
//			uid =0;
//		}
//		pr.setUid(uid);
////		pointEntity.setTp_type(1);
//		pointEntity.setRole(String.valueOf(uid));
//		List<Map<String,Object>> listPoint = mainDeviceService.listLocate(pointEntity);
//		
//		
//		/*if(listPoint ==null){
//			return new IOTResult(false,"父节点不存在",null,3);
//		}*/
//		/*if(!point.get("uid").equals(uid)){
//			return new IOTResult(false,"您无权添加到该节点下",null,4);
//		}*/
//		/*if(pr.getTp_type() >0&&pr.getTp_type()<5){
//			return new IOTResult(false,"类型不正确",null,5);
//		}*/
//		//int addPoint = pointService.addPoint(pr);
//		// 您无权添加到该节点下
//		// 节点不存在
//		if(listPoint!=null && listPoint.size() >0){
//			return new IOTResult(true,"查看成功",listPoint,0);
//		}
//		return new IOTResult(false,"暂无相关信息",null,0);
//	}


    //	// 修改ipc代理(写入数据库)
//	@CrossOrigin
//	@RequestMapping(value="/updateProxy",method=RequestMethod.POST)
//	public IOTResult addProxy(@RequestBody IPCProxyRequest  mdr){
//		if(mdr.getCksid()==null || mdr.getCksid().trim().length()<1||mdr.getCkuid()==null||mdr.getCkuid().trim().length()<1){
//			return  new IOTResult(false,"信息不规范",null,1);
//		}
//		// 注册登陆按照什么来????
//		String check = toolUtil.getCheck(ToolUtil.IOT+mdr.getCkuid());
//		if(check ==null ||!mdr.getCksid().equals(check)){
//			return  new IOTResult(false,"登陆失效",null,2);
//		}
///*		if(mdr.getTp_id()>0){
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(mdr.getUid());
//			pointEntity.setTp_id(mdr.getTp_id());
//			pointEntity.setTp_type(mdr.getTp_type());
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
//		}*/
//		int updateProxy = settingService.updateProxy(mdr);
//		if(updateProxy>0){
//			return new IOTResult(true,"修改成功",null,0);
//		}
//		return new IOTResult(false,"修改失败",null,0);
//	}
//
//	// 获得代理配置
//	@CrossOrigin
//	@RequestMapping(value="/getProxy",method=RequestMethod.POST)
//	public IOTResult getProxy(@RequestBody IPCProxyRequest ipc) {
////		if(ipc.getCksid()==null || ipc.getCksid().trim().length()<1||ipc.getCkuid()==null||ipc.getCkuid().trim().length()<1){
////			return  new IOTResult(false,"信息不规范",null,1);
////		}
////		// 注册登陆按照什么来????
////		String check = toolUtil.getCheck(ToolUtil.IOT+ipc.getCkuid());
////		if(check ==null ||!ipc.getCksid().equals(check)){
////			return  new IOTResult(false,"登陆失效",null,2);
////		}
//		/*if(ipc.getTp_id()>0){
//			PointEntity pointEntity = new PointEntity();
//			pointEntity.setUid(ipc.getUid());
//			pointEntity.setTp_id(ipc.getTp_id());
//			pointEntity.setTp_type(ipc.getTp_type());
//			PointEntity point = pointService.getPoint(pointEntity);//已经包括了节点的权限问题
//			if(point==null){
//				return new IOTResult(false,"节点不存在",null,3);
//			}
//		}*/
//		
//		Map<String, Object> proxy = settingService.getProxy(ipc);
//		if(proxy==null || proxy.get("id").toString().trim().length()<1){
//			return new IOTResult(false,"暂无相关信息",null,0);
//		}
//		return new IOTResult(true,"查看成功",proxy,0);
//		
//	}
//	public static void main(String[] args) {
//		String string = "s_repIpServIp1:192.168.0.186;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;";
//		String replace = string.replace("s_repIpServIp1:192.168.0.186", "s_repIpServIp1:192.168.0.187");
//		System.out.println(replace);
//	}
    // 批量添加主设备
    @CrossOrigin
    @RequestMapping(value = "/addMainDeviceList", method = RequestMethod.POST)
    public IOTResult addMainDeviceList(@RequestBody MainDeviceList mdr) {
//		if(mdr.getCksid()==null || mdr.getCksid().trim().length()<1||mdr.getCkuid()==null||mdr.getCkuid().trim().length()<1){
//			return  new IOTResult(false,"信息不规范",null,1);
//		}
//		// 注册登陆按照什么来????
//		String check = toolUtil.getCheck(ToolUtil.IOT+mdr.getCkuid());
//		if(check ==null ||!mdr.getCksid().equals(check)){
//			return  new IOTResult(false,"登陆失效",null,2);
//		}
        try {
            int addMainDeviceList2 = mainDeviceService.addMainDeviceList2(mdr);
            if (addMainDeviceList2 > 0) {
                return new IOTResult(true, "添加成功", null, 0);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new IOTResult(false, "添加失败", null, 0);
        }
//			if(addMainDeviceList2 <0){
//				return new IOTResult(false,"添加失败",null,0);
//			}
//		}
        return new IOTResult(false, "添加失败", null, 0);
    }

}
