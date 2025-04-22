package com.kh.ecolog.market.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.market.model.dto.MarketCommentDTO;
import com.kh.ecolog.market.model.service.MarketCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/markets/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class MarketCommentController {
	private final MarketCommentService commentService;
	
	@PostMapping
	public ResponseEntity<?> insertComment(@RequestBody MarketCommentDTO dto) {
		commentService.insertComment(dto);
		return ResponseEntity.ok("댓글 등록 완료");
	}
	
	@GetMapping("/{marketNo}")
	public ResponseEntity<List<MarketCommentDTO>> getComments(@PathVariable Long marketNo) {
		List<MarketCommentDTO> comments = commentService.selectCommentsByMarketNo(marketNo);
		return ResponseEntity.ok(comments);
	}
	
	@GetMapping("/delete/{commentNo}")
	public ResponseEntity<?> deleteComment(@PathVariable Long commentNo, @RequestParam Long userId) {
		commentService.deleteComment(commentNo, userId);
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	
	@PutMapping
	public ResponseEntity<?> updateComment(@RequestBody MarketCommentDTO dto) {
		commentService.updateComment(dto);
		return ResponseEntity.ok("댓글 수정 완료");
	}


}
