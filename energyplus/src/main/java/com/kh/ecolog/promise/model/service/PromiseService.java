package com.kh.ecolog.promise.model.service;

import com.kh.ecolog.promise.model.dto.PromiseDTO;

public interface PromiseService {
	
	void insert(PromiseDTO promise);
	
	void updateMyPromise(PromiseDTO promise);
	
	PromiseDTO selectMyPromise();
}
