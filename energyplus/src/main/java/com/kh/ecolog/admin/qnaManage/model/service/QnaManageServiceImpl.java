package com.kh.ecolog.admin.qnaManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.qnaManage.model.dao.QnaManageMapper;
import com.kh.ecolog.admin.qnaManage.model.dto.QnaManageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnaManageServiceImpl implements QnaManageService {
	
	private final QnaManageMapper qnaManageMapper;
	
	@Override
	public Map<String, Object> selectAllAdmin(int pageNo, String keyword) {
		
		int size = 5;
	    RowBounds rowBounds = new RowBounds(pageNo * size, size);

	    List<QnaManageDTO> list;
	    int totalCount;

	    if (keyword == null || keyword.trim().isEmpty()) {
	        // 전체 목록 조회
	        list = qnaManageMapper.selectAllAdmin(rowBounds);
	        totalCount = qnaManageMapper.countAllAdmin();
	    } else {
	        // 검색어가 있을 경우
	        Map<String, Object> param = new HashMap<>();
	        param.put("keyword", keyword);

	        list = qnaManageMapper.searchQnaAdmin(param, rowBounds);
	        totalCount = qnaManageMapper.countSearchAdmin(param);
	    }

	    Map<String, Object> result = new HashMap<>();
	    result.put("list", list);
	    result.put("totalCount", totalCount);
	    return result;
	}

	@Override
	public QnaManageDTO selectById(Long qnaId) {
		QnaManageDTO qna = qnaManageMapper.selectById(qnaId);
		if(qna == null) {
			throw new RuntimeException("QnA 게시글이 존재하지 않습니다.");
		}
		return qna;
	}

	@Override
	public void deleteById(Long qnaId) {
		qnaManageMapper.deleteById(qnaId);
	}

}
