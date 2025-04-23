package com.kh.ecolog.auth.service;

import java.util.Map;

import com.kh.ecolog.auth.model.dto.LoginDTO;
import com.kh.ecolog.auth.model.vo.CustomUserDetails;
import com.kh.ecolog.member.model.dto.MemberDTO;

public interface AuthService {
	
	Map<String, String> login(LoginDTO loginDTO);
	
	CustomUserDetails getUserDetails();
	
	void logout(Long userId);

}