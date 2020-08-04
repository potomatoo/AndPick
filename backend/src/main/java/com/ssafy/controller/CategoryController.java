package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Category;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/api/category/findall")
	public Object findAll(@RequestHeader(value = "Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.message = "모든 카테고리 목록입니다.";
		result.data = categoryService.findAll();

		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

	@PostMapping(value = "/api/category/findbynamelike")
	public Object findByNameLike(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.message = "모든 카테고리 목록입니다.";
		result.data = categoryService.findByCategoryNameLike(categoryName);

		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}
	
	@PostMapping(value = "/api/category/findbyname")
	public Object findByName(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.message = "모든 카테고리 목록입니다.";
		result.data = categoryService.findByName(categoryName);

		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

	@PostMapping(value = "/api/category/save")
	public Object saveCategory(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {

		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.data = categoryService.findByName(categoryName);
		
		if(result.data==null) {
			Category category = new Category();
			category.setCategoryName(categoryName);
			
			result.status=true;
			result.message="카테고리가 저장되었습니다.";
			result.data = categoryService.save(category);
			response = new ResponseEntity<>(result,HttpStatus.OK);
		}else {	
			result.status=false;
			result.message="이미 존재하는 카테고리 입니다.";
			response = new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
		}
		
		
		return response;

	}

}
