package com.kh.ecolog.admin.noticeManage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.ecolog.admin.noticeManage.model.dto.NoticeManageDTO;
import com.kh.ecolog.admin.noticeManage.model.vo.NoticeManage;

@Mapper
public interface NoticeManageMapper {
	
	List<NoticeManageDTO> findAll (RowBounds rowBounds); // 전채 조회 

	void save(NoticeManage notice);
	
	NoticeManageDTO findById(Long noticeId);
	
	void update(NoticeManageDTO notice);
	
    void deleteById(@Param("noticeId") Long noticeId);
    
    // 검색
    List<NoticeManageDTO> searchNotice(Map<String, Object> param, RowBounds rowBounds);
    
    int countAll();

    int countSearch(Map<String, Object> param);

}
