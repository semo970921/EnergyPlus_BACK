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
	    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}
)
public class MarketController {
	private final MarketService marketService; 
	
	@PostMapping("/write")
	public ResponseEntity<?> insertMarket(
	    @RequestPart("market") MarketDTO dto,
	    @RequestPart("images") List<MultipartFile> images) {
	    marketService.insertMarket(dto, images);
	    Map<String, Object> res = new HashMap<>();
	    res.put("message", "글 등록 성공!");
	    res.put("marketNo", dto.getMarketNo()); // 응답에 marketNo 포함
	    return ResponseEntity.ok(res);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateMarket(
	    @RequestPart("market") MarketDTO dto,
	    @RequestPart(value = "images", required = false) List<MultipartFile> images
	) {
	    log.info("수정 요청 들어옴: {}", dto);
	    marketService.updateMarket(dto, images);
	    return ResponseEntity.ok("수정 성공!");
	}
	
	// 게시글 삭제
	
	@DeleteMapping("/delete/{marketNo}")
	public ResponseEntity<String> deleteMarket(@PathVariable("marketNo") Long marketNo) {
	    marketService.deleteMarket(marketNo);
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
