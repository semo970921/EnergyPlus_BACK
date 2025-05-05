package com.kh.ecolog.mymile.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TotalCateDTO {
	private Long mileageSeq;
	private Long userId;
	private String mileageCategory;
	private String mileageStatus;
	private int mileageScore;
	
	private int bikeTotal; // 자전거
	private int reuseTotal; // 다회용기
	private int etcTotal; // 기타
}
