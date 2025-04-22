package com.kh.ecolog.market.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class MarketCommentController {
	private final MarketCommentService commentService;
	 // 댓글 등록
    @PostMapping
    public ResponseEntity<String> insertComment(@RequestBody MarketCommentDTO commentDto) {
        commentService.insertComment(commentDto);
        return ResponseEntity.ok("댓글 등록 성공!");
    }

    // 특정 게시글의 댓글 리스트 조회 (서비스 메서드에 맞춰 이름 수정)
    @GetMapping("/{marketNo}")
    public ResponseEntity<List<MarketCommentDTO>> getComments(@PathVariable Long marketNo) {
        List<MarketCommentDTO> comments = commentService.selectCommentsByMarketNo(marketNo); // ← 여기 수정
        return ResponseEntity.ok(comments);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentNo}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentNo) {
        commentService.deleteComment(commentNo, null); // userId는 현재 인증 안 쓰니 null 처리
        return ResponseEntity.ok("댓글 삭제 성공!");
    }


}
