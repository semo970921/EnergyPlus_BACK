package com.kh.ecolog.mymile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.mymile.model.dto.TotalMileDTO;
import com.kh.ecolog.mymile.model.service.TotalMileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("totalmile")
public class TotalMileController {
	
	private final TotalMileService totalMileService;
	
	@GetMapping
	public ResponseEntity<TotalMileDTO> selectMyMile() {
		TotalMileDTO myMile = totalMileService.selectMyMile();
		return ResponseEntity.ok(myMile);
	}

}
