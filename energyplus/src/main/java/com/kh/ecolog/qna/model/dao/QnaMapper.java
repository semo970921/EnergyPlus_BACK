package com.kh.ecolog.qna.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.qna.model.dto.QnaDTO;
import com.kh.ecolog.qna.model.vo.Qna;

@Mapper
public interface QnaMapper {
	
	void insert(Qna qna);
	
	List<QnaDTO> selectAll(@Param("userId") Long userId, RowBounds rb);
	int countAll(@Param("userId") Long userId);
	
	QnaDTO selectById(Long qnaId);
	
	int update(QnaDTO qna);
	
	void deleteById(@Param("qnaId") Long qnaId);

	// 검색
	List<QnaDTO> searchQna(Map<String, Object> param, RowBounds rowBounds);
	int countSearch(Map<String, Object> param);
	
}
