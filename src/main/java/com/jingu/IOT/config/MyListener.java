package com.jingu.IOT.config;

/**
 * Created by weifengxu on 2018/10/1.
 */

import com.jingu.IOT.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 */
@WebListener
public class MyListener  implements ServletContextListener {
    @Autowired
    RedisService redisService;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("启动成功 初始化数据资源");
        System.out.println(servletContextEvent.getServletContext().getServerInfo());
        redisService.resetMonitor();
        redisService.resetRuleList();
        redisService.resetAlarmList();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听器销毁");
    }
}