package com.nt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.nt.config.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties
public class BootRestProj09BankingAppDemoApplication {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	@PostConstruct
	public void setUp() {
		
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken()); 
	}

	public static void main(String[] args) {
		SpringApplication.run(BootRestProj09BankingAppDemoApplication.class, args);
	}

}
