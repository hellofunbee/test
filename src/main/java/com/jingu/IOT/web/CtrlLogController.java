package com.jingu.IOT.web;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.CtrlLogService;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CtrlLogController {
    @Autowired
    private CtrlLogService ctrlLogService;

    /**
     * 列表
     */
    @RequestMapping("/ctr_log/list")
    public IOTResult list(@RequestParam PageData params) {
        return ctrlLogService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/ctr_log/info")
    public IOTResult info(@RequestParam PageData params) {

        return ctrlLogService.findById(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public IOTResult save(@RequestBody PageData params) {


        return ctrlLogService.save(params);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public IOTResult update(@RequestBody PageData params) {


        return ctrlLogService.update(params);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public IOTResult delete(@RequestBody PageData params) {
        return ctrlLogService.del(params);
    }

}
