package com.kh.ecolog.market.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.auth.util.SecurityUtil;
import com.kh.ecolog.file.service.FileService;
import com.kh.ecolog.market.model.dao.MarketMapper;
import com.kh.ecolog.market.model.dto.MarketCommentDTO;
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
	
	
	private void checkUserAuthorization(Long writerUserId) {
	    Long currentUserId = SecurityUtil.getCurrentUserId();
	    if (writerUserId == null) {
	        throw new RuntimeException("작성자가 존재하지 않습니다.");
	    }
	    if (!writerUserId.equals(currentUserId)) {
	        throw new RuntimeException("권한이 없습니다.");
	    }
	}
	
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
		
		Long userId = SecurityUtil.getCurrentUserId();
		 dto.setUserId(userId);
	    
	    dto.setMarketStatus("N");
	    dto.setMarketDate(new Date(System.currentTimeMillis()));
	    images.get(0);
	    marketMapper.insertMarket(dto);
	    handleImages(dto.getMarketNo(), images, false);
	}

	@Override
	public void updateMarket(MarketDTO dto, List<MultipartFile> images) {
	    // 1. 작성자 ID 조회 및 권한 확인
	    Long writerUserId = marketMapper.findMarketWriter(dto.getMarketNo());
	    checkUserAuthorization(writerUserId);

	    // 2. 유효한 새 이미지만 필터링
	    List<MultipartFile> validImages = (images != null)
	        ? images.stream().filter(img -> img != null && !img.isEmpty()).toList()
	        : new ArrayList<>();

	    // 3. 유지할 기존 이미지
	    List<String> keepUrls = dto.getKeepImageUrls() != null
	        ? dto.getKeepImageUrls()
	        : new ArrayList<>();

	    // 4. 총 이미지 수 확인
	    int totalCount = validImages.size() + keepUrls.size();
	    if (totalCount != 3) {
	        throw new IllegalArgumentException("수정 시 이미지는 총 3장이 있어야 합니다");
	    }

	    // 5. 기존 이미지 전부 삭제
	    marketMapper.deleteImagesByMarketNo(dto.getMarketNo());

	    // 6. 이미지 순서 초기화
	    AtomicInteger order = new AtomicInteger(1);

	    // 7. 기존 이미지 다시 저장
	    keepUrls.forEach(url -> {
	        MarketImageDTO image = MarketImageDTO.builder()
	            .marketNo(dto.getMarketNo())
	            .imgUrl(url)
	            .imgOrder(order.getAndIncrement())
	            .build();
	        marketMapper.insertMarketImage(image);
	    });

	    // 8. 새 이미지 저장
	    validImages.forEach(file -> {
	        String url = fileService.store(file);
	        MarketImageDTO image = MarketImageDTO.builder()
	            .marketNo(dto.getMarketNo())
	            .imgUrl(url)
	            .imgOrder(order.getAndIncrement())
	            .build();
	        marketMapper.insertMarketImage(image);
	    });

	    // 9. 게시글 정보 업데이트
	    marketMapper.updateMarket(dto);
	}

	private void handleImages(Long marketNo, List<MultipartFile> images, boolean isUpdate) {
	    if (isUpdate) {
	        // 기존 이미지 무조건 삭제
	        marketMapper.deleteImagesByMarketNo(marketNo);
	    }

	    if (images != null && !images.isEmpty()) {
	        saveMarketImages(marketNo, images);
	    }
	}
	
	@Override
	public void deleteImagesByMarketNo(Long marketNo) {
	    marketMapper.deleteImagesByMarketNo(marketNo);
	}
	
	@Override
	public void deleteMarket(Long marketNo, Long userId) {
		
		 Long writerUserId = marketMapper.findMarketWriter(marketNo);
		checkUserAuthorization(writerUserId);
	    
	    
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

	    Long currentUserId = null;
	    try {
	        currentUserId = SecurityUtil.getCurrentUserId();
	    } catch (Exception e) {
	        // 로그인 안 한 경우 null 유지
	    }

	    if (currentUserId != null && dto.getUserId().equals(currentUserId)) {
	        dto.setIsMine(true);
	    } else {
	        dto.setIsMine(false);
	    }

	    return dto;
	}

	
	
}