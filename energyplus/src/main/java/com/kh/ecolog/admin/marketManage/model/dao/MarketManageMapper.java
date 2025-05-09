package com.kh.ecolog.admin.marketManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.market.board.model.dto.MarketDTO;

@Mapper
public interface MarketManageMapper {
	
	List<MarketDTO> selectMarketsWithPaging(@Param("offset") int offset, @Param("limit") int limit);
	int countMarkets();
}
