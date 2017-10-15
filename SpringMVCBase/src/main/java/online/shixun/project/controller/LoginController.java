/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package online.shixun.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录控制器类
 */
@Controller
public class LoginController {

    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "/login/page", method = RequestMethod.GET)
    public String loginPage() {
        //调转到 WEB-INF/views/login.jsp
        return "login";

    }

    /**
     * 响应登录请求
     * @param mode
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(Model mode, @RequestParam("username") String username, @RequestParam("password") String password) {
        //判断登录的用户名和密码是否一致
        if (username.trim().equals("shixun") && password.trim().equals("123")) {
            //用户名及密码一致：向页面传入登录成功信息
            mode.addAttribute("message", "登录成功,用户名称：" + username);
        } else {
            //用户名及密码不一致：向页面传入登录失败信息
            mode.addAttribute("message", "登录失败,用户名称：" + username);
        }
        //调转到 WEB-INF/views/home.jsp
        return "home";
    }

}
