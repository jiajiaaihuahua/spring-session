package online.shixun.project.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import online.shixun.project.spring.model.UserModel;
import online.shixun.project.spring.service.UserService;

public class UserController {
	
	private UserService userService;
	
	List<UserModel> list =null;
	/**
	 * 获取用户信息,返回页面
	 * @return
	 */
	public String saveUser(){
		UserModel user = new UserModel();
		user.setId(9L);
		user.setName("qingshixun9");
		user.setAge(9);
		userService.saveUser(user);
		return "success";
	}
	
	public String getUser(){
		list = userService.getUser();
		return "success";
	
	}
	
	
	/**
	 * 把需要的业务类注入进来
	 * @param userService
	 */
	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserModel> getList() {
		return list;
	}

	public void setList(List<UserModel> list) {
		this.list = list;
	}
	
	
	

}
