/**  
*   
* 项目名称：road  
* 类名称：RedisDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月7日 上午11:36:44  
* 修改人：jianghu  
* 修改时间：2017年9月7日 上午11:36:44  
* 修改备注： 上午11:36:44
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**

* @ClassName: RedisDao
* @Description: TODO
* @author jianghu
* @date 2017年9月7日 上午11:36:44

*/
@Component
public class RedisDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public RedisDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public int addKeyValue(String key,String value){
		String sql = " insert into key_value (redis_value,redis_value) value (?,?)";
		return jdbcTemplate.update(sql,key,value);
	}
	public int updateKeyValue(String key,String value){
		String sql = "update key_value set redis_value =? where redis_value =?";
		return jdbcTemplate.update(sql,value,key);
	}
}
