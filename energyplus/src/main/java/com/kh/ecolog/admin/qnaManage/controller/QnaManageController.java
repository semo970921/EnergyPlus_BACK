package com.kh.ecolog.admin.qnaManage.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.admin.qnaManage.model.dto.QnaManageDTO;
import com.kh.ecolog.admin.qnaManage.model.service.QnaManageService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/qnas")
public class QnaManageController {
	
	private final QnaManageService qnaManageService;
	
	// 전체 조회
	@GetMapping
	public ResponseEntity<Map<String, Object>> selectAll(
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name = "keyword", required = false) String keyword){
		return ResponseEntity.ok(qnaManageService.selectAll(page, keyword));
	}
	
	// 1개 조회
	@GetMapping("/{id}")
	public ResponseEntity<QnaManageDTO> selectById(
			@PathVariable(name="id")
			@Min(value=1, message="너무 작음") Long qnaId){
		return ResponseEntity.ok(qnaManageService.selectById(qnaId));
	}
	
	// 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name="id") Long qnaId){
		qnaManageService.deleteById(qnaId);
		return ResponseEntity.ok().build();
	}
}
