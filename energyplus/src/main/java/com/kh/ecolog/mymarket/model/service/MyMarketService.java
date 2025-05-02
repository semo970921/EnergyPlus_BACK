package com.kh.ecolog.mymarket.model.service;

import java.util.List;

import com.kh.ecolog.mymarket.model.dto.MyMarketDTO;

public interface MyMarketService {
	
	List<MyMarketDTO> selectMyMarket();
	
	MyMarketDTO findMarketByNo(Long marketNo);

}
