package com.kh.ecolog.qna.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ecolog.qna.model.dto.QnaDTO;

public interface QnaService {
	
	void insert(QnaDTO qna);
	
	Map<String, Object> selectAll(int pageNo, String keyword);
	
	QnaDTO selectById(Long qnaId);
	
	QnaDTO update(QnaDTO qna);
	
	void deleteById(Long qnaId);

}
