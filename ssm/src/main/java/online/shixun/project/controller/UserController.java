package online.shixun.project.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.project.dto.UserDto;
import online.shixun.project.service.UserService;

/**
 * 用户控制器类
 * @author mrliu
 *
 */
@Controller
public class UserController {
	
	private UserService userService;
	
	@RequestMapping(path="/getUser/{id}", method=RequestMethod.GET)
	public String getUser(Model model,@PathVariable("id") String id){
		System.out.println("id 的值是"+id);
		List<UserDto> list = userService.getUser(2);
		model.addAttribute("userList", list);
		return "showUser";
	}
	
	@RequestMapping(path="/demo1", method=RequestMethod.GET)
	@ResponseBody
	public String demo1(){
		return "你好";
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
