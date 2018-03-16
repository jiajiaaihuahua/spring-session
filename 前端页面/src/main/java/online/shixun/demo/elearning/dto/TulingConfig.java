/********************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.elearning.dto;

import org.springframework.stereotype.Component;

@Component
public class TulingConfig {
	
	// 访问的url
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
