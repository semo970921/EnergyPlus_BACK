package com.kh.ecolog.mymile.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.mymile.model.dto.TotalCateDTO;

@Mapper
public interface TotalCateMapper {
	TotalCateDTO cateSum(Long userId);
}
