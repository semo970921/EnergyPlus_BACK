package com.kh.ecolog.market.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketCommentDTO;

@Mapper
public interface MarketCommentMapper {
	
	void insertComment(MarketCommentDTO comment);
	
	List<MarketCommentDTO> selectCommentsByMarketNo(Long marketNo);
	
	void deleteComment(Long commentNo);
}
