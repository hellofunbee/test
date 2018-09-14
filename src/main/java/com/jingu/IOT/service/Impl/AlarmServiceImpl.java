/**  
*   
* 项目名称：IOT  
* 类名称：AlarmServiceImpl  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午2:47:00  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午2:47:00  
* 修改备注： 下午2:47:00
* @version   
*   
*/ 
package com.jingu.IOT.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.AlarmDao;
import com.jingu.IOT.entity.AlarmRuleEntity;
import com.jingu.IOT.service.AlarmService;

/**

* @ClassName: AlarmServiceImpl
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午2:47:00

*/
@Component
public class AlarmServiceImpl implements AlarmService {

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#addAlarmRule(com.jingu.IOT.entity.AlarmRuleEntity)
	 */
	private AlarmDao alarmDao;
	
	
	@Autowired
	public AlarmServiceImpl(AlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	@Override
	public int addAlarmRule(AlarmRuleEntity ae) {
		// TODO Auto-generated method stub
		return alarmDao.addAlarmRule(ae);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#deleteAlarmById(com.jingu.IOT.entity.AlarmRuleEntity)
	 */
	@Override
	public int deleteAlarmById(AlarmRuleEntity ae) {
		// TODO Auto-generated method stub
		return alarmDao.deleteAlarmById(ae);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#deleteAlarmByDeviceId(com.jingu.IOT.entity.AlarmRuleEntity)
	 */
	@Override
	public int deleteAlarmByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		return alarmDao.deleteAlarmByDeviceId(deviceId);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#addAlarmRuleList(java.util.List)
	 */
	@Override
	public int addAlarmRuleList(List<AlarmRuleEntity> list) {
		// TODO Auto-generated method stub
		return alarmDao.addAlarmRuleList(list);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#updateAlarmRule(com.jingu.IOT.entity.AlarmRuleEntity)
	 */
	@Override
	public int updateAlarmRule(AlarmRuleEntity ae) {
		// TODO Auto-generated method stub
		return alarmDao.updateAlarmRule(ae);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#listAlarmRule(com.jingu.IOT.entity.AlarmRuleEntity)
	 */
	@Override
	public List<Map<String, Object>> listAlarmRule(AlarmRuleEntity ae,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		return alarmDao.listAlarmRule(ae,pageNum,pageSize);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#getFiledByFieldValue(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getFiledByFieldValue(String outFiled, String inField, String value) {
		// TODO Auto-generated method stub
		return alarmDao.getFiledByFieldValue(outFiled, inField, value);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#getFieldValue(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getFieldByValue(String inField, String value) {
		// TODO Auto-generated method stub
		return alarmDao.getFieldByValue(inField, value);
	}

	/* (non-Javadoc)
	 * @see com.jingu.IOT.service.AlarmService#deleteAlarmByIdString(java.lang.String)
	 */
	@Override
	public int deleteAlarmByIdString(String idString) {
		// TODO Auto-generated method stub
		return alarmDao.deleteAlarmByIdString(idString);
	}

}
