/**
 * 项目名称：IOT
 * 类名称：TreeController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月5日 下午4:34:51
 * 修改人：jianghu
 * 修改时间：2017年9月5日 下午4:34:51
 * 修改备注： 下午4:34:51
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.MenuService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @ClassName: TreeController
 * @Description: TODO
 * @author jianghu
 * @date 2017年9月5日 下午4:34:51
 *
 */
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    // 查看节点
    @CrossOrigin
    @RequestMapping(value = "/listMenus", method = RequestMethod.POST)
    public IOTResult listMenus(@RequestBody PageData pd) {

        return menuService.list(pd);
    }


}
