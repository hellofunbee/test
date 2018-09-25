package com.jingu.IOT.imsg;

import com.jingu.IOT.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by weifengxu on 2018/9/25.
 */
@Component
public class WebSocketTemplate {
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    //向订阅了 /topic/hello 客户端websocket实例发送数据
    public void sendMessage(MessageEntity message){
        messagingTemplate.convertAndSend("/topic/imsg",message);
    }
}
