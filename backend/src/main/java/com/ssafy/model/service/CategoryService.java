package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.response.BasicResponse;

@Service
public interface CategoryService {
	public BasicResponse findAll();

	public BasicResponse findByCategoryNameLike(String categoryName);

	public BasicResponse findByName(String categoryName);

	public BasicResponse save(String categoryName);
}
