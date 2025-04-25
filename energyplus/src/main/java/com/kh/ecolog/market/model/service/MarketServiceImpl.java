package com.kh.ecolog.market.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.common.util.SecurityUtil;
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	    
	    Long userId = user.getUserId();
	    dto.setUserId(userId);
	    
	    dto.setMarketStatus("N");
	    dto.setMarketDate(new Date(System.currentTimeMillis()));

	    marketMapper.insertMarket(dto);
	    handleImages(dto.getMarketNo(), images, false);
	}

	@Override
	public void updateMarket(MarketDTO dto, List<MultipartFile> images) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

	    // 1. 유효한 새 이미지만 필터링
	    List<MultipartFile> validImages = (images != null) 
	        ? images.stream().filter(img -> img != null && !img.isEmpty()).toList()
	        : new ArrayList<>();

	    // 2. 유지할 기존 이미지
	    List<String> keepUrls = dto.getKeepImageUrls() != null 
	        ? dto.getKeepImageUrls()
	        : new ArrayList<>();

	    // 3. 총 이미지 수 확인
	    int totalCount = validImages.size() + keepUrls.size();
	    if (totalCount != 3) {
	        throw new IllegalArgumentException("수정 시 이미지는 총 3장이 있어야 합니다");
	    }

	    // 4. 기존 이미지 전부 삭제
	    marketMapper.deleteImagesByMarketNo(dto.getMarketNo());

	    // 5. 이미지 순서 초기화
	    AtomicInteger order = new AtomicInteger(1);

	    // 6. 기존 이미지 다시 저장
	    keepUrls.forEach(url -> {
	        MarketImageDTO image = MarketImageDTO.builder()
	            .marketNo(dto.getMarketNo())
	            .imgUrl(url)
	            .imgOrder(order.getAndIncrement())
	            .build();
	        marketMapper.insertMarketImage(image);
	    });

	    // 7. 새 이미지 저장
	    validImages.forEach(file -> {
	        String url = fileService.store(file);
	        MarketImageDTO image = MarketImageDTO.builder()
	            .marketNo(dto.getMarketNo())
	            .imgUrl(url)
	            .imgOrder(order.getAndIncrement())
	            .build();
	        marketMapper.insertMarketImage(image);
	    });

	    // 8. 게시글 정보 업데이트
	    marketMapper.updateMarket(dto);
	}


	private void handleImages(Long marketNo, List<MultipartFile> images, boolean isUpdate) {
	    if (images != null && !images.isEmpty()) {
	        if (isUpdate) {
	            marketMapper.deleteImagesByMarketNo(marketNo);
	        }
	        saveMarketImages(marketNo, images);
	    }
	}
	
	@Override
	public void deleteImagesByMarketNo(Long marketNo) {
	    marketMapper.deleteImagesByMarketNo(marketNo);
	}
	
	@Override
	public void deleteMarket(Long marketNo, Long userId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		// 1. 글 작성자 확인
	    MarketDTO market = marketMapper.selectMarketByNo(marketNo);
	    if (!market.getUserId().equals(userId)) {
	        throw new SecurityException("작성자만 삭제할 수 있습니다.");
	    }
	    
	    
		marketMapper.deleteImagesByMarketNo(marketNo);

	    // 2. 게시글 삭제
	    marketMapper.deleteMarket(marketNo);
		
	}

	@Override
	public List<MarketDTO> findAllMarkets() {
	    return marketMapper.findAllMarkets();
	}

	@Override
	public MarketDTO findMarketByNo(Long marketNo) {
		MarketDTO dto = marketMapper.selectMarketByNo(marketNo);
		dto.setImageList(marketMapper.selectImagesByMarketNo(marketNo));
	    return dto;
	}

	
	
}