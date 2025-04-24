package com.kh.ecolog.qna_reply.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.ecolog.qna.model.service.QnaService;
import com.kh.ecolog.qna_reply.model.dao.QnaReplyMapper;
import com.kh.ecolog.qna_reply.model.dto.QnaReplyDTO;
import com.kh.ecolog.qna_reply.model.vo.QnaReply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService {

	private final QnaReplyMapper qnaReplyMapper;
	private final QnaService qnaService;
	
	@Override
	public void insertReply(QnaReplyDTO reply) {
		// 게시글 있음?
		log.info("{}", reply);
		qnaService.selectById(reply.getQnaId());
		
		// 사용자 토큰 검증 로직 필요
		
		QnaReply requestData = QnaReply.builder()
						// 사용자 토큰번호 추가해야함
						.qnaReply(reply.getQnaReply())
						.qnaId(reply.getQnaId())
						.build();
		qnaReplyMapper.insertReply(requestData);
	}

	@Override
	public List<QnaReplyDTO> selectReplyList(Long qnaId) {
		try {
	        qnaService.selectById(qnaId); // 존재 확인용
	    } catch (RuntimeException e) {
	        return new ArrayList(); // 게시글이 없으면 댓글도 없음
	    }
		
		return qnaReplyMapper.selectReplyList(qnaId);
	}

}
