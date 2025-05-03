package com.kh.ecolog.admin.mileageManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.mileage.model.dto.MileageDTO;

@Mapper
public interface MileageManageMapper {
	
	List<MileageDTO> findAllMileage(RowBounds rb);
	
	MileageDTO detailMileage(@Param("mileageSeq") Long mileageSeq);
	
	void updateMileageStatus(@Param("mileageSeq") Long mileageSeq, @Param("mileageStatus") String status);

}
