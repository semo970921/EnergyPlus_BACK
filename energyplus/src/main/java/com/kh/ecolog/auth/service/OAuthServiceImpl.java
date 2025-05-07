package com.kh.ecolog.auth.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.ecolog.auth.model.dao.OAuthMapper;
import com.kh.ecolog.exception.OAuthProcessingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl {
	
	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;
	
    @Value("${oauth2.kakao.client-id}")
    private String clientId;
    
    @Value("${oauth2.kakao.redirect-uri}")
    private String redirectUri;

	public String getKaKaoAccessToken(String code) {
		
		log.info("카카오 액세스토큰 요청 >> 인증코드 : {}", code);
		
		// 요총헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// 바디 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);
        
        // HTTP 요청 Entity 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
		
        try {
            // 카카오 토큰 API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            
            // 응답 처리
            if (response.getStatusCode() == HttpStatus.OK) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                return jsonNode.get("access_token").asText();
            } else {
                log.error("카카오 토큰 요청 실패: {}", response.getStatusCodeValue());
                throw new OAuthProcessingException("카카오 토큰 요청 실패");
            }
        } catch (JsonProcessingException e) {
            log.error("카카오 토큰 파싱 오류", e);
            throw new OAuthProcessingException("카카오 토큰 파싱 오류: " + e.getMessage());
        } catch (Exception e) {
            log.error("카카오 토큰 요청 중 오류 발생", e);
            throw new OAuthProcessingException("카카오 토큰 요청 중 오류 발생: " + e.getMessage());
        }
	}
	
}
