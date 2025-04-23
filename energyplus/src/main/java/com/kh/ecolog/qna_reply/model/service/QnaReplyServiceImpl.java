package com.kh.ecolog.qna_reply.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.ecolog.qna_reply.model.dto.QnaReplyDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService {

	@Override
	public void insertReply(QnaReplyDTO reply) {
		
	}

	@Override
	public List<QnaReplyDTO> selectReplyList(Long replyId) {
		return null;
	}

}
