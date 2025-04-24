package com.kh.ecolog.challenge.model.service;

import java.util.List;

import com.kh.ecolog.challenge.model.dto.ChallengeDTO;

public interface ChallengeService {

	
	List<ChallengeDTO> getChallengeList();

    ChallengeDTO getChallengeDetail(Long challengeSeq);

    int createChallenge(ChallengeDTO dto);
}
