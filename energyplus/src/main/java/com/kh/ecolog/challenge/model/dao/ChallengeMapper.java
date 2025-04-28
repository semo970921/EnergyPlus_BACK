package com.kh.ecolog.challenge.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.kh.ecolog.challenge.model.dto.ChallengeDTO;
import com.kh.ecolog.challenge.model.vo.Challenge;

@Mapper
public interface ChallengeMapper {

	List<ChallengeDTO> selectChallengeList(); // 모든 챌린지 목록 조회 

    ChallengeDTO selectChallengeDetail(Long challengeSeq); // 특정 챌린지 상세보기 
   
    void saveChallenge(Challenge challenge); // 챌린지 등록 
    
    void deleteChallenge(@Param("challengeSeq") long challengeSeq); // 챌린지 삭제 
    
    void updateChallenge(ChallengeDTO challenge); // 챌린지 수정 
    
    
}
