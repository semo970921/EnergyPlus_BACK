package com.kh.ecolog.admin.mileageManage.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.mileageManage.model.dao.MileageManageMapper;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.file.service.FileService;
import com.kh.ecolog.mileage.model.dao.MileageMapper;
import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.service.MileageServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MileageManageServiceImpl implements MileageManageService {
	
	private final MileageManageMapper mileageManageMapper;

	// 관리자 권한
	@Override
	public List<MileageDTO> findAllMileage(int pageNo) {
		int size = 5;
		RowBounds rowBounds = new RowBounds(pageNo * size, size);
			
		return mileageManageMapper.findAllMileage(rowBounds);
	}
		
	@Override
	public MileageDTO detailMileage(Long mileageSeq) {
			
		return mileageManageMapper.detailMileage(mileageSeq);
	}
		

	@Override
	public void updateMileageStatus(Long mileageSeq, String mileageStatus) {
			
		mileageManageMapper.updateMileageStatus(mileageSeq, mileageStatus);
		}
}
