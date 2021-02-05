package com.persistent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SsrsServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsrsServiceRegistryApplication.class, args);
	}

}
