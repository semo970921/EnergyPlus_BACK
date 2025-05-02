package com.kh.ecolog.token.model.service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kh.ecolog.auth.util.JWTUtil;
import com.kh.ecolog.exception.InvalidTokenException;
import com.kh.ecolog.token.model.dao.TokenMapper;
import com.kh.ecolog.token.vo.RefreshToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
	
	private final JWTUtil jwtUtil;
	private final TokenMapper tokenMapper;
	
	@Override
	@Transactional
	public Map<String, String> generateToken(String userEmail, Long userId, String role) {
		
		// 액세스토큰과 리프레시토큰 생성
		Map<String, String> tokens = createTokens(userEmail, userId, role);
		
		// 기존의 리프레시토큰 삭제
		tokenMapper.deleteTokenByUserId(userId);
		
		// 리프레시토큰을 DB에 저장
		saveRefreshToken(tokens.get("refreshToken"), userId);
		
		// 만료된 토큰 삭제
		tokenMapper.deleteExpiredRefreshToken(System.currentTimeMillis());
		
		return tokens;
	}
	
	private String getRoleBasedOnUserEmail(String userEmail) {
	    // 실제로는 이 값을 DB에서 조회하거나 특정 로직에 따라 정할 수 있습니다.
	    if (userEmail.contains("admin")) {
	        return "ADMIN";
	    } else {
	        return "USER";
	    }
	}
	
	/**
	 * 리프레시토큰을 DB에 저장
	 * @param refreshToken
	 * @param userId
	 */
	private void saveRefreshToken(String refreshToken, Long userId) {
		
		// 리프레시토큰 유효기간 => 30일
		Long deadline = System.currentTimeMillis() + (3600000L * 24 * 30);
		
		RefreshToken token = RefreshToken.builder()
										 .userId(userId)
										 .token(refreshToken)
										 .deadline(deadline)
										 .build();
		
		tokenMapper.saveToken(token);
		log.info("리프레시토큰 저장 완료: 사용자 시퀀스넘버 = {}", userId);
		
	}
	
	
	/**
	 * 액세스토큰과 리프레시토큰 생성
	 * @param userEmail
	 * @param userId
	 * @return
	 */
	private Map<String, String> createTokens(String userEmail, Long userId, String role){
		
		String accessToken = jwtUtil.getAccessToken(userEmail, userId, role);
		String refreshToken = jwtUtil.getRefreshToken(userEmail, userId, role);
		
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}
	
	
	@Override
	@Transactional
	public Map<String, String> refreshToken(String refreshToken) {
		
		try {
			// 리프레시토큰 검증
			Claims claims = jwtUtil.parseJwt(refreshToken);
			Long userId = Long.parseLong(claims.getSubject());
			String userEmail = jwtUtil.getUserEmailFromToken(refreshToken);
			
			// 사용자 역할 추출 (이메일을 기준으로 역할 조회)
			String role = getRoleBasedOnUserEmail(userEmail);
			
			// DB에서 리프레시토큰 조회
			RefreshToken tokenEntity = tokenMapper.findByToken(refreshToken);
			
			if(tokenEntity == null) {
				throw new InvalidTokenException("존재하지 않는 리프레시 토큰입니다.");
			}
			
			// 토큰 유효기간 확인
			if (tokenEntity.getDeadline() < System.currentTimeMillis()) {
				tokenMapper.deleteTokenByUserId(tokenEntity.getUserId());
				throw new InvalidTokenException("만료된 리프레시 초큰입니다.");
			}
			
			return generateToken(userEmail, userId, role);
			
		} catch (ExpiredJwtException e) {
			throw new InvalidTokenException("만료된 리프레시토큰 입니다.");
		} catch (Exception e) {
			throw new InvalidTokenException("유효하지 않은 리프레시 토큰입니다.");
		}
		
	}
	@Override
	@Transactional
	public void deleteUserToken(Long userId) {
		tokenMapper.deleteTokenByUserId(userId);
		log.info("사용자 시퀀스넘버 삭제 : {} ", userId);
		
	}
}