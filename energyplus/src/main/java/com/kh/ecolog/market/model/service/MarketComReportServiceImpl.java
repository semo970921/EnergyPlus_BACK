package com.kh.ecolog.market.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.market.model.dao.MarketComReportMapper;
import com.kh.ecolog.market.model.dto.MarketComReportDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketComReportServiceImpl implements MarketComReportService {

    private final MarketComReportMapper marketComReportMapper;

    @Override
    public void insertMarketCommentReport(MarketComReportDTO dto) {
        marketComReportMapper.insertMarketComReport(dto);
    }
}