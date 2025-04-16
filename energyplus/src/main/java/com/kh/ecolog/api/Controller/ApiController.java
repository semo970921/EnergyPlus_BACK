package com.kh.ecolog.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.api.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("apis")
@RequiredArgsConstructor
public class ApiController {
	
	private final ApiService apiService;

	@GetMapping
	public ResponseEntity<String> getPublicBicycle(){
		String responseData = apiService.requestGetPublicBicycle();
		return ResponseEntity.ok(responseData);
	}
	
}
