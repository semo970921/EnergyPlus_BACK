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
public class MarketCommentDTO {
	private Long marketCommentNo;
	private Long marketNo;
	private Long userId;
	private String marketCommentContent;
	private Date marketCommentDate;
	
	private String userName;
	private Boolean isMine;
}
