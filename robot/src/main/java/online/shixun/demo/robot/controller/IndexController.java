/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.demo.robot.common.DateUtil;
import online.shixun.demo.robot.common.SensitiveWordFilter;
import online.shixun.demo.robot.dto.Message;
import online.shixun.demo.robot.dto.User;
import online.shixun.demo.robot.service.UserMessageService;

/**
 * <p> 机器人聊天控制器类 </p>
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private UserMessageService userMessageService;
	
	/**
	 * <p> 进入首页  </p>
	 * @return
	 */
	@GetMapping("/")
	public String index(){
		return "index";
	}

	/**
	 * <p> 显示首页用户列表  </p>
	 * @return
	 */
	@PostMapping("/list")
	public String list(Model model){
		List<User> userList = userMessageService.getUser();
		model.addAttribute("userList", userList);
		return "list";
	}

	/**
	 * <p> 搜索首页用户列表  </p>
	 * @return
	 */
	@PostMapping("/search")
	public String search(Model model,@RequestParam("name") String name){
		List<User> userList = userMessageService.getUserByName(name);
		model.addAttribute("userList", userList);
		return "list";
	}
	
	/**
	 * <p> 用户发送消息给机器人，并且返回应答  </p>
	 * @return
	 */
	@PostMapping("/send")
	@ResponseBody
	public String sendUserMessage(Model model ,@RequestParam("sendContent") String sendContent,@RequestParam("userid") Long userid ){
		SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
		sendContent = sensitiveWordFilter.replaceSensitiveWord(sendContent, SensitiveWordFilter.minMatchTYpe, "*");
		String reply = userMessageService.sendUserMessage(sendContent,userid);
		return reply;
	}
	
	/**
	 * <p> 发送消息过滤敏感字符 </p>
	 * @return
	 */
	@PostMapping("/filter")
	@ResponseBody
	public String filterUserMessage(Model model ,@RequestParam("sendContent") String sendContent){
		SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
		sendContent = sensitiveWordFilter.replaceSensitiveWord(sendContent, SensitiveWordFilter.minMatchTYpe, "*");
		return sendContent;
	}
	
	/**
	 * <p> 根据用户id展示用户的基本信息  </p>
	 * @return
	 */
	@GetMapping("/chat")
	public String chat(Model model ,@RequestParam("userid") Long userid ){
		User user = userMessageService.getUserById(userid);
		model.addAttribute("user", user);
		model.addAttribute("week", DateUtil.getWeekOfDate(new Date()));
		return "chat";
	}
	
	/**
	 * <p> 根据用户的userid展示用户的聊天记录  </p>
	 * @return
	 */
	@PostMapping("/history")
	@ResponseBody
	public List getMessage(@RequestParam("userid") Long userid,@RequestParam("pageNo") int pageNo){
		List<Message> list = userMessageService.getMessage(userid,pageNo);
		return list;
	}

}
