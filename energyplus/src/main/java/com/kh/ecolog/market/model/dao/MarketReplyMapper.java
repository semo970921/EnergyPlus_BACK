package com.kh.ecolog.market.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketReplyDTO;

@Mapper
public interface MarketReplyMapper {
	void insertMarketReply(MarketReplyDTO dto);
	
	List<MarketReplyDTO> selectRepliesByCommentNo(Long marketCommentNo);
}
