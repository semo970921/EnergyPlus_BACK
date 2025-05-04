package com.kh.ecolog.admin.memberManage.model.service;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberManageServiceImpl implements MemberManageService {

	@Override
	public Map<String, Object> getAllMembers(int pageNo, Map<String, Object> filter ){
		
		// 페이지당 회원 수
		int size = 10; 
		RowBounds rowBounds = new RowBounds(pageNo*size, size);
		
		
		
		
	}
	
}
