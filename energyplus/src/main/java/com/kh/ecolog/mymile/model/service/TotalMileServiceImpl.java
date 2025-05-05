package com.kh.ecolog.mymile.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.mymile.model.dao.TotalMileMapper;
import com.kh.ecolog.mymile.model.dto.TotalMileDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TotalMileServiceImpl implements TotalMileService {
	
	private final TotalMileMapper totalMileMapper;
	private final AuthService authService;

	@Override
	public TotalMileDTO selectMyMile() {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		
		TotalMileDTO mile = totalMileMapper.findByUserId(userId);
		if(mile == null) {
			return new TotalMileDTO();
		}
		return mile;
	}

}
