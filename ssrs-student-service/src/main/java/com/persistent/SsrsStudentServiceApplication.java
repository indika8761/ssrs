package com.persistent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.persistent.serviceproxy")
@EnableCircuitBreaker
public class SsrsStudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsrsStudentServiceApplication.class, args);
	}

}
