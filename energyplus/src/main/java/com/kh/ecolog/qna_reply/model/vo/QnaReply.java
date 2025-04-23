package com.kh.ecolog.qna_reply.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QnaReply {
	private Long qnaReplyId;
	private Long qnaId;
	private String qnaReply;
	private Date qnaReplyDate;
}
