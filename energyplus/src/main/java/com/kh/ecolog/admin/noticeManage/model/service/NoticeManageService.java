package com.kh.ecolog.admin.noticeManage.model.service;

import java.util.List;

import com.kh.ecolog.admin.noticeManage.model.dto.NoticeManageDTO;


public interface NoticeManageService{

	void save(NoticeManageDTO notice);
	
    List<NoticeManageDTO> findAll(int pageNo, String keyword);
    
    NoticeManageDTO findById(Long noticeId);
    
    NoticeManageDTO update(NoticeManageDTO notice);
    
    void deleteById(Long noticeId);
    
    int getTotalPages(String keyword);
}
