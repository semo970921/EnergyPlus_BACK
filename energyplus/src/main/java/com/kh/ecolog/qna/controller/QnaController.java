package com.kh.ecolog.qna.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.qna.model.dto.QnaDTO;
import com.kh.ecolog.qna.model.service.QnaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {
	
	private final QnaService qnaService;
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody QnaDTO qna){
		qnaService.insert(qna);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<QnaDTO>> selectAll(@RequestParam(name="page", defaultValue="0") int page){
		return ResponseEntity.ok(qnaService.selectAll(page));
	}

}
