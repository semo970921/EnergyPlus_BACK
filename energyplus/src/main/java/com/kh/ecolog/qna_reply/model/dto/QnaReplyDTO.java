package com.kh.ecolog.qna_reply.model.dto;

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
public class QnaReplyDTO {
	private Long qnaReplyId;
	private Long qnaId;
	private String qnaReply;
	private Date qnaReplyDate;
}
