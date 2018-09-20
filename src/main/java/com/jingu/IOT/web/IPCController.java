/**
 * 项目名称：IOT
 * 类名称：IPCController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月7日 下午3:46:50
 * 修改人：jianghu
 * 修改时间：2017年9月7日 下午3:46:50
 * 修改备注： 下午3:46:50
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.*;
import com.jingu.IOT.entity.HCNetSDK.NET_DVR_COMPRESSIONCFG_V30;
import com.jingu.IOT.requset.IPCPointRequset;
import com.jingu.IOT.requset.IPCPointsRequest;
import com.jingu.IOT.requset.IPCRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.response.PointResult;
import com.jingu.IOT.service.*;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.ToolUtil;
import com.jingu.IOT.util.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author jianghu
 * @ClassName: IPCController
 * @Description: TODO
 * @date 2017年9月7日 下午3:46:50 摄像头操作
 */
@RestController
public class IPCController {

    // private final Logger logger
    // =LoggerFactory.getLogger(CtrlController.class);

    private IPCService ipcService;
    private ToolUtil toolUtil;
    private PointService pointService;
    private SettingService settingService;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public IPCController(IPCService ipcService, ToolUtil toolUtil, PointService pointService,
                         SettingService settingService, RoleService roleService, UserService userService) {
        this.ipcService = ipcService;
        this.toolUtil = toolUtil;
        this.pointService = pointService;
        this.settingService = settingService;
        this.roleService = roleService;
        this.userService = userService;
    }


    /**
     * TODO Requset 需统一继承
     *
     * @param obj
     * @param request_admin
     * @return
     */
    private IOTResult validUser(Object obj, boolean request_admin) {
        IPCRequest ipcRequest = null;
        IPCPointRequset iPCPointRequset = null;
        String cksid = null;
        String ckuid = null;
        if (obj == null) return new IOTResult(false, "信息不规范", null, 1);

        if (obj instanceof IPCRequest) {
            ipcRequest = (IPCRequest) obj;
            cksid = ipcRequest.getCksid();
            ckuid = ipcRequest.getCkuid();
        } else if (obj instanceof IPCPointRequset) {
            iPCPointRequset = (IPCPointRequset) obj;
            cksid = iPCPointRequset.getCksid();
            ckuid = iPCPointRequset.getCkuid();
        }


        if (cksid == null || cksid.trim().length() < 1 || ckuid == null
                || ckuid.trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }

        String check = toolUtil.getCheck(ToolUtil.IOT + ckuid);
        if (check == null || !cksid.equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        if (request_admin) {
            long uid = toolUtil.getbase_uidSid(ckuid, cksid);
            int ckAdmin = userService.ckAdmin(uid);
            if (ckAdmin == 0) {
                return new IOTResult(false, "权限不足", null, 111);
            }
        }

        return null;
    }

    // 添加摄像头
    @CrossOrigin
    @RequestMapping(value = "/findIPCById", method = RequestMethod.POST)
    public IOTResult findById(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;

        IPCEntity ipc = ipcService.getIPCById(ipcRequest);
        if (ipc == null)
            return new IOTResult(false, "数据不存在", null, Types.STATE_FAIL);
        return new IOTResult(true, "请求成功", ipc, Types.STATE_OK);
    }


    // 添加摄像头

    /**
     * 先去设置 如果设置失败 再去根据ip&端口&主设备id 获取ipc
     *
     * @param ipcRequest
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/addIPC", method = RequestMethod.POST)
    public IOTResult addIPC(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;
/* TODO 暂时注掉 @ xwf
        // RoleEntity roleEntity = new RoleEntity();
        // roleEntity.setR_name(point.getRole());
        // Map<String, Object> role = roleService.getRole(roleEntity);
        // "s_nod:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;"
        // s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:1;s_stream:0
        String config = "s_nod:" + ipcRequest.getS_nod() + ";s_power:" + ipcRequest.getS_power() + ";s_ip:"
                + ipcRequest.getS_ip() + ";s_port:" + ipcRequest.getS_port() + ";s_username:"
                + ipcRequest.getS_username() + ";s_password:" + ipcRequest.getS_password() + ";s_online:"
                + ipcRequest.getS_online() + ";s_stream:" + ipcRequest.getS_stream() + ";";

        boolean setIpc1 = Client.setIpc1("add", config, ipcRequest.getDeviceId(), ipcRequest.getPointEntity().getIp(),
                ipcRequest.getPointEntity().getPort());
        if (!setIpc1) {
            // s_ipcnum:2;s_nod:0;s_power:0;s_ip:192.168.0.157;s_port:80;s_username:admin;s_password:vr123456;s_online:1;s_stream:0;s_nod:-1;s_power:-1;s_ip:null;s_port:0;s_username:null;s_password:null;s_online:0;s_stream:0;
            // boolean setIpc12 = Client.setIpc1("delete", config,
            // ipcRequest.getDeviceId(), ipcRequest.getPointEntity().getIp(),
            // ipcRequest.getPointEntity().getPort());
            String ipc2 = Client.getIpc2(ipcRequest.getPointEntity().getIp(), ipcRequest.getPointEntity().getPort(),
                    ipcRequest.getDeviceId());
            if (ipc2 != null && ipc2.length() > 11) {
                int indexOf = ipc2.indexOf(";");
                String substring = ipc2.substring(indexOf + 1);
                System.out.println(substring);
                String[] split2 = substring.split(";");
                Map<String, String> map = new HashMap<>();

                for (int i = 0; i < split2.length; i++) {
                    String[] split3 = split2[i].split(":");
                    map.put(split3[0], split3[1]);
                    if (map.containsKey("s_stream")) {
                        if (map.get("s_ip").equals(ipcRequest.getS_ip())
                                && Integer.parseInt(map.get("s_port").toString()) == ipcRequest.getS_port()) {
                            // 摄像头已存在s_nod:0;s_power:0;s_ip:192.168.0.172;s_port:80;s_username:admin;s_password:vr123456;s_online:1;s_stream:0;
                            ipcRequest.setS_ip(map.get("s_ip"));
                            ipcRequest.setS_nod(Integer.parseInt(map.get("s_nod")));
                            ipcRequest.setS_port(Integer.parseInt(map.get("s_port")));
                            ipcRequest.setS_username(map.get("s_username"));
                            ipcRequest.setS_password(map.get("s_password"));
                            ipcRequest.setS_online(Integer.parseInt(map.get("s_online")));
                            ipcRequest.setS_stream(Integer.parseInt(map.get("s_stream")));
                            PointResult addExitstIPC = ipcService.addExitstIPC(ipcRequest);
                            if (addExitstIPC.isSuccess()) {
                                // 少了多个摄像头存在时候的逻辑
                                return new IOTResult(true, "添加成功", null, 0);
                            }
                        }
                    }
                }
            } else {
                return new IOTResult(false, "添加失败,摄像头ip输入有误", null, 0);
            }
        }*/



        PointResult addIPC = ipcService.addIPC(ipcRequest);
        /*if (!addIPC.isSuccess()) {
            boolean setIpc12 = Client.setIpc1("delete", config, ipcRequest.getDeviceId(),
                    ipcRequest.getPointEntity().getIp(), ipcRequest.getPointEntity().getPort());
            if (setIpc12) {
                return new IOTResult(false, "添加失败", null, 5);
            }
        }*/
        // List<Map<String,Object>> listPoint =
        // pointService.listPoint2(pointEntity2);
        // HashMap<String,Object> map = new HashMap<>();
        // map.put("point", listPoint.get(0));
        // map.put("ipc", addIPC.getObject());
        if (!addIPC.isSuccess())
        return new IOTResult(false, "添加失败", null, 0);
        else
        return new IOTResult(true, "添加成功", null, 0);

    }

