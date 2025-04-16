package com.kh.ecolog.market.model.service;

import java.awt.datatransfer.Clipboard;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void insertMarket(MarketDTO dto, List<MultipartFile> images) {
		
	   dto.setUserId(1L);
	   
	   marketMapper.insertMarket(dto);
	   
	   Long marketNo = dto.getMarketNo();
	   
	   if(images == null || images.size() != 3) {
		   throw new IllegalArgumentException("이미지는 3장 등록해야합니다.");
	   }
	   
	    
	}

	@Override
	public MarketDTO updateMarket(MarketDTO market, MultipartFile file) {

		marketMapper.update(market);
		return market;
	}


	
	
}
