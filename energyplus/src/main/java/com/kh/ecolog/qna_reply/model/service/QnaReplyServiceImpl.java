package com.kh.ecolog.qna_reply.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void insertReply(QnaReplyDTO reply) {
		// 게시글 있음?
		log.info("{}", reply);
		qnaService.selectById(reply.getQnaId());
		
		// 사용자 토큰 검증 로직 필요
		
		log.info("STEP 2: 게시글 존재 확인됨");
		
		QnaReply requestData = QnaReply.builder()
						// 사용자 토큰번호 추가해야함
						.qnaReply(reply.getQnaReply())
						.qnaId(reply.getQnaId())
						.build();
		// 댓글 등록
		qnaReplyMapper.insertReply(requestData);
		log.info("STEP 3: 댓글 insert 완료");
		
		// qna status 상태 업데이트
		qnaReplyMapper.updateQnaStatusToY(reply.getQnaId());
		log.info("STEP 4: QNA 상태 업데이트 완료");
	}
	
	@Override
	@Transactional
	public void deleteById(Long replyId) {
		// 댓글에 연결된 QNA_ID 조회
		Long qnaId = qnaReplyMapper.findQnaIdByReplyId(replyId);
		
		// 댓글 삭제
		qnaReplyMapper.deleteById(replyId);
		
		// 해당 QNA에 남은 댓글 수 확인
		int count = qnaReplyMapper.countByQnaId(qnaId);
		
		// 댓글 없으면 상태 N으로 변경
		if(count == 0) {
			qnaReplyMapper.updateQnaStatusToN(qnaId);
		}
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
	
	@Override
	public QnaReplyDTO update(QnaReplyDTO reply) {
		qnaReplyMapper.update(reply);
		return reply;
	}

}
