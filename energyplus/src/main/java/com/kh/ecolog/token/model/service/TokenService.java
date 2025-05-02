package com.kh.ecolog.token.model.service;

import java.util.Map;

public interface TokenService {
	
	/**
	 * 토큰 생성(액세스 토큰, 리프레시 토큰) 
	 * @param userEmail
	 * @param userId 사용자 시퀀스 번호
	 * @return 액세스토큰과 리프레시 토큰을 담은 Map
	 */
	Map<String, String> generateToken(String userEmail, Long userId, String role);
	
	/**
	 * 리프레시 토큰으로 새로운 토큰 발급
	 * @param refreshToken
	 * @return
	 */
	Map<String, String> refreshToken(String refreshToken);
	
	/**
	 * 특정 사용자의 토큰 삭제
	 * @param userId
	 */
	void deleteUserToken(Long userId);

}
