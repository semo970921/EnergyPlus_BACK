package com.kh.ecolog.market.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.auth.util.SecurityUtil;
import com.kh.ecolog.market.report.model.dto.MarketReportDTO;
import com.kh.ecolog.market.report.model.service.MarketReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/markets/report")
@RequiredArgsConstructor
public class MarketReportController {
	private final MarketReportService marketReportService;
	
	@PostMapping
	public ResponseEntity<String> insertReport(@RequestBody MarketReportDTO dto) {
		// 현재 로그인한 유저 ID를 가져온다
		Long userId = SecurityUtil.getCurrentUserId();
	  	dto.setReporterUserId(userId);

	    marketReportService.insertReport(dto);

	    return ResponseEntity.ok("신고가 정상적으로 접수되었습니다.");
	}
}
