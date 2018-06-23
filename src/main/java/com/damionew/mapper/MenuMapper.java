/**
 * @author yinyunqi
 * @datetime 2018年6月23日
 * @Content 
 */
package com.damionew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.damionew.model.Menu;

@Mapper
public interface MenuMapper {
	public List<Menu> parentMenu();
	
	public List<Menu> childMenu(String parentId); 
}
