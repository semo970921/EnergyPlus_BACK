package com.kh.ecolog.common.model.dao.service.verification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerificationServiceImpl implements VerificationService {
	
	// 인증 코드 저장 MAP
	private final Map<String, VerificationData> verificationCodes = new ConcurrentHashMap<>();
	
	// 인증된 이메일 목록
	private final Set<String> verifiedEmails= ConcurrentHashMap.newKeySet();
	
	// 인증 코드 유효 시간(분)
	private final int VERIFICATION_CODE_EXPIRY_MINUTES = 30;

	/**
	 * 인증 코드 생성 및 저장
	 * @param email 사용자 이메일
	 * @return 생성된 인증 코드
	 */
	@Override
	public String generateVerificationCode(String email) {
		// 6자리 랜덤 숫자 코드 생성
		String code = generateRandomCode();
		
		// 인증 데이터 생성 및 저장
		VerificationData data = new VerificationData(code, LocalDateTime.now());
		verificationCodes.put(email, data);
		
		log.info("인증 코드 생성: 이메일 = {}, 코드 = {}", email, code);
		
		return code;
	}

	/**
	 * 인증 코드 검증
	 * @param email 
	 * @param code
	 * @return 검증결과(t->유효한 코드, f->유효하지 않은 코드)
	 */
	@Override
	public boolean verifyCode(String email, String code) {
		VerificationData data = verificationCodes.get(email);
		
		if (data == null) {
			log.warn("인증 코드 없음: 이메일 = {}", email);
			return false;
		}
		
		if (isCodeExpired(data.getCreatedAt())) {
			log.warn("인증 코드 만료: 이메일 = {}", email);
			verificationCodes.remove(email);
			return false;
		}
		
		// 인증 코드 일치 여부 확인
		if (!data.getCode().equals(code)) {
			log.warn("인증 코드 불일치: 이메일 = {}, 입력 코드 = {}, 저장 코드 = {}", 
					email, code, data.getCode());
			return false;
		}
		
		// 인증 성공 - 인증된 이메일 목록에 추가하고 인증 코드 삭제
		verifiedEmails.add(email);
		verificationCodes.remove(email);
		log.info("이메일 인증 성공: 이메일 = {}", email);
		
		return true;
	}

	/**
	 * 이메일 인증 여부 확인
	 * @param email 사용자 이메일
	 * @return 인증 여부 (true: 인증됨, false: 인증되지 않음)
	 */
	@Override
	public boolean isEmailVerified(String email) {
		return verifiedEmails.contains(email);
	}

	/**
	 * 인증된 이메일 제거 (회원가입 완료 후 호출)
	 * @param email 사용자 이메일
	 */
	@Override
	public void removeVerifiedEmail(String email) {
		verifiedEmails.remove(email);
		log.info("인증된 이메일 제거: 이메일 = {}", email);
	}
	
	// ---------------------------------------------
	
	/**
	 * 6자리 랜덤 인증 코드 생성
	 * @return 생성된 인증 코드
	 */
	private String generateRandomCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000); // 100000 ~ 999999
		return String.valueOf(code);
	}
	
	/**
	 * 인증 코드 만료 여부 확인
	 * @param createdAt 인증 코드 생성 시간
	 * @return 만료 여부 (true: 만료됨, false: 유효함)
	 */
	private boolean isCodeExpired(LocalDateTime createdAt) {
		return createdAt.plusMinutes(VERIFICATION_CODE_EXPIRY_MINUTES)
				.isBefore(LocalDateTime.now());
	}
}
