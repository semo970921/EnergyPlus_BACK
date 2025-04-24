package com.kh.ecolog.market.model.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.market.model.dao.MarketCommentMapper;
import com.kh.ecolog.market.model.dto.MarketCommentDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketCommentServiceImpl implements MarketCommentService  {
	
	private final MarketCommentMapper commentMapper;

	@Override
	public void insertComment(MarketCommentDTO dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	    Object principal = auth.getPrincipal();
	    
	    System.out.println("Principal: " + principal);  // 이거 찍어봐!
	    
	    dto.setUserId(user.getUserId());
	    
		commentMapper.insertComment(dto);
		
	}

	@Override
	public List<MarketCommentDTO> selectCommentsByMarketNo(Long marketNo) {
		return commentMapper.selectCommentsByMarketNo(marketNo);
	}
	
	@Override
	public void updateComment(MarketCommentDTO dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	    
	    dto.setUserId(user.getUserId());
	    
		commentMapper.updateComment(dto);
		
	}
	
	@Override
	public void deleteComment(Long commentNo, Long userId) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		    
		    // 댓글 작성자 확인
		    MarketCommentDTO comment = commentMapper.selectCommentByNo(commentNo);  // 이 메서드 필요함!
		    if (!comment.getUserId().equals(user.getUserId())) {
		        throw new SecurityException("본인 댓글만 삭제할 수 있습니다.");
		    }

		    commentMapper.deleteComment(commentNo);

	
	}
	}
