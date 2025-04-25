package com.kh.ecolog.member.model.service;

import com.kh.ecolog.member.model.dto.MemberDTO;

public interface MemberService {
	
	/**
	 * 회원가입 메서드
	 * @param memberDTO
	 */
	void signUp(MemberDTO memberDTO);
	
	/**
	 * 이메일 중복 확이 메서드
	 * @param email
	 * @return 중복 여부(t->중복, f -> 중복 아님)
	 */
	boolean isEmailDuplicated(String email);

}

