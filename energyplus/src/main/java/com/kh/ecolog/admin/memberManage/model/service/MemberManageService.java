package com.kh.ecolog.admin.memberManage.model.service;

import java.util.Map;

public interface MemberManageService {

	/**
	 * 전체 회원 목록 조회
	 * @param pageNo 페이지번호(0~)
	 * @param filter 필터링 조건이 담긴 Map(keyword, dateRange, grade, status, role)
	 * @return 회원목록과 Map
	 */
	Map<String, Object> getAllMembers(int pageNo, Map<String, Object>filter);
	
	/**
	 * 회원 상태 변경(회원/탈퇴)
	 * @param userId
	 * @param status
	 */
	void updateMemberStatus(Long userId, String status);
}
