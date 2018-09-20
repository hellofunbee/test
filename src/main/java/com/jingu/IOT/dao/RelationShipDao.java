/**  
*   
* 项目名称：IOT  
* 类名称：RelationShipDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月20日 下午1:25:24  
* 修改人：jianghu  
* 修改时间：2017年11月20日 下午1:25:24  
* 修改备注： 下午1:25:24
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

import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.requset.RelationShipRequset;

/**

* @ClassName: RelationShipDao
* @Description: TODO
* @author jianghu
* @date 2017年11月20日 下午1:25:24

*/
@Component
public class RelationShipDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	
	
	public int addRelationShip(RelationShipEntity relationShipEntity){
		String sql =" insert into relationship(deviceId,producerid,producername,superviserid,supervisename,ownername,ownerid,professorid,professorname) value (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,relationShipEntity.getDeviceId(),relationShipEntity.getProducerid(),relationShipEntity.getProducername(),relationShipEntity.getSuperviseid(),relationShipEntity.getSupervisename(),relationShipEntity.getOwnername(),relationShipEntity.getOwnerid(),relationShipEntity.getProfessorid(),relationShipEntity.getProfessorname());		
	}
	
	public int updateRelationShip(RelationShipEntity relationShipEntity){
		String sql =" update relationship set deviceId = ?";
		List<Object> list = new ArrayList<>();
		list.add(relationShipEntity.getDeviceId());
		if(relationShipEntity.getOwnername()!=null && relationShipEntity.getOwnername().trim().length()>0){
			sql +=" , ownername = ?";
			list.add(relationShipEntity.getOwnername());
		}
		if(relationShipEntity.getProducername()!=null && relationShipEntity.getProducername().trim().length()>0){
			sql +=" , producername = ?";
			list.add(relationShipEntity.getProducername());
		}
		if(relationShipEntity.getProfessorname()!=null && relationShipEntity.getProfessorname().trim().length()>0){
			sql +=" , professorname = ?";
			list.add(relationShipEntity.getProfessorname());
		}
		if(relationShipEntity.getSupervisename()!=null && relationShipEntity.getSupervisename().trim().length()>0){
			sql +=" , supervisename = ?";
			list.add(relationShipEntity.getSupervisename());
		}
		if(relationShipEntity.getOwnerid()>0){
			sql +=" , ownerid = ?";
			list.add(relationShipEntity.getOwnerid());
		}
		if(relationShipEntity.getProducerid()>0){
			sql +=" , producerid = ?";
			list.add(relationShipEntity.getProducerid());
		}
		if(relationShipEntity.getProfessorid()>0){
			sql +=" , professorid = ?";
			list.add(relationShipEntity.getProfessorid());
		}
		if(relationShipEntity.getSuperviseid()>0){
			sql +=" , superviserid = ?";
			list.add(relationShipEntity.getSuperviseid());
		}
		sql +=" where deviceId =?";
		list.add(relationShipEntity.getDeviceId());
		return jdbcTemplate.update(sql,list.toArray());		
	}
	
	public int updateRelationShipProducer(RelationShipEntity relationShipEntity){
		String sql =" update relationship set  producerid =? ,producername =? where id =?";
		return jdbcTemplate.update(sql,relationShipEntity.getProducerid(),relationShipEntity.getProducername(),relationShipEntity.getId());		
	}
	
	
	public int deleteRelationShip(RelationShipEntity relationShipEntity){
		String sql =" delete from relationship where deviceId = ? ";
		return jdbcTemplate.update(sql,relationShipEntity.getDeviceId());
	}
	
	
	public List<Map<String, Object>> listRelationShip(RelationShipEntity relationShipEntity){
//		String sql =" select r.*,COALESCE(t.tu_id,0) tu_id,COALESCE(t.tu_name,'') tu_name,COALESCE(t.tu_state,'2') tu_state,tp.tp_name from  relationship r left join t_user t on r.producerid = t.tu_id left join t_point tp on tp.deviceId = r.deviceId   where 1=1";
		String sql =" select r.*,t.*,tp.tp_name from  relationship r " +
				"left join t_user t on r.producerid = t.tu_id " +
				"left join t_point tp on tp.deviceId = r.deviceId   where 1=1";

		
		List<Object> list = new ArrayList<>();
		if(relationShipEntity.getOwnername()!=null && relationShipEntity.getOwnername().trim().length()>0){
			sql +=" and r.ownername = ?";
			list.add(relationShipEntity.getOwnername());
		}
		if(relationShipEntity.getProducername()!=null && relationShipEntity.getProducername().trim().length()>0){
			sql +=" and r.producername = ?";
			list.add(relationShipEntity.getProducername());
		}
		if(relationShipEntity.getProfessorname()!=null && relationShipEntity.getProfessorname().trim().length()>0){
			sql +=" and r.professorname = ?";
			list.add(relationShipEntity.getProfessorname());
		}
		if(relationShipEntity.getSupervisename()!=null && relationShipEntity.getSupervisename().trim().length()>0){
			sql +=" and r.supervisename = ?";
			list.add(relationShipEntity.getSupervisename());
		}
		if(relationShipEntity.getOwnerid()>0){
			sql +=" and r.ownerid = ?";
			list.add(relationShipEntity.getOwnerid());
		}
		if(relationShipEntity.getProducerid()>0){
			sql +=" and r.producerid = ?";
			list.add(relationShipEntity.getProducerid());
		}
		if(relationShipEntity.getProfessorid()>0){
			sql +=" and r.professorid = ?";
			list.add(relationShipEntity.getProfessorid());
		}
		if(relationShipEntity.getSuperviseid()>0){
			sql +=" and r.superviserid = ?";
			list.add(relationShipEntity.getSuperviseid());
		}
		if(relationShipEntity.getDeviceId()!=null && relationShipEntity.getDeviceId().trim().length()>0){
			sql +=" and r.deviceId = ?";
			list.add(relationShipEntity.getDeviceId());
		}
		if(relationShipEntity.getId()>0){
			sql +=" and r.id = ?";
			list.add(relationShipEntity.getId());
		}
		if(relationShipEntity.getStart() >0){
			sql +=" limit "+(relationShipEntity.getStart()-1)*relationShipEntity.getPagesize()+","+relationShipEntity.getPagesize();
		}
		
		return jdbcTemplate.queryForList(sql,list.toArray());		
	}


	public int listRelationShipCount(RelationShipRequset re) {
		// TODO Auto-generated method stub
		String sql =" select count(*) from  relationship where 1=1 ";
		List<Object> list = new ArrayList<>();
		if(re.getOwnername()!=null && re.getOwnername().trim().length()>0){
			sql +=" and ownername = ?";
			list.add(re.getOwnername());
		}
		if(re.getProducername()!=null && re.getProducername().trim().length()>0){
			sql +=" and producername = ?";
			list.add(re.getProducername());
		}
		if(re.getProfessorname()!=null && re.getProfessorname().trim().length()>0){
			sql +=" and professorname = ?";
			list.add(re.getProfessorname());
		}
		if(re.getSupervisename()!=null && re.getSupervisename().trim().length()>0){
			sql +=" and supervisename = ?";
			list.add(re.getSupervisename());
		}
		if(re.getOwnerid()>0){
			sql +=" and ownerid = ?";
			list.add(re.getOwnerid());
		}
		if(re.getProducerid()>0){
			sql +=" and producerid = ?";
			list.add(re.getProducerid());
		}
		if(re.getProfessorid()>0){
			sql +=" and professorid = ?";
			list.add(re.getProfessorid());
		}
		if(re.getSuperviseid()>0){
			sql +=" and superviseid = ?";
			list.add(re.getSuperviseid());
		}
		if(re.getDeviceId()!=null && re.getDeviceId().trim().length()>0){
			sql +=" and deviceId = ?";
			list.add(re.getDeviceId());
		}
		if(re.getId()>0){
			sql +=" and id = ?";
			list.add(re.getId());
		}
		if(re.getStart() >0){
			sql +=" limit "+(re.getStart()-1)*re.getPagesize()+","+re.getPagesize();
		}
		
		return jdbcTemplate.queryForObject(sql,Integer.class,list.toArray());	
	}
	
}
