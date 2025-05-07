package com.kh.ecolog.auth.controller;

import com.kh.ecolog.auth.model.dto.KakaoUserInfoDTO;
import com.kh.ecolog.auth.model.dto.OAuthLoginResponseDTO;
import com.kh.ecolog.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/oauth2")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    
    @Value("${oauth2.kakao.base-url}")
    private String baseUrl;
    
    @Value("${oauth2.kakao.client-id}")
    private String clientId;
    
    @Value("${oauth2.kakao.redirect-uri}")
    private String redirectUri;

    /**
     * 카카오 로그인 URL 생성
     */
    @GetMapping("/kakao/url")
    public ResponseEntity<?> getKakaoLoginUrl() {
        try {

            String kakaoLoginUrl = baseUrl + "?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&response_type=code";
            
            return ResponseEntity.ok().body(Map.of("loginUrl", kakaoLoginUrl));
        } catch (Exception e) {
            log.error("카카오 로그인 URL 생성 오류", e);
            return ResponseEntity.badRequest().body(Map.of("error", "카카오 로그인 URL 생성 중 오류가 발생"));
        }
    }

    /**
     * 카카오 로그인 콜백 처리
     */
    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        log.info("카카오 콜백 >> 인증 코드: {}", code);
        
        try {
        	
            String accessToken = oAuthService.getKaKaoAccessToken(code);
            
            KakaoUserInfoDTO kakaoUserInfo = oAuthService.getKakaoUserInfo(accessToken);
            
            OAuthLoginResponseDTO loginResponse = oAuthService.processKakaoLogin(kakaoUserInfo);
            
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            log.error("카카오 로그인 처리 중 오류 발생", e);
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
