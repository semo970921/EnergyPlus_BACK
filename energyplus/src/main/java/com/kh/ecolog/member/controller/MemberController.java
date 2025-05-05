package com.kh.ecolog.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.member.model.dto.MemberDTO;
import com.kh.ecolog.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/**
	 * 회원가입API
	 * @param member
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid MemberDTO member){
		
		log.info("회원가입 요청: {}", member.getUserEmail());
		
		memberService.signUp(member);
		
	    Map<String, String> response = Map.of(
	            "message", "회원가입이 성공적으로 완료되었습니다.",
	            "userEmail", member.getUserEmail()
	        );
		
		
		log.info("회원가입 성공: {}", member.getUserEmail());
		return ResponseEntity.status(201).body(response);
	}
	
	/**
	 * 회원탈퇴 API
	 * @param userDetails 인증된 사용자 
	 * @return
	 */
	@DeleteMapping("/withdrawal")
	public ResponseEntity<?> withdrawMember(@AuthenticationPrincipal CustomUserDetails userDetails) {
		
		// 로그인X
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "인증 정보가 없으므로 로그인이 필요합니다."));
        }
        
        // 로그인O
        Long userId = userDetails.getUserId();
        log.info("회원 탈퇴 요청 : 사용자ID = {}", userId);
        
        boolean result = memberService.withdrawMember(userId);
        if(result) {
        	return ResponseEntity.ok(Map.of("message", "회원탈퇴 완료!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "회원 탈퇴 처리 중 오류가 발생했습니다."));
        }
        
        
	}

}