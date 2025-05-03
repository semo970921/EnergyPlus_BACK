package com.kh.ecolog.mileage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.mileage.model.dto.MileageDTO;
import com.kh.ecolog.mileage.model.dto.MileageStoreDTO;
import com.kh.ecolog.mileage.model.vo.Mileage;


@Mapper
public interface MileageMapper {
	
	void saveMileage(Mileage mileage);
	
	// 마일리지 사용처 
	List<MileageStoreDTO> findAllStore();

}
