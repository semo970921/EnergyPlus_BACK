package com.kh.ecolog.common.model.dao.service.email;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
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
			
		}catch(MessagingException e) {
			log.error("이메일 발송 실패 : {}", e.getMessage());
			throw new RuntimeException("이메일 발송에 실패했습니다.", e);
		}
		
	}

	@Override
	public void sendSignUpVerificationEmail(String to, String verificationCode) {
		String subject = "[에너지생활 플러스] 회원가입 이메일 인증";
		String content = 
                "<div style='margin:20px;'>" +
                        "<h2>에너지생활 플러스 회원가입을 환영합니다!</h2>" +
                        "<p>회원가입을 완료하기 위한 이메일 인증 코드입니다:</p>" +
                        "<div style='background-color:#f8f9fa; padding:10px; font-size:24px; " +
                            "font-weight:bold; text-align:center; letter-spacing:5px;'>" +
                            verificationCode +
                        "</div>" +
                        "<p>인증 코드는 30분 동안 유효합니다.</p>" +
                        "<p>본인이 요청하지 않았다면 이 이메일을 무시하셔도 됩니다.</p>" +
                        "<p>감사합니다.<br>Ecolog 팀</p>" +
                    "</div>";
		sendEmail(to, subject, content);
	}
	

}
