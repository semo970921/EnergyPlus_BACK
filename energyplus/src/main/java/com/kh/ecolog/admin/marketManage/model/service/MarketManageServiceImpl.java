package com.kh.ecolog.admin.marketManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.marketManage.model.dao.MarketManageMapper;
import com.kh.ecolog.market.board.model.dto.MarketDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketManageServiceImpl implements MarketManageService {
	
	 private final MarketManageMapper marketManageMapper;

	 public Map<String, Object> findMarketsWithPaging(int page, int size) {
		    int offset = page * size;
		    List<MarketDTO> list = marketManageMapper.selectMarketsWithPaging(offset, size);
		    int totalCount = marketManageMapper.countMarkets();

		    Map<String, Object> result = new HashMap<>();
		    result.put("list", list);
		    result.put("totalCount", totalCount);
		    return result;
		}

}