    // 同步配置
    @CrossOrigin
    @RequestMapping(value = "/autoSyn", method = RequestMethod.POST)
    public IOTResult autoSyn(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;


        byte b = 1;
        List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2(ipcRequest.getMapingDeviceId(),
                ipcRequest.getDeviceId(), b, ipcRequest.getPointEntity().getIp(),
                ipcRequest.getPointEntity().getPort());
        // 批量添加监视点
        int addIPCPointList = ipcService.addIPCPointList(ipcMonitor2);
        int updateIPC = ipcService.updateIPC2(ipcRequest.getId(), 1);
        if (addIPCPointList > 0) {
            return new IOTResult(true, "同步成功", null, 0);
        }
        return new IOTResult(false, "同步失败", null, 0);

    }

    // 删除ipc
    @CrossOrigin
    @RequestMapping(value = "/deleteIPC", method = RequestMethod.POST)
    public IOTResult deleteIPC(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;


        IPCEntity ipc = ipcService.getIPCById(ipcRequest);


        if (ipc == null || ipc.getDeviceId() == null || ipc.getDeviceId().toString().trim().length() < 1) {
            return new IOTResult(false, "IPC不存在", null, 4);
        }
        ipcRequest.setDeviceId(ipc.getDeviceId());
        ipcRequest.setMapingDeviceId(ipc.getMapingDeviceId());


        int addIPC = ipcService.deleteIPC(ipcRequest);

        String config = "s_nod:" + ipc.getS_nod() + ";s_ip:" + ipc.getS_ip() + ";s_port:" + ipc.getS_port()
                + ";s_username:" + ipc.getS_username() + ";s_password:" + ipc.getS_password() + ";";

        if (addIPC > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 5);

    }

    // 修改ipc配置
    @CrossOrigin
    @RequestMapping(value = "/updateIPC", method = RequestMethod.POST)
    public IOTResult updateIPC(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;

//TODO xwf 暂时注释掉
        /*
        String config = "s_nod:" + ipcRequest.getS_nod() + ";s_ip:" + ipcRequest.getS_ip() + ";s_port:"
                + ipcRequest.getS_port() + ";s_username:" + ipcRequest.getS_username() + ";s_password:"
                + ipcRequest.getS_password() + ";";parents
        boolean setIpc1 = Client.setIpc1("edit", config, ipcRequest.getDeviceId(), ipcRequest.getPointEntity().getIp(),
                ipcRequest.getPointEntity().getPort());
		if (!setIpc1) {
			return new IOTResult(false, "修改失败", null, 4);
		}*/
        int addIPC = ipcService.updateIPC(ipcRequest);
        if (addIPC < 1) {
            return new IOTResult(false, "修改失败", null, 5);
        }
        return new IOTResult(true, "修改成功", null, 0);

    }

    // 修改代理
    @CrossOrigin
    @RequestMapping(value = "/updateIPCProxy", method = RequestMethod.POST)
    public IOTResult updateIPCProxy(@RequestBody IPCRequest ipcRequest) {

        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;


        if (ipcRequest.getIpc().getId() <= 0) {
            //新建
            int updateIPCProxy = ipcService.addIPCProxy(ipcRequest.getIpc());
            if (updateIPCProxy < 1) {
             return    new IOTResult(false, "修改失败", null, 0);
            }

        } else {
            int updateIPCProxy = ipcService.updateIPCProxy(ipcRequest.getIpc());
            if (updateIPCProxy < 1) {
                return   new IOTResult(false, "修改失败", null, 0);
            }

        }

        return new IOTResult(true, "修改成功", 1, 0);
    }

    // 修改代理
    @CrossOrigin
    @RequestMapping(value = "/updateIPCParam", method = RequestMethod.POST)
    public IOTResult updateIPCPrarm(@RequestBody IPCRequest ipcRequest) {

        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;

        ipcRequest.setS_username(ipcRequest.getIpc().getUsername());
        ipcRequest.setS_password(ipcRequest.getIpc().getPassword());
        ipcRequest.setName(ipcRequest.getPointEntity().getTp_name());
        ipcRequest.setS_ip(ipcRequest.getIpc().getS_host());
        ipcRequest.setS_port(ipcRequest.getIpc().getS_hostport());

        int updateIPC = ipcService.updateIPC(ipcRequest);

        int updateIPCProxy = ipcService.updateIPCProxyByMapingDeviceId(ipcRequest.getIpc());
        if (updateIPCProxy < 1) {
            new IOTResult(false, "修改失败", null, 0);
        }
        return new IOTResult(true, "修改成功", updateIPCProxy, 0);
    }

    // 获得代理配置 // 主码流 子码流
    @CrossOrigin
    @RequestMapping(value = "/getIPCProxy", method = RequestMethod.POST)
    public IOTResult getIPCProxy(@RequestBody IPCRequest ipcRequest) {

        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;

        Map<String, Object> ctrlProxy = ipcService.getCtrlProxy(ipcRequest.getIpc());
        // if(ipcRequest.getIpc().getType()==1){
        // // 视屏代理
        // ctrlProxy = ipcService.getCtrlProxy(ipcRequest.getIpc());
        // }
        // if(ipcRequest.getIpc().getType()==2){
        //
        // // 控制代理
        // ctrlProxy = ipcService.getCtrlProxy(ipcRequest.getIpc());
        // }
        if (ctrlProxy == null) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", ctrlProxy, 0);

    }

