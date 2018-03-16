/********************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.elearning.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import online.shixun.demo.elearning.common.DateUtil;
import online.shixun.demo.elearning.common.SensitiveWordFilter;
import online.shixun.demo.elearning.dto.Message;
import online.shixun.demo.elearning.dto.User;
import online.shixun.demo.elearning.service.UserMessageService;

@Controller
public class IndexController {
	
	@Autowired
	private UserMessageService userMessageService;
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@PostMapping("/list")
	public String list(Model model){
		List<User> userList = userMessageService.getUser();
		model.addAttribute("userList", userList);
		return "list";
	}
	
	@PostMapping("/search")
	public String search(Model model,@RequestParam("name") String name){
		List<User> userList = userMessageService.getUserByName(name);
		model.addAttribute("userList", userList);
		return "list";
	}
	
	@PostMapping("/send")
	@ResponseBody
	public String sendUserMessage(Model model ,@RequestParam("sendContent") String sendContent,@RequestParam("userid") Long userid ){
		SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
		sendContent = sensitiveWordFilter.replaceSensitiveWord(sendContent, SensitiveWordFilter.minMatchTYpe, "*");
		String reply = userMessageService.sendUserMessage(sendContent,userid);
		return reply;
	}
	
	@PostMapping("/filter")
	@ResponseBody
	public String filterUserMessage(Model model ,@RequestParam("sendContent") String sendContent){
		SensitiveWordFilter sensitiveWordFilter = new SensitiveWordFilter();
		sendContent = sensitiveWordFilter.replaceSensitiveWord(sendContent, SensitiveWordFilter.minMatchTYpe, "*");
		return sendContent;
	}
	
	@GetMapping("/chat")
	public String chat(Model model ,@RequestParam("userid") Long userid ){
		User user = userMessageService.getUserById(userid);
		model.addAttribute("user", user);
		model.addAttribute("week", DateUtil.getWeekOfDate(new Date()));
		return "chat";
	}
	
	@PostMapping("/history")
	@ResponseBody
	public String getMessage(@RequestParam("userid") Long userid,@RequestParam("pageNo") int pageNo){
		List<Message> list = userMessageService.getMessage(userid,pageNo);
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject(); 
		for(Message message : list ){
			json.put("sendContent", message.getSendContent());
			json.put("replyContent", message.getReplyContent());
			json.put("createTime", message.getCreateTimeString());
			array.add(message);
		}
		return array.toJSONString();
	}

}
