package com.kh.ecolog.info.model.service;

import java.lang.ProcessHandle.Info;

import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.info.model.dao.InfoMapper;
import com.kh.ecolog.info.model.dto.InfoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
	
	private final InfoMapper infoMapper;
	private final AuthService authService;

	@Override
	public InfoDTO selectMyInfo() {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		InfoDTO info = infoMapper.selectMyInfo(userId);
		
		if(info == null) {
			throw new RuntimeException("내 정보가 없습니다.");
		}
		
		return info;
	}

	@Override
	public void updateMyInfo(InfoDTO info) {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		info.setUserId(userId);
		
		infoMapper.updateMyInfo(info);
	}

}