    // 获得代理配置
    @CrossOrigin
    @RequestMapping(value = "/listIPCProxy", method = RequestMethod.POST)
    public IOTResult listIPCProxy(@RequestBody IPCRequest ipcRequest) {

        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;

        List<Map<String, Object>> listProxy = ipcService.listProxy(ipcRequest.getIpc());
        if (listProxy == null || listProxy.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", listProxy, 0);

    }

    // 查看摄像头
    @CrossOrigin
    @RequestMapping(value = "/listIPC", method = RequestMethod.POST)
    public IOTResult getIPC(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;


        // s_ipcnum:2;s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:1;s_stream:0;s_nod:0;s_power:0;s_ip:192.168.0.234;s_port:80;s_username:admin;s_password:12345;s_online:1;s_stream:0;
        /*String ipc2 = Client.getIpc2(ipcRequest.getPointEntity().getIp(), ipcRequest.getPointEntity().getPort(),
                ipcRequest.getPointEntity().getDeviceId());
		if (ipc2 == null) {
			List<Map<String, Object>> listIPC = ipcService.listIPC(ipcRequest);
			for (Map<String, Object> map : listIPC) {
				map.put("s_online", 0);
			}
			return new IOTResult(true, "摄像头存在", listIPC, 0);
		}
		if (ipc2.length() > 11) {
			int indexOf = ipc2.indexOf(";");
			String substring = ipc2.substring(indexOf + 1);
			System.out.println(substring);
			String[] split2 = substring.split(";");
			Map<String, String> map = new HashMap<>();
			for (int i = 0; i < split2.length; i++) {
				String[] split3 = split2[i].split(":");
				map.put(split3[0], split3[1]);
				if (map.containsKey("s_stream")) {
					map.put("deviceId", ipcRequest.getDeviceId());
					int updateIPC = ipcService.updateIPC2(map);
					map.remove("s_stream", map.get("s_stream"));
				}
			}
		}*/
        List<Map<String, Object>> listIPC = ipcService.listIPC(ipcRequest);
        if (listIPC == null || listIPC.size() < 1) {
            new IOTResult(false, "该节点下不存在摄像头", null, 0);
        }
        return new IOTResult(true, "摄像头存在", listIPC, 0);

    }


    // 获得ipc編碼的设置
    @CrossOrigin
    @RequestMapping(value = "/getIPCCodeing", method = RequestMethod.POST)
    public IOTResult getIPCSetting(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, false);
        if (valid != null) return valid;

        // 记得扩充ipc表,填入信息
        IPCEntity ipc = ipcService.getIPC(ipcRequest);
        IPCProxyEntity pe = new IPCProxyEntity();
        pe.setType(ipcRequest.getType());
        pe.setMapingDeviceId(ipcRequest.getMapingDeviceId());
        Map<String, Object> proxy = ipcService.getProxy(pe);
        // 条件代表还没有
        String config = "s_host:192.168.0.234;s_rport:8000;s_lport:9100;s_pwr:1;s_pwrval:0;s_timeout:60;";
        // "s_host:192.168.0.234;s_rport:8000;s_lport:9100;s_pwr:1;s_pwrval:0;s_timeout:120;"
        // String config =
        // "s_host:"+proxy.get("s_host").toString()+";s_rport:"+proxy.get("s_hostport").toString()+";s_lport:"+proxy.get("s_proxy").toString()+";s_pwr:"+proxy.get("s_pwr").toString()+";s_pwrval:"+proxy.get("s_pwrval").toString()+";s_timeout:120;";
        String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, proxy.get("deviceId").toString(),
                ipcRequest.getPointEntity().getIp(), ipcRequest.getPointEntity().getPort());

        System.out.println(setIpcProxyEx1);

        VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility(proxy.get("username").toString(),
                proxy.get("password").toString(), ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString());
        if (ipcAbility == null) {
            return new IOTResult(false, "查看失败", null, 5);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("ablity", ipcAbility);
        VideoParamsBean videoParamsBean = null;
        // if(ipc.getMchannel()==-1){
        videoParamsBean = new VideoParamsBean();
        NET_DVR_COMPRESSIONCFG_V30 compressInfo = HkSdkEx.getCompressInfo(ipc.getS_username(), ipc.getS_password(),
                ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString());
        videoParamsBean.parse(compressInfo);
        map.put("setting", videoParamsBean);
        return new IOTResult(true, "查看成功", map, 0);
        // }
        // map.put("setting", ipc);
        // return new IOTResult(true,"查看成功",map,12);

		/*
         * IPCPointEntity ipce = new IPCPointEntity();
		 * ipce.setDeviceId(map.get("deviceId")); List<Map<String,Object>>
		 * listIPCPoint = ipcService.listIPCPoint(ipce); return new
		 * IOTResult(true,"查看成功",listIPCPoint,0);
		 */

    }

    // 设置ipc的编码//通道参数
    @CrossOrigin
    @RequestMapping(value = "/setIPCCodeing", method = RequestMethod.POST)
    public IOTResult setIPCCodeing(@RequestBody IPCRequest ipcRequest) {
        IOTResult valid = validUser(ipcRequest, true);
        if (valid != null) return valid;

        List<Map<String, Object>> listIPC = ipcService.listIPC(ipcRequest);
        if (listIPC == null || listIPC.size() < 1) {
            return new IOTResult(false, "该节点下不存在摄像头", null, 4);
        }
        // 记得扩充ipc表,填入信息
        IPCEntity ipc = ipcService.getIPC(ipcRequest);
        IPCProxyEntity pe = new IPCProxyEntity();
        pe.setType(ipcRequest.getType());
        pe.setMapingDeviceId(ipcRequest.getMapingDeviceId());
        Map<String, Object> proxy = ipcService.getProxy(pe);

        // 条件代表还没有
        // int compareTo = ipcRequest.compareTo(proxy);
        // if(compareTo == 0){
        // return new IOTResult(true,"没有要修改的参数",null,11);
        // }
        NET_DVR_COMPRESSIONCFG_V30 compressInfo = HkSdkEx.getCompressInfo(proxy.get("username").toString(),
                proxy.get("password").toString(), ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString());
        NET_DVR_COMPRESSIONCFG_V30 compressionCfg = ipcRequest.getVideoParamsBean().toCompressionCfg(compressInfo);
        boolean setCompressInfo = HkSdkEx.setCompressInfo(ipc.getS_username(), ipc.getS_password(),
                ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString(), compressionCfg);
        if (!setCompressInfo) {
            return new IOTResult(false, "配置失败", null, 5);
        }
        if (ipcRequest.getCktime() == 1) {
            Calendar instance = Calendar.getInstance();
            HCNetSDK.NET_DVR_TIME m_struTime = new HCNetSDK.NET_DVR_TIME();
            m_struTime.dwYear = instance.get(Calendar.YEAR);
            m_struTime.dwMonth = instance.get(Calendar.MONTH);
            m_struTime.dwDay = instance.get(Calendar.DATE);
            m_struTime.dwHour = instance.get(Calendar.HOUR_OF_DAY);
            m_struTime.dwMinute = instance.get(Calendar.MINUTE);
            m_struTime.dwSecond = instance.get(Calendar.SECOND);
            boolean setSetDateTime = HkSdkEx.setSetDateTime(ipc.getS_username(), ipc.getS_password(),
                    ipcRequest.getPointEntity().getIp(), proxy.get("s_proxy").toString(), m_struTime);
            System.out.println(setSetDateTime);
        }
        // int updateIPC = ipcService.updateIPC(ipc);
        // if(updateIPC >0){
        return new IOTResult(true, "配置成功", null, 0);
        // }else{
        // return new IOTResult(false,"配置失败,请重新配置",null,0);
        // }

        // if(ipcAbility ==null){
        // return new IOTResult(false,"查看失败",null,0);
        // }
        // Map<String, Object> map = new HashMap<>();
        // map.put("ablity", ipcAbility);
        // map.put("setting", ipc);
        // return new IOTResult(true,"查看成功",map,0);

		/*
         * IPCPointEntity ipce = new IPCPointEntity();
		 * ipce.setDeviceId(map.get("deviceId")); List<Map<String,Object>>
		 * listIPCPoint = ipcService.listIPCPoint(ipce); return new
		 * IOTResult(true,"查看成功",listIPCPoint,0);
		 */

    }

