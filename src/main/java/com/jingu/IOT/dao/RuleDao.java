/**  
*   
* 项目名称：IOT  
* 类名称：RuleDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月24日 上午11:12:43  
* 修改人：jianghu  
* 修改时间：2017年10月24日 上午11:12:43  
* 修改备注： 上午11:12:43
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.RuleEntity;


/**

* @ClassName: RuleDao
* @Description: TODO
* @author jianghu
* @date 2017年10月24日 上午11:12:43

*/
@Component
public class RuleDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public RuleDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	
	public int addRule(RuleEntity re){
		String sql = " insert into t_varstriver_switchctrl_rule (id,name,deviceId,switchGroupId,switchId,ctrlType,cycleDay,execTime,beginTime,endTime,targetDeviceId,targetFieldName,`maxValue`,minValue,duration,coefficient,ruleEnable,lastExecDataTime,type,ctrl_id,execEndTime) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,RIGHT(from_unixtime(UNIX_TIMESTAMP(?)+?),8))";
		return jdbcTemplate.update(sql,re.getR_id(),re.getR_name(),re.getR_deviceId(),re.getSwitchGroupId(),re.getSwitchId(),re.getCtrlType(),re.getCycleDay(),re.getExecTime(),re.getBeginTime(),re.getEndTime(),re.getTargetDeviceId(),re.getTargetFieldName(),re.getMaxValue(),re.getMinValue(),re.getDuration(),re.getCoefficient(),re.getRuleEnable(),re.getLastExecDataTime(),re.getType(),re.getCtrl_id(),re.getTime(),re.getDuration());
		
	}
	public int deleteRule(RuleEntity re){
		String sql = " delete from t_varstriver_switchctrl_rule where id = ?";
		return jdbcTemplate.update(sql,re.getR_id());
		
	}
	public int updateRule(RuleEntity re){
		String sql = " update t_varstriver_switchctrl_rule set id=? ";
		List<Object> list = new ArrayList<>();
		list.add(re.getR_id());
		if(re.getBeginTime()!=null && re.getBeginTime().trim().length()>0){
			sql += " , beginTime =?";
			list.add(re.getBeginTime());
		}
		if(re.getCtrlType()!=null && re.getCtrlType().trim().length()>0){
			sql += " , ctrlType =?";
			list.add(re.getCtrlType());
		}
		if(re.getCycleDay()!=null && re.getCycleDay().trim().length()>0){
			sql += " , cycleDay =?";
			list.add(re.getCycleDay());
		}
		if(re.getEndTime()!=null && re.getEndTime().trim().length()>0){
			sql += " , endTime =?";
			list.add(re.getEndTime());
		}
		if(re.getExecTime()!=null && re.getExecTime().trim().length()>0){
			sql += " , execTime =?";
			list.add(re.getExecTime());
		}
		if(re.getLastExecDataTime()!=null && re.getLastExecDataTime().trim().length()>0){
			sql += " , lastExecDataTime =?";
			list.add(re.getLastExecDataTime());
		}
		if(re.getR_name()!=null && re.getR_name().trim().length()>0){
			sql += " , name =?";
			list.add(re.getR_name());
		}
		if(re.getRuleEnable()!=null && re.getRuleEnable().trim().length()>0){
			sql += " , ruleEnable =?";
			list.add(re.getRuleEnable());
		}
		if(re.getSwitchGroupId()!=null && re.getSwitchGroupId().trim().length()>0){
			sql += " , switchGroupId =?";
			list.add(re.getSwitchGroupId());
		}
		if(re.getSwitchId()!=null && re.getSwitchId().trim().length()>0){
			sql += " , switchId =?";
			list.add(re.getSwitchId());
		}
		if(re.getTargetDeviceId()!=null && re.getTargetDeviceId().trim().length()>0){
			sql += " , targetDeviceId =?";
			list.add(re.getTargetDeviceId());
		}
		if(re.getTargetFieldName()!=null && re.getTargetFieldName().trim().length()>0){
			sql += " , targetFieldName =?";
			list.add(re.getTargetFieldName());
		}
		if(re.getDuration()>0){
			sql += " , duration =?";
			list.add(re.getDuration());
		}
		if(re.getTime()!=null && re.getTime().trim().length()>0){
			sql += " , execEndTime = RIGHT(from_unixtime(UNIX_TIMESTAMP(?)+?),8)";
			list.add(re.getTime());
			list.add(re.getDuration());
		}
		if(list.size() ==1){
			return 0;
		}
		sql += " where id = ?";
		list.add(re.getR_id());
		return jdbcTemplate.update(sql,list.toArray());
		
	}
	public List<RuleEntity> listRule(RuleEntity re){
		String sql = " select * from  t_varstriver_switchctrl_rule where 1=1 ";
		List<Object> list = new ArrayList<>();
		if(re.getBeginTime()!=null && re.getBeginTime().trim().length()>0){
			sql += " and beginTime =?";
			list.add(re.getBeginTime());
		}
		if(re.getCtrlType()!=null && re.getCtrlType().trim().length()>0){
			sql += " and ctrlType =?";
			list.add(re.getCtrlType());
		}
		if(re.getCycleDay()!=null && re.getCycleDay().trim().length()>0){
			sql += " and cycleDay =?";
			list.add(re.getCycleDay());
		}
		if(re.getEndTime()!=null && re.getEndTime().trim().length()>0){
			sql += " and endTime =?";
			list.add(re.getEndTime());
		}
		if(re.getExecTime()!=null && re.getExecTime().trim().length()>0){
			sql += " and execTime =?";
			list.add(re.getExecTime());
		}
		if(re.getLastExecDataTime()!=null && re.getLastExecDataTime().trim().length()>0){
			sql += " and lastExecDataTime =?";
			list.add(re.getLastExecDataTime());
		}
		if(re.getR_name()!=null && re.getR_name().trim().length()>0){
			sql += " and name =?";
			list.add(re.getR_name());
		}
		if(re.getRuleEnable()!=null && re.getRuleEnable().trim().length()>0){
			sql += " and ruleEnable =?";
			list.add(re.getRuleEnable());
		}
		if(re.getSwitchGroupId()!=null && re.getSwitchGroupId().trim().length()>0){
			sql += " and switchGroupId =?";
			list.add(re.getSwitchGroupId());
		}
		if(re.getSwitchId()!=null && re.getSwitchId().trim().length()>0){
			sql += " and switchId =?";
			list.add(re.getSwitchId());
		}
		if(re.getTargetDeviceId()!=null && re.getTargetDeviceId().trim().length()>0){
			sql += " and targetDeviceId =?";
			list.add(re.getTargetDeviceId());
		}
		if(re.getTargetFieldName()!=null && re.getTargetFieldName().trim().length()>0){
			sql += " and targetFieldName =?";
			list.add(re.getTargetFieldName());
		}
		if(re.getCtrl_id()>0){
			sql += " and ctrl_id =?";
			list.add(re.getCtrl_id());
		}
		if(re.getTime()!=null && re.getTime().trim().length()>0){
			sql += " , execEndTime = RIGHT(from_unixtime(UNIX_TIMESTAMP(?)+?),8)";
			list.add(re.getTime());
			list.add(re.getDuration());
		}
		if(re.getR_id() >0){
			sql += " and id = ?";
			list.add(re.getR_id());
		}
		return jdbcTemplate.query(sql,list.toArray(),new RuleEntity());
				
	}
	/**
	 * 2017年11月9日
	 * jianghu
	 * @param ids
	 * @return
	 * TODO
	 */
	public int deleteRuleIds(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from t_varstriver_switchctrl_rule where id ";
		String s = "";
		String[] split = ids.split(",");
		if(split.length>1){
			for (String string : split) {
				s += ","+string; 
			}
			String substring = s.substring(1);
			sql = sql +"in ("+substring+")";
		}else{
			sql +="= "+ids;
		}
		
		return jdbcTemplate.update(sql);
	}
	
	
	public int addMonitor(MonitorEntity mo) {
		String sql = " insert into t_monitor (mo_name,mo_deviceId,mo_time,mo_channel,mo_type,mo_state,mo_high,mo_lower,ctrl_id) value(?,?,UNIX_TIMESTAMP(),?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,mo.getMo_name(),mo.getMo_deviceId(),mo.getMo_channel(),mo.getMo_type(),mo.getMo_state(),mo.getMo_high(),mo.getMo_lower(),mo.getCtrl_id());
	}
	
	
	public int deleteMonitor(MonitorEntity mo) {
		String sql = " delete from t_monitor where mo_id =?";
		return jdbcTemplate.update(sql,mo.getMo_id());
	}
	
	public int updateMonitor(MonitorEntity mo) {
		String sql = " update t_monitor set mo_id =? ";
		List<Object> list = new ArrayList<>();
		list.add(mo.getMo_id());
		if(mo.getMo_name()!=null && mo.getMo_name().trim().length()>0){
			sql +=" , mo_name =? ";
			list.add(mo.getMo_name());
		}
		if(mo.getCtrl_id()>0){
			sql +=" , ctrl_id =? ";
			list.add(mo.getCtrl_id());
		}
		if(mo.getMo_channel()!=null && mo.getMo_channel().trim().length()>0){
			sql +=" , mo_channel =? ";
			list.add(mo.getMo_channel());
		}
		if(mo.getMo_deviceId()!=null && mo.getMo_deviceId().trim().length()>0){
			sql +=" , mo_deviceId =? ";
			list.add(mo.getMo_deviceId());
		}
		if(mo.getMo_high()>0){
			sql +=" , mo_high =? ";
			list.add(mo.getMo_high());
		}
		if(mo.getMo_lower()>0){
			sql +=" , mo_lower =? ";
			list.add(mo.getMo_lower());
		}
		if(mo.getMo_state()>0){
			sql +=" , mo_state =? ";
			list.add(mo.getMo_state());
		}
		if(mo.getMo_time()!=null && mo.getMo_time().trim().length()>0){
			sql +=" , mo_time =? ";
			list.add(mo.getMo_time());
		}
		if(mo.getMo_type()>0){
			sql +=" , mo_type =? ";
			list.add(mo.getMo_type());
		}
		if(mo.getMo_lower()>0){
			sql +=" , mo_lower =? ";
			list.add(mo.getMo_lower());
		}
		sql +=" where mo_id =?";
		list.add(mo.getMo_id());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	
	public List<MonitorEntity> listMonitor(MonitorEntity mo) {
		String sql = " select * from  t_monitor  where 1=1 ";
		List<Object> list = new ArrayList<>();
		if(mo.getMo_name()!=null && mo.getMo_name().trim().length()>0){
			sql +=" and mo_name =? ";
			list.add(mo.getMo_name());
		}
		if(mo.getCtrl_id()>0){
			sql +=" and ctrl_id =? ";
			list.add(mo.getCtrl_id());
		}
		if(mo.getMo_channel()!=null && mo.getMo_channel().trim().length()>0){
			sql +=" and mo_channel =? ";
			list.add(mo.getMo_channel());
		}
		if(mo.getMo_deviceId()!=null && mo.getMo_deviceId().trim().length()>0){
			sql +=" and mo_deviceId =? ";
			list.add(mo.getMo_deviceId());
		}
		if(mo.getMo_high()>0){
			sql +=" and mo_high =? ";
			list.add(mo.getMo_high());
		}
		if(mo.getMo_lower()>0){
			sql +=" and mo_lower =? ";
			list.add(mo.getMo_lower());
		}
		if(mo.getMo_state()>0){
			sql +=" and mo_state =? ";
			list.add(mo.getMo_state());
		}
		if(mo.getMo_time()!=null && mo.getMo_time().trim().length()>0){
			sql +=" and getMo_time =? ";
			list.add(mo.getMo_time());
		}
		if(mo.getMo_type()>0){
			sql +=" and getMo_type =? ";
			list.add(mo.getMo_type());
		}
		if(mo.getMo_lower()>0){
			sql +=" and mo_lower =? ";
			list.add(mo.getMo_lower());
		}
		return jdbcTemplate.query(sql,new MonitorEntity(),list.toArray());
	}
	
	
}
