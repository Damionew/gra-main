/**
 * @author yinyunqi
 * @datetime 2018年5月30日
 * @Content 
 */
package com.damionew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
