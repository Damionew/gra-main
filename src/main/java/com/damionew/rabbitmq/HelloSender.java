/**
 * @author yinyunqi
 * @datetime 2018年8月26日
 * @Content 
 */
package com.damionew.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
	public final String RabbitMqName = "RedisTest";
	// rabbitTemplate是SpringBoot提供的默认实现
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void send() {
		String context = "hello " + new Date();
		System.out.print("Sender : "+context);
		this.rabbitTemplate.convertAndSend("hello", context);
		stringRedisTemplate.opsForValue().set(RabbitMqName, "Redis Info:"+context);
	}
}
