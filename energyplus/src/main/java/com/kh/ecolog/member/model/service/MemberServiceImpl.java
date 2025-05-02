package com.kh.ecolog.member.model.service;


import java.beans.Transient;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ecolog.exception.MemberEmailDuplicateException;
import com.kh.ecolog.member.model.dao.MemberMapper;
import com.kh.ecolog.member.model.dto.MemberDTO;
import com.kh.ecolog.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder; // 비밀번호, 전화번호 암호화를 위한 의존성 주입

	@Override
	public void signUp(MemberDTO member) {
		
		// 같은 이메일이 있는지 유효성 검사
		MemberDTO searchedMember = memberMapper.getMemberByMemberEmail(member.getUserEmail());
		
		if(searchedMember != null) {
			throw new MemberEmailDuplicateException("이미 존재하는 이메일입니다.");
		}

		// 이메일 중복x
		Member memberValue = Member.builder()
								   .gradeId(member.getGradeId())
								   .userEmail(member.getUserEmail())
								   .userPassword(passwordEncoder.encode(member.getUserPassword()))
								   .userName(member.getUserName())
								   .userPhone(member.getUserPhone() != null ? passwordEncoder.encode(member.getUserPhone()) : null)
								   .role("ROLE_USER")
								   .build();
		
		memberMapper.signUp(memberValue);
		
	}

	@Override
	public boolean isEmailDuplicated(String email) {
		
		return memberMapper.existsByEmail(email)>0;
	}
	
	@Override
	@Transactional
	public boolean withdrawMember(Long userId) {
		
		
		return true;
	}
	
	@Override
	public MemberDTO getMemberByUserId(Long userId) {
		
		
		return 
	}

}