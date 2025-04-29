package com.kh.ecolog.info.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InfoDTO {
	private Long userId;
	private String userEmail;
	
	@Size(min=2, max=10, message="이름은 2글자 이상 10글자 이하 입력 가능합니다.")
	private String userName;
	
	@Pattern(regexp="^\\d{11}$", message="전화번호는 11자리 숫자만 입력해주세요.")
	private String userPhone;
}
