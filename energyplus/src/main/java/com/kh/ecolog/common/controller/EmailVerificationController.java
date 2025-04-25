package com.kh.ecolog.common.controller;


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
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class EmailVerificationController {
    
    private final EmailService emailService;
    private final VerificationService verificationService;
    private final MemberMapper memberMapper;
    
    /**
     * 이메일 인증 코드 발송 API
     * @param request 이메일이 포함된 요청 객체
     * @return 응답 메시지
     */
    @PostMapping("/send-code")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        log.info("인증 코드 발송 요청: 이메일 = {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "이메일을 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 이메일 형식 검증
        if (!email.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "유효한 이메일 형식이 아닙니다.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 이메일 중복 확인
        if (memberMapper.existsByEmail(email) > 0) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "이미 가입된 이메일입니다.");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 인증 코드 생성
        String verificationCode = verificationService.generateVerificationCode(email);
        
        // 이메일 발송
        emailService.sendSignUpVerificationEmail(email, verificationCode);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "인증 코드가 이메일로 발송되었슴니다.");
        return ResponseEntity.ok(response);
    }
    
    /**
     * 이메일 인증 코드 확인 API
     * @param 
     * @return 인증 결과 응답
     */
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        
        log.info("인증 코드 확인 요청: 이메일 = {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "이메일을 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
        
        if (code == null || code.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "인증 코드를 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean isVerified = verificationService.verifyCode(email, code);
        
        Map<String, String> response = new HashMap<>();
        
        if (isVerified) {
            response.put("message", "이메일 인증이 성공적으로 완료되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "인증 코드가 유효하지 않거나 만료되었습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * 이메일 인증 상태 확인 API
     * @param 
     * @return 인증 상태 응답
     */
    @PostMapping("/check-status")
    public ResponseEntity<?> checkVerificationStatus(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        
        if (email == null || email.trim().isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "이메일을 입력해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean isVerified = verificationService.isEmailVerified(email);
        
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("verified", isVerified);
        
        return ResponseEntity.ok(response);
    }
}
