package com.kh.ecolog.market.reply.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.auth.util.SecurityUtil;
import com.kh.ecolog.market.reply.model.dto.MarketReplyDTO;
import com.kh.ecolog.market.reply.model.service.MarketReplyService;


@RestController
@RequestMapping("/markets/reply")
public class MarketReplyController {

	private final MarketReplyService marketReplyService;
	
	public MarketReplyController(MarketReplyService marketReplyService) {
	   this.marketReplyService = marketReplyService;
	   // 
	}
	 
	@PostMapping("/write")
	 public ResponseEntity<String> insertMarketReply(@RequestBody MarketReplyDTO dto) {
		
	    marketReplyService.insertMarketReply(dto);

	    return ResponseEntity.ok("답댓글 등록 성공!");
	  }
	@GetMapping("/{marketCommentNo}")
	public ResponseEntity<List<MarketReplyDTO>> getReplies(@PathVariable("marketCommentNo") Long marketCommentNo) {
		List<MarketReplyDTO> replies = marketReplyService.selectRepliesByCommentNo(marketCommentNo);
		return ResponseEntity.ok(replies);
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateReply(@RequestBody MarketReplyDTO dto) {
	    try {
	        marketReplyService.updateMarketReply(dto);
	        return ResponseEntity.ok("대댓글 수정 완료!");
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	    }
	}
	
	
	@DeleteMapping("/{replyNo}")
	public ResponseEntity<String> deleteMarketReply(@PathVariable("replyNo") Long replyNo) {
	    try {
	        MarketReplyDTO dto = new MarketReplyDTO();
	        dto.setReplyNo(replyNo);
	        marketReplyService.deleteMarketReply(dto);
	        return ResponseEntity.ok("대댓글 삭제 완료!");
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	    }
	}
	
	
	
}
