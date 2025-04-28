package com.kh.ecolog.qnareply.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.qnareply.model.dto.QnaReplyDTO;
import com.kh.ecolog.qnareply.model.vo.QnaReply;

@Mapper
public interface QnaReplyMapper {
	
	void insertReply(QnaReply reply);
	
	void deleteById(Long replyId);
	
	// 댓글 등록 시 qna status 상태 업데이트(Y)
	void updateQnaStatusToY(Long qnaId);
	
	// 댓글 등록 시 qna status 상태 업데이트(N)
	void updateQnaStatusToN(Long qnaId);
	// 댓글 있는지 확인
	int countByQnaId(Long qnaId);
	Long findQnaIdByReplyId(Long replyId);
	
	List<QnaReplyDTO> selectReplyList(Long qnaId);
	
	// 수정
	int update(QnaReplyDTO reply);
}
