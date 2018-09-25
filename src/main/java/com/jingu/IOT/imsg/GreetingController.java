package com.jingu.IOT.imsg;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created y weifengxu on 2018/9/25.
 */
@Controller
public class GreetingController {
    /*@MessageMapping("/sendimsg")
    @SendTo("/topic/imsg")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return message;
    }*/

    @MessageMapping("/sendimsg")
    @SendTo("/topic/imsg")
    public String send(String mr) {

        System.out.println(mr);

        return mr;
    }
}
