/**  
*   
* 项目名称：IOT  
* 类名称：DistributionDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午3:15:05  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午3:15:05  
* 修改备注： 下午3:15:05
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

import com.jingu.IOT.entity.DistributionEntity;
import com.jingu.IOT.entity.DistributionEntity2;
import com.jingu.IOT.entity.InputEntity;


/**

* @ClassName: DistributionDao
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午3:15:05

*/
@Component
public class DistributionDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public DistributionDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public int addDistribution(DistributionEntity de){
		String sql ="insert into distribution (d_type,d_state,d_province,d_city,d_district,d_content,d_value,d_index,d_time) value (?,?,?,?,?,?,?,?,UNIX_TIMESTAMP()) ";
		return jdbcTemplate.update(sql,de.getD_type(),de.getD_state(),de.getD_province(),de.getD_city(),de.getD_district(),de.getD_content(),de.getD_value(),de.getD_index());
	}
	
	public int addDistribution2(DistributionEntity2 de){
		String sql ="insert into distribute  (d_type,d_state,d_province,d_city,d_district,d_content,d_value,d_index,d_time,d_originalname,d_filename,d_procedure) value (?,?,?,?,?,?,?,?,UNIX_TIMESTAMP(),?,?,?) ";
		return jdbcTemplate.update(sql,de.getD_type(),de.getD_state(),de.getD_province(),de.getD_city(),de.getD_district(),de.getD_content(),de.getD_value(),de.getD_index(),de.getOrginalName(),de.getFileName(),de.getD_procedure());
	}
