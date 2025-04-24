package com.kh.ecolog.market.controller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.market.model.dto.MarketDTO;
import com.kh.ecolog.market.model.service.MarketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/markets")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowedHeaders = "*",
	    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
	)
public class MarketController {
	
	private final MarketService marketService; 
	
	private void setTempUserId(MarketDTO dto) {
	    dto.setUserId(1L);  // 임시 userId
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> insertMarket(
	    @ModelAttribute MarketDTO dto,  // FormData 각 필드로 받음
	    @RequestPart("images") List<MultipartFile> images) {
		
		setTempUserId(dto);  // 임의 유저아이디
	    
	    marketService.insertMarket(dto, images);
	    Map<String, Object> res = new HashMap<>();
	    res.put("message", "글 등록 성공!");
	    res.put("marketNo", dto.getMarketNo());
	    return ResponseEntity.ok(res);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateMarket(
	    @ModelAttribute MarketDTO dto,
	    @RequestPart(value = "images", required = false) List<MultipartFile> images
	) {
		setTempUserId(dto);  // 임의 유저아이디
		
	    marketService.updateMarket(dto, images);
	    return ResponseEntity.ok("수정 성공!");
	}
	
	// 게시글 삭제
	
	@DeleteMapping("/delete/{marketNo}")
	public ResponseEntity<String> deleteMarket(@PathVariable("marketNo") Long marketNo) {

	    Long tempUserId = 1L;  // 임시 userId
	    marketService.deleteMarket(marketNo, tempUserId);  // userId 전달!

	    return ResponseEntity.ok("삭제 성공!");
	}
	
//	게시글 조회
	
	@GetMapping
	public ResponseEntity<List<MarketDTO>> getAllMarkets() {
	    List<MarketDTO> list = marketService.findAllMarkets();
	    return ResponseEntity.ok(list);
	}
	
// 게시글 상세 조회
	
	@GetMapping("/{marketNo}")
	public ResponseEntity<MarketDTO> getMarket(@PathVariable("marketNo") Long marketNo) {
	    MarketDTO dto = marketService.findMarketByNo(marketNo);
	    return ResponseEntity.ok(dto);
	}
	

	
}
