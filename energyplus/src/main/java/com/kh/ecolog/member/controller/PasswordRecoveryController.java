package com.kh.ecolog.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.common.model.dao.service.email.EmailService;
import com.kh.ecolog.common.model.dao.service.verification.VerificationService;
import com.kh.ecolog.member.model.dao.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("members/password")
@RequiredArgsConstructor
public class PasswordRecoveryController {
	
	private final MemberMapper memberMapper;
	private final VerificationService verificationService;
	private final EmailService emailService;

	/**
	 * 비밀번호 재설정 이메일 요청
	 * @param request
	 * @return
	 */
	@PostMapping("/request-reset")
	public ResponseEntity<?> requestPasswordReset (@RequestBody Map<String, String> request){
		String email = request.get("email");
		log.info("비밀번호 재설정 요청한 이메일 : {}", email);
		
	// 이메일 입력x
	if(email == null || email.trim().isEmpty()) {
		Map<String, String> response = new HashMap<>();
		response.put("error", "이메일을 입력해주세요");
		return ResponseEntity.badRequest().body(response);
	}
	
	// 이메일 입력O
	// 사용자 없는 경우
	if(memberMapper.existsByEmail(email) == 0) {
		Map<String, String> response = new HashMap<>();
		response.put("error", "등록되지 않은 이메일입니다.");
		return ResponseEntity.badRequest().body(response);
	}
		
	// 사용자가 맞는 경우
	String verificationCode = verificationService.generateVerificationCode(email);
	emailService.sendPasswordResetEmail(email, verificationCode);
	Map<String,String> response = new HashMap<>();
	response.put("message", "비밀전호 재설정 안내가 이멜로 발송되었습니다.");
	return ResponseEntity.ok(response);
	}
	
	
	/**
	 * 비밀번호 재설정 인증코드 확인
	 * @param request 이메일, 코드
	 * @return 결과
	 */
	@PostMapping("/verifyCode")
	public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> request){
		String email = request.get("email");
		String code = request.get("code");
		
		// 이메일이나 인증코드 입력 안한경우
		if(email == null || email.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "이메일을 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
		}
		
        if (code == null || code.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "인증 코드를 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
		
        // 다 입력한 경우
        boolean isVerified = verificationService.verifyCode(email,  code);
      
        Map<String, Object> response = new HashMap<>();
        
        if(isVerified) {
            response.put("message", "인증이 성공적으로 완료되었습니다.");
            response.put("verified", true);
            return ResponseEntity.ok(response);    	
        }else {
        	response.put("error", "인증코드가 유효하지 않습니다.");
        	response.put("verified", false);
        	return ResponseEntity.badRequest().body(response);
        }
        
	}
	
	
	
}
