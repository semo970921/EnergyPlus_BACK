package com.kh.ecolog.challenge.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChallengeDTO {
	
	private Long challengeSeq;
    private String challengeTitle;  
    private String challengeContent;  
    private String challengeStatus; 
    private Long userId;
    private Date enrollDate;
    private String challengeImg;
    
    private String userName;
    

}
