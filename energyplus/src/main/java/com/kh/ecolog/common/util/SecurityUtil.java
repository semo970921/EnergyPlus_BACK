package com.kh.ecolog.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kh.ecolog.auth.model.vo.CustomUserDetails;

public class SecurityUtil {
	 public static Long getCurrentUserId() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
	        return user.getUserId();
	    }
}
