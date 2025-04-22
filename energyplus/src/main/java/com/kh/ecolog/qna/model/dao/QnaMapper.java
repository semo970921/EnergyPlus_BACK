package com.kh.ecolog.qna.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.qna.model.dto.QnaDTO;
import com.kh.ecolog.qna.model.vo.Qna;

@Mapper
public interface QnaMapper {
	
	void insert(Qna qna);
	
	List<QnaDTO> selectAll(RowBounds rb);
	int countAll();
	
	QnaDTO selectById(Long qnaId);
	
	int update(QnaDTO qna);
	
	void deleteById(@Param("qnaId") Long qnaId);

	// 검색
	List<QnaDTO> searchQna(@Param("keyword") String keyword, RowBounds rowBounds);
	int countSearch(@Param("keyword") String keyword);
	
}
