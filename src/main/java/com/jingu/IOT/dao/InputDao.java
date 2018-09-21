/**  
*   
* 项目名称：IOT  
* 类名称：InputDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午6:29:11  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午6:29:11  
* 修改备注： 下午6:29:11
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.InputEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

* @ClassName: InputDao
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午6:29:11

*/
@Component
public class InputDao {
	
	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public InputDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	public int addInput(InputEntity ie) {
		String sql =" insert into input (in_ownername,in_class1,in_class2,in_mattername,in_total,in_pid,in_pname,in_pstandrad,in_parea,in_time,tp_id,in_unit) value (?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,ie.getIn_ownername(),ie.getIn_class1(),ie.getIn_class2(),ie.getIn_mattername(),ie.getIn_total(),ie.getIn_pid(),ie.getIn_pname(),ie.getIn_pstandrad(),ie.getIn_parea(),ie.getIn_time(),ie.getTp_id(),ie.getIn_unit());
	}
	
	public int addInputList(List<InputEntity> ie) {
		String sql =" insert into input (in_ownername,in_class1,in_class2,in_mattername,in_total,in_pid,in_pname,in_pstandrad,in_parea,in_time,tp_id,in_unit) values ";
//		for(int i =0;i<ie.size();i++){
//			sql +=" (?,?,?,?,?,?,?,?,?,?) ,";
//		}
		for (InputEntity i : ie) {
			sql +=" ('"+i.getIn_ownername()+"',"+i.getIn_class1()+","+i.getIn_class2()+",'"+i.getIn_mattername()+"','"+i.getIn_total()+"',"+i.getIn_pid()+",'"+i.getIn_pname()+"','"+i.getIn_pstandrad()+"','"+i.getIn_parea()+"','"+i.getIn_time()+"','"+i.getTp_id()+"','"+i.getIn_unit()+"') ,";
		}
		String substring = sql.substring(0, sql.length()-1);
		return jdbcTemplate.update(substring);
	}
	
	public int deleteInput(InputEntity ie) {
		String sql ="delete from input where in_id =?";
		return jdbcTemplate.update(sql,ie.getIn_id());
	}
	
