package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.ExpProService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weifengxu on 2018/9/26.
 */
@RestController
public class ExpProController {

    @Autowired
    ExpProService expProService;

    // 添加规则
    @CrossOrigin
    @RequestMapping(value = "/expPro/save", method = RequestMethod.POST)
    public IOTResult save(@RequestBody PageData pd) {

        return expProService.save(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expPro/del", method = RequestMethod.POST)
    public IOTResult del(@RequestBody PageData pd) {

        return expProService.del(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expPro/update", method = RequestMethod.POST)
    public IOTResult update(@RequestBody PageData pd) {

        return expProService.update(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expPro/list", method = RequestMethod.POST)
    public IOTResult list(@RequestBody PageData pd) {

        return expProService.list(pd);
    }


}
