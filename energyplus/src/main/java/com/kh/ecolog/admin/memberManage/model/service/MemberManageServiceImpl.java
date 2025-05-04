package com.kh.ecolog.admin.memberManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ecolog.admin.memberManage.model.dao.MemberManageMapper;
import com.kh.ecolog.exception.UserNotFoundException;
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
	
	
	
	@Override
	@Transactional
	public void updateMemberStatus(Long userId, String status) {
		
		// 회원존재 확인
		MemberDTO member = memberManageMapper.getMemberById(userId);
		if(member==null) {
			throw new UserNotFoundException("존재하지 않는 회원입니다.");
		}
		
		memberManageMapper.updateMemberStatus(userId, status);
		
        log.info("회원 상태 업데이트 완료: ID={}, 이전 상태={}, 새 상태={}", userId, member.getStatus(), status);
		
	}
	
	@Override
	@Transactional
	public void updateMemberRole(Long userId, String role) {
		
		// 회원존재 확인
		MemberDTO member = memberManageMapper.getMemberById(userId);
		if(member==null) {
			throw new UserNotFoundException("존재하지 않는 회원입니다.");
		}
		
		memberManageMapper.updateMemberRole(userId, role);
		
		
	}
	
}
