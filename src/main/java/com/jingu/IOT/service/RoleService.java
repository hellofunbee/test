/**
 * 项目名称：IOT
 * 类名称：RoleService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月13日 下午1:15:51
 * 修改人：jianghu
 * 修改时间：2017年10月13日 下午1:15:51
 * 修改备注： 下午1:15:51
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.RoleDao;
import com.jingu.IOT.entity.RoleEntity;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: RoleService
 * @Description: TODO
 * @date 2017年10月13日 下午1:15:51
 */
@Component
public class RoleService {

    private RoleDao roleDao;
    @Autowired
    MenuService menuService;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public IOTResult listRole(RoleEntity re) {
        List<Map<String, Object>> roles = roleDao.listRole(re);

        if (roles != null && roles.size() > 0) {
            for (Map m : roles) {
                if (CommonUtils.has(m.get("menu_ids"))) {
                    List<Map<String, Object>> menu_ids = menuService.listByIds((String) m.get("menu_ids"));
                    if (menu_ids != null && menu_ids.size() > 0)
                        m.put("menu_ids", menu_ids);
                }

            }


            return new IOTResult(true, "查看成功", roles, 0);
        }
        return new IOTResult(false, "暂无数据", null, 0);
    }

    public Map<String, Object> getRole(RoleEntity re) {
        List<Map<String, Object>> listRole = roleDao.listRole(re);
        if (listRole == null || listRole.isEmpty()) {
            return null;
        }
        return listRole.get(0);
    }

    public IOTResult updateRole(RoleEntity role) {

        int status = roleDao.updateRole(role);

        if (status > 0)
            return new IOTResult(true, "修改成功", status, 0);
        return new IOTResult(false, "修改失败", null, 0);
    }

}
