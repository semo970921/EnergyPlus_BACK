package com.kh.ecolog.mileage.model.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.file.service.FileService;
import com.kh.ecolog.mileage.model.dao.MileageMapper;
import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.vo.Mileage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MileageServiceImpl implements MileageService {
	
	private final MileageMapper mileageMapper;
	private final FileService fileService;
	
	@Override
	public ResponseEntity<?> saveMileage(MileageDTO mileage, MultipartFile file) {

		Mileage requestData = null;
		
		if(file == null || file.isEmpty()) {
			return ResponseEntity.badRequest().body("인증사진을 첨부해주세요.");
		}
		
		String filePath = fileService.store(file);
		log.info("파일 저장 완료 : {}", filePath);
		
		requestData = Mileage.builder()
				.userId(mileage.getUserId())
				.mileageTitle(mileage.getMileageTitle())
				.mileageCategory(mileage.getMileageCategory())
				.mileageContent(mileage.getMileageContent())
				.mileageImg(filePath)
				.build();
		
		mileageMapper.saveMileage(requestData);
		
		return ResponseEntity.ok().body("신청이 완료되었습니다.");
	}

	@Override
	public MileageDTO detailMileage(Long mileageSeq) {
		
	    return mileageMapper.detailMileage(mileageSeq);
	}


	

	
	
}
