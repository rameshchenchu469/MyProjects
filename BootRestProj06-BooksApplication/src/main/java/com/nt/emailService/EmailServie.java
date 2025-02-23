package com.nt.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServie {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendData(String emailTo,String subject,String body) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setTo(emailTo);
		helper.setSubject(subject);
		helper.setText(body,true);
		
		mailSender.send(message);
	}
}
