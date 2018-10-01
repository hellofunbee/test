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
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    public void updateRuleList(RuleEntity re) throws UnsupportedEncodingException {
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

    /**
     * 按照 ctrl 删除
     *
     * @param re
     * @return
     * @throws UnsupportedEncodingException
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
     * @param re 删除一个
     * @throws UnsupportedEncodingException
     */
    public void deleRuleList(RuleEntity re) throws UnsupportedEncodingException {
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
     * 重置ruler list
     *
     * @throws UnsupportedEncodingException
     */
    public void resetRuleList() {
        RuleEntity re = new RuleEntity();
        re.setType(1);
        List<RuleEntity> ruleList = ruleService.listRule(re);
        toolUtil.setRuleList(ToolUtil.RULE, ruleList);
    }

    /**
     * 清空ruler list
     *
     * @throws UnsupportedEncodingException
     */
    public void clearRuleList() throws UnsupportedEncodingException {
        toolUtil.setRuleList(ToolUtil.RULE, null);
    }

    /**
     * 2017年11月26日
     *
     * @param moRequest
     * @return TODO
     * @throws UnsupportedEncodingException
     */
    public int deleteMonitorList(MonitorEntity moRequest) throws UnsupportedEncodingException {
        List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR + moRequest.getMo_deviceId());
        if (monitorList == null || monitorList.isEmpty()) {
            return 0;
        }
        List<MonitorEntity> collect = monitorList.stream().filter(x -> x.getCtrl_id() != moRequest.getCtrl_id()).collect(Collectors.toList());
        System.out.println(collect);
        toolUtil.setMonitorList(ToolUtil.MONITOR, collect);
        return 1;
    }

    public void resetMonitor() {
        List<MonitorEntity> listMonitor = ruleService.listMonitor(new MonitorEntity());
        toolUtil.setMonitorList(ToolUtil.MONITOR, listMonitor);

    }

    /**
     * 重置预警 redis
     */
    public void resetAlarmList() {
        AlarmRuleEntity ar = new AlarmRuleEntity();
        ar.setAla_state("1");
        List<Map<String,Object>> alarms =  alarmService.listAlarmRule(ar, 0, 0);
        toolUtil.setAlarmList(ToolUtil.ALARM ,alarms);
    }
}
