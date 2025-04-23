package com.kh.ecolog.qna_reply.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.qna_reply.model.dto.QnaReplyDTO;
import com.kh.ecolog.qna_reply.model.vo.QnaReply;

@Mapper
public interface QnaReplyMapper {
	
	void insertReply(QnaReply reply);
	
	List<QnaReplyDTO> selectReplyList(Long qnaId);
}
