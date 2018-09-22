/**
 * 项目名称：IOT
 * 类名称：MainDeviceService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月6日 下午1:51:19
 * 修改人：jianghu
 * 修改时间：2017年9月6日 下午1:51:19
 * 修改备注： 下午1:51:19
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.MainDeviceDao;
import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.dao.RelationShipDao;
import com.jingu.IOT.dao.SettingDao;
import com.jingu.IOT.entity.MainDeviceEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.entity.VAREntity;
import com.jingu.IOT.requset.MainDeviceList;
import com.jingu.IOT.requset.MainDeviceRequset;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**

 * @ClassName: MainDeviceService
 * @Description: TODO
 * @author jianghu
 * @date 2017年9月6日 下午1:51:19

 */
@Component
public class MainDeviceService {

    private MainDeviceDao mainDeviceDao;
    private PointDao pointDao;
    private SettingDao settingDao;
    private ToolUtil toolUtil;
    private RelationShipDao relationShipDao;

    @Autowired
    public MainDeviceService(MainDeviceDao mainDeviceDao, PointDao pointDao, SettingDao settingDao, ToolUtil toolUtil, RelationShipDao relationShipDao) {
        this.mainDeviceDao = mainDeviceDao;
        this.pointDao = pointDao;
        this.settingDao = settingDao;
        this.toolUtil = toolUtil;
        this.relationShipDao = relationShipDao;
    }

    @Transactional(value = "primaryTransactionManager")
    public int addMainDevice(MainDeviceRequset mdr) {
        PointEntity pointEntity = mdr.getPointEntity();
        pointEntity.setTp_id(mdr.getId());
        pointEntity.setTp_pid(mdr.getGroupid());
        pointEntity.setTp_name(mdr.getName());
        pointEntity.setTp_order(mdr.getOrderNo());
        pointEntity.setDeviceId(mdr.getDeviceId());
        pointEntity.setX(mdr.getX());
        pointEntity.setY(mdr.getY());
        pointEntity.setTp_type(3);
        // 验证是否能链接上设备
        String ip = "";
        int port = 0;
        int useIPConnect = mdr.getUseIPConnect();
        //useIPConnect 1 内网 2 外网 3 代理 4 路由
        if (useIPConnect == 1) {
            ip = mdr.getNip();
            port = mdr.getNport();
        } else if (useIPConnect == 2) {
            ip = mdr.getIp();
            port = mdr.getPort();
        } else if (useIPConnect == 3) {
            ip = mdr.getProxyIp();
            port = mdr.getProxyPort();
        } else if (useIPConnect == 4) {
            //ip= mdr.();
            //port = mdr.getNPort();
        } else {
            return 2;// 请输入正确的链接方式
        }
        String deviceStatus2 = Client.getDeviceStatus2(null, null, ip, port);
        if (deviceStatus2 == null || !deviceStatus2.equals(mdr.getDeviceId())) {
            return 3;// 请输入正确的ip/port 和链接方式
        }
        pointEntity.setIp(ip);
        pointEntity.setPort(port);
        //String role = "{superviser:"+mdr.getSuperviserid()+",producer:"+mdr.getProducerid()+",perfessor:"+mdr.getExportorid()+",owner"+mdr.getUid()+"}";
        String role = "{," + mdr.getSuperviserid() + ":superviser," + mdr.getProducerid() + ":producer," + mdr.getExportorid() + ":perfessor," + mdr.getUid() + ":owner}";
        pointEntity.setRole(role);
        pointEntity.setX(mdr.getX());
        pointEntity.setY(mdr.getY());
        pointEntity.setZoom(mdr.getZoom());
        pointEntity.setIndex(mdr.getOrderNo());
        String name = mdr.getDeviceId().replace(".", "");
        int creatTable = settingDao.creatTable(name);
        int addPoint = pointDao.addPoint(pointEntity);
        int copyParaseFile = settingDao.copyParaseFile(mdr.getDeviceId());
        int addMainDevice = mainDeviceDao.addMainDevice(mdr);
        RelationShipEntity relationShipEntity = new RelationShipEntity();
        relationShipEntity.setDeviceId(mdr.getDeviceId());
        relationShipEntity.setOwnername(mdr.getName());
        relationShipEntity.setProfessorname(mdr.getExportorname());
        relationShipEntity.setProducername(mdr.getProducername());
        relationShipEntity.setSupervisename(mdr.getSuperivsername());
        relationShipEntity.setSuperviseid(mdr.getSuperviserid());
        relationShipEntity.setProfessorid(mdr.getExportorid());
        relationShipEntity.setProducerid(mdr.getExportorid());
        int addRelationShip = relationShipDao.addRelationShip(relationShipEntity);
        System.out.println("");
        //String deviceId, String ip, String configStr, int port, int state
        VAREntity var = new VAREntity(mdr.getDeviceId(), ip, "", port, 1);
        settingDao.addSettings(var);
        if (addPoint == 0 || addMainDevice == 0) {
            return 0;
        }
        return 1;
    }

