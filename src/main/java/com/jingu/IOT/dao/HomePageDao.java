/**  
*   
* 项目名称：sxcms  
* 类名称：HomePageDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月19日 下午5:06:30  
* 修改人：jianghu  
* 修改时间：2017年10月19日 下午5:06:30  
* 修改备注： 下午5:06:30
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.HomePageEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

* @ClassName: HomePageDao
* @Description: TODO
* @author jianghu
* @date 2017年10月19日 下午5:06:30

*/
@Component
public class HomePageDao {
	
	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public int addHomePagePicture(HomePageEntity he){
		String sql = " insert into homepage (h_name,h_url,h_cover,h_type,h_state) value (?,?,?,?,?)";
		return jdbcTemplate.update(sql,he.getH_name(),he.getH_url(),he.getH_cover(),he.getH_type(),he.getH_state());
	}
	public int deleteHomePagePicture(HomePageEntity he){
		String sql = " delete from homepage where h_id =?";
		return jdbcTemplate.update(sql,he.getH_id());
	}
	
	public int updateHomePagePicture(HomePageEntity he){
		String sql = " update homepage set h_id =?";
		List<Object> list = new ArrayList<>();
		list.add(he.getH_id());
		if(he.getH_cover()!=null ){
			sql += " , h_cover = ? ";
			list.add(he.getH_cover());
		}
		if(he.getH_name()!=null && he.getH_name().trim().length()>0){
			sql += " , h_name = ? ";
			list.add(he.getH_name());
		}
		if(he.getH_url()!=null ){
			sql += " , h_url = ? ";
			list.add(he.getH_url());
		}
		if(he.getH_state() >0){
			sql += " , h_state = ? ";
			list.add(he.getH_state());
		}
		if(he.getH_type() >0){
			sql += " , h_type = ? ";
			list.add(he.getH_type());
		}
		if(list.size()==1){
			return 0;
		}
		sql += " where h_id =? ";
		list.add(he.getH_id());
		return jdbcTemplate.update(sql,list.toArray());
	}
	public List<Map<String, Object>> listHomePagePicture(HomePageEntity he){
		String sql = " select * from  homepage where 1=1 ";
		List<Object> list = new ArrayList<>();
		if(he.getH_cover()!=null && he.getH_cover().trim().length()>0){
			sql += " and h_cover = ? ";
			list.add(he.getH_cover());
		}
		if(he.getH_name()!=null && he.getH_name().trim().length()>0){
			sql += " and h_name = ? ";
			list.add(he.getH_name());
		}
		if(he.getH_url()!=null && he.getH_url().trim().length()>0){
			sql += " and h_url = ? ";
			list.add(he.getH_url());
		}
		if(he.getH_state() >0){
			sql += " and h_state = ? ";
			list.add(he.getH_state());
		}
		if(he.getH_type() >0){
			sql += " and h_type = ? ";
			list.add(he.getH_type());
		}
		if(he.getH_id() >0){
			sql += " where h_id =? ";
			list.add(he.getH_id());
		}
		sql+=" order by update_time desc";
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	/**
	 * 2017年12月6日
	 * jianghu
	 * @param c_id
	 * @return
	 * TODO
	 */
	public int ckClass(int c_id) {
		// TODO Auto-generated method stub
		String sql ="select count(*) from homepage where";
		return 0;
	}
}
