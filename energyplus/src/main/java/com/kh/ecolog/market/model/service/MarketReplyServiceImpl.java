package com.kh.ecolog.market.model.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.util.SecurityUtil;
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
	    List<MarketReplyDTO> replies = marketReplyMapper.selectRepliesByCommentNo(marketCommentNo);

	    Long currentUserId = null;
	    try {
	        currentUserId = SecurityUtil.getCurrentUserId();
	    } catch (Exception e) {
	        // 로그인 안 했을 경우
	    }

	    if (currentUserId != null) {
	        for (MarketReplyDTO reply : replies) {
	            reply.setIsMine(reply.getUserId().equals(currentUserId));
	        }
	    }

	    return replies;
	}
	@Override
	public void updateMarketReply(MarketReplyDTO dto) {
	    // 로그인한 사용자 ID를 가져옵니다.
	    Long userId = SecurityUtil.getCurrentUserId();
	    
	    // 요청된 대댓글에 작성된 사용자 ID를 확인합니다.
	    Long writerUserId = marketReplyMapper.findMarketReplyWriter(dto.getReplyNo());

	    // 대댓글이 존재하지 않거나, 권한이 없으면 예외를 던집니다.
	    if (writerUserId == null) {
	        throw new RuntimeException("대댓글이 존재하지 않습니다.");
	    }

	    if (!writerUserId.equals(userId)) {
	        throw new RuntimeException("수정 권한이 없습니다.");
	    }

	    // 대댓글 수정
	    dto.setUserId(userId); // 수정하려는 사용자의 ID를 설정
	    marketReplyMapper.updateMarketReply(dto);
	}
	@Override
	public void deleteMarketReply(MarketReplyDTO dto) {
	    // 로그인한 사용자 ID를 가져옵니다.
	    Long userId = SecurityUtil.getCurrentUserId();
	    
	    // 요청된 대댓글에 작성된 사용자 ID를 확인합니다.
	    Long writerUserId = marketReplyMapper.findMarketReplyWriter(dto.getReplyNo());

	    // 대댓글이 존재하지 않거나, 권한이 없으면 예외를 던집니다.
	    if (writerUserId == null) {
	        throw new RuntimeException("대댓글이 존재하지 않습니다.");
	    }

	    if (!writerUserId.equals(userId)) {
	        throw new RuntimeException("삭제 권한이 없습니다.");
	    }

	    // 대댓글 삭제
	    marketReplyMapper.deleteMarketReply(dto.getReplyNo());
	}

	
		

}
