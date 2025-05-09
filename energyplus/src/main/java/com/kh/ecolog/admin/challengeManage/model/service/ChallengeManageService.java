package com.kh.ecolog.admin.challengeManage.model.service;

import java.util.List;

import com.kh.ecolog.admin.challengeManage.model.dto.ChallengeManageDTO;



public interface ChallengeManageService {

	// 전체 조회 
	List<ChallengeManageDTO> findAllChallenges(int page);
    
	// 참여 상세 조회
    ChallengeManageDTO getChallengeDetail(Long challengeSeq);

    // 마일리지 승인
    void approveChallenge(Long challengeSeq, long mileage);

    // 반려 처리
    void rejectChallenge(Long challengeSeq, String reason);
    
}
