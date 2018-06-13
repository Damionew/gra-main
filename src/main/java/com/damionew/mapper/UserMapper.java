package com.damionew.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.damionew.model.User;

@Mapper
public interface UserMapper {
	public void insertTest();
	public User findUserByUserName(String username);
	public List<Map<String, String>> findUserRoleByUserName(String username);
}
