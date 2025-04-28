package com.kh.ecolog.mymarket.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.mymarket.model.dto.MyMarketDTO;
import com.kh.ecolog.mymarket.model.dto.MyMarketImageDTO;

@Mapper
public interface MyMarketMapper {

	List<MyMarketDTO> selectMyMarket(Long userId);
	
	MyMarketDTO selectMarketByNo(Long marketNo);
	List<MyMarketImageDTO> selectImagesByMarketNo(Long marketNo);
}
