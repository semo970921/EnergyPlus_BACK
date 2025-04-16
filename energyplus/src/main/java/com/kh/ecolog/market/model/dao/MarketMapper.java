package com.kh.ecolog.market.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketDTO;
import com.kh.ecolog.market.model.dto.MarketImageDTO;

@Mapper
public interface MarketMapper {
	void insertMarket(MarketDTO dto);
	void insertMarketImage(MarketImageDTO image);
	void updateMarket(MarketDTO market);
}
