package com.kh.ecolog.market.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketReReportDTO;

@Mapper
public interface MarketReReportMapper {
	 void insertMarketReReport(MarketReReportDTO dto);
}
