package com.persistent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SsrsClassesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsrsClassesServiceApplication.class, args);
	}

}
