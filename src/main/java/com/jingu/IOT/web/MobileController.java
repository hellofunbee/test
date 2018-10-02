package com.jingu.IOT.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weifengxu on 2018/10/2.
 */
@Controller
public class MobileController {


    @RequestMapping(value = "/app/homepage")
    public String app() {
        return "mobile/index.html";
    }
}
