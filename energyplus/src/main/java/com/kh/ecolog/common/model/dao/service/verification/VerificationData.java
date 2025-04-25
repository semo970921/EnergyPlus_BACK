package com.kh.ecolog.common.model.dao.service.verification;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 이메일 인증 코드 데이터를 저장하기 위한 클래스
 */
@Getter
@AllArgsConstructor
public class VerificationData {

	private final String code;
	private final LocalDateTime createdAt;
	
	
}
