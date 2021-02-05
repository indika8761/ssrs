package com.persistent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.persistent.filters.ErrorFilter;
import com.persistent.filters.PostFilter;
import com.persistent.filters.PreFilter;
import com.persistent.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class SsrsApigatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsrsApigatwayApplication.class, args);
	}
	
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

}