    // pc获得监视点
    @CrossOrigin
    @RequestMapping(value = "/getIPCPoint", method = RequestMethod.POST)
    public IOTResult getIPCPoint(@RequestBody Map<String, String> map) {
        IPCPointEntity ipce = new IPCPointEntity();
        ipce.setDeviceId(map.get("deviceId"));
        ipce.setBeginTime(map.get("beginTime"));
        ipce.setEndTime(map.get("endTime"));
        ipce.setSuccess(1);
        IPCEntity ipc = new IPCEntity();
        ipc.setMapingDeviceId(map.get("deviceId"));
        ipc.setS_username(map.get("username"));
        ipc.setS_password(map.get("password"));
        List<Map<String, Object>> listIPC = ipcService.listIPC(ipc);
        if (listIPC == null || listIPC.isEmpty()) {
            return new IOTResult(false, "密码错误", null, 0);
        }
        List<Map<String, Object>> listIPCPoint = ipcService.ckIPCPoint(ipce);
        if (listIPCPoint == null || listIPCPoint.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", listIPCPoint, 0);

    }

    // App获得监视点
    @CrossOrigin
    @RequestMapping(value = "/getAppIPCPoint", method = RequestMethod.POST)
    public IOTResult getAppIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, false);
        if (valid != null) return valid;

        List<Map<String, Object>> appIPCPoint = ipcService.getAppIPCPoint(ipcPointRequset);
        if (appIPCPoint == null || appIPCPoint.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", appIPCPoint, 0);

    }

