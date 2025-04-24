package com.kh.ecolog.common.model.dao.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public void sendEmail(String to, String subject, String content) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom(senderEmail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			mailSender.send(message);
			
			log.info("이메일 발송 성공 : 수신자 = {}", to);
			
		}catch() {
			// 예외처리
		}
		
	}

	@Override
	public void sendSignUpVerificationEmail(String to, String verificationCode) {
		
		
	}
	

}
