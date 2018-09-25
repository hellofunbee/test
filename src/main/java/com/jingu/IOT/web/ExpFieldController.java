/**
 * 项目名称：IOT
 * 类名称：AreaController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月30日 上午11:05:50
 * 修改人：jianghu
 * 修改时间：2017年9月30日 上午11:05:50
 * 修改备注： 上午11:05:50
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.requset.ExpFieldRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.ExpFieldService;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ExpFieldController {

    private ToolUtil toolUtil;
    private ExpFieldService expFieldService;


    @Autowired
    public ExpFieldController(ToolUtil toolUtil, ExpFieldService service) {
        this.toolUtil = toolUtil;
        this.expFieldService = service;
    }

    @CrossOrigin
    @RequestMapping(value = "/listExpFileds", method = RequestMethod.POST)
    public IOTResult listExpFileds(@RequestBody ExpFieldRequest a) {
       return expFieldService.listExpFields(a);

    }

    @CrossOrigin
    @RequestMapping(value = "/listExpByFields", method = RequestMethod.POST)
    public IOTResult listExpByFields(@RequestBody ExpFieldRequest a) {
        return expFieldService.listExpByFields(a);
    }

}
