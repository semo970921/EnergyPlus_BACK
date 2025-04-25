package com.kh.ecolog.common.model.dao.service.email;

public interface EmailService {
	
	/**
	 * 이메일 발송 메서드
	 * @param to 수신자 이메일
	 * @param subject 이메일 제목
	 * @param content 이메일 내용
	 */
	void sendEmail(String to, String subject, String content);
	
	/**
	 * 회원가입 이메일 인증코드 발송
	 * @param to
	 * @param verificationCode 인증코드
	 */
	void sendSignUpVerificationEmail(String to, String verificationCode);

}
