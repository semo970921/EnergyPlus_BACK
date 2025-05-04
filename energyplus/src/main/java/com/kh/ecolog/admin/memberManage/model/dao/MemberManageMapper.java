package com.kh.ecolog.admin.memberManage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.member.model.dto.MemberDTO;

@Mapper
public interface MemberManageMapper {
	
	/**
	 * 모든회원 조회
	 * @param filter 필터링 조건이 담긴 Map(keyword, dateRange, grade, status, role)
	 * @param rowBounds 페이징처리 객체
	 * @return 필터링 조건에 맞는 회원목록
	 */
	List<MemberDTO> getAllMembers(@Param("filter")Map<String, Object> filter, RowBounds rowBounds);

	/**
	 * 필터링 조건에 맞는 전체 회원 수
	 * @param filter
	 * @return
	 */
	int countAllMembers(@Param("filter") Map<String, Object> filter);
	
	/**
	 * userId(시퀀스넘버)로 회원 조회
	 * @param userId
	 * @return
	 */
	MemberDTO getMemberById(@Param("userId")Long userId);
	
	
	/**
	 * 회원상태 업데이트
	 * @param userId 상태변경할 회원의 시퀀스넘버
	 * @param status
	 */
	void updateMemberStatus(@Param("userId") Long userId, @Param("status") String status);
	
}
