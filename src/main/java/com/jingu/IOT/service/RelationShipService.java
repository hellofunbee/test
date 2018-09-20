/**
 * 项目名称：IOT
 * 类名称：RelationShipService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年11月20日 下午1:47:16
 * 修改人：jianghu
 * 修改时间：2017年11月20日 下午1:47:16
 * 修改备注： 下午1:47:16
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.dao.RelationShipDao;
import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.requset.RelationShipRequset;
import com.jingu.IOT.requset.UserRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: RelationShipService
 * @Description: TODO
 * @date 2017年11月20日 下午1:47:16
 */
@Component
public class RelationShipService {

    private RelationShipDao relationShipDao;
    private PointDao pointDao;

    @Autowired
    public RelationShipService(RelationShipDao relationShipDao, PointDao pointDao) {
        this.relationShipDao = relationShipDao;
        this.pointDao = pointDao;
    }

    public int addRelationShip(RelationShipEntity relationShipEntity) {
        return relationShipDao.addRelationShip(relationShipEntity);
    }

    public int updateRelationShip(RelationShipEntity relationShipEntity) {
        return relationShipDao.updateRelationShip(relationShipEntity);
    }

    public int deleteRelationShip(RelationShipEntity relationShipEntity) {
        return relationShipDao.deleteRelationShip(relationShipEntity);
    }

    public List<Map<String, Object>> listRelationShip(RelationShipEntity relationShipEntity) {
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
        if (ur.getUnbind() > 0) {
            //解绑设备

            RelationShipEntity rs = new RelationShipEntity();
            rs.setProducerid(ur.getUid());
            List<Map<String, Object>> rses = relationShipDao.listRelationShip(rs);

            if (rses != null && rses.size() > 0) {
                for (Map m : rses) {

                    RelationShipEntity relationShipEntity = new RelationShipEntity();
                    relationShipEntity.setId((Integer) m.get("r_id"));
                    relationShipEntity.setProducerid(0);
                    relationShipEntity.setProducername("");
                    int updateRelationShip = relationShipDao.updateRelationShipProducer(relationShipEntity);


                    String deviceId = (String) m.get("deviceId");
                    if (deviceId == null || deviceId == "") {
                        continue;
                    }
                    String role = pointDao.getRole(deviceId);

                    /*{,17:superviser,21:producer,1:perfessor,0:owner}*/
//                    String[] split = role.split(",");
//                    String newRole = split[0] + "," + split[1] + ",:producer," + split[3] + "," + split[4];

                    if (role == null) continue;

                    String[] split = role.split(",");
                    if (role.indexOf("producer") != -1) {
                        for (int i = 0; i < split.length; i++) {
                            if (split[i].indexOf("producer") != -1) {
                                split[i] = "0:producer";
                            }
                        }

                        int update = pointDao.updateRole(deviceId, StringUtils.join(split, ","));
                    }


                }
            }


            /*RelationShipEntity relationShipEntity = new RelationShipEntity();
            relationShipEntity.setId(ur.getR_id());
            relationShipEntity.setProducerid(0);
            relationShipEntity.setProducername("");


            int updateRelationShip = relationShipDao.updateRelationShipProducer(relationShipEntity);
            String deviceId = ur.getDeviceId();
//			String deviceId ="10.00.21.27";
            String role = pointDao.getRole(deviceId);
            String[] split = role.split(",");
            String newRole = split[0] + "," + split[1] + ",:producer," + split[3] + "," + split[4];
            int update = pointDao.updateRole(deviceId, newRole);
            if (update > 0 && updateRelationShip > 0) {
                return 1;
            }*/

        }
        return 1;

    }

}
