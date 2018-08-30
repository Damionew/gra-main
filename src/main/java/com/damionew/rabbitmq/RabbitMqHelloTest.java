/**
 * @author yinyunqi
 * @datetime 2018年8月26日
 * @Content 
 */
package com.damionew.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

@Controller
public class RabbitMqHelloTest {
	
	@Autowired
	private HelloSender helloSender;
	
	@ApiOperation(value = "RabbitMq消息队列测试")
	@ResponseBody
	@RequestMapping(value = "/rabbitTest",method = RequestMethod.GET)
	public void hello() throws Exception{
		System.out.println("rabbitTest");
		helloSender.send();
	} 
}
