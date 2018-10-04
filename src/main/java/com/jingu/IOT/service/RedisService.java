/**
 * 项目名称：IOT
 * 类名称：RedisService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年12月1日 上午11:21:51
 * 修改人：jianghu
 * 修改时间：2017年12月1日 上午11:21:51
 * 修改备注： 上午11:21:51
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.RedisDao;
import com.jingu.IOT.entity.AlarmRuleEntity;
import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.RuleEntity;
import com.jingu.IOT.requset.ControlRequset;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianghu
 * @ClassName: RedisService
 * @Description: TODO
 * @date 2017年12月1日 上午11:21:51
 */
@Component
public class RedisService {

    @Autowired
    ToolUtil toolUtil;

    @Autowired
    RedisDao redisDao;

    @Autowired
    RuleService ruleService;

    @Autowired
    AlarmService alarmService;


    public int addKeyValue(String key, String value) {
        return redisDao.addKeyValue(key, value);
    }

    public int updateKeyValue(String key, String value) {
        return redisDao.addKeyValue(key, value);
    }

    /**
     * @param re 添加一个
     * @throws UnsupportedEncodingException
     */
    public void updateRuleListBy(RuleEntity re) throws UnsupportedEncodingException {
        if (re == null || re.getR_id() == 0) {
            return;
        }
        List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
        Iterator<RuleEntity> iterator = ruleList.iterator();
        while (iterator.hasNext()) {
            RuleEntity next = iterator.next();
            if (next.getR_id() == re.getR_id()) {
                iterator.remove();
            }
        }
        ruleList.add(re);
        toolUtil.setRuleList(ToolUtil.RULE, ruleList);
    }

    public void addMonitorByCtrlId(MonitorEntity rule) throws UnsupportedEncodingException {
        MonitorEntity mo = new MonitorEntity();
        mo.setMo_state(1);
        mo.setCtrl_id(rule.getCtrl_id());
        deleteMonitorList(rule);
        List<MonitorEntity> listMonitor = ruleService.resetMonitor(mo);
        List<MonitorEntity> mos = toolUtil.getMonitorList(ToolUtil.MONITOR);

        if (mos == null) mos = new ArrayList<>();
        mos.addAll(listMonitor);

        toolUtil.setMonitorList(ToolUtil.MONITOR, mos);
    }

    public void addRuleByCtrlId(RuleEntity rule) throws UnsupportedEncodingException {

        rule.setRuleEnable("1");

        List<RuleEntity> listMonitor = ruleService.resetRule(rule);
        List<RuleEntity> rules = toolUtil.getRuleList(ToolUtil.RULE);

        if (rules == null) rules = new ArrayList<>();
        rules.addAll(listMonitor);

        toolUtil.setRuleList(ToolUtil.RULE, rules);
    }


    /**
     * @param re 删除一个
     */
    public void deleRuleByRuleId(RuleEntity re) throws UnsupportedEncodingException {
        if (re == null || re.getR_id() == 0) {
            return;
        }
        List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
        Iterator<RuleEntity> iterator = ruleList.iterator();
        while (iterator.hasNext()) {
            RuleEntity next = iterator.next();
            if (next.getR_id() == re.getR_id()) {
                iterator.remove();
            }
        }
        toolUtil.setRuleList(ToolUtil.RULE, ruleList);
    }


    /**
     * 清空ruler list
     */
    public void clearRuleList() {
        toolUtil.setRuleList(ToolUtil.RULE, null);
    }

    /**
     * 2017年11月26日
     * 根据ctrl_id 删除智能控制
     *
     * @param moRequest
     * @return TODO
     */
    public int deleteMonitorList(MonitorEntity moRequest) throws UnsupportedEncodingException {
        List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR);
        if (monitorList == null || monitorList.isEmpty()) {
            return 0;
        }
        List<MonitorEntity> collect = monitorList.stream().filter(x -> x.getCtrl_id() != moRequest.getCtrl_id()).collect(Collectors.toList());
        System.out.println(collect);
        toolUtil.setMonitorList(ToolUtil.MONITOR, collect);
        return 1;
    }

    /**
     * 按照 ctrl 删除
     *
     * @param re
     * @return
     */
    public int deleteRuleListByCtrl_id(RuleEntity re) throws UnsupportedEncodingException {
        if (re == null || re.getCtrl_id() < 1)
            return 0;
        List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
        if (ruleList == null || ruleList.isEmpty()) {
            return 1;
        }
        List<RuleEntity> collect = ruleList.stream().filter(x -> x.getCtrl_id() != re.getCtrl_id()).collect(Collectors.toList());
        System.out.println(collect);
        toolUtil.setRuleList(ToolUtil.RULE, collect);
        return 2;
    }


    /**
     * 智能控制
     */
    public void resetMonitor() {
        MonitorEntity mo = new MonitorEntity();
        mo.setMo_state(1);
        List<MonitorEntity> listMonitor = ruleService.resetMonitor(mo);
        toolUtil.setMonitorList(ToolUtil.MONITOR, listMonitor);

    }

    /**
     * 重置ruler list
     */
    public void resetRuleList() {
        RuleEntity re = new RuleEntity();
        re.setType(1);
        re.setRuleEnable("1");

        List<RuleEntity> ruleList = ruleService.resetRule(re);
        //校验规则

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat dfDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String format = dfDateFormat.format(time);

        List<RuleEntity> collect = ruleList.stream()
                .filter(x -> (x.getBeginTime().substring(0, 10) + " " + x.getExecTime()).compareTo(format) >= 0)
                .collect(Collectors.toList());

        if (collect == null || collect.isEmpty()) {
            System.out.println("规则无效");
        }

        toolUtil.setRuleList(ToolUtil.RULE, ruleList);
    }

    /**
     * 重置预警 redis
     */
    public void resetAlarmList() {
        AlarmRuleEntity ar = new AlarmRuleEntity();
        ar.setAla_state("1");
        List<Map<String, Object>> alarms = alarmService.listAlarmRule(ar, 0, 0);
        toolUtil.setAlarmList(ToolUtil.ALARM, alarms);
    }

    public void updateCtrl(ControlRequset cr) throws UnsupportedEncodingException {
        if (cr.getCtrl_id() <= 0) {
            return;
        }
        //monitor
        if (cr.getState_type() == 3) {
            //删除预约控制
            RuleEntity r = new RuleEntity();
            r.setCtrl_id(cr.getCtrl_id());
            deleteRuleListByCtrl_id(r);
            //添加智能控制
            MonitorEntity m = new MonitorEntity();
            m.setCtrl_id(cr.getCtrl_id());
            deleteMonitorList(m);
            addMonitorByCtrlId(m);


        }

        if (cr.getState_type() == 2) {
            //删除智能控制
            MonitorEntity m = new MonitorEntity();
            m.setCtrl_id(cr.getCtrl_id());
            deleteMonitorList(m);

            //添加预约控制

            RuleEntity r = new RuleEntity();
            r.setCtrl_id(cr.getCtrl_id());


            deleteRuleListByCtrl_id(r);
            addRuleByCtrlId(r);
        }

    }
}
