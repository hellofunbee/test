/**  
*   
* 项目名称：IOT  
* 类名称：RedisService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年12月1日 上午11:21:51  
* 修改人：jianghu  
* 修改时间：2017年12月1日 上午11:21:51  
* 修改备注： 上午11:21:51
* @version   
*   
*/ 
package com.jingu.IOT.service;

import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.RedisDao;

/**

* @ClassName: RedisService
* @Description: TODO
* @author jianghu
* @date 2017年12月1日 上午11:21:51

*/
@Component
public class RedisService {

	private RedisDao redisDao;
	
	public int addKeyValue(String key,String value){
		return redisDao.addKeyValue(key, value);
	}
	
	public int updateKeyValue(String key,String value){
		return redisDao.addKeyValue(key, value);
	}
}
