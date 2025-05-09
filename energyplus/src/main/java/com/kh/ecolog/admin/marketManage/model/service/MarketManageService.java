package com.kh.ecolog.admin.marketManage.model.service;

import java.util.Map;


public interface MarketManageService {
	public Map<String, Object> findMarketsWithPaging(int page, int size);
}
