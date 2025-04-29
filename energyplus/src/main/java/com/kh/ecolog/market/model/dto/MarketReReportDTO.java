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
public class MarketReReportDTO {
	 private Long replyReportId;       // 답글 신고 번호
	 private Long replyNo;              // 신고당한 답글 번호
	 private String replyReportReason;  // 신고 사유
	 private Date replyReportDate;      // 신고 일자
	 private Long reporterUserId;       // 신고자 ID
}
