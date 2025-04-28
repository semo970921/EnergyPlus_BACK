package com.kh.ecolog.mymarket.model.dto;

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
@Builder
@ToString
public class MyMarketImageDTO {
 	private Long marketImgId;   
    private Long marketNo;       
    private String imgUrl;     
    private int imgOrder;    
	   
}
