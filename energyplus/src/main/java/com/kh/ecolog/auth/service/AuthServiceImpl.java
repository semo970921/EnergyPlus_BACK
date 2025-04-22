package com.kh.ecolog.auth.service;

import java.util.Map;
import java.util.HashMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.dto.LoginDTO;
import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.exception.CustomAuthenticationException;
import com.kh.ecolog.token.model.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public Map<String, String> login(LoginDTO loginDTO) {

	    Authentication authentication = null;
	    try {
	        authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginDTO.getUserEmail(),
	                        loginDTO.getUserPassword()));
	    } catch(AuthenticationException e) {
	        throw new CustomAuthenticationException("이메일 또는 비밀번호를 잘못 입력하셨습니다.");
	    }
	    
	    CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
	    
	    log.info("로그인 성공!");
	    log.info("인증에 성공한 사용자의 정보: {}", user);
	    
	    // 토큰 발급
	    Map<String, String> loginResponse = tokenService.generateToken(user.getUsername(),
	                                                                  user.getUserId());
	    
	    // 사용자 정보 추가
	    loginResponse.put("userEmail", user.getUsername());
	    loginResponse.put("userName", user.getName());
	    
	    return loginResponse;
	}

	@Override
	public CustomUserDetails getUserDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
		    return (CustomUserDetails)auth.getPrincipal();
		}
		
		return null;
	}
	
	@Override
	public void logout(Long userId) {
	    // 토큰 서비스를 통해 해당 사용자의 토큰 삭제
	    tokenService.deleteUserToken(userId);
	    log.info("로그아웃 처리 완료: 사용자 ID = {}", userId);
	    
	    // SecurityContext 초기화
	    SecurityContextHolder.clearContext();
	}
}