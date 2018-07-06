package com.damionew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.damionew.utils.UserInfoUtil;

/**
 * 聊天室
 * @author yinyunqi
 *
 */
@Controller
public class ChatRoom {
	//聊天室页面
	@RequestMapping("/chatroom")
	public String chatRoomPage(Model model) {
		String username = UserInfoUtil.getCurUsername();
		model.addAttribute("username", username);
		return "chatroom";
	}
	
	
}
