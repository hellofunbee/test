/**
 * 项目名称：IOT
 * 类名称：RuleController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月27日 上午11:06:54
 * 修改人：jianghu
 * 修改时间：2017年10月27日 上午11:06:54
 * 修改备注： 上午11:06:54
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.RoleEntity;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.RoleService;
import com.jingu.IOT.util.CommonUtils;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jianghu
 * @ClassName: RuleController
 * @Description: TODO
 * @date 2017年10月27日 上午11:06:54 规定
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


    @CrossOrigin
    @RequestMapping(value = "/listRole", method = RequestMethod.POST)
    public IOTResult listRole(@RequestBody PageData pd) {

        RoleEntity r = new RoleEntity();
        if (CommonUtils.has(pd.get("r_value")))
            r.setR_value(Integer.parseInt(pd.get("r_value").toString()));

        return roleService.listRole(r);
    }

    @CrossOrigin
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public IOTResult updateRole(@RequestBody RoleEntity role) {
        return roleService.updateRole(role);
    }
}
