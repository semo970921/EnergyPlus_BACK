package com.kh.ecolog.mymile.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.mymile.model.dto.MyMileDTO;

@Mapper
public interface MyMileMapper {
	
	List<MyMileDTO> selectAll(@Param("userId") Long userId, RowBounds rb);
	int countAll(@Param("userId") Long userId);
	
	List<MyMileDTO> searchMile(Map<String, Object> param, RowBounds rb);
	int countSearch(Map<String, Object> param);
	
	void deleteById(Long mileId);
}
