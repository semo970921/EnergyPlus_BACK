package com.kh.ecolog.market.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.market.model.dto.MarketDTO;

public interface MarketService {

	 void insertMarket(MarketDTO dto, List<MultipartFile> images);
	


	 MarketDTO updateMarket(MarketDTO market, MultipartFile file);

	
}
