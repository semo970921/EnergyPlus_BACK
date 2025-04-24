package com.kh.ecolog.challenge.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.ecolog.challenge.model.dao.ChallengeMapper;
import com.kh.ecolog.challenge.model.dto.ChallengeDTO;

@Service
public class ChallengeServiceImpl implements ChallengeService{

	private final ChallengeMapper challengeMapper;
	
	public ChallengeServiceImpl(ChallengeMapper challengeMapper) {
		this.challengeMapper = challengeMapper;
	}
	
	
	@Override
	public List<ChallengeDTO> getChallengeList(){
		return challengeMapper.selectChallengeList();
	}
	
	@Override
	public ChallengeDTO getChallengeDetail(Long challengeSeq) {
		return challengeMapper.selectChallengeDetail(challengeSeq);
	}
	
	@Override
	public int createChallenge(ChallengeDTO dto) {
		return challengeMapper.insertChallenge(dto);
	}
	
	
	
}
