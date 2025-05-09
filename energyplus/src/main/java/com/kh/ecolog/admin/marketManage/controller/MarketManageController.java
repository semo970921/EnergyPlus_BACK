package com.kh.ecolog.admin.marketManage.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.admin.marketManage.model.service.MarketManageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/market")
@RequiredArgsConstructor
public class MarketManageController {

    private final MarketManageService marketManageService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getMarketsWithPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Map<String, Object> response = marketManageService.findMarketsWithPaging(page, size);
        return ResponseEntity.ok(response);
    }
}
