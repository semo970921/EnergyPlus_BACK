package com.kh.ecolog.admin.cardnews.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.admin.cardnews.model.dto.CardNewsDTO;

public interface CardNewsService {
	void insertCardNews(CardNewsDTO cardNewsDTO, MultipartFile file);

	List<CardNewsDTO> findAll(int pageNo); 
	

}
