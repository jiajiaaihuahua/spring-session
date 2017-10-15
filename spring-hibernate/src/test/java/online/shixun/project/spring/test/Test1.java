package online.shixun.project.spring.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import online.shixun.project.spring.model.UserModel;
import online.shixun.project.spring.service.UserService;

public class Test1 {
	
	
	@Test
	public void test2() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		UserModel user = new UserModel();
		user.setId(5L);
		user.setName("qingshixun5");
		user.setAge(100);
		userService.saveUser(user);
	}

}
