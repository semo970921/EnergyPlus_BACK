package com.kh.ecolog.market.model.dao;

import java.util.List;

import com.kh.ecolog.market.model.dto.MarketCommentDTO;

public interface MarketCommentMapper {
	
	void insertComment(MarketCommentDTO comment);
	
	List<MarketCommentDTO> selectCommentsByMarketNo(Long marketNo);
	
	void deleteComment(Long commentNo);
}
