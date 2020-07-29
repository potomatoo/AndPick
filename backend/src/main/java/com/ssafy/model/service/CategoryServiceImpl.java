package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findByCategoryNameLike(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCategoryNameLike('%'+categoryName+'%');
	}

	@Override
	public Category findByName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryRepository.findOneByCategoryName(categoryName);
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

}
