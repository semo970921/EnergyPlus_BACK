package com.kh.ecolog.market.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.market.model.dto.MarketCommentDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:5173")
public class MarketCommentController {
	
	@PostMapping
	public ResponseEntity<?> insertComment(@RequestBody MarketCommentDTO dto) {
		return ResponseEntity.ok().build();
	}
	


}
