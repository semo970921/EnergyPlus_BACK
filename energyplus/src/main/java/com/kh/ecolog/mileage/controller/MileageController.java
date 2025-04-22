package com.kh.ecolog.mileage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.service.MileageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/mileages")
public class MileageController {

	private final MileageService mileageService;
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveMileage(@ModelAttribute MileageDTO mileage, @RequestParam(name="mileageImg", required=false) MultipartFile file){
		
		log.info("인증 신청글 정보 : {}, 파일 정보 : {}", mileage, file);
		
		return mileageService.saveMileage(mileage, file);
	}
	
	@GetMapping("/{mileageSeq}")
	public ResponseEntity<MileageDTO> getMileageDetail(@PathVariable("mileageSeq") Long mileageSeq){
		
		MileageDTO mileage = mileageService.detailMileage(mileageSeq);
		
	    return ResponseEntity.ok(mileage);
	}
	
	@GetMapping
	public ResponseEntity<List<MileageDTO>> findAllMileage(@RequestParam(name="page", defaultValue="0") int page){
		
		return ResponseEntity.ok(mileageService.findAllMileage(page));
	}
	
	@PutMapping("/{mileageSeq}/status")
	public ResponseEntity<?> updateMileageStatus(@PathVariable Long mileageSeq, @RequestParam String mileageStatus) {
	    
	    mileageService.updateMileageStatus(mileageSeq, mileageStatus);
	    return ResponseEntity.ok("상태가 업데이트되었습니다.");
	}
	
}
