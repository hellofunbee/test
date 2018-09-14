/**  
*   
* 项目名称：IOT  
* 类名称：RelationShipService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月20日 下午1:47:16  
* 修改人：jianghu  
* 修改时间：2017年11月20日 下午1:47:16  
* 修改备注： 下午1:47:16
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.dao.RelationShipDao;
import com.jingu.IOT.dao.UserDao;
import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.requset.RelationShipRequset;
import com.jingu.IOT.requset.UserRequest;

/**

* @ClassName: RelationShipService
* @Description: TODO
* @author jianghu
* @date 2017年11月20日 下午1:47:16

*/
@Component
public class RelationShipService {

	private RelationShipDao relationShipDao;
	private PointDao pointDao;
	
	@Autowired
	public RelationShipService(RelationShipDao relationShipDao,PointDao pointDao) {
		this.relationShipDao = relationShipDao;
		this.pointDao = pointDao;
	}
	
	public int addRelationShip(RelationShipEntity relationShipEntity){
		return relationShipDao.addRelationShip(relationShipEntity);
	}
	
	public int updateRelationShip(RelationShipEntity relationShipEntity){
		return relationShipDao.updateRelationShip(relationShipEntity);
	}
	
	public int deleteRelationShip(RelationShipEntity relationShipEntity){
		return relationShipDao.deleteRelationShip(relationShipEntity);
	}
	
	public List<Map<String, Object>> listRelationShip(RelationShipEntity relationShipEntity){
		return relationShipDao.listRelationShip(relationShipEntity);
	}

	public int listRelationShipCount(RelationShipRequset re) {
		// TODO Auto-generated method stub
		return relationShipDao.listRelationShipCount(re);
	}

	/**
	 * 2017年12月19日
	 * jianghu
	 * TODO
	 */
	public int unbind(UserRequest ur) {
		if(ur.getUnbind() >0){
			//解绑设备
			RelationShipEntity relationShipEntity = new RelationShipEntity();
			relationShipEntity.setId(ur.getR_id());
			relationShipEntity.setProducerid(0);
			relationShipEntity.setProducername("");
			int updateRelationShip = relationShipDao.updateRelationShipProducer(relationShipEntity);
			String deviceId = ur.getDeviceId();
//			String deviceId ="10.00.21.27";
			String role = pointDao.getRole(deviceId);
			String[] split = role.split(",");
			String newRole = split[0]+","+split[1]+",:producer,"+split[3]+","+split[4];
			int update = pointDao.updateRole(deviceId,newRole);
			if(update >0 && updateRelationShip >0){
				return 1;
			}
			
		}
		return  0;
		
	}
	
}
