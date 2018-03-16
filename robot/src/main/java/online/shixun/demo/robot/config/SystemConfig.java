/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import online.shixun.demo.robot.dto.TulingConfig;

/**
 * <p> 系统需要的配置常量 </p>
 *
 */
@Configuration
public class SystemConfig {
	
	/**
	 * <p> 配置图灵机器人API的访问地址 </p>
	 * @return
	 */
	@Bean
	public TulingConfig tulingConfig(){
		String url = "http://openapi.tuling123.com/openapi/api/v2";
		return new TulingConfig(url);
	}
	

}
