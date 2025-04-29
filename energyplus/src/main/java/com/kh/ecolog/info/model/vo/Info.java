package com.kh.ecolog.info.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder
@Getter
public class Info {
	private Long userId;
	private String userEmail;
	private String userName;
	private String userPhone;
}
