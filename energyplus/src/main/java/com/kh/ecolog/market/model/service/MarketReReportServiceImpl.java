package com.kh.ecolog.market.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.market.model.dao.MarketReReportMapper;
import com.kh.ecolog.market.model.dto.MarketReReportDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketReReportServiceImpl implements MarketReReportService {

    private final MarketReReportMapper marketReReportMapper;

    @Override
    public void insertMarketReReport(MarketReReportDTO dto) {
        marketReReportMapper.insertMarketReReport(dto);
    }
}