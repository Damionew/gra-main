/**
 * @author yinyunqi
 * @datetime 2018年8月26日
 * @Content 
 */
package com.damionew.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver :"+hello);
		String redisInfo = stringRedisTemplate.opsForValue().get("RedisTest");
		System.out.println("Redis get Info:"+redisInfo);
	}
}
