package com.kh.ecolog.admin.noticeManage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.ecolog.admin.noticeManage.model.dao.NoticeManageMapper;
import com.kh.ecolog.admin.noticeManage.model.dto.NoticeManageDTO;
import com.kh.ecolog.admin.noticeManage.model.vo.NoticeManage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeManageServiceImpl implements NoticeManageService{
	

	private final NoticeManageMapper noticeManageMapper;
	
	@Override
    public void save(NoticeManageDTO notice) {
        Long userId = 1L;

        NoticeManage requestData = NoticeManage.builder()
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .userId(userId)
                .build();

        noticeManageMapper.save(requestData);
    }

    @Override
    public List<NoticeManageDTO> findAll(int pageNo, String keyword) {
        int size = 10;
        RowBounds rowBounds = new RowBounds(pageNo * size, size);
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            Map<String, Object> param = new HashMap<>();
            param.put("keyword", keyword);
            return noticeManageMapper.searchNotice(param, rowBounds);
        }
        
        return noticeManageMapper.findAll(rowBounds);
    }

    @Override
    public NoticeManageDTO findById(Long noticeId) {
        NoticeManageDTO notice = noticeManageMapper.findById(noticeId);
        if (notice == null) {
            throw new RuntimeException("해당 공지사항이 존재하지 않습니다.");
        }
        return notice;
    }

    @Override
    public NoticeManageDTO update(NoticeManageDTO notice) {
        noticeManageMapper.update(notice);
        return notice;
    }

    @Override
    public void deleteById(Long noticeId) {
        noticeManageMapper.deleteById(noticeId);
    }
	
    // 페이지 수 계산 
    @Override
    public int getTotalPages(String keyword) {
        int size = 10;
        int totalCount;

        if (keyword != null && !keyword.trim().isEmpty()) {
            Map<String, Object> param = new HashMap<>();
            param.put("keyword", keyword);
            totalCount = noticeManageMapper.countSearch(param);
        } else {
            totalCount = noticeManageMapper.countAll();
        }

        return (int) Math.ceil((double) totalCount / size);
    }



}
