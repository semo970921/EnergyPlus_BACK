package com.kh.ecolog.mymile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.mymile.model.dto.TotalCateDTO;
import com.kh.ecolog.mymile.model.service.TotalCateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("totalcategory")
public class TotalCateController {
	
	private final TotalCateService totalCateService;
	
	@GetMapping
	public ResponseEntity<TotalCateDTO> cateSum(){
		return ResponseEntity.ok(totalCateService.cateSum());
	}
}
