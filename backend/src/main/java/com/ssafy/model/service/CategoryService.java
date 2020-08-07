package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;

@Service
public interface CategoryService {
	public List<Category> findAll();

	public List<Category> findByCategoryNameLike(String categoryName);
	
	public Category findByName(String categoryName);
	
	public Category save(Category category);
}
