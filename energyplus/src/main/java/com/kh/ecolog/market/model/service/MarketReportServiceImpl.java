package com.kh.ecolog.market.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.market.model.dao.MarketReportMapper;
import com.kh.ecolog.market.model.dto.MarketReportDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketReportServiceImpl implements MarketReportService {

	private final MarketReportMapper marketReportMapper;
	
	@Override
	public void insertReport(MarketReportDTO dto) {
		marketReportMapper.insertMarketReport(dto);
		
	}

}
