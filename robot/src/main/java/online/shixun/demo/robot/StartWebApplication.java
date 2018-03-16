/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Spring Boot启动类</p>
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "online.shixun.demo")
public class StartWebApplication {

	/**
	 * <p>Spring Boot启动方法</p>
	 *
	 */
    public static void main(String[] args) {
        SpringApplication.run(StartWebApplication.class, args);
    }

}
