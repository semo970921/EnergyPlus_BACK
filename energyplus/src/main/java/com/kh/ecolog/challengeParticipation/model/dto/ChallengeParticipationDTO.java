package com.kh.ecolog.challengeParticipation.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChallengeParticipationDTO {

	 private Long participationSeq;  
	 private Long challengeSeq; 
	 private Integer mileageRewarded;
	 private Date rewardedDate;     
	 private Date participationDate;  
	 private String participationImg;
	 private String participationStatus;
	 private String rejectReason; 
}
