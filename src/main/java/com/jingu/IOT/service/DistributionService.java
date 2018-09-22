/**
 * 项目名称：IOT
 * 类名称：DistributionService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月11日 下午6:39:13
 * 修改人：jianghu
 * 修改时间：2017年10月11日 下午6:39:13
 * 修改备注： 下午6:39:13
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.DistributionDao;
import com.jingu.IOT.entity.DistributionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: DistributionService
 * @Description: TODO
 * @date 2017年10月11日 下午6:39:13
 */
@Component
public class DistributionService {

    private DistributionDao distributionDao;

    @Autowired
    public DistributionService(DistributionDao distributionDao) {
        this.distributionDao = distributionDao;
    }

    public int addDistribution(DistributionEntity de) {
        return distributionDao.addDistribution(de);
    }

    public int addDistribution2(DistributionEntity de) {
        return distributionDao.addDistribution2(de);
    }

    public int addDistributionList(List<DistributionEntity> de) {
        return distributionDao.addDistributionList(de);
    }

    public int deleteDistribution(DistributionEntity de) {
        return distributionDao.deleteDistribution(de);
    }

    public int updateDistribution(DistributionEntity de) {
        return distributionDao.updateDistribution(de);
    }

    public List<Map<String, Object>> listDistribution(DistributionEntity de) {
        return distributionDao.listDistribution(de);
    }

    public List<Map<String, Object>> listDistribution2(DistributionEntity de) {
        return distributionDao.listDistribution2(de);
    }

    public List<Map<String, Object>> listDistribution3(DistributionEntity de) {
        List<Map<String, Object>> listDistribution2 = distributionDao.listDistribution4(de, "d_province");
        for (Map<String, Object> map : listDistribution2) {
            map.remove("d_city");
            map.remove("d_district");
            map.remove("city");
            map.remove("district");
            String d_province = map.get("d_province").toString();
            DistributionEntity distributionEntity = new DistributionEntity();
            distributionEntity.setD_province(d_province);
            distributionEntity.setD_type(de.getD_type());
            List<Map<String, Object>> listDistribution22 = distributionDao.listDistribution4(distributionEntity, "d_city");
            for (Map<String, Object> map2 : listDistribution22) {
                map2.remove("d_district");
                map2.remove("d_province");
                map2.remove("district");
                map2.remove("province");
                String d_city = map2.get("d_city").toString();
                distributionEntity.setD_city(d_city);
                List<Map<String, Object>> listDistribution3 = distributionDao.listDistribution3(distributionEntity, "d_district");
                for (Map<String, Object> map3 : listDistribution3) {
                    map3.remove("d_city");
                    map3.remove("d_content");
                    map3.remove("d_province");
                    map3.remove("city");
                    map3.remove("province");
                    String d_district = map3.get("d_district").toString();
                    distributionEntity.setD_district(d_district);
                    List<Map<String, Object>> listDistribution4 = distributionDao.listDistribution5(distributionEntity, null);
                    map3.put("files", listDistribution4);
                }
                distributionEntity.setD_district(null);
                map2.put("districts", listDistribution3);
            }
            distributionEntity.setD_city(null);
            map.put("citys", listDistribution22);
        }
        return listDistribution2;
    }

    /**
     * 2018年4月9日
     * jianghu
     * TODO
     */
    public Map<String, Object> getLastProcess(Integer type, Integer procedure) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = distributionDao.getLastProcess(type, procedure);
        if (list == null || list.size() == 0)
            return null;
        else
            return list.get(0);

    }

}
