package com.nt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.config.TwilioConfig;
import com.nt.dto.OtpRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;


@Service
public class OtpService {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	public int sendOtp(String phoneNumber) {
        int otp = generateOtp();
        String messageBody = "Your OTP is: " + otp;

        try {
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioConfig.getPhNumber()),
                    messageBody
            ).create();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send OTP via SMS");
        }

        return otp;
    }

    private int generateOtp() {
        return (int) (Math.random() * 900000) + 100000; // Generates a 6-digit OTP
    }
}