    // 添加监视点
    @CrossOrigin
    @RequestMapping(value = "/addIPCPoint", method = RequestMethod.POST)
    public IOTResult addIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, true);
        if (valid != null) return valid;


        MonitorHBM hbm = new MonitorHBM();
        hbm.setBeginTime(ipcPointRequset.getBeginTime());// "12:00:00"
        hbm.setEndTime(ipcPointRequset.getEndTime());// 18:00:00"
        hbm.setCycleDay(ipcPointRequset.getCycleDay());
        int lastIndexOf = ipcPointRequset.getDeviceId().lastIndexOf(".");
        String substring = ipcPointRequset.getDeviceId().substring(0, lastIndexOf);
        System.out.println(substring);
        hbm.setDeviceId(substring);
        hbm.setMonitorName(ipcPointRequset.getMonitorName());
        if (ipcPointRequset.getMonitorId() < 1) {
            int maxMonitorId = ipcService.getMaxMonitorId(ipcPointRequset.getDeviceId());
            ipcPointRequset.setMonitorId(maxMonitorId + 1);
        }
        hbm.setMonitorId(ipcPointRequset.getMonitorId());
        hbm.setRateSecond(ipcPointRequset.getRateSecond());
        // hbm.set
        byte b = 1;
        boolean setIpcMonitor = Client.setIpcMonitor(ipcPointRequset.getPointEntity().getIp(),
                ipcPointRequset.getPointEntity().getPort(), b, hbm);
        if (setIpcMonitor) {
            ipcPointRequset.setSuccess(1);
            if (ipcPointRequset.getId() > 0) {
                int addIPCPoint = ipcService.updateIPCPoint(ipcPointRequset);
                if (addIPCPoint > 0) {
                    return new IOTResult(true, "监视点修改成功", null, 0);
                }
                return new IOTResult(false, "监视点修改失败", null, 3);
            }
            int addIPCPoint = ipcService.addIPCPoint(ipcPointRequset);
            int ipcPointId = ipcService.getIPCPointId(ipcPointRequset.getDeviceId(), ipcPointRequset.getMonitorId());
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("ipcPointId", ipcPointId);
            hashMap.put("deviceId", hbm.getDeviceId());
            hashMap.put("monitorId", hbm.getMonitorId());
            if (addIPCPoint > 0) {
                return new IOTResult(true, "监视点设置成功", hashMap, 0);
            }
            Client.delIpcMonitor(ipcPointRequset.getPointEntity().getIp(), ipcPointRequset.getPointEntity().getPort(),
                    b, hbm);
        }
        return new IOTResult(false, "监视点设置失败", hbm, 3);
        // System.out.println(setIpcMonitor);
    }

    // 添加监视点
    @CrossOrigin
    @RequestMapping(value = "/addIPCPoint2", method = RequestMethod.POST)
    public IOTResult addIPCPoint2(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, true);
        if (valid != null) return valid;


        ipcPointRequset.setBeginTime("12:00:00");
        ipcPointRequset.setEndTime("18:00:00");
        ipcPointRequset.setMonitorName("");
        ipcPointRequset.setCycleDay(1);
        ipcPointRequset.setRateSecond(5);
        MonitorHBM hbm = new MonitorHBM();
        hbm.setBeginTime(ipcPointRequset.getBeginTime());// "12:00:00"
        hbm.setEndTime(ipcPointRequset.getEndTime());// 18:00:00"
        hbm.setCycleDay(ipcPointRequset.getCycleDay());
        int lastIndexOf = ipcPointRequset.getDeviceId().lastIndexOf(".");
        String substring = ipcPointRequset.getDeviceId().substring(0, lastIndexOf);
        System.out.println(substring);
        hbm.setDeviceId(substring);
        hbm.setMonitorName(ipcPointRequset.getMonitorName());
        if (ipcPointRequset.getMonitorId() < 1) {
            int maxMonitorId = ipcService.getMaxMonitorId(ipcPointRequset.getDeviceId());
            ipcPointRequset.setMonitorId(maxMonitorId + 1);
        }
        hbm.setMonitorId(ipcPointRequset.getMonitorId());
        hbm.setRateSecond(ipcPointRequset.getRateSecond());
        // hbm.set
        byte b = 1;
        boolean setIpcMonitor = Client.setIpcMonitor(ipcPointRequset.getPointEntity().getIp(),
                ipcPointRequset.getPointEntity().getPort(), b, hbm);
        if (setIpcMonitor) {
            ipcPointRequset.setSuccess(1);
            if (ipcPointRequset.getId() > 0) {
                int addIPCPoint = ipcService.updateIPCPoint(ipcPointRequset);
                if (addIPCPoint > 0) {
                    return new IOTResult(true, "监视点修改成功", null, 0);
                }
                return new IOTResult(false, "监视点修改失败", null, 3);
            }
            int addIPCPoint = ipcService.addIPCPoint(ipcPointRequset);
            int ipcPointId = ipcService.getIPCPointId(ipcPointRequset.getDeviceId(), ipcPointRequset.getMonitorId());
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("ipcPointId", ipcPointId);
            hashMap.put("deviceId", hbm.getDeviceId());
            hashMap.put("monitorId", hbm.getMonitorId());
            if (addIPCPoint > 0) {
                return new IOTResult(true, "监视点设置成功", hashMap, 0);
            }
            Client.delIpcMonitor(ipcPointRequset.getPointEntity().getIp(), ipcPointRequset.getPointEntity().getPort(),
                    b, hbm);
        }
        return new IOTResult(false, "监视点设置失败", hbm, 3);
        // System.out.println(setIpcMonitor);
    }

    // 修改监视点
    @CrossOrigin
    @RequestMapping(value = "/updateIPCPoint", method = RequestMethod.POST)
    public IOTResult updateIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, true);
        if (valid != null) return valid;

        MonitorHBM hbm = new MonitorHBM();
        hbm.setBeginTime(ipcPointRequset.getBeginTime());// "12:00:00"
        hbm.setEndTime(ipcPointRequset.getEndTime());// 18:00:00"
        hbm.setCycleDay(ipcPointRequset.getCycleDay());
        hbm.setDeviceId(ipcPointRequset.getDeviceId());
        hbm.setMonitorName(ipcPointRequset.getMonitorName());
        hbm.setMonitorId(ipcPointRequset.getMonitorId());
        hbm.setRateSecond(ipcPointRequset.getRateSecond());
        // hbm.set
        byte b = 1;
        boolean setIpcMonitor = Client.setIpcMonitor(ipcPointRequset.getPointEntity().getIp(),
                ipcPointRequset.getPointEntity().getPort(), b, hbm);
        if (setIpcMonitor) {
            ipcPointRequset.setSuccess(1);
            int addIPCPoint = ipcService.updateIPCPoint(ipcPointRequset);
            if (addIPCPoint > 0) {
                return new IOTResult(true, "监视点修改成功", null, 0);
            }
            Client.delIpcMonitor(ipcPointRequset.getPointEntity().getIp(), ipcPointRequset.getPointEntity().getPort(),
                    b, hbm);
        }
        return new IOTResult(false, "监视点设置失败", null, 3);
        // System.out.println(setIpcMonitor);
    }

    //
    // 修改监视点
    @CrossOrigin
    @RequestMapping(value = "/updateAppIPCPoint", method = RequestMethod.POST)
    public IOTResult updateAppIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, true);
        if (valid != null) return valid;

        ipcPointRequset.setBeginTime("12:00:00");
        ipcPointRequset.setEndTime("18:00:00");
        ipcPointRequset.setRateSecond(5);
        ipcPointRequset.setCycleDay(1);
        ipcPointRequset.setMonitorName("");
        MonitorHBM hbm = new MonitorHBM();
        hbm.setBeginTime(ipcPointRequset.getBeginTime());// "12:00:00"
        hbm.setEndTime(ipcPointRequset.getEndTime());// 18:00:00"
        hbm.setCycleDay(ipcPointRequset.getCycleDay());
        hbm.setDeviceId(ipcPointRequset.getDeviceId());
        hbm.setMonitorName(ipcPointRequset.getMonitorName());
        hbm.setMonitorId(ipcPointRequset.getMonitorId());
        hbm.setRateSecond(ipcPointRequset.getRateSecond());
        // hbm.set
        byte b = 1;
        boolean setIpcMonitor = Client.setIpcMonitor(ipcPointRequset.getIp(), ipcPointRequset.getPort(), b, hbm);
        if (setIpcMonitor) {
            ipcPointRequset.setSuccess(1);
            // int addIPCPoint = ipcService.updateIPCPoint(ipcPointRequset);
            // if(addIPCPoint >0){
            return new IOTResult(true, "摄像头调整成功", null, 0);
            // }
            // Client.delIpcMonitor(ipcPointRequset.getPointEntity().getIp(),
            // ipcPointRequset.getPointEntity().getPort(), b, hbm);
        }
        return new IOTResult(false, "摄像头调整失败", null, 3);
        // System.out.println(setIpcMonitor);
    }
    //

    // 删除监视点
    @CrossOrigin
    @RequestMapping(value = "/deleteIPCPoint", method = RequestMethod.POST)
    public IOTResult deleteIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, true);
        if (valid != null) return valid;

        Map<String, Object> ipcPoint = ipcService.getIPCPoint(ipcPointRequset);
        if (ipcPoint == null || ipcPoint.get("deviceId") == null
                || ipcPoint.get("deviceId").toString().trim().length() < 1) {
            return new IOTResult(true, "监视点不存在", null, 4);
        }
        MonitorHBM hbm = new MonitorHBM();
        hbm.setMonitorId(Integer.parseInt(ipcPoint.get("monitorId").toString()));
        hbm.setBeginTime(ipcPoint.get("beginTime").toString());// "12:00:00"
        hbm.setEndTime(ipcPoint.get("endTime").toString());// 18:00:00"
        hbm.setCycleDay(Integer.parseInt(ipcPoint.get("cycleDay").toString()));
        hbm.setDeviceId(ipcPoint.get("deviceId").toString());
        hbm.setMonitorName(ipcPoint.get("monitorName").toString());
        hbm.setRateSecond(Integer.parseInt(ipcPoint.get("rateSecond").toString()));
        // hbm.set
        byte b = 1;
        boolean setIpcMonitor = Client.delIpcMonitor(ipcPointRequset.getPointEntity().getIp(),
                ipcPointRequset.getPointEntity().getPort(), b, hbm);
        if (setIpcMonitor) {
            int addIPCPoint = ipcService.deleteIPCPoint(ipcPointRequset);
            if (addIPCPoint > 0) {
                return new IOTResult(true, "监视点删除成功", null, 0);
            }
            Client.setIpcMonitor(ipcPointRequset.getPointEntity().getIp(), ipcPointRequset.getPointEntity().getPort(),
                    b, hbm);
        }
        return new IOTResult(false, "监视点删除失败", null, 3);

    }

    // 删除监视点
    @CrossOrigin
    @RequestMapping(value = "/deleteIPCPointBatch", method = RequestMethod.POST)
    public IOTResult deleteIPCPointBatch(@RequestBody IPCPointsRequest points) {
        IOTResult valid = validUser(points, true);
        if (valid != null) return valid;

        List<IPCPointEntity> ipcPointList = points.getIpcPointList();
        String idString = points.getIdString();
        String[] split = idString.split(",");
        IPCPointEntity ipcPointEntity = new IPCPointEntity();
        for (String string : split) {
            ipcPointEntity.setId(Integer.parseInt(string));
            Map<String, Object> ipcPoint = ipcService.getIPCPoint(ipcPointEntity);
            if (ipcPoint == null || ipcPoint.get("deviceId") == null
                    || ipcPoint.get("deviceId").toString().trim().length() < 1) {
                return new IOTResult(true, "监视点不存在", null, 4);
            }
            MonitorHBM hbm = new MonitorHBM();
            hbm.setMonitorId(Integer.parseInt(ipcPoint.get("monitorId").toString()));
            hbm.setBeginTime(ipcPoint.get("beginTime").toString());// "12:00:00"
            hbm.setEndTime(ipcPoint.get("endTime").toString());// 18:00:00"
            hbm.setCycleDay(Integer.parseInt(ipcPoint.get("cycleDay").toString()));
            hbm.setDeviceId(ipcPoint.get("deviceId").toString());
            hbm.setMonitorName(ipcPoint.get("monitorName").toString());
            hbm.setRateSecond(Integer.parseInt(ipcPoint.get("rateSecond").toString()));
            // hbm.set
            byte b = 1;
            boolean setIpcMonitor = Client.delIpcMonitor(points.getPointEntity().getIp(),
                    points.getPointEntity().getPort(), b, hbm);
            System.out.println("删除监视点" + ipcPoint.get("monitorId").toString() + setIpcMonitor);
            if (setIpcMonitor) {
                int addIPCPoint = ipcService.deleteIPCPoint(ipcPointEntity);
            }
        }
        return new IOTResult(true, "监视点删除成功", null, 0);

        // for (IPCPointEntity ipcPointEntity : ipcPointList) {
        // Map<String, Object> ipcPoint =
        // ipcService.getIPCPoint(ipcPointEntity);
        // if(ipcPoint == null || ipcPoint.get("deviceId")==null
        // ||ipcPoint.get("deviceId").toString().trim().length()<1){
        // return new IOTResult(true,"监视点不存在",null,4);
        // }
        // MonitorHBM hbm = new MonitorHBM();
        // hbm.setMonitorId(Integer.parseInt(ipcPoint.get("monitorId").toString()));
        // hbm.setBeginTime(ipcPoint.get("beginTime").toString());//"12:00:00"
        // hbm.setEndTime(ipcPoint.get("endTime").toString());//18:00:00"
        // hbm.setCycleDay(Integer.parseInt(ipcPoint.get("cycleDay").toString()));
        // hbm.setDeviceId(ipcPoint.get("deviceId").toString());
        // hbm.setMonitorName(ipcPoint.get("monitorName").toString());
        // hbm.setRateSecond(Integer.parseInt(ipcPoint.get("rateSecond").toString()));
        // //hbm.set
        // byte b =1;
        // boolean setIpcMonitor =
        // Client.delIpcMonitor(points.getPointEntity().getIp(),
        // points.getPointEntity().getPort(), b, hbm);
        // System.out.println("删除监视点"+ipcPoint.get("monitorId").toString()+setIpcMonitor);
        // if(setIpcMonitor){
        // int addIPCPoint = ipcService.deleteIPCPoint(ipcPointEntity);
        // }
        //// return new IOTResult(false,"监视点删除失败",null,3);
        // }
        // return new IOTResult(true,"监视点删除成功",null,0);

        // return null;
        /*
         * if(ipcRequest.getCksid()==null ||
		 * ipcRequest.getCksid().trim().length()<1||ipcRequest.getCkuid()==null|
		 * |ipcRequest.getCkuid().trim().length()<1){ return new
		 * IOTResult(false,"信息不规范",null,1); } // 注册登陆按照什么来???? String check =
		 * toolUtil.getCheck(ToolUtil.IOT+ipcRequest.getCkuid()); if(check
		 * ==null ||!ipcRequest.getCksid().equals(check)){ return new
		 * IOTResult(false,"登陆失效",null,2); } long uid =
		 * toolUtil.getbase_uidSid(ipcRequest.getCkuid(),
		 * ipcRequest.getCksid()); ipcRequest.setUid(uid); PointEntity
		 * pointEntity = new PointEntity(); pointEntity.setUid(1); // 修改
		 * if(ipcRequest.getId()>0){ pointEntity.setTp_id(ipcRequest.getId());
		 * PointEntity point = pointService.getPoint(pointEntity); if(
		 * pointEntity.getTp_pid()!=0){ if(point==null){ return new
		 * IOTResult(false,"节点不存在",null,3); } } if(ipcRequest.getTp_type()!=4){
		 * return new IOTResult(false,"类型不正确",null,5); } String deviceId =
		 * ipcRequest.getDeviceId(); if(deviceId==null ||
		 * ipcRequest.getDeviceId().trim().length()<1){ return new
		 * IOTResult(false,"请输入设备号",null,7); } int updateMainDevice =0; try {
		 * updateMainDevice = ipcService.updateMainDevice(ipcRequest); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); return new IOTResult(false,"节点修改失败",null,11); }
		 * switch (updateMainDevice) { case 1: return new
		 * IOTResult(true,"节点添加成功",null,6); case 2: return new
		 * IOTResult(false,"请输入正确的链接方式",null,9); case 3: return new
		 * IOTResult(false,"请输入正确的ip port和链接方式",null,10); default: return new
		 * IOTResult(false,"节点添加失败",null,8); } }
		 * pointEntity.setTp_id(ipcRequest.getTp_pid()); PointEntity point =
		 * pointService.getPoint(pointEntity); if( pointEntity.getTp_pid()!=0){
		 * if(point==null){ return new IOTResult(false,"父节点不存在",null,3); } }
		 * if(!point.get("uid").equals(uid)){ return new
		 * IOTResult(false,"您无权添加到该节点下",null,4); }
		 * if(ipcRequest.getTp_type()!=4){ return new
		 * IOTResult(false,"类型不正确",null,5); } String maxId =
		 * toolUtil.getMaxId(ToolUtil.TREEID); if(maxId ==null){
		 * toolUtil.setMaxId(ToolUtil.TREEID, 0); } Long maxIdInc =
		 * toolUtil.MaxIdInc(ToolUtil.TREEID);
		 * ipcRequest.setId(maxIdInc.intValue()); String deviceId =
		 * ipcRequest.getDeviceId(); if(deviceId==null ||
		 * ipcRequest.getDeviceId().trim().length()<1){ return new
		 * IOTResult(false,"请输入设备号",null,7); } int addPoint; try { addPoint =
		 * ipcService.addIPC(ipcRequest); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); return new
		 * IOTResult(false,"节点添加失败",null,8); } // 您无权添加到该节点下 // 节点不存在
		 * if(addPoint ==1){ return new IOTResult(true,"节点添加成功",null,6); }
		 * switch (addPoint) { case 1: return new
		 * IOTResult(true,"节点添加成功",null,6); case 2: return new
		 * IOTResult(false,"请输入正确的链接方式",null,9); case 3: return new
		 * IOTResult(false,"请输入正确的ip port和链接方式",null,10); default: return new
		 * IOTResult(false,"节点添加失败",null,8); } }
		 */
        /*
         * IPCPointEntity ipce = null; byte b =1; int i =0;
		 * //List<Map<String,Object>> listIPCPoint =
		 * ipcService.listIPCPoint(ipce); List<IPCPointEntity> ipcMonitor2 =
		 * Client.getIpcMonitor2("10.00.21.74.01", "10.00.21.74", b,
		 * "192.168.0.168", 52390); for (IPCPointEntity ipcPointEntity :
		 * ipcMonitor2) { ipcPointEntity.setId(i++); int addIPCPoint =
		 * ipcService.addIPCPoint(ipcPointEntity); } return new
		 * IOTResult(true,"查看成功",null,0);
		 */

    }

    // @CrossOrigin
    // @RequestMapping(value="/listIPCPointIMG",method=RequestMethod.POST)
    // public IOTResult listIPCPointIMG(@RequestBody Map<String, String> map) {
    // IPCPointEntity ipce = new IPCPointEntity();
    // String deivceId = map.get("deviceId");
    // String beginTime = map.get("beginTime");
    // String endTime = map.get("endTime");
    // String monitorId = map.get("monitorId");
    // /*ipce.setDeviceId("10.00.21.74.01");
    // ipce.setMonitorId(1);
    // ipce.setBeginTime("2017-09-01");
    // ipce.setEndTime("2017-09-07");*/
    // ipce.setDeviceId(deivceId);
    // ipce.setBeginTime(beginTime);
    // ipce.setEndTime(endTime);
    // ipce.setMonitorId(Integer.parseInt(monitorId));
    // List<Map<String,Object>> listIPCPointIMG =
    // ipcService.listIPCPointIMG(ipce);
    // return new IOTResult(true,"查看成功",listIPCPointIMG,0);
    // }

    // 物联网获得图片
    @CrossOrigin
    @RequestMapping(value = "/listIPCPointImg", method = RequestMethod.POST)
    public IOTResult listIPCPointImg(@RequestBody Map<String, String> map) {
        IPCPointEntity ipce = new IPCPointEntity();
        String deivceId = map.get("deviceId");
        String beginTime = map.get("beginTime");
        String endTime = map.get("endTime");
        String monitorId = map.get("monitorId");
        // 应该要改下逻辑
        ipce.setDeviceId(deivceId);
        ipce.setBeginTime(beginTime);
        ipce.setEndTime(endTime);
        ipce.setMonitorId(Integer.parseInt(monitorId));
        List<Map<String, Object>> listIPCPointIMG = ipcService.listIPCPointIMG(ipce);
        return new IOTResult(true, "查看成功", listIPCPointIMG, 0);
    }

    // 物联网获得监视点
    @CrossOrigin
    @RequestMapping(value = "/listIPCPoint", method = RequestMethod.POST)
    public IOTResult listIPCPoint(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, false);
        if (valid != null) return valid;

        // 应该要改下逻辑
        IPCPointEntity ipcPointEntity = new IPCPointEntity();
        ipcPointEntity.setDeviceId(ipcPointEntity.getDeviceId());
        // ipcPointRequset.setEndTime("");
        // ipcPointRequset.setBeginTime("");
        // ipcPointRequset.setRateSecond(0);
        // ipcPointRequset.setCycleDay(0);
        List<Map<String, Object>> listIPCPointIMG = ipcService.listIPCPoint(ipcPointRequset);
        if (listIPCPointIMG == null || listIPCPointIMG.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", listIPCPointIMG, 0);
    }

    // 物联网获得图片
    @CrossOrigin
    @RequestMapping(value = "/listLastIPCPointIMG", method = RequestMethod.POST)
    public IOTResult listLastIPCPointIMG(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, false);
        if (valid != null) return valid;

        Map<String, Object> listLastIPCPointImg = ipcService.listLastIPCPointImg(ipcPointRequset.getDeviceId());
        if (listLastIPCPointImg == null || listLastIPCPointImg.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", listLastIPCPointImg, 0);
    }

    // 物联网获得图片
    @CrossOrigin
    @RequestMapping(value = "/listIPCPointIMG", method = RequestMethod.POST)
    public IOTResult listIPCPointIMG(@RequestBody IPCPointRequset ipcPointRequset) {
        IOTResult valid = validUser(ipcPointRequset, false);
        if (valid != null) return valid;

        // 应该要改下逻辑
        IPCPointEntity ipcPointEntity = new IPCPointEntity();
        ipcPointEntity.setDeviceId(ipcPointEntity.getDeviceId());
        if (ipcPointRequset.getApp() == 1) {
            ipcPointRequset.setPagesize(3);
        }
        List<Map<String, Object>> listIPCPointIMG = ipcService.listIPCPointIMG(ipcPointRequset);
        int listIPCPointIMGCount = ipcService.listIPCPointIMGCount(ipcPointRequset);

        int totalpage = 0;
        if (listIPCPointIMGCount % ipcPointRequset.getPagesize() > 0) {
            totalpage = listIPCPointIMGCount / ipcPointRequset.getPagesize() + 1;
        } else {
            totalpage = listIPCPointIMGCount / ipcPointRequset.getPagesize();
        }
        ipcService.listIPCPointIMGCount(ipcPointRequset);
        if (listIPCPointIMG == null || listIPCPointIMG.isEmpty()) {
            return new IOTResult2(false, "暂无相关信息", null, 0, totalpage, listIPCPointIMGCount);
        }
        return new IOTResult2(true, "查看成功", listIPCPointIMG, 0, totalpage, listIPCPointIMGCount);
    }

    @CrossOrigin
    @RequestMapping(value = "/testCoding", method = RequestMethod.POST)
    public IOTResult testCoding(@RequestBody IPCPointRequset ipcPointRequset) {
        System.out.println("测试获得编码能力");
        // VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility("admin",
        // "vr123456", "111.53.182.34", "9021");
        VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility("admin", "12345", "192.168.0.234", "8000");
        VideoChannel subChannel = ipcAbility.getSubChannel();
        String channelType = subChannel.getChannelType();
        List<VideoResolution> solutions = subChannel.getSolutions();
        System.out.println("子码流");
        System.out.println(channelType);
        List<VideoEncodeType> videoEncodeTypes = subChannel.getVideoEncodeTypes();
        for (VideoResolution videoResolution : solutions) {

            System.out.println("videoResolution index :" + videoResolution.getIndex());
            System.out.println("videoResolution name" + videoResolution.getName());
            System.out.println("videoResolution Resolution" + videoResolution.getResolution());
            System.out.println("videoResolution VideoBitrate" + videoResolution.getVideoBitrate());
            System.out.println("videoResolution VideoFrameRate" + videoResolution.getVideoFrameRate());

        }
        for (VideoEncodeType videoEncodeType : videoEncodeTypes) {
            System.out.println("videoEncodeType VideoEncodeEfficiency :" + videoEncodeType.getVideoEncodeEfficiency());
            System.out.println("videoEncodeType EncodeType :" + videoEncodeType.getVideoEncodeType());
        }
        VideoChannel mainChannel = ipcAbility.getMainChannel();
        String channelType2 = mainChannel.getChannelType();
        System.out.println(channelType2);
        List<VideoResolution> solutions2 = mainChannel.getSolutions();
        System.out.println("主码流");
        List<VideoEncodeType> videoEncodeTypes2 = mainChannel.getVideoEncodeTypes();
        for (VideoResolution videoResolution : solutions2) {

            System.out.println("videoResolution index :" + videoResolution.getIndex());
            System.out.println("videoResolution name" + videoResolution.getName());
            System.out.println("videoResolution Resolution" + videoResolution.getResolution());
            System.out.println("videoResolution VideoBitrate" + videoResolution.getVideoBitrate());
            System.out.println("videoResolution VideoFrameRate" + videoResolution.getVideoFrameRate());

        }
        for (VideoEncodeType videoEncodeType : videoEncodeTypes2) {
            System.out.println("videoEncodeType VideoEncodeEfficiency :" + videoEncodeType.getVideoEncodeEfficiency());
            System.out.println("videoEncodeType EncodeType :" + videoEncodeType.getVideoEncodeType());
        }
        return null;

    }

    // 用前端调起摄像头
    @CrossOrigin
    @RequestMapping(value = "/getShopCamera", method = RequestMethod.POST)
    public IOTResult getShopCamera(@RequestBody Map<String, String> map) {
        String string = map.get("mapingDeviceId");
        // 应该要改下逻辑'
        // map.put("username", "admin");
        // map.put("password", "12345");
        // map.put("s_proxy", "9001");
        //// map.put("username", "admin");
        //// map.put("username", "admin");
        //// map.put("username", "admin");
        // return new IOTResult(true,"查看成功",map,0);
        IPCProxyEntity ipcEntity = new IPCProxyEntity();
        // int lastIndexOf = string.lastIndexOf(".");
        // String substring = string.substring(0, lastIndexOf);
        ipcEntity.setMapingDeviceId(string);
        ipcEntity.setType(1);
        // 设置代理
        Map<String, Object> proxy = ipcService.getProxy(ipcEntity);
        if (proxy == null || proxy.get("id").toString().trim().length() < 1) {
            return new IOTResult(false, "摄像头不存在", null, 0);
        }
        // String config =
        // "s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;";
        String check = "s_host:" + proxy.get("s_host") + ";s_port:" + proxy.get("s_hostport") + ";s_lport:"
                + proxy.get("s_proxy") + ";s_pwr:" + proxy.get("s_pwr") + ";s_pwrval:" + proxy.get("s_pwrval")
                + ";s_timeout:" + proxy.get("s_timeout") + ";";

        PointEntity pointEntity = new PointEntity();
        pointEntity.setDeviceId(proxy.get("deviceId").toString());
        PointEntity point = pointService.getPoint(pointEntity);
        proxy.put("ip", point.getIp());
        proxy.put("x", point.getX());
        proxy.put("y", point.getY());
        // return new IOTResult(true,"查看成功",proxy,0);
        String setIpcProxyEx1 = Client.setIpcProxyEx1("add", check, proxy.get("deviceId").toString(), point.getIp(),
                point.getPort());
        if (setIpcProxyEx1.equals("ok") || setIpcProxyEx1.equals("01")) {
            return new IOTResult(true, "查看成功", proxy, 0);
        }

        // String check2 =
        // toolUtil.getCheck(ToolUtil.VIDEO+ToolUtil.CTRL+string);
        // if(check2 == null){
        // toolUtil.setCheck(ToolUtil.VIDEO+ToolUtil.CTRL+string, check);
        // toolUtil.setCheckexpire(ToolUtil.VIDEO+ToolUtil.CTRL+string,
        // Integer.parseInt(proxy.get("s_timeout").toString()));
        // System.out.println(setIpcProxyEx1);
        //// }

        // }
        return new IOTResult(false, "查看失败", proxy, 0);
    }

    // 用前端调起摄像头
    @CrossOrigin
    @RequestMapping(value = "/getCamera", method = RequestMethod.POST)
    public IOTResult getCamera(@RequestBody Map<String, String> map) {
        String string = map.get("mapingDeviceId");
        // 应该要改下逻辑
        IPCProxyEntity ipcEntity = new IPCProxyEntity();
        int lastIndexOf = string.lastIndexOf(".");
        String substring = string.substring(0, lastIndexOf);
        ipcEntity.setMapingDeviceId(string);
        ipcEntity.setType(2);
        // 设置代理
        Map<String, Object> proxy = ipcService.getProxy(ipcEntity);
        if (proxy == null || proxy.get("id").toString().trim().length() < 1) {
            return new IOTResult(false, "摄像头不存在", null, 0);
        }
        // String config =
        // "s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;";
        String check = "s_host:" + proxy.get("s_host") + ";s_rport:" + proxy.get("s_hostport") + ";s_lport:"
                + proxy.get("s_proxy") + ";s_pwr:" + proxy.get("s_pwr") + ";s_pwrval:" + proxy.get("s_pwrval")
                + ";s_timeout:" + proxy.get("s_timeout") + ";";

        PointEntity pointEntity = new PointEntity();
        pointEntity.setDeviceId(proxy.get("deviceId").toString());
        PointEntity point = pointService.getPoint(pointEntity);
        proxy.put("ip", point.getIp());
        String setIpcProxyEx1 = Client.setIpcProxyEx1("add", check, proxy.get("deviceId").toString(), point.getIp(),
                point.getPort());
        // s_host:192.168.0.234;s_port:8000;s_lport:9100;s_pwr:1;s_pwrval:0;s_timeout:180;
        // s_host:192.168.0.234;s_rport:8000;s_lport:9100;s_pwr:1;s_pwrval:0;s_timeout:60;
        if (setIpcProxyEx1.equals("ok") || setIpcProxyEx1.equals("01")) {
            return new IOTResult(true, "查看成功", proxy, 0);
        }
        // String check2 =
        // toolUtil.getCheck(ToolUtil.VIDEO+ToolUtil.CTRL+string);
        // if(check2 == null){
        // toolUtil.setCheck(ToolUtil.VIDEO+ToolUtil.CTRL+string, check2);
        // toolUtil.setCheckexpire(ToolUtil.VIDEO+ToolUtil.CTRL+string,
        // Integer.parseInt(proxy.get("s_timeout").toString()));
        // String setIpcProxyEx1 = Client.setIpcProxyEx1("add", check,
        // proxy.get("deviceId").toString(), point.getIp(), point.getPort());
        // System.out.println(setIpcProxyEx1);
        //// }
        // return new IOTResult(true,"查看成功",proxy,0);
        //
        // }
        // String setIpcProxyEx1 = Client.setIpcProxyEx1("add", check,
        // proxy.get("deviceId").toString(), point.getIp(), point.getPort());
        System.out.println(setIpcProxyEx1);
        return new IOTResult(false, "查看失败", null, 0);
    }
    // 清空监视点
    // 后台调起摄像头
    // 获取日志
    // 软件更新
    // 控制采集服务
    // 开关量控制


}