    @Transactional(value = "primaryTransactionManager")
    public int updateMainDevice(MainDeviceRequset mdr) {
        PointEntity pointEntity = mdr.getPointEntity();
        pointEntity.setTp_id(mdr.getId());
        pointEntity.setTp_name(mdr.getName());
        pointEntity.setTp_order(mdr.getOrderNo());
        pointEntity.setDeviceId(mdr.getDeviceId());
        pointEntity.setX(mdr.getX());
        pointEntity.setY(mdr.getY());
        pointEntity.setTp_type(3);
        ;
        // 验证是否能链接上设备
        String ip = "";
        int port = 0;
        int useIPConnect = mdr.getUseIPConnect();
        //useIPConnect 1 内网 2 外网 3 代理 4 路由
        if (useIPConnect == 1) {
            ip = mdr.getNip();
            port = mdr.getNport();
        } else if (useIPConnect == 2) {
            ip = mdr.getIp();
            port = mdr.getPort();
        } else if (useIPConnect == 3) {
            ip = mdr.getProxyIp();
            port = mdr.getProxyPort();
        } else if (useIPConnect == 4) {
            //ip= mdr.();
            //port = mdr.getNPort();
        } else {
            return 2;// 请输入正确的链接方式
        }
        String deviceStatus2 = Client.getDeviceStatus2(null, null, ip, port);
        if (deviceStatus2 == null || !deviceStatus2.equals(mdr.getDeviceId())) {
            return 3;// 请输入正确的ip/port 和链接方式
        }
        pointEntity.setIp(ip);
        pointEntity.setPort(port);
        pointEntity.setZoom(mdr.getZoom());
        pointEntity.setIndex(mdr.getOrderNo());
        String role = "{," + mdr.getSuperviserid() + ":superviser," + mdr.getProducerid() + ":producer," + mdr.getExportorid() + ":perfessor," + mdr.getUid() + ":owner}";
        pointEntity.setRole(role);
        int addPoint = pointDao.updatePoint(pointEntity);
        int addMainDevice = mainDeviceDao.updateMainDevice(mdr);
        RelationShipEntity relationShipEntity = new RelationShipEntity();
        relationShipEntity.setDeviceId(mdr.getDeviceId());
        relationShipEntity.setOwnername(mdr.getName());
        relationShipEntity.setProfessorname(mdr.getExportorname());
        relationShipEntity.setProducername(mdr.getProducername());
        relationShipEntity.setSupervisename(mdr.getSuperivsername());
        relationShipEntity.setSuperviseid(mdr.getSuperviserid());
        relationShipEntity.setProfessorid(mdr.getExportorid());
        relationShipEntity.setProducerid(mdr.getProducerid());
        int addRelationShip = relationShipDao.updateRelationShip(relationShipEntity);
        //String deviceId, String ip, String configStr, int port, int state
        VAREntity var = new VAREntity(mdr.getDeviceId(), ip, "", port, 1);
        settingDao.updateSettings(var);
        if (addPoint == 0 || addMainDevice == 0) {
            return 0;
        }
        return 1;

    }

    @Transactional(value = "primaryTransactionManager")
    public int deleteMainDevice(PointRequest mdr) {
        VAREntity varEntity = new VAREntity();
        varEntity.setDeviceId(mdr.getDeviceId());
        int deleteSettings = settingDao.deleteSettings(varEntity);
        int deleteMainDeviceById = mainDeviceDao.deleteMainDeviceById(mdr);
        if (deleteSettings > 0 && deleteMainDeviceById > 0) {
            PointEntity pointEntity = new PointEntity();
            pointEntity.setTp_id(mdr.getTp_id());
            int deletePoint = pointDao.deletePoint(pointEntity);
            if (deletePoint > 0) {
                return 1;
            }
        }
        return 0;

    }

    public MainDeviceEntity getMainDeviceById(MainDeviceEntity mde) {
        List<MainDeviceEntity> mainDeviceById = mainDeviceDao.getMainDeviceById(mde);
        if (mainDeviceById == null || mainDeviceById.isEmpty()) {
            return null;
        }
        return mainDeviceById.get(0);
    }

    public List<Map<String, Object>> listMainDevice(MainDeviceEntity mde) {
        return mainDeviceDao.listMainDevice(mde);
    }

    public int addMainDeviceList(List<MainDeviceEntity> mde) {
        return mainDeviceDao.addMainDeviceList(mde);
    }

    @Transactional(value = "primaryTransactionManager")
    public int addMainDeviceList2(MainDeviceList mdr) {
        List<MainDeviceEntity> list = mdr.getList();
        PointEntity pointEntity = new PointEntity();
        int i = 0;
        for (MainDeviceEntity mainDeviceEntity : list) {
            i++;
            Long maxIdInc = toolUtil.MaxIdInc(ToolUtil.TREEID);
            mainDeviceEntity.setId(maxIdInc.intValue());
            pointEntity.setTp_id(maxIdInc.intValue());
            pointEntity.setDeviceId(mainDeviceEntity.getDeviceId());
            pointEntity.setTp_pid(2);
            pointEntity.setTp_type(3);
            pointEntity.setTp_state(1);
            pointEntity.setTp_name("监控点" + i);
            pointEntity.setPort(52390);
            int addPoint = pointDao.addPoint(pointEntity);
            String name = mainDeviceEntity.getDeviceId().replace(".", "");
            int creatTable = settingDao.creatTable(name);
            // 创建规格文件
            int copyParaseFile = settingDao.copyParaseFile(mainDeviceEntity.getDeviceId());
            int addMainDeviceList2 = mainDeviceDao.addMainDeviceList2(mainDeviceEntity);
        }
        return 1;
    }


    public Map<String, Object> getMainDeviceByDeviceId(MainDeviceEntity mde) {
        return mainDeviceDao.getMainDeviceByDeviceId(mde);
    }

    public Map<String, Object> getMainDeviceId(MainDeviceEntity mde) {
        return mainDeviceDao.getMainDeviceId(mde);
    }


    public Map findById(MainDeviceEntity dv) {
        return mainDeviceDao.findById(dv);
    }

}
