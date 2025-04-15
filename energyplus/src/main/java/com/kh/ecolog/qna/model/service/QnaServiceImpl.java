package com.kh.ecolog.qna.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.ecolog.qna.model.dto.QnaDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

	@Override
	public void insert(QnaDTO qna) {

	}

	@Override
	public List<QnaDTO> selectAll(int pageNo) {
		return null;
	}

	@Override
	public QnaDTO selectById(Long qnaId) {
		return null;
	}

	@Override
	public QnaDTO update(QnaDTO qna) {
		return null;
	}

	@Override
	public void deleteById(Long qnaId) {

	}

}
