package com.kh.ecolog.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.market.model.dto.MarketDTO;
import com.kh.ecolog.market.model.service.MarketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
public class MarketController {
	private final MarketService marketService; 
	
	@PostMapping("/write")
	public String insertMarket(@ModelAttribute MarketDTO dto,
								@RequestParam("images") List<MultipartFile> images) {
		if(images == null || images.size() != 3) {
			return "이미지는 정확히 3장 등록해야합니다.";
		}
		
		marketService.insertMarket(dto, images);
		return "글 등록 성공!";
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MarketDTO> update(@PathVariable(name = "id") Long marketNo,
										    @Valid MarketDTO market,
										    @RequestParam(name = "file", required = false) MultipartFile file) {
		market.setMarketNo(marketNo);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(marketService.updateMarket(market,file));
	}
	
}
