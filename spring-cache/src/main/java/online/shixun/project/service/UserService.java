package online.shixun.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="myCache")
public class UserService {
	
	@Cacheable(cacheManager="ehcacheManager",value="myCache",key="1")
	public Date getUser(String name){
		System.out.println("name 的值为 "+name);
		return new Date();
	}

}
