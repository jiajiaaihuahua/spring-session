package online.shixun.project.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import online.shixun.project.dto.UserDto;
import online.shixun.project.service.UserService;

public class Test1 {
	
	@Test
	public void test2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService.getUserById(1l, 18, null).get(0).getName());
		UserDto user = new UserDto();
		user.setId(1l);
		user.setName("name1");
		userService.updateUser(user);
	}

}
