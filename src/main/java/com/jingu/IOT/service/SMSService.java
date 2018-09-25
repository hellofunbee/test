package com.jingu.IOT.service;

import com.jingu.IOT.entity.MessageEntity;
import com.jingu.IOT.imsg.WebSocketTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by weifengxu on 2018/9/25.
 * 信息服务 发短信 和即时消息
 */
@Component
public class SMSService {
    @Autowired
    UserService userService;
    @Autowired
    WebSocketTemplate webs;

    /**
     * 根据用户类型发送信息
     *
     * @param userType
     */
    public void sendSMSByUserType(List<Integer> userType, MessageEntity message) {
        List<Map<String, Object>> list = userService.listUserInType(userType);

        if (list == null || list.size() == 0) {
            return;
        }
        //过滤
        List<String> phones = new ArrayList<>();
        for (Map u : list) {
            String p = (String) u.get("tu_phone");
            if (p != null && p.length() > 1) {
                phones.add(p);
            }
        }
        sendSMS(phones, message);

    }

    /**
     * 发送短信
     *
     * @param phones
     * @param message
     */

    public static void sendSMS(List<String> phones, MessageEntity message) {

        System.out.println(phones);
        System.out.println(message);
    }

    /**
     * 发送即时消息
     *
     * @param mr
     */
    public void sendimsg(MessageEntity mr) {
        webs.sendMessage(mr);

    }
}