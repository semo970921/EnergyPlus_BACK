package com.kh.ecolog.common.model.dao.service.verification;

public interface VerificationService {

	/**
	 * 인증 코드 생성 및 저장
	 * @param email
	 * @return
	 */
	String generateVerificationCode(String email);
	
	/**
	 * 인증 코드 검증
	 * @param email 
	 * @param code
	 * @return 검증결과(t->유효한 코드, f->유효하지 않은 코드)
	 */
	boolean verifyCode(String email, String code);
	
	/**
	 * 이메일이 인증되었는지 확인
	 * @param email 확인할 이메일
	 * @return 인증여부(t->인증됨, f->인증되지않음)
	 */
	boolean isEmailVerified(String email);
	
	/**
	 * 인증된 이메일 제거
	 * @param email
	 */
	void removeVerifiedEmail(String email);
	
}
