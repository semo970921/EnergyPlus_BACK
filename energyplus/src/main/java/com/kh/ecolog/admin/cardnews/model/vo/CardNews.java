package com.kh.ecolog.admin.cardnews.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CardNews {
    private Long cardNewsNo;
    private String cardNewsTitle;
    private String cardNewsContent;
    private Date cardNewsDate;
    private Long userId;
    private String cardNewsImgUrl;
}