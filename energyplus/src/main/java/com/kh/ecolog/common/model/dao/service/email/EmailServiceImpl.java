package com.kh.ecolog.common.model.dao.service.email;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String senderEmail;

	/**
	 * 이메일 발송 
	 */
	@Override
	public void sendEmail(String to, String subject, String content) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom(senderEmail);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(content);
			
			mailSender.send(message);
			
			log.info("이메일 발송 성공 : 수신자 = {}", to);
			
		}catch(Exception e) {
			log.error("이메일 발송 실패 : {}", e.getMessage());
			throw new RuntimeException("이메일 발송에 실패하셨습니다.",e);
		}
		
	}

	@Override
	public void sendSignUpVerificationEmail(String to, String verificationCode) {
	    String subject = "[에너지생활 플러스] 회원가입 이메일 인증";
	    String content = 
	            "에너지생활 플러스 회원가입을 환영합니다!\n\n" +
	            "회원가입을 완료하기 위한 이메일 인증 코드입니다:\n\n" +
	            verificationCode + "\n\n" +
	            "인증 코드는 30분 동안 유효합니다.\n\n" +
	            "본인이 요청하지 않았다면 이 이메일을 무시하셔도 됩니다.\n\n" +
	            "감사합니다.\n" +
	            "Ecolog 팀";
	    
	    sendEmail(to, subject, content);
	}
	
	@Override
	public void sendPasswordResetEmail(String to, String verificationCode) {
	    String subject = "[에너지생활 플러스] 비밀번호 재설정 안내";
	    String content = 
	            "안녕하세요! 에너지생활 플러스입니다.\n\n" +
	            "비밀번호 재설정을 위한 인증 코드입니다:\n\n" +
	            verificationCode + "\n\n" +
	            "인증 코드는 30분 동안 유효합니다.\n\n" +
	            "본인이 요청하지 않았다면 이 이메일을 무시하시고, 계정 보안을 위해 비밀번호를 변경해주세요.\n\n" +
	            "감사합니다.\n" +
	            "Ecolog 팀";
	    
	    sendEmail(to, subject, content);
	}
	

}
