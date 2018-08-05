package com.damionew.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damionew.mapper.LoginHistoryMapper;
import com.damionew.utils.DateUtil;
import com.damionew.utils.UserInfoUtil;

@Service
public class LoginHistoryService {
	@Autowired
	LoginHistoryMapper loginHistoryMapper;
	public void insertLoginHistory(Map<String, String> map) {
		map.put("username", UserInfoUtil.getCurUsername());
		map.put("date", DateUtil.DateFormate1());
		loginHistoryMapper.insertLoginHistory(map);
	}
	public String selectCountVistors() {
		String count = loginHistoryMapper.selectCountVistors();
		return count;
	}
}
