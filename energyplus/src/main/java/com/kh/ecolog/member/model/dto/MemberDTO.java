package com.kh.ecolog.member.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

	private long userId;
	private Integer gradeId;
	
	@Pattern(regexp="^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message="유효한 이메일을 입력해쥬세요.")
	@NotBlank(message="이메일은 반드시 입력바랍니다.")
	private String userEmail;
	
	@Size(min=8, max=20, message="비밀번호 값은 8글자 이상 20글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d!@._^]{8,20}", message="비밀번호는 영문 대문자, 소문자 각각 한자 이상, 숫자 하나 이상 포함, !, @, _, ^ 포함해야 합니다.")
	@NotBlank(message = "비밀번호 값은 비어있을 수 없습니다.")
	private String userPassword;
	
	@Size(min=2, max=10, message="이름은 2글자 이상 10글자 이하 입력 가능합니다.")
	@NotBlank(message="이름은 반드시 입력바랍니다.")
	private String userName;
	
	
	private String userPhone;
	
	
	private  String role;
	
}
