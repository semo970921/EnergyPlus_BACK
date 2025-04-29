package com.kh.ecolog.info.model.service;

import com.kh.ecolog.info.model.dto.InfoDTO;

public interface InfoService {

	// 내 정보 조회(select)
	InfoDTO selectMyInfo();
	
	// 내 정보 수정(update)
	void updateMyInfo(InfoDTO info);
}
