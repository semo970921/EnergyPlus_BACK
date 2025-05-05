package com.kh.ecolog.admin.memberManage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.admin.memberManage.model.service.MemberManageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/admin/members")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
public class MemberManageController {
	
	private final MemberManageService memberManageService;
	
	/**
	 * 전체 회원
	 * @param page
	 * @param keyword
	 * @param dateRange
	 * @param grade
	 * @param status
	 * @param role
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllMembers(
			@RequestParam(name="page", defaultValue = "0") int page, // 페이지번호
			@RequestParam(name="keyword", required=false) String keyword, // 검색어
			@RequestParam(name="dateRange", required=false) String dateRange, // 가입일범위
			@RequestParam(name="grade", required=false) String grade, // 등급
			@RequestParam(name="status", required=false) String status, // 상태(회원/탈퇴)
			@RequestParam(name="role", required=false) String role){ // 역할(관리자/사용자)
	
		// 모든 필터를 Map으로~
		Map<String, Object> filter = new HashMap<>();
		filter.put("keyword",keyword);
		filter.put("dateRange",dateRange);
		filter.put("grade", grade);
		filter.put("status", status);
		filter.put("role", role);
		
		Map<String, Object> result = memberManageService.getAllMembers(page,  filter);
		
		return ResponseEntity.ok(result);
		
		
	}
	
	
	/**
	 * 회원상태
	 * @param userId
	 * @param status
	 * @return
	 */
	@PutMapping("/{userId}/status")
	public ResponseEntity<?> updateMemberStatus(
			@PathVariable("userId") Long userId,
			@RequestParam("status") String status){
		
		memberManageService.updateMemberStatus(userId, status);
		log.info("회원상태 변경 완료 : userId={}", userId);
		
		return ResponseEntity.ok(Map.of("message", "회원 상태가 변경됨."));
	}
	
	
	/**
	 * 회원역할변경(사용자/관리자)
	 * @param userId
	 * @param role
	 * @return
	 */
	@PutMapping("/{userId}/role")
	public ResponseEntity<?> updateMemberRole(
			@PathVariable("userId")Long userId,
			@RequestParam("role")String role){
		
		memberManageService.updateMemberRole(userId, role);
	    log.info("회원 역할 변경 완료: ID={}, 새 역할={}", userId, role);
	       
        return ResponseEntity.ok(Map.of("message", "회원 권한이 업데이트 완료~"));
	}
	
	
	
	

}
