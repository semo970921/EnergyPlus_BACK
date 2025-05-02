package com.kh.ecolog.market.report.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.market.report.model.dto.MarketComReportDTO;


@Mapper
public interface MarketComReportMapper {
    void insertMarketComReport(MarketComReportDTO dto);
}