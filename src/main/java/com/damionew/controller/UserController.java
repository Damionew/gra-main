package com.damionew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.damionew.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/transactional")
	public void transactionalTest() throws Exception {
		try {
			userService.transactionalTest();
		} catch (Exception e) {
			try {
				userService.transactionalTest2();
			} catch (Exception e2) {
				try {
					userService.transactionalTest3();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
		
		
		
	}
}
