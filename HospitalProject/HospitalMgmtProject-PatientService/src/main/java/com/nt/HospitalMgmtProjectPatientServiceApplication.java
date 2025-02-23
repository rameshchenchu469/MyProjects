package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class HospitalMgmtProjectPatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalMgmtProjectPatientServiceApplication.class, args);
	}

}
