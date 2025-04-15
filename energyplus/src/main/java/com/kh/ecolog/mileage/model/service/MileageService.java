package com.kh.ecolog.mileage.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.mileage.model.dto.MileageDTO;

public interface MileageService {

	// 마일리지 적립 신청 게시글 작성
	// (제목, 카테고리, 설명, 인증사진(multipartFile)) 입력 
	public void saveMileage(MileageDTO mileage, MultipartFile file);
	
	
}
