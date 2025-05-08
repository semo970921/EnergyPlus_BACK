package com.kh.ecolog.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUserDTO {
    private Long oauthId;
    private Long userId;
    private String provider;    // 제공자
    private String providerId;  // 제공자가 제공한 사용자 ID
    private String email;
    private String nickname;
    private Date createdDate;
}