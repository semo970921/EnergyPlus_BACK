package com.kh.ecolog.mymile.model.service;

import java.util.Map;

import com.kh.ecolog.mymile.model.dto.MyMileDTO;

public interface MyMileService {
	
	// 전체 조회
	Map<String, Object> selectAll(int pageNo, String keyword);
	
	void deleteById(Long mileId);
}
