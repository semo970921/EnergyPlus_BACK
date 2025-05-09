package com.kh.ecolog.admin.noticeManage.model.dto;

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
public class NoticeManageDTO {
	
	private long noticeId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private long userId;
	

}
