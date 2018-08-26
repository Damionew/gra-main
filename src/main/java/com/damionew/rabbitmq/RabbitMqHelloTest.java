/**
 * @author yinyunqi
 * @datetime 2018年8月26日
 * @Content 
 */
package com.damionew.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RabbitMqHelloTest {
	
	@Autowired
	private HelloSender helloSender;
	
	@ResponseBody
	@RequestMapping("/rabbitTest")
	public void hello() throws Exception{
		System.out.println("rabbitTest");
		helloSender.send();
	} 
}
