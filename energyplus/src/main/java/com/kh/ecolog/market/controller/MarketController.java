package com.kh.ecolog.market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.market.model.dto.MarketDTO;
import com.kh.ecolog.market.model.service.MarketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {
	private final MarketService marketService; 
	
	@PostMapping("/write")
	public String insertMarket(@RequestBody MarketDTO dto) {
		log.info("글 등록됨 → {}", dto);
		marketService.insertMarket(dto);
		return "글 등록 성공!";
	}
	
}
