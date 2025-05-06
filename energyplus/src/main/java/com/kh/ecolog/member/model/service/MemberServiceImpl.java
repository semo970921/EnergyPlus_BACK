package com.kh.ecolog.member.model.service;


import java.beans.Transient;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ecolog.exception.MemberEmailDuplicateException;
import com.kh.ecolog.exception.UserNotFoundException;
import com.kh.ecolog.member.model.dao.MemberMapper;
import com.kh.ecolog.member.model.dto.MemberDTO;
import com.kh.ecolog.member.model.vo.Member;
import com.kh.ecolog.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder; // 비밀번호, 전화번호 암호화를 위한 의존성 주입
	private final TokenService tokenService;

	@Override
	public void signUp(MemberDTO member) {
		
		// 같은 이메일이 있는지 유효성 검사
		if(isEmailDuplicated(member.getUserEmail())){
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
		
		MemberDTO member = memberMapper.getMemberByUserId(userId);
		if(member == null) {
			throw new UserNotFoundException("존재하지 않는 사용자입니다,");
		}
		
		// 회원이 진짜 있는게 맞다면 -> 회원 상태를 'N'으로 변경
		int result = memberMapper.withdrawMember(userId); 
		tokenService.deleteUserToken(userId);
		log.info("회원탈퇴 완료 : 사용자 ID = {}",userId);
		
		return result > 0;
	}
	
	@Override
	public MemberDTO getMemberByUserId(Long userId) {
		MemberDTO member = memberMapper.getMemberByUserId(userId);
		if(member ==null) {
			throw new UserNotFoundException("존재하지 않는 사용자입니다.");
		}
		
		// 존재하면
		return member;
	}
	
	@Override
	public void resetPassword(String email, String newPassword) {
		
	}

}