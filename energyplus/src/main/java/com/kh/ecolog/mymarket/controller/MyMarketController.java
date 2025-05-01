package com.kh.ecolog.mymarket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.mymarket.model.dto.MyMarketDTO;
import com.kh.ecolog.mymarket.model.service.MyMarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("mymarket")
public class MyMarketController {

	private final MyMarketService myMarketService;

	@GetMapping
	public ResponseEntity<List<MyMarketDTO>> selectMyMarket(){
		List<MyMarketDTO> myMarket = myMarketService.selectMyMarket();
		return ResponseEntity.ok(myMarket);
	}
	
	// 게시글 상세 조회
	@GetMapping("/{marketNo}")
	public ResponseEntity<MyMarketDTO> getMarket(@PathVariable("marketNo") Long marketNo) {
		MyMarketDTO dto = myMarketService.findMarketByNo(marketNo);
	    return ResponseEntity.ok(dto);
	}
	
}
