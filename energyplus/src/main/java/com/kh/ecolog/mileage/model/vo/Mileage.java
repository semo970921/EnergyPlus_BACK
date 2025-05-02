package com.kh.ecolog.mileage.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Mileage {

	private Long mileageSeq;
	private Long userId;
	private String mileageTitle;
	private String mileageContent;
	private String mileageCategory;
	private String mileageImg;
	private String mileageStatus;
	private int mileageScore;
	private String mileageReject;
	private Date createDate;
	private Date approveDate;
	private Date useDate;
	
}
