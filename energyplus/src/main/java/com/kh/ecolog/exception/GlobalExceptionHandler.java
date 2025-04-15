package com.kh.ecolog.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MemberEmailDuplicateException.class)
	public ResponseEntity<Map<String, String>> handleDuplicateMemberEmail(MemberEmailDuplicateException e){
		return ResponseEntity.badRequest().build();
	}
	

}