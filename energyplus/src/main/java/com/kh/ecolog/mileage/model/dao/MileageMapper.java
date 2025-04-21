package com.kh.ecolog.mileage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.vo.Mileage;


@Mapper
public interface MileageMapper {
	
	void saveMileage(Mileage mileage);
	
	MileageDTO detailMileage(@Param("mileageSeq") Long mileageSeq);
	
	List<MileageDTO> findAllMileage(RowBounds rb);
	
	void updateMileageStatus(@Param("mileageSeq") Long mileageSeq, @Param("mileageStatus") String status);

}
