package com.kh.ecolog.mymile.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.mymile.model.dao.TotalCateMapper;
import com.kh.ecolog.mymile.model.dto.TotalCateDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TotalCateServiceImpl implements TotalCateService {

	private final TotalCateMapper totalCateMapper;
	private final AuthService authService;
	
	@Override
	public TotalCateDTO cateSum() {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		
		TotalCateDTO cate = totalCateMapper.cateSum(userId);
		if(cate == null) {
			return new TotalCateDTO();
		}
		return cate;
	}

}
