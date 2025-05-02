package com.kh.ecolog.admin.qnaManage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.admin.qnaManage.model.dto.QnaManageDTO;

@Mapper
public interface QnaManageMapper {

	List<QnaManageDTO> selectAllAdmin(RowBounds rowBounds);
	int countAllAdmin();

	QnaManageDTO selectById(Long qnaId);

	void deleteById(@Param("qnaId") Long qnaId);

	int countSearchAdmin(Map<String, Object> param);
	List<QnaManageDTO> searchQnaAdmin(Map<String, Object> param, RowBounds rowBounds);
	
}
