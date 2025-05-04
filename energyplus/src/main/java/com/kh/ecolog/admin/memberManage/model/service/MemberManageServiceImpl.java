package com.kh.ecolog.admin.memberManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.memberManage.model.dao.MemberManageMapper;
import com.kh.ecolog.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {

	private final MemberManageMapper memberManageMapper;
	
	@Override
	public Map<String, Object> getAllMembers(int pageNo, Map<String, Object> filter ){
		
		// 페이지당 회원 수
		int size = 10; 
		RowBounds rowBounds = new RowBounds(pageNo*size, size);
		
		// 필터링에 맞는 회원목록
		List<MemberDTO> members = memberManageMapper.getAllMembers(filter, rowBounds);
		// 필터링에 맞는 전체 회원 수
		int totalCount = memberManageMapper.countAllMembers(filter);
		
		Map<String, Object> result = new HashMap<>();
		result.put("members", members);
		result.put("totalCount", totalCount);
		
		log.info("회원 목록 조회 : 페이지={}, 필터 = {} ", pageNo, filter);
		
		return result;
		
		
	}
	
}
