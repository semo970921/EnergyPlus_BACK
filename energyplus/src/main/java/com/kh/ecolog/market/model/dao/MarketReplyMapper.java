package com.kh.ecolog.market.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketReplyDTO;

@Mapper
public interface MarketReplyMapper {
	// 대댓글 작성
	void insertMarketReply(MarketReplyDTO dto);
	
	// 대댓글 리스트 조회
	List<MarketReplyDTO> selectRepliesByCommentNo(Long marketCommentNo);
	
	// 대댓글 수정
	void updateMarketReply(MarketReplyDTO replyDTO);
	
	// 대댓글 삭제
	void deleteMarketReply(Long replyNo);
	
	// 대댓글 작성자 확인 
	Long findMarketReplyWriter(Long replyNo);
	
}
