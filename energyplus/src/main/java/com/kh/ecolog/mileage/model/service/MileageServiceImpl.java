package com.kh.ecolog.mileage.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.file.service.FileService;
import com.kh.ecolog.mileage.model.dao.MileageMapper;
import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.dto.MileageStoreDTO;
import com.kh.ecolog.mileage.model.vo.Mileage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MileageServiceImpl implements MileageService {
	
	private final MileageMapper mileageMapper;
	private final FileService fileService;
	private final AuthService authService;
	
	
	// 마일리지 인정 신청글 저장
	@Override
	public ResponseEntity<?> saveMileage(MileageDTO mileage, MultipartFile file) {

		Mileage requestData = null;
		
		if(file == null || file.isEmpty()) {
			return ResponseEntity.badRequest().body("인증사진을 첨부해주세요.");
		}
		
		// 로그인한 사용자 정보 가져오기
	    CustomUserDetails user = authService.getUserDetails();
		
		String filePath = fileService.store(file);
		log.info("파일 저장 완료 : {}", filePath);
		
		requestData = Mileage.builder()
				.userId(user.getUserId())
				.mileageTitle(mileage.getMileageTitle())
				.mileageCategory(mileage.getMileageCategory())
				.mileageContent(mileage.getMileageContent())
				.mileageImg(filePath)
				.build();
		
		mileageMapper.saveMileage(requestData);
		
		return ResponseEntity.ok().body("신청이 완료되었습니다.");
	}
	
	
	// 마일리지 사용처
	@Override
	public List<MileageStoreDTO> findAllStores() {
		
		return mileageMapper.findAllStore();
	}
	
	
	
	
}