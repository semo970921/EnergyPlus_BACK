package com.kh.ecolog.market.report.controller;

import com.kh.ecolog.auth.util.SecurityUtil;

import com.kh.ecolog.market.report.model.dto.MarketReReportDTO;
import com.kh.ecolog.market.report.model.service.MarketReReportService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/markets/replyReport")
@RequiredArgsConstructor
public class MarketReReportController {

    private final MarketReReportService marketReReportService;

    @PostMapping
    public ResponseEntity<String> insertReplyReport(@RequestBody MarketReReportDTO dto) {
        // 현재 로그인한 유저 ID를 가져온다
        Long userId = SecurityUtil.getCurrentUserId();
        dto.setReporterUserId(userId);

        marketReReportService.insertMarketReReport(dto);
        return ResponseEntity.ok("답글 신고가 정상적으로 접수되었습니다.");
    }
}
