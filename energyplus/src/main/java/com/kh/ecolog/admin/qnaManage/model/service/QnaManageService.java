package com.kh.ecolog.admin.qnaManage.model.service;

import java.util.Map;

import com.kh.ecolog.admin.qnaManage.model.dto.QnaManageDTO;

public interface QnaManageService {
	
	Map<String, Object> selectAllAdmin(int pageNo, String keyword);
	
	QnaManageDTO selectById(Long qnaId);
	
	void deleteById(Long qnaId);
}
