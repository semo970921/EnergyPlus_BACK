package com.kh.ecolog.market.model.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.market.model.dao.MarketReplyMapper;
import com.kh.ecolog.market.model.dto.MarketReplyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketReplyServiceImpl implements MarketReplyService {

	private final MarketReplyMapper marketReplyMapper;
	
	@Override
	public void insertMarketReply(MarketReplyDTO dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	    
	    dto.setUserId(user.getUserId());
		 marketReplyMapper.insertMarketReply(dto);
		
	}

}
