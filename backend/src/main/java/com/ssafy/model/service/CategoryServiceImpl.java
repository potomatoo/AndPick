package com.ssafy.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.repository.CategoryRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public BasicResponse findAll() {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = categoryRepository.findAll();
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findByCategoryNameLike(String categoryName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (categoryName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			return result;
		}

		result.data = categoryRepository.findByCategoryNameLike('%' + categoryName + '%');
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findByName(String categoryName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (categoryName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			return result;
		}

		result.data = categoryRepository.findOneByCategoryName(categoryName);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse save(String categoryName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (categoryName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			return result;
		}

		if (categoryRepository.findOneByCategoryName(categoryName) != null) {
			result.status = false;
			result.message = "해당 카테고리가 존재합니다.";
			return result;
		}

		Category category = new Category();
		category.setCategoryName(categoryName);

		result.data = categoryRepository.save(category);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리 조회에 실패하였습니다.";
		}
		return result;
	}

}
