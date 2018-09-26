package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.ExpAnsService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weifengxu on 2018/9/26.
 */
@RestController
public class ExpAnsController {

    @Autowired
    ExpAnsService expAnsService;

    // 添加规则
    @CrossOrigin
    @RequestMapping(value = "/expAns/save", method = RequestMethod.POST)
    public IOTResult save(@RequestBody PageData pd) {

        return expAnsService.save(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expAns/del", method = RequestMethod.POST)
    public IOTResult del(@RequestBody PageData pd) {

        return expAnsService.del(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expAns/update", method = RequestMethod.POST)
    public IOTResult update(@RequestBody PageData pd) {

        return expAnsService.update(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/expAns/list", method = RequestMethod.POST)
    public IOTResult list(@RequestBody PageData pd) {

        return expAnsService.list(pd);
    }


}
