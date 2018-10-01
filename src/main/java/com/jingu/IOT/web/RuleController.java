/**
 * 项目名称：IOT
 * 类名称：RuleController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月27日 上午11:06:54
 * 修改人：jianghu
 * 修改时间：2017年10月27日 上午11:06:54
 * 修改备注： 上午11:06:54
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.RuleEntity;
import com.jingu.IOT.requset.MonitorRequest;
import com.jingu.IOT.requset.RuleRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.RedisService;
import com.jingu.IOT.service.RuleService;
import com.jingu.IOT.service.SettingService;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.PublicMethod;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianghu
 * @ClassName: RuleController
 * @Description: TODO
 * @date 2017年10月27日 上午11:06:54 规定
 */
@RestController
public class RuleController {

    private RuleService ruleService;
    private static ToolUtil toolUtil;
    private SettingService settingService;
    @Autowired
    RedisService redisService;

    @Autowired
    public RuleController(RuleService ruleService, ToolUtil toolUtil, SettingService settingService) {
        this.ruleService = ruleService;
        this.toolUtil = toolUtil;
        this.settingService = settingService;
    }


    // 更新预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/updateRule", method = RequestMethod.POST)
    public IOTResult updateRule(@RequestBody RuleRequset ruleRequset) {

        Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String time = year + "/" + month + 1 + "/" + date + " " + ruleRequset.getExecTime();
        ruleRequset.setTime(time);
        if (ruleRequset.getR_id() > 0) {
            int addRule = ruleService.updateRule(ruleRequset);
            if (addRule > 0) {
                return new IOTResult(true, "更新成功", null, 0);
            }
            return new IOTResult(false, "更新失败", null, 0);
        }
        if (ruleRequset.getR_id() == 0) {
            int addRule = ruleService.addRule(ruleRequset);
            if (addRule > 0) {
                return new IOTResult(true, "新增成功", null, 0);
            }
            return new IOTResult(false, "新增失败", null, 0);
        }
        return new IOTResult(false, "请求失败", null, 0);
    }

    // 删除预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/deleteRule", method = RequestMethod.POST)
    public IOTResult deleteRule(@RequestBody RuleRequset ruleRequset) {
        int addRule = ruleService.deleteRule(ruleRequset);
        if (addRule > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 0);
    }

    // 批量删除预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/deleteRuleIds", method = RequestMethod.POST)
    public IOTResult deleteRuleIds(@RequestBody RuleRequset ruleRequset) {
        int addRule = ruleService.deleteRuleIds(ruleRequset.getIds());
        if (addRule > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 0);
    }

    // 查看预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/listRule", method = RequestMethod.POST)
    public IOTResult listRule(@RequestBody RuleRequset ruleRequset) {
        List<RuleEntity> listRule = ruleService.listRule(ruleRequset);
        if (listRule != null && !listRule.isEmpty()) {
            return new IOTResult(true, "查找成功", listRule, 0);
        }
        return new IOTResult(false, "暂无相关信息", null, 0);
    }

