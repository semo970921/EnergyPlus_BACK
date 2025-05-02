package com.kh.ecolog.info.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.info.model.dto.InfoDTO;

@Mapper
public interface InfoMapper {
	
	InfoDTO selectMyInfo(Long userId);
	
	void updateMyInfo(InfoDTO info);
	
	void changePassword(Map<String, Object> changeRequest);
	
	InfoDTO selectMyGrade(Long userId);
}
