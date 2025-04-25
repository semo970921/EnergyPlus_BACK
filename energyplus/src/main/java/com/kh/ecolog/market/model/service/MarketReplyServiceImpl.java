package com.kh.ecolog.market.model.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.common.util.SecurityUtil;
import com.kh.ecolog.market.model.dao.MarketReplyMapper;
import com.kh.ecolog.market.model.dto.MarketReplyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketReplyServiceImpl implements MarketReplyService {

	private final MarketReplyMapper marketReplyMapper;
	
	@Override
	public void insertMarketReply(MarketReplyDTO dto) {
		
		Long userId = SecurityUtil.getCurrentUserId();
		
	    dto.setUserId(userId);
		marketReplyMapper.insertMarketReply(dto);
		
	}

	@Override
	public List<MarketReplyDTO> selectRepliesByCommentNo(Long marketCommentNo) {
		 return marketReplyMapper.selectRepliesByCommentNo(marketCommentNo);
	}

}
