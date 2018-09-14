/**  
*   
* 项目名称：IOT  
* 类名称：PointDoa  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月28日 下午5:43:37  
* 修改人：jianghu  
* 修改时间：2017年8月28日 下午5:43:37  
* 修改备注： 下午5:43:37
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.PointEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

* @ClassName: PointDoa
* @Description: TODO
* @author jianghu
* @date 2017年8月28日 下午5:43:37

*/
@Component
public class PointDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public PointDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	};
	
	public int addPoint(PointEntity pointEntity){
		String sql ="insert into t_point (tp_id,tp_name,tp_type,tp_pid,tp_state,tp_order,tp_time,ip,port,uid,deviceId,t_role,x,y,zoom,tp_index) values (?,?,?,?,?,?,UNIX_TIMESTAMP(),?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,pointEntity.getTp_id(),pointEntity.getTp_name(),pointEntity.getTp_type(),pointEntity.getTp_pid(),pointEntity.getTp_state(),pointEntity.getTp_order(),pointEntity.getIp(),pointEntity.getPort(),pointEntity.getUid(),pointEntity.getDeviceId(),pointEntity.getRole(),pointEntity.getX(),pointEntity.getY(),pointEntity.getZoom(),pointEntity.getIndex());
		
	}
	
//	public int addPointUidRole(PointEntity pointEntity){
//		String sql ="insert into t_point (tp_id,tp_name,tp_type,tp_pid,tp_state,tp_order,tp_time,ip,port,uid,deviceId) values (?,?,?,?,?,?,UNIX_TIMESTAMP(),?,?,?,?)";
//		return jdbcTemplate.update(sql,pointEntity.getTp_id(),pointEntity.getTp_name(),pointEntity.getTp_type(),pointEntity.getTp_pid(),pointEntity.getTp_state(),pointEntity.getTp_order(),pointEntity.getIp(),pointEntity.getPort(),pointEntity.getRole(),pointEntity.getDeviceId());
//		
//	}
	
	
	public int deletePoint(PointEntity pointEntity){
		String sql ="delete from t_point where tp_id=?";
		return jdbcTemplate.update(sql,pointEntity.getTp_id());
		
	}
	public List<Map<String, Object>> listPoint(PointEntity  pointEntity){
		String sql ="select t.*,p.name,p.supervisername,p.producername,p.exportorname,if(UNIX_TIMESTAMP()-UNIX_TIMESTAMP(p.infoDataTime)>180 ,2,1) state from t_point t left join t_vastriver_ip p on t.tp_id =p.id where 1=1";
		List<Object> list = new ArrayList<Object>();
		if(pointEntity.getTp_id()>0){
			sql +=" and t.tp_id =?";
			list.add(pointEntity.getTp_id());
		}
		if(pointEntity.getTp_name()!=null&&pointEntity.getTp_name().trim().length()>0){
			sql +=" and t.tp_name =?";
			list.add(pointEntity.getTp_name());
		}
		if(pointEntity.getTp_order()>0){
			sql +=" and t.tp_order =?";
			list.add(pointEntity.getTp_order());
		}
		if(pointEntity.getTp_pid()>-1){
			sql +=" and t.tp_pid =?";
			list.add(pointEntity.getTp_pid());
		}
		if(pointEntity.getTp_state()>0){
			sql +=" and t.tp_state =?";
			list.add(pointEntity.getTp_state());
		}
		if(pointEntity.getTp_type()>0){
			sql +=" and t.tp_type =?";
			list.add(pointEntity.getTp_type());
		}
		if(pointEntity.getTp_time()!=null && pointEntity.getTp_time().trim().length()>0){
			sql +=" and t.tp_time =?";
			list.add(pointEntity.getTp_time());
		}
		if(pointEntity.getUid()>0){
			sql +=" and t.uid =?";
			list.add(pointEntity.getUid());
		}
		if(pointEntity.getTp_type() ==3){
			if(pointEntity.getRole()!=null && pointEntity.getRole().trim().length()>0){
				sql +=" and t_role like '%,"+pointEntity.getRole()+":%' and t_role not like '%,"+pointEntity.getRole()+":owner%'";
			}
			if(pointEntity.getU_type()!=2){
				sql +=" and t_role not like '%,"+pointEntity.getRole()+":perfessor%'";
			}
		}
		
		return jdbcTemplate.queryForList(sql, list.toArray());
	}
	public int updatePoint(PointEntity  pointEntity){
		String sql ="update t_point set tp_id = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(pointEntity.getTp_id());
		if(pointEntity.getTp_name()!=null&&pointEntity.getTp_name().trim().length()>0){
			sql +=" , tp_name =?";
			list.add(pointEntity.getTp_name());
		}
		if(pointEntity.getTp_order()>0){
			sql +=" , tp_order =?";
			list.add(pointEntity.getTp_order());
		}
		if(pointEntity.getTp_pid()>0){
			sql +=" , tp_pid =?";
			list.add(pointEntity.getTp_pid());
		}
		if(pointEntity.getTp_state()>0){
			sql +=" , tp_state =?";
			list.add(pointEntity.getTp_state());
		}
		if(pointEntity.getTp_type()>0){
			sql +=" , tp_type =?";
			list.add(pointEntity.getTp_type());
		}
		if(pointEntity.getTp_time()!=null && pointEntity.getTp_time().trim().length()>0){
			sql +=" , tp_time = ?";
			list.add(pointEntity.getTp_time());
		}
		if(pointEntity.getRole()!=null && pointEntity.getRole().trim().length()>0){
			sql +=" , t_role = ?";
			list.add(pointEntity.getRole());
		}
		if(pointEntity.getDeviceId()!=null && pointEntity.getDeviceId().trim().length()>0){
			sql +=" , deviceId = ?";
			list.add(pointEntity.getDeviceId());
		}
		if(pointEntity.getIp()!=null && pointEntity.getIp().trim().length()>0){
			sql +=" , ip =?";
			list.add(pointEntity.getIp());
		}
		if(pointEntity.getZoom()>0){
			sql +=" , zoom =?";
			list.add(pointEntity.getZoom());
		}
		if(pointEntity.getPort()>0){
			sql +=" , port =?";
			list.add(pointEntity.getPort());
		}
		if(pointEntity.getIndex()>0){
			sql +=" , tp_index =?";
			list.add(pointEntity.getIndex());
		}
		sql += " where tp_id = ?";
		list.add(pointEntity.getTp_id());
		return jdbcTemplate.update(sql, list.toArray());
	}
	public List<PointEntity> getPoint(PointEntity  pointEntity){
		String sql ="select * from t_point where 1=1";
		List<Object> list = new ArrayList<Object>();
		if(pointEntity.getTp_id()>0){
			sql +=" and tp_id =?";
			list.add(pointEntity.getTp_id());
		}
		if(pointEntity.getTp_name()!=null&&pointEntity.getTp_name().trim().length()>0){
			sql +=" and tp_name =?";
			list.add(pointEntity.getTp_name());
		}
		if(pointEntity.getTp_order()>0){
			sql +=" and tp_order =?";
			list.add(pointEntity.getTp_order());
		}
		if(pointEntity.getTp_pid()>0){
			sql +=" and tp_pid =?";
			list.add(pointEntity.getTp_pid());
		}
		if(pointEntity.getTp_state()>0){
			sql +=" and tp_state =?";
			list.add(pointEntity.getTp_state());
		}
		if(pointEntity.getTp_type()>0){
			sql +=" and tp_type =?";
			list.add(pointEntity.getTp_type());
		}
		if(pointEntity.getTp_time()!=null && pointEntity.getTp_time().trim().length()>0){
			sql +=" and tp_time =?";
			list.add(pointEntity.getTp_time());
		}
		if(pointEntity.getDeviceId()!=null && pointEntity.getDeviceId().trim().length()>0){
			sql +=" and deviceId =?";
			list.add(pointEntity.getDeviceId());
		}
		if(pointEntity.getIp()!=null && pointEntity.getIp().trim().length()>0){
			sql +=" and ip =?";
			list.add(pointEntity.getIp());
		}
		if(pointEntity.getPort()>0){
			sql +=" and port =?";
			list.add(pointEntity.getPort());
		}
		if(pointEntity.getRole()!=null && pointEntity.getRole().trim().length()>0){
			sql +=" and t_role like '%,"+pointEntity.getRole()+":%' ";
		}
		return jdbcTemplate.query(sql, list.toArray(),new PointEntity());
	}

	/**
	 * 2017年11月30日
	 * jianghu
	 * @param string
	 * TODO
	 */
	public List<Map<String, Object>> listPointByDeviceId(String string) {
		// TODO Auto-generated method stub
		String sql ="select tp_name FROM t_point where deviceId =?";
		return jdbcTemplate.queryForList(sql,string);
		
	}

	/**
	 * 2017年12月2日
	 * jianghu
	 * @return
	 * TODO
	 */
	public int listMaxId() {
		// TODO Auto-generated method stub
		String sql = "select max(tp_id) from t_point";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
	 * 2017年12月20日
	 * jianghu
	 * @param deviceId
	 * @return
	 * TODO
	 */
	public int updateRole(String deviceId,String role) {
		// TODO Auto-generated method stub
		String sql =" update t_point set t_role = ? where deviceId =?";
		return jdbcTemplate.update(sql,role,deviceId);
	}

	public String getRole(String deviceId) {
		// TODO Auto-generated method stub
		String sql ="  SELECT t_role from t_point where deviceId =?";
		return jdbcTemplate.queryForObject(sql, String.class,deviceId);
	}
	
	
}
