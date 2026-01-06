package com.amazon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	public void sendPlainMail(String subject, String descriptiion, String fromEmailAddress, String toEmailAddress) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmailAddress);
		message.setTo(toEmailAddress);
		message.setSubject(subject);
		message.setText(descriptiion);

		javaMailSender.send(message);
	}

}
