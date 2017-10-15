package online.shixun.project.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import online.shixun.project.spring.dao.UserDao;
import online.shixun.project.spring.model.UserModel;

@Service
public class UserService {
	
	private UserDao userDao;
	
	public void saveUser(UserModel user){
		userDao.saveUser(user);
	}

	/**
	 * 查询用户信息业务类
	 * @return
	 */
	public List<UserModel> getUser(){
		System.out.println("getUser方法开始。。。");
		return userDao.getUser();
	}
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
