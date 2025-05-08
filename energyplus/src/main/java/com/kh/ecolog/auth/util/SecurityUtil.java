package com.kh.ecolog.auth.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;

public class SecurityUtil {
	public static Long getCurrentUserId() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
	        throw new IllegalStateException("로그인한 사용자만 접근 가능합니다.");
	    }
	    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	    System.out.println("현재 로그인한 사용자 ID: " + user.getUserId());
	    return user.getUserId();
	}
	 
	 
}
