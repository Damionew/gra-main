/**
 * @author yinyunqi
 * @datetime 2018年8月14日
 * @Content 消息队列配置
 */
package com.damionew.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
	public static final String QUEUE_NAME = "spring-boot-simple";
	@Bean
	public Queue queue(){
		return new Queue(QUEUE_NAME);
	}
}
