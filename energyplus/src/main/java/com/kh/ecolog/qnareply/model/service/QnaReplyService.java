package com.kh.ecolog.qnareply.model.service;

import java.util.List;

import com.kh.ecolog.qnareply.model.dto.QnaReplyDTO;

public interface QnaReplyService {
	
	// 등록
	void insertReply(QnaReplyDTO reply);
	
	// 삭제
	void deleteById(Long replyId);
	
	// 조회
	List<QnaReplyDTO> selectReplyList(Long replyId);
	
	// 수정
	QnaReplyDTO update(QnaReplyDTO reply);
}
