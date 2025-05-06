package com.kh.ecolog.mymile.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.mymile.model.service.MyMileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("mymile")
public class MyMileController {
	
	private final MyMileService myMileService;
	
	// 마일리지 신청 내역 전체 조회
	@GetMapping
	public ResponseEntity<Map<String, Object>> selectAll(
			@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="keyword", required = false) String keyword) {
		return ResponseEntity.ok(myMileService.selectAll(page, keyword));
	}
	
	// 마일리지 신청 취소하기
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name="id") Long mileId){
		myMileService.deleteById(mileId);
		return ResponseEntity.ok().build();
	}
	
}
