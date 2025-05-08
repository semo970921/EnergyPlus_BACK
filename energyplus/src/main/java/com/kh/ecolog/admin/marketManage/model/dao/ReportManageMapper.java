package com.kh.ecolog.admin.marketManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.ecolog.admin.marketManage.model.dto.ReportManageDTO;


@Mapper
public interface ReportManageMapper {
	List<ReportManageDTO> findAllReports();
	ReportManageDTO selectById(Long reportId);
	void deleteById(Long reportId);
	void markReportAsHidden(@Param("reportId") Long reportId);

}
