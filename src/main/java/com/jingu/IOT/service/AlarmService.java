/**  
*   
* 项目名称：IOT  
* 类名称：AlarmService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午2:38:07  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午2:38:07  
* 修改备注： 下午2:38:07
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jingu.IOT.entity.AlarmRuleEntity;

/**

* @ClassName: AlarmService
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午2:38:07

*/
@Service
public interface AlarmService {

	// 新增规则
	public int addAlarmRule(AlarmRuleEntity ae);
	// 通过id删除规则
	public int deleteAlarmById(AlarmRuleEntity ae);
	
	// 通过id批量删除规则
	public int deleteAlarmByIdString(String idString);
	
	// 通过deviceId删除规则
	public int deleteAlarmByDeviceId(String deviceId);
	// 批量添加规则
	public int addAlarmRuleList(List<AlarmRuleEntity> list);
	// 修改规则
	public int updateAlarmRule(AlarmRuleEntity ae);
	// 查看规则列表
	public List<Map<String, Object>> listAlarmRule(AlarmRuleEntity ae,Integer pageNum,Integer pageSize);
	// 通过输入的字段的值获取输出字段的值
	/**
	 * 
	 * 2018年4月13日
	 * jianghu
	 * @param outFiled 	输出字段
	 * @param inField	输入字段
	 * @param value		输入字段值
	 * @return			输出字段值
	 * TODO
	 */
	public String getFiledByFieldValue(String outFiled,String inField,String value);
	// 通过输入字段获取某个实体
	public Map<String, Object> getFieldByValue(String inField,String value);
}
