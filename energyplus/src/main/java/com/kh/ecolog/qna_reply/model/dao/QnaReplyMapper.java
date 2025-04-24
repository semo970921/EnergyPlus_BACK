package com.kh.ecolog.qna_reply.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.qna_reply.model.dto.QnaReplyDTO;
import com.kh.ecolog.qna_reply.model.vo.QnaReply;

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
}
