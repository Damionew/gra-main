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
import com.damionew.service.LoginHistoryService;
import com.damionew.service.MenuService;
import com.damionew.utils.UserInfoUtil;

@Controller
public class IndexController {
	
	@Autowired
	MenuService menuService;
	@Autowired
	LoginHistoryService loginHistoryService;
	@RequestMapping(value= {"/index","/"})
	public String indexPage(Model model) {
		
		// 菜单（二级）
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menuList", menuList);
		String curentUserName = UserInfoUtil.getCurUsername();
		model.addAttribute("curentUserName", curentUserName);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("menuList",menuList);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/countVistors")
	public String countVistors() {
		String count = loginHistoryService.selectCountVistors();
		System.out.println(count);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("countVistors",count);
		return jsonObject.toJSONString();
	}
}
