/**  
*   
* 项目名称：IOT  
* 类名称：ProduceDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午4:08:52  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午4:08:52  
* 修改备注： 下午4:08:52
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.ProduceEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

* @ClassName: ProduceDao

* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午4:08:52

*/
@Component
public class ProduceDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public ProduceDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public int addProducePlan(ProduceEntity pe){
		String sql =" insert into produce(p_name,p_type,p_state,p_class1,p_class2,p_standrad,p_begintime,p_endtime,p_harvesttime,p_harvestarea,p_ownername,tp_id) value(?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,pe.getP_name(),pe.getP_type(),pe.getP_state(),pe.getP_class1(),pe.getP_class2(),pe.getP_standrad(),pe.getP_begintime(),pe.getP_endtime(),pe.getP_harvesttime(),pe.getP_harvestarea(),pe.getP_ownername(),pe.getTp_id());
	}
	
	public int deleteProducePlan(ProduceEntity pe){
		String sql =" delete from produce where p_id =?";
		return jdbcTemplate.update(sql,pe.getP_id());
	}
	
	public int updateProducePlan(ProduceEntity pe){
		String sql ="update produce set p_id =?";
		ArrayList<Object> list = new ArrayList<>();
		list.add(pe.getP_id());
		if(pe.getP_class1()>0){
			sql +=" , p_class1 =?";
			list.add(pe.getP_class1());
		}
		if(pe.getP_class2()>0){
			sql +=" , p_class2 =?";
			list.add(pe.getP_class2());
		}
		if(pe.getP_type()>0){
			sql +=" , p_type =?";
			list.add(pe.getP_type());
		}
		if(pe.getP_state()>0){
			sql +=" , p_state =?";
			list.add(pe.getP_state());
		}
		if(pe.getP_name()!=null && pe.getP_name().trim().length()>0){
			sql +=" , p_name =?";
			list.add(pe.getP_name());
		}
		if(pe.getP_begintime()!=null && pe.getP_begintime().trim().length()>0){
			sql +=" , p_begintime =?";
			list.add(pe.getP_begintime());
		}
		if(pe.getP_endtime()!=null && pe.getP_endtime().trim().length()>0){
			sql +=" , p_endtime =?";
			list.add(pe.getP_endtime());
		}
		if(pe.getP_harvesttime()!=null && pe.getP_harvesttime().trim().length()>0){
			sql +=" , p_harvesttime =?";
			list.add(pe.getP_harvesttime());
		}
		if(pe.getP_standrad()!=null && pe.getP_standrad().trim().length()>0){
			sql +=" , p_standrad =?";
			list.add(pe.getP_standrad());
		}
		if(pe.getP_ownername()!=null && pe.getP_ownername().trim().length()>0){
			sql +=" , p_ownername =?";
			list.add(pe.getP_ownername());
		}
		if(pe.getP_harvestarea()!=null && pe.getP_harvestarea().trim().length()>0){
			sql +=" , p_harvestarea =?";
			list.add(pe.getP_harvestarea());
		}
		sql += " where p_id =?";
		list.add(pe.getP_id());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	
	public List<Map<String, Object>> listProducePlan(ProduceEntity pe){
		String sql ="select p.*,c1.c_name c1_name,c2.c_name c2_name from  produce p " +
				"left join class c1 on c1.c_id =p.p_class1 " +
				"left join class c2 on c2.c_id = p.p_class2 where 1=1 ";
		ArrayList<Object> list = new ArrayList<>();
		if(pe.getP_class1()>0){
			sql +=" and p.p_class1 =?";
			list.add(pe.getP_class1());
		}
		if(pe.getP_class2()>0){
			sql +=" and p.p_class2 =?";
			list.add(pe.getP_class2());
		}
		if(pe.getP_type()>0){
			sql +=" and p.p_type =?";
			list.add(pe.getP_type());
		}
		if(pe.getP_state()>0){
			sql +=" and p.p_state =?";
			list.add(pe.getP_state());
		}
		if(pe.getP_name()!=null && pe.getP_name().trim().length()>0){
			sql += " and  p.p_name like '%" + pe.getP_name() + "%'";
		}
		if(pe.getP_begintime()!=null && pe.getP_begintime().trim().length()>0){
			sql +=" and p.p_begintime >= ?";
			list.add(pe.getP_begintime());
		}
		if(pe.getP_endtime()!=null && pe.getP_endtime().trim().length()>0){
			sql +=" and p.p_endtime <= ?";
			list.add(pe.getP_endtime());
		}
		if(pe.getP_harvesttime()!=null && pe.getP_harvesttime().trim().length()>0){
			sql +=" and p.p_harvesttime =?";
			list.add(pe.getP_harvesttime());
		}
		if(pe.getP_standrad()!=null && pe.getP_standrad().trim().length()>0){
			sql +=" and p.p_standrad =?";
			list.add(pe.getP_standrad());
		}
		if(pe.getP_ownername()!=null && pe.getP_ownername().trim().length()>0){
			sql +=" and p.p_ownername =?";
			list.add(pe.getP_ownername());
		}
		if(pe.getTp_id()>0){
			sql += " and p.tp_id =?";
			list.add(pe.getTp_id());
		}
		if(pe.getP_id()>0){
			sql += " and p.p_id =?";
			list.add(pe.getP_id());
		}
		if(pe.getStart()>0){
			if(pe.getApp()==1){
				sql +=" limit "+(pe.getStart()-1)*pe.getAppPagesize()+","+pe.getAppPagesize();
			}else{
				sql +=" limit "+(pe.getStart()-1)*pe.getPagesize()+","+pe.getPagesize();
			}
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	
	public int listProducePlanCount(ProduceEntity pe){
		String sql ="select count(*) from  produce p " +
				"left join class c1 on c1.c_id =p.p_class1 " +
				"left join class c2 on c2.c_id = p.p_class2 where 1=1 ";
		ArrayList<Object> list = new ArrayList<>();
		if(pe.getP_class1()>0){
			sql +=" and p.p_class1 =?";
			list.add(pe.getP_class1());
		}
		if(pe.getP_class2()>0){
			sql +=" and p.p_class2 =?";
			list.add(pe.getP_class2());
		}
		if(pe.getP_type()>0){
			sql +=" and p.p_type =?";
			list.add(pe.getP_type());
		}
		if(pe.getP_state()>0){
			sql +=" and p.p_state =?";
			list.add(pe.getP_state());
		}
		if(pe.getP_name()!=null && pe.getP_name().trim().length()>0){
			sql += " and  p.p_name like '%" + pe.getP_name() + "%'";
		}
		if(pe.getP_begintime()!=null && pe.getP_begintime().trim().length()>0){
			sql +=" and p.p_begintime >= ?";
			list.add(pe.getP_begintime());
		}
		if(pe.getP_endtime()!=null && pe.getP_endtime().trim().length()>0){
			sql +=" and p.p_endtime <= ?";
			list.add(pe.getP_endtime());
		}
		if(pe.getP_harvesttime()!=null && pe.getP_harvesttime().trim().length()>0){
			sql +=" and p.p_harvesttime =?";
			list.add(pe.getP_harvesttime());
		}
		if(pe.getP_standrad()!=null && pe.getP_standrad().trim().length()>0){
			sql +=" and p.p_standrad =?";
			list.add(pe.getP_standrad());
		}
		if(pe.getP_ownername()!=null && pe.getP_ownername().trim().length()>0){
			sql +=" and p.p_ownername =?";
			list.add(pe.getP_ownername());
		}
		if(pe.getTp_id()>0){
			sql += " and p.tp_id =?";
			list.add(pe.getTp_id());
		}
		if(pe.getP_id()>0){
			sql += " and p.p_id =?";
			list.add(pe.getP_id());
		}
		return jdbcTemplate.queryForObject(sql,Integer.class,list.toArray());
	}
	
	
	
	
	
	
	
	
	
	
	public List<Map<String, Object>> listProducePlanBytime(ProduceEntity pe){
		String sql ="select * from  produce where p_endtime >= ? and p_begintime <= ? ";
		return jdbcTemplate.queryForList(sql,pe.getP_begintime(),pe.getP_begintime());
	}


	public int ckClass(int c_id) {
		// TODO Auto-generated method stub
		String sql =" select count(*) from produce where p_class2 =? or p_class1 = ? ";
		return jdbcTemplate.queryForObject(sql, Integer.class,c_id,c_id);
	}
}
