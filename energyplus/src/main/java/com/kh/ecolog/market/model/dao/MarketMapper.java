package com.kh.ecolog.market.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketDTO;

@Mapper
public interface MarketMapper {
	void insertMarket(MarketDTO dto);
}
