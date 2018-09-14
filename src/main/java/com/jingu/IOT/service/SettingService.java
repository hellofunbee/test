/**  
*   
* 项目名称：IOT  
* 类名称：SettingService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月1日 下午3:32:07  
* 修改人：jianghu  
* 修改时间：2017年9月1日 下午3:32:07  
* 修改备注： 下午3:32:07
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.SettingDao;
import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.VAREntity;

/**

* @ClassName: SettingService
* @Description: TODO
* @author jianghu
* @date 2017年9月1日 下午3:32:07

*/
@Component
public class SettingService {

	private SettingDao settingDao;

	@Autowired
	public SettingService(SettingDao settingDao) {
		this.settingDao = settingDao;
	}
	public String getSettings(VAREntity var){
		return settingDao.getSettings(var);
	}
	public int createTable(String name) {
		return settingDao.creatTable(name);
	}
	public int addSettings(VAREntity var){
		return settingDao.addSettings(var);
		
	}
	public int updateSettings(VAREntity var){
		return settingDao.updateSettings(var);
	}

	public int deleteSettings(VAREntity var){
		return settingDao.deleteSettings(var);
	}
	public Map<String, Object> getPointSetting(PointEntity pe){
		return settingDao.getPointSetting(pe);
	}
//	
//	// 添加代理
//	public int addProxy(IPCProxyEntity pe){
//		return settingDao.addProxy(pe);
//	}
//	
//	// 修改代理
//	public int updateProxy(IPCProxyEntity pe){
//		return settingDao.updateProxy(pe);
//				
//	}
//	
//	// 代理列表
//	public List<Map<String, Object>> listProxy(IPCProxyEntity pe){
//		return settingDao.listIPCProxy(pe);
//				
//	}
//	// 获得代理
//	public Map<String,Object> getProxy(IPCProxyEntity pe){
//		List<Map<String,Object>> listIPCProxy = settingDao.listIPCProxy(pe);
//		if(listIPCProxy==null || listIPCProxy.size()<1){
//			return null;
//		}
//		return listIPCProxy.get(0);		
//	}
//	
//	// 查询代理
//	public List<Map<String, Object>> listIPCProxy(IPCProxyEntity pe){
//		return settingDao.listIPCProxy(pe);
//	}
	
	// 记得发送命令封装业务
	public int addControlSetting(ControlEntity ce){
		return settingDao.addControlSetting(ce);
	}
	public int deleteControlSetting(ControlEntity ce){
		return settingDao.deleteControlSetting(ce);
	}
	public int updateControlSetting(ControlEntity ce){
		return settingDao.updateControlSetting(ce);
	}
	public List<Map<String, Object>> listControlSetting(ControlEntity ce){
		return settingDao.listControlSetting(ce);
	}
	public Map<String, Object> getControlSetting(ControlEntity ce){
		List<Map<String, Object>> listControlSetting = settingDao.listControlSetting(ce);
		if(listControlSetting ==null || listControlSetting.isEmpty()){
			return null;
		}
		return listControlSetting.get(0);
	}
	
	public List<Map<String, Object>> listControlDevByDeviceId(PointEntity pe){
		return settingDao.listControlDevByDeviceId(pe);
	}
	/**
	 * 2017年10月20日
	 * jianghu
	 * @param pointEntity
	 * @return
	 * TODO
	 */
	/**
	 * 2018年1月18日
	 * jianghu
	 * @param ctrl_id
	 * @return
	 * TODO
	 */
	public Map<String, Object> getControlSetting(int ctrl_id) {
		// TODO Auto-generated method stub
		return settingDao.getControlSetting(ctrl_id);
	}
	
}

