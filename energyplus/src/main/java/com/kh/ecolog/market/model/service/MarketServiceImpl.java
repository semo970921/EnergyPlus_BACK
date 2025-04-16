package com.kh.ecolog.market.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.market.model.dao.MarketMapper;
import com.kh.ecolog.market.model.dto.MarketDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketServiceImpl implements MarketService  {
	private final MarketMapper marketMapper;

	@Override
	public void insertMarket(MarketDTO dto) {
	    marketMapper.insertMarket(dto);
	}
	
	
}
