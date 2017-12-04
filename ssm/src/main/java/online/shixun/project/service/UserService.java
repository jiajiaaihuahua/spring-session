package online.shixun.project.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import online.shixun.project.dao.UserDao;
import online.shixun.project.dto.UserDto;

/**
 * 用户业务类
 * @author mrliu
 *
 */
@Service
public class UserService {
	
	
	private UserDao userDao;
	
	public List<UserDto> getUser(int id){
		return userDao.getUser(id);
	}
	
	public List<UserDto> getUserById(long id,int age,String name){
		return userDao.getUserById(id, age, name);
	}
	
	public void updateUser(UserDto user){
		userDao.updateUser(user);
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
	

}
