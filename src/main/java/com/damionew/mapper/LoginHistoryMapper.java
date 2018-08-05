package com.damionew.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginHistoryMapper {
	public void insertLoginHistory(Map<String,String> map);
	public String selectCountVistors();
}
