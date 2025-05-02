package com.kh.ecolog.admin.qnaManage.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.qnaManage.model.dao.QnaManageMapper;
import com.kh.ecolog.admin.qnaManage.model.dto.QnaManageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnaManageServiceImpl implements QnaManageService {
	
	private final QnaManageMapper qnaManageMapper;
	
	@Override
	public Map<String, Object> selectAll(int pageNo, String keyword) {
		
		int size = 5
		return null;
	}

	@Override
	public QnaManageDTO selectById(Long qnaId) {
		return null;
	}

	@Override
	public void deleteById(Long qnaId) {

	}

}
