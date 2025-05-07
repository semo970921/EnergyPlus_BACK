package com.kh.ecolog.auth.service;

public interface OAuthService {

	// 카카오인증 코드 -> 액세스토큰 얻음
	String getKaKaoAccessToken(String code);
	
}
