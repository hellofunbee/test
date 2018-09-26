package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.UserQuestionService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weifengxu on 2018/9/26.
 */
@RestController
public class UserQuestionController {

    @Autowired
    UserQuestionService userQuestionService;

    // 添加规则
    @CrossOrigin
    @RequestMapping(value = "/uq/save", method = RequestMethod.POST)
    public IOTResult save(@RequestBody PageData pd) {

        return userQuestionService.save(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/uq/del", method = RequestMethod.POST)
    public IOTResult del(@RequestBody PageData pd) {

        return userQuestionService.del(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/uq/update", method = RequestMethod.POST)
    public IOTResult update(@RequestBody PageData pd) {

        return userQuestionService.update(pd);
    }

    @CrossOrigin
    @RequestMapping(value = "/uq/list", method = RequestMethod.POST)
    public IOTResult list(@RequestBody PageData pd) {

        return userQuestionService.list(pd);
    }


}
