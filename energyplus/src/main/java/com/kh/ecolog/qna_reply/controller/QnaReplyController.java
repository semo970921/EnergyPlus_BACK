package com.kh.ecolog.qna_reply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.qna_reply.model.dto.QnaReplyDTO;
import com.kh.ecolog.qna_reply.model.service.QnaReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replys")
@RequiredArgsConstructor
public class QnaReplyController {
	
	private final QnaReplyService qnaReplyService;
	
	@PostMapping
	public ResponseEntity<?> insertReply(
			@Valid @RequestBody QnaReplyDTO reply){
		log.info("댓글 내용 : {}", reply);
		return null;
	}
	
}
