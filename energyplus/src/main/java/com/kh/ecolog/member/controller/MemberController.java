package com.kh.ecolog.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}