/**
 * @author yinyunqi
 * @datetime 2018年5月30日
 * @Content 
 */
package com.damionew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	
	@ResponseBody
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
