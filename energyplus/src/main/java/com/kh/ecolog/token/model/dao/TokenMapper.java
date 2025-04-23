package com.kh.ecolog.token.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.token.vo.RefreshToken;

@Mapper // 추가: MyBatis 매퍼로 인식되도록 @Mapper 어노테이션 추가
public interface TokenMapper {

    /**
     * 리프레시 토큰을 DB에 저장
     * @param token 저장할 토큰 정보
     */
    void saveToken(@Param("token") RefreshToken token);
    
    /**
     * 토큰 문자열로 리프레시 토큰 정보 조회
     * @param token 조회할 토큰 문자열
     * @return 조회된 리프레시 토큰 정보
     */
    RefreshToken findByToken(@Param("token") String token);
    
    /**
     * userId로 저장된 토큰 조회
     * @param userId 사용자의 시퀀스 넘버
     * @return 해당 사용자의 리프레시 토큰 정보 반환
     */
    RefreshToken findByUserId(@Param("userId") Long userId);
    
    /**
     * 만료된 리프레시 토큰 삭제
     * @param currentTime
     */
    void deleteExpiredRefreshToken(@Param("currentTime") Long currentTime);
    
    /**
     * 유저 토큰 삭제
     * @param userID
     */
    void deleteTokenByUserId(@Param("userId") Long userID);
    
}