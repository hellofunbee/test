package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.ExpClientService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weifengxu on 2018/9/26.
 */
@RestController
public class ExpClientController {

    @Autowired
    ExpClientService expClientService;

    // 添加规则
    @CrossOrigin
    @RequestMapping(value = "/expClient/save", method = RequestMethod.POST)
    public IOTResult save(@RequestBody PageData pd) {

        return expClientService.save(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expClient/del", method = RequestMethod.POST)
    public IOTResult del(@RequestBody PageData pd) {

        return expClientService.del(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expClient/update", method = RequestMethod.POST)
    public IOTResult update(@RequestBody PageData pd) {

        return expClientService.update(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expClient/list", method = RequestMethod.POST)
    public IOTResult list(@RequestBody PageData pd) {

        return expClientService.list(pd);
    }


}
