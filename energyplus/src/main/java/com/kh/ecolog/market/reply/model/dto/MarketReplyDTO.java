package com.kh.ecolog.market.reply.model.dto;

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
	private Date replyDate; 
	private String replyContent;
	private Long marketCommentNo;
	private Long userId;
	private String userName;
	private Boolean isMine;
}
