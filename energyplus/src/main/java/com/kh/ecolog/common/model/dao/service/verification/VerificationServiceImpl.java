package com.kh.ecolog.common.model.dao.service.verification;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerificationServiceImpl implements VerificationService {
	
	// private final Map<String, VerificationData> verificationCodes = new ConcurrentHashMap<>();
	

	@Override
	public String generateVerificationCode(String email) {
		
		return null;
	}

	@Override
	public boolean verifyCode(String email, String code) {
		
		return false;
	}

	@Override
	public boolean isEmailVerified(String email) {
		
		return false;
	}

	@Override
	public void removeVerifiedEmail(String email) {
		
		
	}

}
