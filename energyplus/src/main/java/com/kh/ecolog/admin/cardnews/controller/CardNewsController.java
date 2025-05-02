package com.kh.ecolog.admin.cardnews.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.admin.cardnews.model.dto.CardNewsDTO;
import com.kh.ecolog.admin.cardnews.model.service.CardNewsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/cardnews")
@RequiredArgsConstructor
public class CardNewsController {
	
	private final CardNewsService cardNewsService;
	
	@PostMapping("/form")
	public ResponseEntity<?> save(@Valid CardNewsDTO cardNewsDTO,
								  @RequestParam(name="file", required = false)MultipartFile file) {
		cardNewsService.insertCardNews(cardNewsDTO, file);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<CardNewsDTO>> findAll(@RequestParam(name = "page", defaultValue = "0" ) int page ) {
		return ResponseEntity.ok(cardNewsService.findAll(page));
	}
	

}
