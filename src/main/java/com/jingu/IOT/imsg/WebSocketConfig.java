package com.jingu.IOT.imsg;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by weifengxu on 2018/9/25.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");//配置客户端订阅前缀
        config.setApplicationDestinationPrefixes("/app");//配置客户端发送消息路径前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //"/gs-guide-websocket"是前台连接的端点url
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}
