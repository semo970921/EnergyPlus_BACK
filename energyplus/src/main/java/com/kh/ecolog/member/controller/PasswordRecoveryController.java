package com.kh.ecolog.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("members/password")
public class PasswordRecoveryController {

	@PostMapping("/request-reset")
	public ResponseEntity<?> requestPasswordReset (@RequestBody Map<String, String> request){
		String email = request.get("email");
		log.info("비밀번호 재설정 요청한 이메일 : {}", email);
		
		
		
		
		return null;
	}
	
}
