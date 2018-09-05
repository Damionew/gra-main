package com.damionew.controller.console;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.damionew.model.Menu;
import com.damionew.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags="菜单管理")
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	@ApiOperation(value="返回菜单页面",notes="")
	@GetMapping(value="menu")
	public String menuIndex(Model model) {
		List<Menu> menuList = menuService.menuList();
		model.addAttribute("menu",menuList);
		return "console/menu";
	}
	
	@ApiOperation(value="添加子菜单",notes="")
	@GetMapping(value="menu/addMenu")
	@ResponseBody
	public String menuAdd(String parentMenu,String menuName,String menuUrl) {
		System.out.println(parentMenu+menuName+menuUrl);
		menuService.menuAdd(parentMenu,menuName,menuUrl);
		JSONObject object = new JSONObject();
		object.put("code", 1);
		return object.toJSONString();
	}
	
}