//	String sql =" insert into input (in_ownername,in_class1,in_class2,in_mattername,in_total,in_pid,in_pname,in_pstandrad,in_parea,in_time) values ";
////	for(int i =0;i<ie.size();i++){
////		sql +=" (?,?,?,?,?,?,?,?,?,?) ,";
////	}
//	for (InputEntity inputEntity : ie) {
//		sql +=" ('"+inputEntity.getIn_ownername()+"',"+inputEntity.getIn_class1()+","+inputEntity.getIn_class2()+",'"+inputEntity.getIn_mattername()+"','"+inputEntity.getIn_total()+"',"+inputEntity.getIn_pid()+",'"+inputEntity.getIn_pname()+"','"+inputEntity.getIn_pstandrad()+"','"+inputEntity.getIn_parea()+"','"+inputEntity.getIn_time()+"') ,";
//	}
//	String substring = sql.substring(0, sql.length()-1);
//	return jdbcTemplate.update(substring);
	
	public int addDistributionList(List<DistributionEntity> de){
		String sql ="insert into distribution (d_type,d_state,d_province,d_city,d_district,d_content,d_value,d_index,d_time) values ";
		for (DistributionEntity distributionEntity : de) {
			sql +=" ("+distributionEntity.getD_type()+","+distributionEntity.getD_state()+",'"+distributionEntity.getD_province()+"','"+distributionEntity.getD_city()+"','"+distributionEntity.getD_district()+"','"+distributionEntity.getD_content()+"','"+distributionEntity.getD_value()+"',"+distributionEntity.getD_index()+",UNIX_TIMESTAMP()) ,";
		}
		String substring = sql.substring(0, sql.length()-1);
		return jdbcTemplate.update(substring);
	}
	
	public int deleteDistribution(DistributionEntity de){
		String sql ="delete from distribute where d_id =?";
		return jdbcTemplate.update(sql,de.getD_id());
	}
	
	public int updateDistribution(DistributionEntity de){
		String sql ="update distribution set d_id =?";
		List<Object> list = new ArrayList<>();
		list.add(de.getD_id());
		if(de.getD_type()>0){
			sql +=" ,d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>-1){
			sql +=" ,d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" ,d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" ,d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" ,d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" ,d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" ,d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" ,d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" ,d_time =?";
			list.add(de.getD_time());
		}
		if(list.size()==1){
			return 0;
		}
		sql +=" where d_id =?";
		list.add(de.getD_id());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	public List<Map<String, Object>> listDistribution(DistributionEntity de){
		String sql ="select d.*,s.a_name province,sa.a_name city,sr.a_name district from distribution d left join s_area s on s.ar_id =d.d_province left join s_area sa on sa.ar_id =d.d_city left join s_area sr on sr.ar_id = d.d_district  where 1=1";
		List<Object> list = new ArrayList<>();
		if(de.getD_type()>0){
			sql +=" and d.d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>0){
			sql +=" and d.d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" and d.d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" and d.d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" and d.d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" and d.d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" and d.d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" and d.d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" and d.d_time =?";
			list.add(de.getD_time());
		}
		if(de.getD_id() >0){
			sql +=" and d.d_id =?";
			list.add(de.getD_id());
		}
		if(de.getP_province()!=null && de.getP_province().trim().length() >0){
			sql +=" and s.a_name =?";
			list.add(de.getP_province());
		}
		if(de.getP_city()!=null && de.getP_city().trim().length() >0){
			sql +=" and sa.a_name =?";
			list.add(de.getD_city());
		}
		if(de.getP_district()!=null && de.getP_district().trim().length() >0){
			sql +=" and sr.a_name =?";
			list.add(de.getP_district());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	public List<Map<String, Object>> listDistribution2(DistributionEntity2 de){
		String sql ="select d.*,s.a_name province,sa.a_name city,sr.a_name district from distribute d left join s_area s on s.ar_id =d.d_province left join s_area sa on sa.ar_id =d.d_city left join s_area sr on sr.ar_id = d.d_district  where 1=1";
		List<Object> list = new ArrayList<>();
		if(de == null){
			return jdbcTemplate.queryForList(sql,list.toArray());
		}
		if(de.getD_type()>0){
			sql +=" and d.d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>0){
			sql +=" and d.d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" and d.d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" and d.d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" and d.d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" and d.d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" and d.d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" and d.d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" and d.d_time =?";
			list.add(de.getD_time());
		}
		if(de.getD_id() >0){
			sql +=" and d.d_id =?";
			list.add(de.getD_id());
		}
		if(de.getP_province()!=null && de.getP_province().trim().length() >0){
			sql +=" and s.a_name =?";
			list.add(de.getP_province());
		}
		if(de.getP_city()!=null && de.getP_city().trim().length() >0){
			sql +=" and sa.a_name =?";
			list.add(de.getD_city());
		}
		if(de.getP_district()!=null && de.getP_district().trim().length() >0){
			sql +=" and sr.a_name =?";
			list.add(de.getP_district());
		}
		if(de.getD_procedure() !=null && de.getD_procedure()>0){
			sql +=" and d.d_procedure =?";
			list.add(de.getD_procedure());
		}
		sql += " order by d_time desc";
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	
	public List<Map<String, Object>> listDistribution3(DistributionEntity2 de,String groupByString){
		String sql ="select d.*,s.a_name province,sa.a_name city,sr.a_name district from distribute d left join s_area s on s.ar_id =d.d_province left join s_area sa on sa.ar_id =d.d_city left join s_area sr on sr.ar_id = d.d_district  where 1=1";
		List<Object> list = new ArrayList<>();
		if(de == null){
			return jdbcTemplate.queryForList(sql,list.toArray());
		}
		if(de.getD_type()>0){
			sql +=" and d.d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>0){
			sql +=" and d.d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" and d.d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" and d.d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" and d.d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" and d.d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" and d.d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" and d.d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" and d.d_time =?";
			list.add(de.getD_time());
		}
		if(de.getD_id() >0){
			sql +=" and d.d_id =?";
			list.add(de.getD_id());
		}
		if(de.getP_province()!=null && de.getP_province().trim().length() >0){
			sql +=" and s.a_name =?";
			list.add(de.getP_province());
		}
		if(de.getP_city()!=null && de.getP_city().trim().length() >0){
			sql +=" and sa.a_name =?";
			list.add(de.getD_city());
		}
		if(de.getP_district()!=null && de.getP_district().trim().length() >0){
			sql +=" and sr.a_name =?";
			list.add(de.getP_district());
		}
		if(de.getD_procedure() !=null && de.getD_procedure()>0){
			sql +=" and d.d_procedure =?";
			list.add(de.getD_procedure());
		}
		sql += " group by "+groupByString;
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	public List<Map<String, Object>> listDistribution4(DistributionEntity2 de,String groupByString){
		String sql ="select d.d_province,d.d_city,d.d_district,s.a_name province,sa.a_name city,sr.a_name district from distribute d left join s_area s on s.ar_id =d.d_province left join s_area sa on sa.ar_id =d.d_city left join s_area sr on sr.ar_id = d.d_district  where 1=1";
		if(de == null){
			sql += " group by "+groupByString;
			return jdbcTemplate.queryForList(sql);
		}
		ArrayList<Object> list = new ArrayList<>();
		if(de.getD_type()>0){
			sql +=" and d.d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>0){
			sql +=" and d.d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" and d.d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" and d.d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" and d.d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" and d.d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" and d.d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" and d.d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" and d.d_time =?";
			list.add(de.getD_time());
		}
		if(de.getD_id() >0){
			sql +=" and d.d_id =?";
			list.add(de.getD_id());
		}
		if(de.getP_province()!=null && de.getP_province().trim().length() >0){
			sql +=" and s.a_name =?";
			list.add(de.getP_province());
		}
		if(de.getP_city()!=null && de.getP_city().trim().length() >0){
			sql +=" and sa.a_name =?";
			list.add(de.getD_city());
		}
		if(de.getP_district()!=null && de.getP_district().trim().length() >0){
			sql +=" and sr.a_name =?";
			list.add(de.getP_district());
		}
		if(de.getD_procedure() !=null && de.getD_procedure()>0){
			sql +=" and d.d_procedure =?";
			list.add(de.getD_procedure());
		}
		sql += " group by "+groupByString;
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	public List<Map<String, Object>> listDistribution5(DistributionEntity2 de,String groupByString){
		String sql ="select d.d_originalname,d.d_id from distribute d left join s_area s on s.ar_id =d.d_province left join s_area sa on sa.ar_id =d.d_city left join s_area sr on sr.ar_id = d.d_district  where 1=1";
		if(de == null){
			sql += " group by "+groupByString;
			return jdbcTemplate.queryForList(sql);
		}
		ArrayList<Object> list = new ArrayList<>();
		if(de.getD_type()>0){
			sql +=" and d.d_type =?";
			list.add(de.getD_type());
		}
		if(de.getD_index()>0){
			sql +=" and d.d_index =?";
			list.add(de.getD_index());
		}
		if(de.getD_state()>0){
			sql +=" and d.d_state =?";
			list.add(de.getD_state());
		}
		if(de.getD_province()!=null && de.getD_province().trim().length()>0){
			sql +=" and d.d_province =?";
			list.add(de.getD_province());
		}
		if(de.getD_city()!=null && de.getD_city().trim().length()>0){
			sql +=" and d.d_city =?";
			list.add(de.getD_city());
		}
		if(de.getD_district()!=null && de.getD_district().trim().length()>0){
			sql +=" and d.d_district =?";
			list.add(de.getD_district());
		}
		if(de.getD_content()!=null && de.getD_content().trim().length()>0){
			sql +=" and d.d_content =?";
			list.add(de.getD_content());
		}
		if(de.getD_value()!=null && de.getD_value().trim().length()>0){
			sql +=" and d.d_value =?";
			list.add(de.getD_value());
		}
		if(de.getD_time()!=null && de.getD_time().trim().length()>0){
			sql +=" and d.d_time =?";
			list.add(de.getD_time());
		}
		if(de.getD_id() >0){
			sql +=" and d.d_id =?";
			list.add(de.getD_id());
		}
		if(de.getP_province()!=null && de.getP_province().trim().length() >0){
			sql +=" and s.a_name =?";
			list.add(de.getP_province());
		}
		if(de.getP_city()!=null && de.getP_city().trim().length() >0){
			sql +=" and sa.a_name =?";
			list.add(de.getD_city());
		}
		if(de.getP_district()!=null && de.getP_district().trim().length() >0){
			sql +=" and sr.a_name =?";
			list.add(de.getP_district());
		}
		if(groupByString !=null){
			sql += " group by "+groupByString;
		}
		if(de.getD_procedure() !=null && de.getD_procedure()>0){
			sql +=" and d.d_procedure =?";
			list.add(de.getD_procedure());
		}
		sql += " order by d_time desc";
		return jdbcTemplate.queryForList(sql,list.toArray());
	}

	/**
	 * 2018年4月9日
	 * jianghu
	 * TODO
	 */
	public Map<String, Object>  getLastProcess(Integer type ,Integer procedure) {
		// TODO Auto-generated method stub
		String sql =" select * from distribute d left join class c on d.d_procedure = c.c_id where d_type = ? and d_procedure = ? order by d_time desc limit 0,1";
		return jdbcTemplate.queryForMap(sql,type,procedure);
		
	}
	
}
