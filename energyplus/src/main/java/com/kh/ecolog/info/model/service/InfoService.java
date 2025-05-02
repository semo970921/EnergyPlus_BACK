package com.kh.ecolog.info.model.service;

import com.kh.ecolog.info.model.dto.InfoDTO;
import com.kh.ecolog.info.model.dto.PasswordDTO;

public interface InfoService {

	// 내 정보 조회(select)
	InfoDTO selectMyInfo();
	
	// 내 정보 수정(update)
	void updateMyInfo(InfoDTO info);
	
	// 비밀번호 수정(update)
	void changePassword(PasswordDTO changePassword);
	
	// 내 등급 조회(gradeId)
	InfoDTO selectMyGrade();
	
}
