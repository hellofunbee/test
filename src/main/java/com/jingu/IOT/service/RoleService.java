/**  
*   
* 项目名称：IOT  
* 类名称：RoleService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月13日 下午1:15:51  
* 修改人：jianghu  
* 修改时间：2017年10月13日 下午1:15:51  
* 修改备注： 下午1:15:51
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.RoleDao;
import com.jingu.IOT.entity.RoleEntity;

/**

* @ClassName: RoleService
* @Description: TODO
* @author jianghu
* @date 2017年10月13日 下午1:15:51

*/
@Component
public class RoleService {

	private RoleDao roleDao;

	@Autowired
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public  List<Map<String, Object>> listRole(RoleEntity re) {
		return roleDao.listRole(re);
	}
	
	public  Map<String, Object> getRole(RoleEntity re) {
		List<Map<String,Object>> listRole = roleDao.listRole(re);
		if(listRole ==null || listRole.isEmpty()){
			return null;
		}
		return listRole.get(0);
	}
	
}
