package com.kh.ecolog.api.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kh.ecolog.api.model.dao.ZerostoreMapper;
import com.kh.ecolog.api.model.dto.ZerostoreDTO;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
	
	@Value("${api.public.service-key}")
	private String serviceKey;
	
	private String apiRequest(String uriPath) {
		URI uri = null;
		
		try {
			uri = new URI(uriPath);			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String apiResponseData = new RestTemplate().getForObject(uri, String.class);
		return apiResponseData;
	}

	@Override
	public String requestGetPublicBicycle() {
		
		StringBuilder sb = new StringBuilder("http://openapi.seoul.go.kr:8088");
		sb.append("/" + serviceKey);
		sb.append("/json/tbCycleStationInfo/1/100");
		
		return apiRequest(sb.toString());
	}

	// --------------------------------------------------- //
	private final ZerostoreMapper zerostoreMapper;
	
	@Override
	public List<ZerostoreDTO> requestGetZerostore() {
		return zerostoreMapper.requestGetZerostore();
	}


	

}
