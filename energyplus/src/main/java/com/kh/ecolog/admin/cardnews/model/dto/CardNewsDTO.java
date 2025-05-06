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
	  private Long cardNewsNo;
	  private String cardNewsTitle;
	  private String cardNewsContent;
	  private String cardNewsImgUrl;
	  private Long userId;
	  private Date cardNewsDate;
}
