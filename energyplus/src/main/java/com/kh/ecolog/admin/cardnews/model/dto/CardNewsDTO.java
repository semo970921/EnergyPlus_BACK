package com.kh.ecolog.admin.cardnews.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CardNewsDTO {
	  private Long cardnewsNo;
	  private String cardnewsTitle;
	  private String cardnewsContent;
	  private String cardnewsImgUrl;
	  private Long userId;
	  private Date cardnewsDate;
}
