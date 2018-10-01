/**
 * 项目名称：IOT
 * 类名称：GatherService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月8日 下午8:02:42
 * 修改人：jianghu
 * 修改时间：2017年9月8日 下午8:02:42
 * 修改备注： 下午8:02:42
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.GatherDao;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.SettingEntity;
import com.jingu.IOT.entity.VAREntity;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.response.IOTResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: GatherService
 * @Description: TODO
 * @date 2017年9月8日 下午8:02:42
 */
@Component
public class GatherService {

    private GatherDao gatherDao;

    @Autowired
    public GatherService(GatherDao gatherDao) {
        this.gatherDao = gatherDao;
    }

    public List<Map<String, Object>> getGatherSettings(VAREntity varEntity) {
        return gatherDao.getGatherSettings(varEntity);
    }
//	public int deleteSettingByDeviceId(PointEntity se){
//		return gatherDao.deleteSettingByDeviceId(se);
//	}

    // 展示采集数据
    public List<Map<String, Object>> listSensorInfo(PointRequest pEntity) {
        return gatherDao.listSensorInfo(pEntity);
    }

    public List<Map<String, Object>> listSensorInfoListUnit(PointEntity pEntity) {
        return gatherDao.listSensorInfoListUnit(pEntity);

    }

    // 展示采集数据单位 // 图标显示
    public List<Map<String, Object>> listSensorInfoChartUnit(PointEntity pEntity) {
        return gatherDao.listSensorInfoChartUnit(pEntity);
    }

    public List<Map<String, Object>> listSensorInfoFirst(PointRequest pEntity) {
        return gatherDao.listSensorInfoFirst(pEntity);
    }

    public int addGatherSettings(List<SettingEntity> list) {
        return gatherDao.addGatherSettings(list);
    }

    // 展示采集数据单位
    public List<Map<String, Object>> listwaring(PointEntity pEntity) {
        return gatherDao.listwaring(pEntity);
    }

    // 根据deviceId删除数据
    public int deleteSettingByDeviceId(PointEntity pe) {
        return gatherDao.deleteSettingByDeviceId(pe);
    }

    public int deleteSettingById(PointEntity se) {
        return gatherDao.deleteSettingById(se);
    }

    // 获得个数
    public int getSettingCountByDeviceId(PointEntity pe) {
        return gatherDao.getSettingCountByDeviceId(pe);
    }

    public List<Map<String, Object>> listChannel(PointEntity pEntity) {
        return gatherDao.listChannel(pEntity);
    }

    public List<Map<String, Object>> listChannelByDeviceId(String deviceId) {
        return gatherDao.listChannelByDeviceId(deviceId);
    }

    public int listSensorInfoCount(PointRequest pr) {
        // TODO Auto-generated method stub
        return gatherDao.listSensorInfoCount(pr);
    }

    public List<Map<String, Object>> listStaticData(List<String> list) {
        return gatherDao.listStaticData(list);
    }

    public List<Map<String, Object>> listStaticInfo(String field, String deviceId, String beginTime, String endTime) {
        return gatherDao.listStateInfo(field, deviceId, beginTime, endTime);
    }


    public Map<String, Object> getChannalNByField(String field) {
        List<Map<String, Object>> channels = gatherDao.getChannalNByField(field);
        if (channels == null || channels.size() == 0)
            return null;
        else {
            if (channels.get(0).get("name") != null)
                return channels.get(0);
            else
                return null;
        }

    }

    /**
     * @param statDisplay 分析显示
     * @param ids         设备ids
     * @return
     */
    public IOTResult getSttings(int statDisplay, List<String> ids) {
        List<Map<String, Object>> settings = gatherDao.getSettings(statDisplay, ids);

        if (settings == null || settings.size() == 0)
            return new IOTResult(false, "无数据", null, 0);
        int count = ids.size();
        List<Map<String, Object>> result = new ArrayList<>();

        //取合集
        List<String> listTemp = new ArrayList();
        for (int i = 0; i < settings.size(); i++) {
            if (!listTemp.contains(settings.get(i).get("fieldName"))) {
                listTemp.add((String) settings.get(i).get("fieldName"));
            }
        }
        //取交集
        for (String feildName : listTemp) {
            int c = 0;
            Map now = null;
            for (Map s : settings) {
                if (feildName.equals(s.get("fieldName"))) {
                    c++;
                }
                if (count == c) {
                    result.add(s);
                    break;
                }
            }
        }

        return new IOTResult(true, "success", result, 0);
    }

    /**
     * 根据deviceId 和channel 的field 查找top 1 数据
     * @param params
     * @return
     */
    public Map<String,Object> findTopval(Map params){

        try {
            return gatherDao.findTopval(params);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
