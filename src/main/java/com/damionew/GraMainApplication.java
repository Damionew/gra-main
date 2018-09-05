package com.damionew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@EnableWebSocket
/**@EnableDiscoveryClient**/
@SpringBootApplication
@MapperScan("com.damionew.mapper")  /**开启自动扫描Mapper**/
public class GraMainApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(GraMainApplication.class, args);
	}
	// 部署到外部Tomcat服务器时需注释掉
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
