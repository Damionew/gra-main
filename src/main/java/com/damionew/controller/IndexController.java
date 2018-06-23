/**
 * @author yinyunqi
 * @datetime 2018年6月23日
 * @Content 
 */
package com.damionew.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.damionew.model.Menu;
import com.damionew.service.MenuService;

@Controller
public class IndexController {
	
	@Autowired
	MenuService menuService;
	
	@RequestMapping("/index")
	public String indexPage(Model model) {
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menuList", menuList);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("menuList",menuList);
		return "index";
	}
}
