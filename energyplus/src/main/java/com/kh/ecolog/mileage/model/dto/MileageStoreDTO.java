package com.kh.ecolog.mileage.model.dto;

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
public class MileageStoreDTO {
	
	private int mileageStoreSeq;
	private String mileageStoreCategory;
	private String mileageStoreName;
	private int mileageStorePrice;

}