    // 设置预约控制规则进入redis
    @CrossOrigin
    @RequestMapping(value = "/setRuleList", method = RequestMethod.POST)
    public IOTResult setRuleList(@RequestBody RuleRequset ruleRequset) throws UnsupportedEncodingException {
        // 验证控制设备是否存在
        // 验证权限
        MonitorEntity monitorEntity = new MonitorEntity();
        monitorEntity.setCtrl_id(ruleRequset.getCtrl_id());
        monitorEntity.setMo_deviceId(ruleRequset.getR_deviceId());
        int deleteRuleList2 = redisService.deleteMonitorList(monitorEntity);

        ControlEntity controlEntity = new ControlEntity();
        controlEntity.setCtrl_id(ruleRequset.getCtrl_id());
        if (ruleRequset.getType() == 1) {
            List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
            if (ruleList == null) {
                ruleList = new ArrayList<>();
                toolUtil.setMaxId(ToolUtil.RULEID, 0);
            }
            // 设备控制状态
            controlEntity.setState_type(2);
            int updateControlSetting2 = settingService.updateControlSetting(controlEntity);
            List<RuleEntity> listRule = ruleService.listRule(ruleRequset);
            if (listRule == null || listRule.isEmpty()) {
                return new IOTResult(true, "没有预约控制规则", null, 5);
            }

            Calendar calendar = Calendar.getInstance();
            Date time = calendar.getTime();
            SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String format = dfDateFormat.format(time);

            List<RuleEntity> collect = listRule.stream()
                    .filter(x -> (x.getBeginTime().substring(0, 10) + " " + x.getExecTime()).compareTo(format) >= 0)
                    .collect(Collectors.toList());
            if (collect == null || collect.isEmpty()) {
                return new IOTResult(true, "规则无效", null, 16);
            }
            // if(collect ==null || collect.isEmpty()){
            // controlEntity.setState_type(2);
            // int updateControlSetting =
            // settingService.updateControlSetting(controlEntity);
            // if(updateControlSetting >0){
            // return new IOTResult(true,"预约控制生效",null,0);
            // }
            // return new IOTResult(false,"预约控制没有生效",null,6);
            //
            // }
            boolean addAll = ruleList.addAll(collect);
            // controlEntity.setState_type(2);
            // int updateControlSetting =
            // settingService.updateControlSetting(controlEntity);
            if (addAll) {

                toolUtil.setRuleList(ToolUtil.RULE, ruleList);
                if (ruleRequset.getApp() == 1) {
                    Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    int second = c.get(Calendar.SECOND);
                    System.out.println(hour + "-" + minute + "-" + second);
                    String cktime = toolUtil.cktimeHour(hour + ":" + minute + ":" + second);
                    // List<RuleEntity> collect2 = collect.stream().filter(x
                    // ->x.getExecTime().compareTo(cktime) >
                    // 0).collect(Collectors.toList());
                    Comparator<RuleEntity> byTime = (m1,
                                                     m2) -> (m1.getBeginTime().substring(0, 10) + " " + m1.getExecTime())
                            .compareTo(m2.getBeginTime().substring(0, 10) + " " + m2.getExecTime());
                    Collections.sort(collect, byTime);
                    System.out.println("比较了时间...");
                    if (collect == null || collect.isEmpty()) {
                        return new IOTResult(true, "规则无效", null, 6);
                    }
                    RuleEntity ruleEntity = collect.get(0);
                    // 获得当前开启度

                    Map<String, Object> controlSetting = settingService.getControlSetting(ruleRequset.getCtrl_id());
                    int result = -1;
                    if (controlSetting.get("ctrl_type").toString().equals("2")) {
                        byte[] recData = Client.getMotorSensor2(ruleRequset.getPointEntity().getIp(),
                                ruleRequset.getPointEntity().getPort(), ruleRequset.getPointEntity().getDeviceId());

                        int posSensorSh = Integer.parseInt(controlSetting.get("ctrl_channel").toString());
                        String maxVal = controlSetting.get("ctrl_max").toString();
                        String minVal = controlSetting.get("ctrl_min").toString();
                        try {
                            byte[] value = new byte[2];

                            System.arraycopy(recData, 14 + 4 + 1 + (posSensorSh - 1) * 2, value, 0, 2);// 数据头+id+数据类型+...
                            System.out.println("没有报错。。。");
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
                    }
                    ruleEntity.setGrade(String.valueOf(result));
                    return new IOTResult(true, "预约控制生效", ruleEntity, 7);
                }
                return new IOTResult(true, "预约控制生效", collect, 7);
            }
            return new IOTResult(false, "预约控制没有生效", null, 8);
        }
        if (ruleRequset.getType() == 2) {
            int deleteRuleList = redisService.deleteRuleListByCtrl_id(ruleRequset);

            controlEntity.setState_type(0);
            settingService.updateControlSetting(controlEntity);
            if (deleteRuleList > 0) {
                return new IOTResult(true, "预约控制停止成功", null, 0);
            }
            return new IOTResult(false, "预约控制停止失败", null, 0);
        }
        return new IOTResult(false, "请求失败", null, 0);

    }

    // 从redis中删除预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/removeRuleList", method = RequestMethod.POST)
    public IOTResult removeRuleList(@RequestBody RuleRequset ruleRequset) throws UnsupportedEncodingException {

        int status = redisService.deleteRuleListByCtrl_id(ruleRequset);
        if (status > 0)
            return new IOTResult(true, "删除成功", status, 0);
        else
            return new IOTResult(false, "删除失败", status, 0);
    }

    // 删除redis中rule数据
    @CrossOrigin
    @RequestMapping(value = "/resetRuleSet", method = RequestMethod.POST)
    public IOTResult resetRuleSet(@RequestBody RuleRequset ruleRequset) throws UnsupportedEncodingException {
        toolUtil.setRuleList(ToolUtil.RULE, null);
        return new IOTResult(true, "删除成功", 1, 0);
    }


    // 重置智能控制规则
    @CrossOrigin
    @RequestMapping(value = "/resetMonitorList", method = RequestMethod.POST)
    public IOTResult resetMonitorList(@RequestBody MonitorRequest mr) throws UnsupportedEncodingException {
        toolUtil.setRuleList(ToolUtil.MONITOR + mr.getMo_deviceId(), null);
        return new IOTResult(true, "修改成功", null, 0);
    }


    // 获得预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/getRuleSet", method = RequestMethod.POST)
    public IOTResult getRuleSet(@RequestBody RuleRequset ruleRequset) throws UnsupportedEncodingException {
        if (ruleRequset.getCksid() == null || ruleRequset.getCksid().trim().length() < 1
                || ruleRequset.getCkuid() == null || ruleRequset.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ruleRequset.getCkuid());
        if (check == null || !ruleRequset.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        List<RuleEntity> listRule = ruleService.listRule(ruleRequset);
        if (listRule != null && !listRule.isEmpty()) {
            return new IOTResult(false, "查看成功", listRule, 0);
        }
        return new IOTResult(false, "查看失败", null, 0);

    }

    // 修改预约控制规则
    @CrossOrigin
    @RequestMapping(value = "/updateRuleSet", method = RequestMethod.POST)
    public IOTResult updateRuleSet(@RequestBody RuleRequset ruleRequset) throws UnsupportedEncodingException {
        if (ruleRequset.getCksid() == null || ruleRequset.getCksid().trim().length() < 1
                || ruleRequset.getCkuid() == null || ruleRequset.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ruleRequset.getCkuid());
        if (check == null || !ruleRequset.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }

        int updateRule = ruleService.updateRule(ruleRequset);
        if (updateRule > 0) {
            redisService.updateRuleList(ruleRequset);
            return new IOTResult(true, "修改成功", null, 0);
        }
        return new IOTResult(false, "修改失败", null, 0);
    }


    // 修改预约智能控制规则
    @CrossOrigin
    @RequestMapping(value = "/updateMonitor", method = RequestMethod.POST)
    public IOTResult updateMonitor(@RequestBody MonitorRequest mr) {

        if (mr.getMo_id() > 0) {
            int update = ruleService.updateMonitor(mr);
            if (update > 0) {
                return new IOTResult(true, "更新成功", null, 0);
            }
            return new IOTResult(false, "更新失败", null, 0);
        }
        if (mr.getMo_id() == 0) {
            int addRule = ruleService.addMonitor(mr);
            if (addRule > 0) {
                return new IOTResult(true, "新增成功", null, 0);
            }
            return new IOTResult(false, "新增失败", null, 0);
        }
        return new IOTResult(false, "请求失败", null, 0);
    }

    // 修改预约智能控制规则

    @CrossOrigin
    @Transactional(value = "primaryTransactionManager")
    @RequestMapping(value = "/addListMonitor", method = RequestMethod.POST)
    public IOTResult addListMonitor(@RequestBody MonitorRequest mr) {
        int ok = 0;
        int er = 0;
        MonitorEntity m_del = new MonitorEntity();
        m_del.setCtrl_id(mr.getCtrl_id());
        ruleService.deleteMonitor(m_del);

        List<MonitorEntity> mos = mr.getmList();
        if (mos != null && mos.size() > 0) {
            for (MonitorEntity m : mos) {
                /*if (m.getMo_id() > 0) {

                    int update = ruleService.updateMonitor(m);
                    if (update > 0)
                        ok++;
                    else er++;
                }
                if (m.getMo_id() == 0) {
                */
                int addRule = ruleService.addMonitor(m);
                if (addRule > 0)
                    ok++;
                else er++;
                /*}*/
            }
        }
        redisService.resetMonitor();
        return new IOTResult(true, "保存成功" + er, null, 0);

    }


    // 删除智能控制规则
    @CrossOrigin
    @RequestMapping(value = "/deleteMonitor", method = RequestMethod.POST)
    public IOTResult deleteMonitor(@RequestBody MonitorRequest mRequest) {
        int addRule = ruleService.deleteMonitor(mRequest);
        if (addRule > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 0);
    }

    // 产看数据库中的智能控制规则
    @CrossOrigin
    @RequestMapping(value = "/listMonitor", method = RequestMethod.POST)
    public IOTResult listMonitor(@RequestBody MonitorRequest mRequest) {
        List<MonitorEntity> listMonitor = ruleService.listMonitor(mRequest);
        if (listMonitor == null || listMonitor.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "查看成功", listMonitor, 0);
    }

    // 设置智能控制规则
    @CrossOrigin
    @RequestMapping(value = "/setMonitorList", method = RequestMethod.POST)
    public IOTResult setMonitorList(@RequestBody MonitorRequest moRequest) throws UnsupportedEncodingException {
        // 验证控制设备是否存在
        // 验证权限
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setCtrl_id(moRequest.getCtrl_id());

        int deleteRuleList2 = redisService.deleteRuleListByCtrl_id(ruleEntity);
        ControlEntity controlEntity = new ControlEntity();
        controlEntity.setCtrl_id(moRequest.getCtrl_id());
        controlEntity.setState_type(3);

        int updateControlSetting = settingService.updateControlSetting(controlEntity);

        if (moRequest.getType() == 1) {
            List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR + moRequest.getMo_deviceId());

            if (monitorList == null || monitorList.isEmpty()) {
                monitorList = new ArrayList<>();
                toolUtil.setMaxId(ToolUtil.MONITOR + moRequest.getMo_deviceId(), 0);
            }

            List<MonitorEntity> listMonitor = ruleService.listMonitor(moRequest);

            if (listMonitor == null || listMonitor.isEmpty()) {

                if (updateControlSetting > 0) {
                    return new IOTResult(true, "智能控制生效", null, 0);
                }
                return new IOTResult(false, "智能控制没有生效", null, 6);
            }
            boolean addAll = monitorList.addAll(listMonitor);
            if (addAll) {
                // controlEntity.setState_type(3);
                // settingService.updateControlSetting(controlEntity);
                toolUtil.setMonitorList(ToolUtil.MONITOR + listMonitor.get(0).getMo_deviceId(), monitorList);
                return new IOTResult(true, "智能控制生效", null, 0);
            }
            return new IOTResult(false, "智能控制没有生效", null, 6);
        }
        if (moRequest.getType() == 2) {
            int deleteRuleList = redisService.deleteMonitorList(moRequest);
            controlEntity.setState_type(0);
            settingService.updateControlSetting(controlEntity);
            if (deleteRuleList > 0) {
                return new IOTResult(true, "预约控制停止成功", null, 0);
            }
            return new IOTResult(false, "预约控制停止失败", null, 0);
        }
        return new IOTResult(false, "参数错误", null, 0);

    }



    public static void main(String[] args) {
        int result = -1;
        byte[] recData = Client.getMotorSensor2("111.53.182.34", 52400, "10.00.21.27");
        int posSensorSh = Integer.parseInt("2");
        String maxVal = "10";
        String minVal = "0";
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
    }
}
