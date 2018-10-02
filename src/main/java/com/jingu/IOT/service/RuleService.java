/**
 * 项目名称：IOT
 * 类名称：RuleService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月24日 下午4:55:27
 * 修改人：jianghu
 * 修改时间：2017年10月24日 下午4:55:27
 * 修改备注： 下午4:55:27
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.RuleDao;
import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.RuleEntity;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author jianghu
 * @ClassName: RuleService
 * @Description: TODO
 * @date 2017年10月24日 下午4:55:27
 */
@Component
public class RuleService {

    private RuleDao ruleDao;
    private ToolUtil toolUtil;


    @Autowired
    public RuleService(RuleDao ruleDao, ToolUtil toolUtil) {
        this.ruleDao = ruleDao;
        this.toolUtil = toolUtil;
    }

    public int addRule(RuleEntity re) {


        return ruleDao.addRule(re);
    }

    public int deleteRule(RuleEntity re) {
        return ruleDao.deleteRule(re);
    }

    public List<RuleEntity> listRule(RuleEntity re) {
        return ruleDao.listRule(re);
    }

    public int updateRule(RuleEntity re) {
        return ruleDao.updateRule(re);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat dfDateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = dfDateFormat.format(time);
        String substring = format.substring(0, 5);
        System.out.println(substring);
    }

    public int deleteRuleIds(String ids) {
        // TODO Auto-generated method stub
        return ruleDao.deleteRuleIds(ids);
    }

    public int addMonitor(MonitorEntity mo) {
        return ruleDao.addMonitor(mo);
    }

    public int deleteMonitor(MonitorEntity mo) {
        return ruleDao.deleteMonitor(mo);
    }

    public int deleteByCtrlId(MonitorEntity mo) {
        return ruleDao.deleteByCtrlId(mo);
    }

    public int updateMonitor(MonitorEntity mo) {
        return ruleDao.updateMonitor(mo);
    }

    public List<MonitorEntity> listMonitor(MonitorEntity mo) {
        return ruleDao.listMonitor(mo);
    }

    public List<MonitorEntity> resetMonitor(MonitorEntity mo) {
        return ruleDao.resetMonitor(mo);
    }

    public List<RuleEntity> resetRule(RuleEntity mo) {
        return ruleDao.resetRule(mo);
    }
}
