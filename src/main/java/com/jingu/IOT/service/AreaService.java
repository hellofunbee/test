/**
 * 项目名称：IOT
 * 类名称：AreaService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月30日 上午11:13:40
 * 修改人：jianghu
 * 修改时间：2017年9月30日 上午11:13:40
 * 修改备注： 上午11:13:40
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.AreaDao;
import com.jingu.IOT.entity.AreaBean;
import com.jingu.IOT.requset.AreaReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**

 * @ClassName: AreaService
 * @Description: TODO
 * @author jianghu
 * @date 2017年9月30日 上午11:13:40

 */
@Component
public class AreaService {
    private AreaDao dao;

    @Autowired
    public AreaService(AreaDao dao) {
        this.dao = dao;
    }

    public List<Map<String, Object>> listProvince() {
        return dao.listProvince();
    }

    public List<Map<String, Object>> listCity(AreaBean ab) {
        return dao.listCity(ab);
    }

    public List<Map<String, Object>> listDistrict(AreaReq a) {
        // TODO Auto-generated method stub
        return dao.listDistrict(a);
    }
}
