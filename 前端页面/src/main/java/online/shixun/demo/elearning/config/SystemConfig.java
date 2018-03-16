package online.shixun.demo.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import online.shixun.demo.elearning.dto.TulingConfig;

@Configuration
public class SystemConfig {
	
	@Bean
	public TulingConfig tulingConfig(){
		String url = "http://openapi.tuling123.com/openapi/api/v2";
		return new TulingConfig(url);
	}
	

}
