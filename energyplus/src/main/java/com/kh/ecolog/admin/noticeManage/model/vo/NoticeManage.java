package com.kh.ecolog.admin.noticeManage.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoticeManage {
	Long noticeId;
	String noticeTitle;
	String noticeContent;
	Date noticeDate;
	Long userId;

}
