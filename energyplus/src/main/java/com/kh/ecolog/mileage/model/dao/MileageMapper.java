package com.kh.ecolog.mileage.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.vo.Mileage;


@Mapper
public interface MileageMapper {
	
	void saveMileage(Mileage mileage);
	
	MileageDTO detailMileage(@Param("mileageSeq") Long mileageSeq);
	
	void updateMileageStatus(@Param("mileageSeq") Long mileageSeq, @Param("status") String status);

}
