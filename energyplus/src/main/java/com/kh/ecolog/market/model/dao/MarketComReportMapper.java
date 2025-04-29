package com.kh.ecolog.market.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.model.dto.MarketComReportDTO;

@Mapper
public interface MarketComReportMapper {
    void insertMarketComReport(MarketComReportDTO dto);
}