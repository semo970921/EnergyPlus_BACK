package com.kh.ecolog.market.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MarketReportDTO {
	 private Long reportId;           // 신고 번호
	 private Long marketNo;            // 신고당한 게시글 번호
	 private String reportReason;      // 신고 사유
	 private Date reportDate;          // 신고 날짜
	 private Long reporterUserId;      // 신고한 사람 ID
}
