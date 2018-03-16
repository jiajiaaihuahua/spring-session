/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.dto;

import org.springframework.stereotype.Component;

/**
 * <p> 系统配置Bean类 </p>
 *
 */
@Component
public class TulingConfig {
	
	// 访问的URL
	private String url ;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TulingConfig(String url) {
		super();
		this.url = url;
	}

	public TulingConfig() {
		super();
	}
	
	
	
	
}
