/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package online.shixun.project.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import online.shixun.project.model.Student;

@Controller
public class LoginController {
	//暂存学生信息
	private List<Student> list = new ArrayList<Student>();
	
	/**
	 * 获取学生信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getStudent(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("list", list);
		return "index";
	}
	
	/**
	 * 保存
	 * freemarker必须用modelmap
	 * @param student
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student,@ModelAttribute("model") ModelMap model){
		list.add(student);
		model.addAttribute("list",list);
		return "index";
		
	}

}
