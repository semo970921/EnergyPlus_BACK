package com.kh.ecolog.oauth2.kakao.model.dto;

import lombok.Data;

@Data
public class KaKaoUser {
	
	private Long kakaoId;
	private String email;
	private String name;
	private String phone;

}
