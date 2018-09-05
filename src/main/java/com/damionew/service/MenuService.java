package com.damionew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damionew.mapper.MenuMapper;
import com.damionew.model.Menu;

@Service
public class MenuService {
	@Autowired
	MenuMapper menuMapper;
	
	public List<Menu> menuList(){
		//1.查询父菜单
		List<Menu> parentMenus = menuMapper.parentMenu();
		//2.遍历父菜单
		for(Menu menu: parentMenus ) {
			String parentId = menu.getMenuId();
			//3.根据父菜单id查询子菜单
			List<Menu> childMenus = menuMapper.childMenu(parentId);
			menu.setChildMenus(childMenus);
		}
		return parentMenus;
	}
	
	
	public void menuAdd(String parentMenu,String menuName,String menuUrl) {
		// 首先判断是否有相同菜单名
		List<Menu> menuList = menuMapper.selectMenuByName(menuName);
		
		System.out.println(menuList.get(0).getName());
	}
}
