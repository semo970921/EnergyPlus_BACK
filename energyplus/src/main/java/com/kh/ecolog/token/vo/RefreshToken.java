package com.kh.ecolog.token.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {

	private Long tokenId; // 토큰 ID(시퀀스 넘버)
	private Long userId;
	private String token; // 리프레시
	private Long deadline;
	
}
