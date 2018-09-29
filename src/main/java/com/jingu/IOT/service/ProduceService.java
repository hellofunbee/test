/**
 * 项目名称：IOT
 * 类名称：ProduceService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月10日 下午4:53:04
 * 修改人：jianghu
 * 修改时间：2017年10月10日 下午4:53:04
 * 修改备注： 下午4:53:04
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.ProduceDao;
import com.jingu.IOT.entity.ProduceEntity;
import com.jingu.IOT.requset.ProduceRequset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: ProduceService
 * @Description: TODO
 * @date 2017年10月10日 下午4:53:04
 */
@Component
public class ProduceService {

    private ProduceDao produceDao;

    @Autowired
    public ProduceService(ProduceDao produceDao) {
        this.produceDao = produceDao;
    }

    public int addProducePlan(ProduceEntity pe) {
        return produceDao.addProducePlan(pe);
    }

    public int deleteProducePlan(ProduceEntity pe) {
        return produceDao.deleteProducePlan(pe);
    }

    public int updateProducePlan(ProduceEntity pe) {
        return produceDao.updateProducePlan(pe);
    }

    public List<Map<String, Object>> listProducePlan(ProduceEntity pe) {
        return produceDao.listProducePlan(pe);
    }

    public int listProducePlanCount(ProduceEntity pe) {
        return produceDao.listProducePlanCount(pe);
    }

    // 寻找时间内的作物
    public List<Map<String, Object>> listProducePlanBytime(ProduceEntity pe) {
        return produceDao.listProducePlanBytime(pe);
    }

    /**
     * 2017年12月6日
     * jianghu
     *
     * @param c_id TODO
     */
    public int ckClass(int c_id) {
        // TODO Auto-generated method stub
        return produceDao.ckClass(c_id);

    }

    /**
     * 根据标准分类汇总求和
     *
     * @param pe
     * @return
     */
    public List<Map<String, Object>> groupByStandrad(ProduceRequset pe) {
        return produceDao.groupByStandrad(pe);
    }

    /**
     * 根据标准分类1汇总求和
     *
     * @param pe
     * @return
     */
    public List<Map<String, Object>> groupByClass1(ProduceRequset pe) {
        return produceDao.groupByClass1(pe);
    }

    /**
     * 根据标准分类2汇总求和
     *
     * @param pe
     * @return
     */
    public List<Map<String, Object>> groupByClass2(ProduceRequset pe) {

        return produceDao.groupByClass2(pe);
    }

    /**
     * 求总合
     *
     * @param pe
     * @return
     */
    public Map<String, Object> sumArea(ProduceRequset pe) {
        return produceDao.sumArea(pe);
    }

}
