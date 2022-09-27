package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
public class MvcAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcAppApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
	public RestTemplate template() {
		return new RestTemplate() ; 
	}
}
