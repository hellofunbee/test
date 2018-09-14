/**  
*   
* 项目名称：IOT  
* 类名称：RoleDoa  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月13日 下午12:02:32  
* 修改人：jianghu  
* 修改时间：2017年10月13日 下午12:02:32  
* 修改备注： 下午12:02:32
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.RoleEntity;

/**

* @ClassName: RoleDoa
* @Description: TODO
* @author jianghu
* @date 2017年10月13日 下午12:02:32

*/
@Component
public class RoleDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public RoleDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	  
//	public int  addRole (RoleEntity re) {
//		
//	}
	
	public  List<Map<String, Object>> listRole(RoleEntity re) {
		String sql ="select * from role where r_name =?";
		return jdbcTemplate.queryForList(sql,re.getR_name());
	}
	
}
