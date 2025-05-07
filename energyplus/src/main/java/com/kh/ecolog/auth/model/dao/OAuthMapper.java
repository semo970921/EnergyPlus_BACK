package com.kh.ecolog.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.auth.model.dto.OAuthUserDTO;

@Mapper
public interface OAuthMapper {
	
	// OAuth제공자와 그 제공자가 제공한 providerId로 사용자 조회
	OAuthUserDTO findByProviderAndProviderId(@Param("provider") String provider, @Param("providerId") String providerId);
	
	// OAuth2.0 사용자 정보 저장
	void saveOAuthUser(OAuthUserDTO oAuthUser);
	
	// OAuth 사용자 정보 업데이트
	void updateOAuthUser(OAuthUserDTO oAuthUser);
    
    // OAuth 사용자 정보 삭제
    void deleteOAuthUser(@Param("userId") Long userId);	

}
