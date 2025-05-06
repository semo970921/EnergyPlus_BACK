package com.kh.ecolog.admin.cardnews.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.admin.cardnews.model.dto.CardNewsDTO;

public interface CardNewsService {
	void insertCardNews(CardNewsDTO cardNewsDTO, MultipartFile file);

	List<CardNewsDTO> findAll(int pageNo); 
	
	CardNewsDTO findById(Long id);
	
	List<CardNewsDTO> mainCardNews();
	  
	void updateCardNews(Long id, CardNewsDTO dto, MultipartFile file); 
    void deleteCardNews(Long id);

}