	public int updateInput(InputEntity ie) {
		String sql ="update input set in_id =?";
		ArrayList<Object> list = new ArrayList<>();
		list.add(ie.getIn_id());
		if(ie.getIn_class1()>0){
			sql +=" , in_class1 = ?";
			list.add(ie.getIn_class1());
		}
		if(ie.getIn_class2()>0){
			sql +=" , in_class2 = ?";
			list.add(ie.getIn_class2());
		}
		if(ie.getIn_pid()>0){
			sql +=" , in_pid = ?";
			list.add(ie.getIn_pid());
		}
		if(ie.getIn_mattername()!=null && ie.getIn_mattername().length()>0){
			sql +=" , in_mattername =?";
			list.add(ie.getIn_mattername());
		}
		if(ie.getIn_ownername()!=null && ie.getIn_ownername().length()>0){
			sql +=" , in_ownername =?";
			list.add(ie.getIn_ownername());
		}
		if(ie.getIn_parea()!=null && ie.getIn_parea().length()>0){
			sql +=" , in_parea =?";
			list.add(ie.getIn_parea());
		}
		if(ie.getIn_pname()!=null && ie.getIn_pname().length()>0){
			sql +=" , in_pname =?";
			list.add(ie.getIn_pname());
		}
		if(ie.getIn_pstandrad()!=null && ie.getIn_pstandrad().length()>0){
			sql +=" , in_pstandrad =?";
			list.add(ie.getIn_pstandrad());
		}
		if(ie.getIn_time()!=null && ie.getIn_time().length()>0){
			sql +=" , in_time =?";
			list.add(ie.getIn_time());
		}
		if(ie.getIn_total()!=null && ie.getIn_total().length()>0){
			sql +=" , in_total =?";
			list.add(ie.getIn_total());
		}
		if(ie.getTp_id()>0){
			sql +=" , tp_id =?";
			list.add(ie.getTp_id());
		}
		if(ie.getIn_unit()>0){
			sql +=" , in_unit =?";
			list.add(ie.getIn_unit());
		}
		if(list.size()==1){
			return 0;
		}
		sql += " where in_id = ?";
		list.add(ie.getIn_id());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	public List<Map<String, Object>> listInput(InputEntity ie) {
		String sql ="select i.*,c_name class2name from input i " +
				"left join class c on i.in_class2 = c.c_id  where 1=1";
		ArrayList<Object> list = new ArrayList<>();
		if(ie.getIn_class1()>0){
			sql +=" and i.in_class1 = ?";
			list.add(ie.getIn_class1());
		}
		if(ie.getIn_class2()>0){
			sql +=" and i.in_class2 = ?";
			list.add(ie.getIn_class2());
		}
		if(ie.getIn_pid()>0){
			sql +=" and i.in_pid = ?";
			list.add(ie.getIn_pid());
		}
		if(ie.getIn_mattername()!=null && ie.getIn_mattername().length()>0){
			sql +=" and i.in_mattername like '%"+ie.getIn_mattername()+"%'";

		}
		if(ie.getIn_ownername()!=null && ie.getIn_ownername().length()>0){
			sql +=" and i.in_ownername =?";
			list.add(ie.getIn_ownername());
		}
		if(ie.getIn_parea()!=null && ie.getIn_parea().length()>0){
			sql +=" and i.in_parea =?";
			list.add(ie.getIn_parea());
		}
		if(ie.getIn_pname()!=null && ie.getIn_pname().length()>0){
			sql +=" and i.in_pname =?";
			list.add(ie.getIn_pname());
		}
		if(ie.getIn_pstandrad()!=null && ie.getIn_pstandrad().length()>0){
			sql +=" and i.in_pstandrad =?";
			list.add(ie.getIn_pstandrad());
		}
		if(ie.getIn_time()!=null && ie.getIn_time().length()>0){
			sql +=" and i.in_time =?";
			list.add(ie.getIn_time());
		}
		if(ie.getP_begintime()!=null && ie.getP_begintime().length()>0){
			sql +=" and i.in_time >= ?";
			list.add(ie.getP_begintime());
		}
		if(ie.getP_endtime()!=null && ie.getP_endtime().length()>0){
			sql +=" and i.in_time <= ?";
			list.add(ie.getP_endtime());
		}
		if(ie.getIn_total()!=null && ie.getIn_total().length()>0){
			sql +=" and i.in_total =?";
			list.add(ie.getIn_total());
		}
		if(ie.getIn_id()>0){
			sql += " and i.in_id = ?";
			list.add(ie.getIn_id());
		}
		if(ie.getTp_id()>0){
			sql += " and i.tp_id = ?";
			list.add(ie.getTp_id());
		}
		if(ie.getStart() >0){
			if(ie.getApp()==1){
				sql +=" limit "+(ie.getStart()-1)*ie.getAppPagesize()+","+ie.getAppPagesize();
			}else{
				sql += " limit "+(ie.getStart()-1)*ie.getPagesize()+","+ie.getPagesize();
			}
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	
	public int listInputCount(InputEntity ie) {
		String sql ="select count(*) from input where 1=1";
		ArrayList<Object> list = new ArrayList<>();
		if(ie.getIn_class1()>0){
			sql +=" and in_class1 = ?";
			list.add(ie.getIn_class1());
		}
		if(ie.getIn_class2()>0){
			sql +=" and in_class2 = ?";
			list.add(ie.getIn_class2());
		}
		if(ie.getIn_pid()>0){
			sql +=" and in_pid = ?";
			list.add(ie.getIn_pid());
		}
		if(ie.getIn_mattername()!=null && ie.getIn_mattername().length()>0){
			sql +=" and in_mattername like '%"+ie.getIn_mattername()+"%'";
		}
		if(ie.getIn_ownername()!=null && ie.getIn_ownername().length()>0){
			sql +=" and in_ownername =?";
			list.add(ie.getIn_ownername());
		}
		if(ie.getIn_parea()!=null && ie.getIn_parea().length()>0){
			sql +=" and in_parea =?";
			list.add(ie.getIn_parea());
		}
		if(ie.getIn_pname()!=null && ie.getIn_pname().length()>0){
			sql +=" and in_pname =?";
			list.add(ie.getIn_pname());
		}
		if(ie.getIn_pstandrad()!=null && ie.getIn_pstandrad().length()>0){
			sql +=" and in_pstandrad =?";
			list.add(ie.getIn_pstandrad());
		}
		if(ie.getIn_time()!=null && ie.getIn_time().length()>0){
			sql +=" and in_time =?";
			list.add(ie.getIn_time());
		}
		if(ie.getP_begintime()!=null && ie.getP_begintime().length()>0){
			sql +=" and in_time >= ?";
			list.add(ie.getP_begintime());
		}
		if(ie.getP_endtime()!=null && ie.getP_endtime().length()>0){
			sql +=" and in_time <= ?";
			list.add(ie.getP_endtime());
		}
		if(ie.getIn_total()!=null && ie.getIn_total().length()>0){
			sql +=" and in_total =?";
			list.add(ie.getIn_total());
		}
		if(ie.getTp_id()>0){
			sql += " and tp_id = ?";
			list.add(ie.getTp_id());
		}
		if(ie.getIn_id()>0){
			sql += " and in_id = ?";
			list.add(ie.getIn_id());
		}
		return jdbcTemplate.queryForObject(sql,Integer.class,list.toArray());
	}
	

}
