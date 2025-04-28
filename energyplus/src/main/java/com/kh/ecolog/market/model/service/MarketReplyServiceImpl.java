package com.kh.ecolog.market.model.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
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
	    Long userId = SecurityUtil.getCurrentUserId(); // JWT에서 꺼냄
	    dto.setUserId(userId); // 프론트에서 보내든 말든 무조건 덮어씀!

	    if (userId == null) {
	        throw new AccessDeniedException("로그인 필요");
	    }

	    marketReplyMapper.insertMarketReply(dto);
	}

	@Override
	public List<MarketReplyDTO> selectRepliesByCommentNo(Long marketCommentNo) {
		 return marketReplyMapper.selectRepliesByCommentNo(marketCommentNo);
	}
	@Override
	public void updateMarketReply(MarketReplyDTO dto) {
		 Long userId = SecurityUtil.getCurrentUserId();
		 dto.setUserId(userId);
		  marketReplyMapper.updateMarketReply(dto);
		
	}
	@Override
	public void deleteMarketReply(MarketReplyDTO dto) {
	    Long replyNo = dto.getReplyNo();
	    Long userId = dto.getUserId();   

	    Long writerUserId = marketReplyMapper.findMarketReplyWriter(replyNo);

	    if(writerUserId == null) {
	        throw new RuntimeException("대댓글이 존재하지 않습니다.");
	    }

	    if (!writerUserId.equals(userId)) {
	        throw new RuntimeException("삭제 권한이 없습니다.");
	    }

	    marketReplyMapper.deleteMarketReply(replyNo);
	}

	
		

}
