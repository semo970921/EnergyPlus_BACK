package com.kh.ecolog.admin.challengeManage.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ecolog.admin.challengeManage.model.dao.ChallengeManageMapper;
import com.kh.ecolog.admin.challengeManage.model.dto.ChallengeManageDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeManageServiceImpl implements ChallengeManageService {

    private final ChallengeManageMapper challengeManageMapper;

    // 목록 조회 
    @Override
    public List<ChallengeManageDTO> findAllChallenges(int page) {
    	int size = 10;
    	RowBounds rowBounds = new RowBounds(page * size, size);
    	return challengeManageMapper.selectAllChallenges(rowBounds);
    }

    // 2. 상세 조회
    @Override
    public ChallengeManageDTO getChallengeDetail(Long challengeSeq) {
        return challengeManageMapper.selectChallengeDetail(challengeSeq);
    }

    // 3. 승인
    @Override
    public void approveChallenge(Long challengeSeq, long mileage ) {
    	challengeManageMapper.approveChallenge(challengeSeq);
    	
        challengeManageMapper.insertParticipationIfMissing(challengeSeq);
        
        challengeManageMapper.updateMileageRewarded(challengeSeq, mileage);
    }

    // 4. 반려
    @Override
    public void rejectChallenge(Long challengeSeq, String reason) {
        challengeManageMapper.rejectChallenge(challengeSeq, reason);
        challengeManageMapper.updateRejectReason(challengeSeq, reason);
    }
    

}
