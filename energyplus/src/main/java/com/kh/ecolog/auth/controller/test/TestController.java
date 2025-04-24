package com.kh.ecolog.auth.controller.test;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;

@RestController
@RequestMapping("test")
public class TestController {
    
	@GetMapping("/auth-check")
	public ResponseEntity<?> checkAuth(@AuthenticationPrincipal CustomUserDetails userDetails) {
	    if (userDetails == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                           .body(Map.of("error", "인증 정보가 없습니다. 로그인이 필요합니다."));
	    }
	    
	    return ResponseEntity.ok(Map.of(
	        "message", "인증 성공!",
	        "user", userDetails.getUsername(),
	        "userId", userDetails.getUserId()
	    ));
	}
}
