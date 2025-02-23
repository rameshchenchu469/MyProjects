package com.hunger.saviour.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HungerSaviourApiGateway {

	public static void main(String[] args) {
		SpringApplication.run(HungerSaviourApiGateway.class, args);
	}

	@Bean
	@LoadBalanced
	@Primary
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

}
