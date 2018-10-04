/**
 * 项目名称：IOT
 * 类名称：PointService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月5日 下午4:35:23
 * 修改人：jianghu
 * 修改时间：2017年9月5日 下午4:35:23
 * 修改备注： 下午4:35:23
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.MenuDao;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: PointService
 * @Description: TODO
 * @date 2017年9月5日 下午4:35:23
 */
@Component
public class MenuService {

    @Autowired
    private MenuDao menuDao;


    public IOTResult list(PageData pd) {
        List<Map<String, Object>> listMenus = menuDao.list(pd);
        if (listMenus != null && listMenus.size() > 0) {
            return new IOTResult(true, "查看成功", effact(listMenus), 0);
        }
        return new IOTResult(false, "暂无相关信息", null, 0);
    }

    private List<Map<String, Object>> effact(List<Map<String, Object>> cs) {
        if (cs == null || cs.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map m : cs) {
            if ((Integer) m.get("parent_id") == 0) {
                List<Map<String, Object>> l = getChildrens(m, cs);
                m.put("rank", l);
                list.add(m);

            }

        }
        return list;
    }

    private List<Map<String, Object>> getChildrens(Map m, List<Map<String, Object>> cs) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Map c : cs) {
            if (c.get("parent_id").equals(m.get("id"))) {
                if ((Integer) c.get("parent_id") == 0) {
                    c.put("rank", getChildrens(c, cs));
                }
                list.add(c);
            }
        }
        return list;

    }


    public List<Map<String, Object>> listByIds(String menu_ids) {
        List<Map<String, Object>> listMenus = menuDao.listByIds(menu_ids);
        if (listMenus != null && listMenus.size() > 0) {
            return effact(listMenus);
        }
        return null;
    }
}
