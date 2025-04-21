package com.kh.ecolog.market.model.service;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.file.service.FileService;
import com.kh.ecolog.market.model.dao.MarketMapper;
import com.kh.ecolog.market.model.dto.MarketDTO;
import com.kh.ecolog.market.model.dto.MarketImageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketServiceImpl implements MarketService  {
	private final MarketMapper marketMapper;
	private final FileService fileService;
	

	private void saveMarketImages(Long marketNo, List<MultipartFile> images) {
	    if (images == null || images.size() != 3) {
	        throw new IllegalArgumentException("이미지는 3장 등록해야 합니다.");
	    }

	    AtomicInteger order = new AtomicInteger(1);

	    images.forEach(file -> {
	        String url = fileService.store(file);
	        log.info(" 저장된 이미지 URL: {}", url);

	        MarketImageDTO img = MarketImageDTO.builder()
	                .marketNo(marketNo)
	                .imgUrl(url)
	                .imgOrder(order.getAndIncrement())
	                .build();

	        marketMapper.insertMarketImage(img);
	    });
	}
	
	@Override
	public void insertMarket(MarketDTO dto, List<MultipartFile> images) {
	    dto.setUserId(1L);
	    dto.setMarketStatus("N");
	    dto.setMarketDate(new Date(System.currentTimeMillis()));

	    marketMapper.insertMarket(dto);

	    saveMarketImages(dto.getMarketNo(), images);  // 공통 처리
	}
	
	@Override
	public void updateMarket(MarketDTO dto, List<MultipartFile> images) {
	    log.info("📦 넘어온 이미지 개수: {}", images != null ? images.size() : "null");

	    marketMapper.updateMarket(dto);

	    if (images != null && !images.isEmpty()) {
	        marketMapper.deleteImagesByMarketNo(dto.getMarketNo());
	        saveMarketImages(dto.getMarketNo(), images);  // 공통 처리
	    }
	}

	@Override
	public void deleteMarket(Long marketNo) {
		 // 1. 관련 이미지 먼저 삭제
	    marketMapper.deleteMarketImagesByMarketNo(marketNo);

	    // 2. 게시글 삭제
	    marketMapper.deleteMarket(marketNo);
		
	}

	@Override
	public List<MarketDTO> findAllMarkets() {
	    return marketMapper.findAllMarkets();
	}

	@Override
	public MarketDTO findMarketByNo(Long marketNo) {
	    return marketMapper.selectMarketByNo(marketNo);
	}


	
	
}
