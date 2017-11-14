package online.shixun.project.controller;

import java.util.Date;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import online.shixun.project.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@RequestMapping(path="/getUser",method=RequestMethod.GET)
	public String getUser( ModelMap model,@RequestParam("name") String name ){
		Date date = userService.getUser(name);
		model.addAttribute("date",date);
		return "user";
		
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
