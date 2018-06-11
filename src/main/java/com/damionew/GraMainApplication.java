package com.damionew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GraMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraMainApplication.class, args);
	}
}
