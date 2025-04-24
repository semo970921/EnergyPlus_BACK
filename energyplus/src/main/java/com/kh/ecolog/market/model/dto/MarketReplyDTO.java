package com.kh.ecolog.market.model.dto;

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
public class MarketReplyDTO {
	private Long replyNo;
	private Date replydDate; 
	private String replyContent;
	private Long marketCommentNo;
	private Long userId;
}
