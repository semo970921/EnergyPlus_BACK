package com.kh.ecolog.mymarket.model.vo;

import java.sql.Date;
import java.util.List;

import com.kh.ecolog.market.model.dto.MarketImageDTO;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MyMarket {
	private Long marketNo;
    private Long userId;
    private String userName;
    private String marketTitle;
    private String marketContent;
    private Date marketDate;
    private String marketStatus;
    private Long marketPrice;
    private String marketStatusLabel;
    private String thumbnailUrl;
    private List<MarketImageDTO> imageList;
    private List<String> keepImageUrls;
}
