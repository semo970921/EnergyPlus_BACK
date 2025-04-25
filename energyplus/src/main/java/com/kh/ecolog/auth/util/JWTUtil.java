package com.kh.ecolog.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secretKey;
	
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		
		byte[] keyArr = Base64.getDecoder().decode(secretKey); 
		this.key = Keys.hmacShaKeyFor(keyArr);
		log.info("JWT 시크릿 키 초기화 완료");
		
	}
	
	
	/**
	 * 액세스 토큰 생성 (30분 유효)
	 * @param userEmail
	 * @param userId
	 * @return
	 */
	public String getAccessToken(String userEmail, Long userId) {
		return Jwts.builder()
				   .subject(userId.toString())
				   .claim("userEmail", userEmail)
				   .issuedAt(new Date())
				   .expiration(new Date(System.currentTimeMillis() + 3600000L / 2))
				   .signWith(key)
				   .compact();
	}
	
	
	/**
	 * 리프레시 토큰 생성 (30일 유효)
	 * @param userEmail
	 * @param userId
	 * @return
	 */
	public String getRefreshToken(String userEmail, Long userId) {
		return Jwts.builder()
				   .subject(userId.toString())
				   .claim("userEmail", userEmail)
				   .issuedAt(new Date())
				   .expiration(new Date(System.currentTimeMillis() + 3600000L * 24 * 30))
				   .signWith(key)		   
				   .compact();
	}
	
	
	/**
	 * JWT 토큰 파싱 및 검증
	 * @param token 검증할 토큰
	 * @return 토큰에서 추출한 클레임 정보
	 */
	public Claims parseJwt(String token) {
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
	
	
	
    /**
     * 토큰 만료 시간 조회
     * @param token 조회할 토큰
     * @return 만료 시간 (ms로)
     */
    public Long getExpirationTime(String token) {
        Claims claims = parseJwt(token);
        return claims.getExpiration().getTime();
    }
    
    /**
     * 토큰에서 사용자 ID 추출
     * @param token 토큰
     * @return 사용자 ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = parseJwt(token);
        return Long.parseLong(claims.getSubject());
    }
    
    /**
     * 토큰에서 사용자 이메일 추출
     * @param token 토큰
     * @return 사용자 이메일
     */
    public String getUserEmailFromToken(String token) {
        Claims claims = parseJwt(token);
        return claims.get("userEmail", String.class);
    }
	

}
