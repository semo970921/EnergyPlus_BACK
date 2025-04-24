package com.kh.ecolog.market.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.market.model.dto.MarketCommentDTO;

public interface MarketCommentService {
	
	// 댓글 등록
	void insertComment(MarketCommentDTO dto);
	
	// 댓글 목록 조회 
	List<MarketCommentDTO> selectCommentsByMarketNo(Long marketNo);
	
	// 댓글 수정
		void updateComment(MarketCommentDTO dto);
	
	// 댓글 삭제 
	void deleteComment(@Param("marketCommentNo") Long marketCommentNo, @Param("userId") Long userId);
	
	
	
	// 댓글 신고 
}
