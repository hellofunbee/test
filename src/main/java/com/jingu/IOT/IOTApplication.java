package com.jingu.IOT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@EnableAsync(proxyTargetClass=true)    //配置代理为cglib代理，默认使用 的是jdk动态代理
@SpringBootApplication
@EnableTransactionManagement
public class IOTApplication
{
    public static void main( String[] args )
    {
       SpringApplication.run(IOTApplication.class, args);
//       new Thread(new RunEntity()).start();
    }

}
