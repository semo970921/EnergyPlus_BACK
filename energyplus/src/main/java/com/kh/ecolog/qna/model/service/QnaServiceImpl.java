package com.kh.ecolog.qna.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.ecolog.qna.model.dao.QnaMapper;
import com.kh.ecolog.qna.model.dto.QnaDTO;
import com.kh.ecolog.qna.model.vo.Qna;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
	
	private final QnaMapper	qnaMapper;

	@Override
	public void insert(QnaDTO qna) {
		// 유저 아이디 임의 지정
		Long userId = 1L;
		
		Qna requestData = Qna.builder()
						 .qnaTitle(qna.getQnaTitle())
						 .qnaContent(qna.getQnaContent())
//						 .userId(qna.getUserId())
						 .userId(userId)
						 .build();
		qnaMapper.insert(requestData);
	}

	@Override
	public Map<String, Object> selectAll(int pageNo, String keyword) {
		int size = 5; // 페이지 당 5
		RowBounds rowBounds = new RowBounds(pageNo * size, size);
		
		List<QnaDTO> list;
	    int totalCount;

	    if (keyword == null || keyword.trim().isEmpty()) {
	        list = qnaMapper.selectAll(rowBounds);
	        totalCount = qnaMapper.countAll();
	    } else {
	        list = qnaMapper.searchQna(keyword, rowBounds);
	        totalCount = qnaMapper.countSearch(keyword);
	    }
		
		Map<String, Object> result = new HashMap();
		result.put("list", list);
		result.put("totalCount", totalCount);
		return result;
	}

	@Override
	public QnaDTO selectById(Long qnaId) {
		QnaDTO qna = qnaMapper.selectById(qnaId);
		if(qna == null) {
			throw new RuntimeException("QnA 게시글이 존재하지 않습니다.");
		}
		return qna;
	}

	@Override
	public QnaDTO update(QnaDTO qna) {
		qnaMapper.update(qna);
		return qna;
	}

	@Override
	public void deleteById(Long qnaId) {
		qnaMapper.deleteById(qnaId);
	}

}
