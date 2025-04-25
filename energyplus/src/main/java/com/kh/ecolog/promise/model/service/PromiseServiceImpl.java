package com.kh.ecolog.promise.model.service;

import org.springframework.stereotype.Service;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.auth.service.AuthService;
import com.kh.ecolog.promise.model.dao.PromiseMapper;
import com.kh.ecolog.promise.model.dto.PromiseDTO;
import com.kh.ecolog.promise.vo.Promise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromiseServiceImpl implements PromiseService {

	private final PromiseMapper promiseMapper;
	private final AuthService authService;

	@Override
	public void insert(PromiseDTO promise) {
		// 임의로 아이디 지정
		//Long userId = 1L;
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		
		Promise requestData = 
				Promise.builder()
				.userPromise(promise.getUserPromise())
				.userId(userId)
				.build();
		promiseMapper.insert(requestData);
	}

	@Override
	public void updateMyPromise(PromiseDTO promise) {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
		promise.setUserId(userId); // 사용자 ID 설정
	    
		promiseMapper.updateByUserId(promise);
	}

	
	@Override
	public PromiseDTO selectMyPromise() {
		CustomUserDetails user = authService.getUserDetails();
		Long userId = user.getUserId();
	    
		PromiseDTO promise = promiseMapper.findByUserId(userId);
		if(promise == null) {
			throw new RuntimeException("나의 다짐 정보가 없습니다.");
		}
		return promise;
	}

}
