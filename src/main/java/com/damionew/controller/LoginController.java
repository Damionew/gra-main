package com.damionew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.damionew.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("test")
	public void test() {
		
		userService.test();
	}
}
