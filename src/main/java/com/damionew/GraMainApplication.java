package com.damionew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.damionew.mapper")  //开启自动扫描Mapper
public class GraMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraMainApplication.class, args);
	}
}
