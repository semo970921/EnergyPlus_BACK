package com.kh.ecolog.admin.mileageManage.model.service;

import java.util.List;

import com.kh.ecolog.mileage.model.dto.MileageDTO;

public interface MileageManageService {
	
	// 마일리지 신청 리스트 조회
	public abstract List<MileageDTO> findAllMileage(int pageNo);
	
	// 마일리지 신청 글 조회
	public MileageDTO detailMileage(Long mileageSeq);
	
	// 작성된 인증 게시글의 상태를 변경 (UPDATE)
	public void updateMileageStatus(Long mileageSeq, String mileageStatus);

}
