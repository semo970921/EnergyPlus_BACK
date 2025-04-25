package com.kh.ecolog.promise.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.promise.model.dto.PromiseDTO;
import com.kh.ecolog.promise.vo.Promise;

@Mapper
public interface PromiseMapper {
	
	void insert(Promise promise);
	
	void updateByUserId(PromiseDTO promise);
	
	PromiseDTO findByUserId(Long userId);
	
}
