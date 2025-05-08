package com.kh.ecolog.admin.qnaManage.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QnaManage {
	private Long qnaId;
	private Long userId;
	private String qnaTitle;
	private String qnaContent;
	private Date qnaDate;
	private String qnaStatus;
}
