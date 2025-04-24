package com.kh.ecolog.market.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.market.model.dto.MarketReplyDTO;
import com.kh.ecolog.market.model.service.MarketReplyService;

@RestController
@RequestMapping("/markets/reply")
@CrossOrigin(origins = "http://localhost:5173")
public class MarketReplyController {

	private final MarketReplyService marketReplyService;
	
	public MarketReplyController(MarketReplyService marketReplyService) {
	   this.marketReplyService = marketReplyService;
	}
	 
	@PostMapping("/write")
	 public ResponseEntity<String> insertMarketReply(@RequestBody MarketReplyDTO dto) {

		dto.setUserId(1L);  // 임시 userId

	    marketReplyService.insertMarketReply(dto);

	    return ResponseEntity.ok("답댓글 등록 성공!");
	  }

}
