package com.kh.ecolog.challenge.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.ecolog.challenge.model.dto.ChallengeDTO;

@Mapper
public interface ChallengeMapper {

	List<ChallengeDTO> selectChallengeList(); // 모든 챌린지 목록 조회 

    ChallengeDTO selectChallengeDetail(Long challengeSeq); // 특정 챌린지 상세보기 

    int insertChallenge(ChallengeDTO dto); // 챌린지 등록 
}
