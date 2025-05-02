package com.kh.ecolog.market.report.model.dto;

import java.sql.Date;
import java.util.List;

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
public class MarketComReportDTO {
    private Long commentReportId;        // 댓글 신고 번호 (PK)
    private Long marketCommentNo;         // 신고당한 댓글 번호 (FK)
    private String commentReportReason;   // 신고 사유
    private Date commentReportDate;       // 신고 날짜
    private Long reporterUserId;           // 신고한 사람 ID
}