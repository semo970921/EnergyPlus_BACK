package com.kh.ecolog.market.model.service;

import com.kh.ecolog.market.model.dto.MarketReplyDTO;

public interface MarketReplyService {
	
	// 답댓글 등록 메서드 
	void insertMarketReply(MarketReplyDTO dto);
	

}
