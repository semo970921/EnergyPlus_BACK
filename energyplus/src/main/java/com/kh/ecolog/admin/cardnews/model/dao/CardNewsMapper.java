package com.kh.ecolog.admin.cardnews.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.admin.cardnews.model.dto.CardNewsDTO;
import com.kh.ecolog.admin.cardnews.model.vo.CardNews;

@Mapper
public interface CardNewsMapper {

	void insertCardNews(CardNews cardNews);
	List<CardNewsDTO> selectAllCardNews(RowBounds rowBounds);
	CardNewsDTO selectCardNewsByNo(Long cardnewsNo);
	void updateCardNews(CardNewsDTO dto);
	void deleteCardNews(Long cardnewsNo);
}
