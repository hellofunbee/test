package com.jingu.IOT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

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
