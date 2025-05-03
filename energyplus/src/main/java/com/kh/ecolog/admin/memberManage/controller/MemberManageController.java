package com.kh.ecolog.admin.memberManage.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/admin/members")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class MemberManageController {
	
	
	public ResponseEntity<Map<String, Object>> getAllMembers(
			@RequestParam(name="page", defaultValue = "0") int page, // 페이지번호
			@RequestParam(name="keyword", required=false) String keyword, // 검색어
			@RequestParam(name="dateRange", required=false) String dateRange, // 가입일범위
			
			)
	

}
