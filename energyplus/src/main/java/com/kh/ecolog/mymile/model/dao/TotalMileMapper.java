package com.kh.ecolog.mymile.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.mymile.model.dto.TotalMileDTO;

@Mapper
public interface TotalMileMapper {
	TotalMileDTO findByUserId(Long userId);
}
