package com.kh.ecolog.auth.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    
    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String userEmail;
    
    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    private String userPassword;
}