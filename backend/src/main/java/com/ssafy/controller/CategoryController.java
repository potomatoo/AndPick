package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@GetMapping(value = "/api/category/findall")
	public Object findAll(@RequestHeader(value = "Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = categoryService.findAll();
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping(value = "/api/category/findbynamelike")
	public Object findByNameLike(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = categoryService.findByCategoryNameLike(categoryName);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping(value = "/api/category/findbyname")
	public Object findByName(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result.data = categoryService.findByName(categoryName);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping(value = "/api/category/save")
	public Object saveCategory(@RequestHeader(value = "Authorization") String jwtToken,
			@RequestParam(value = "categoryName") String categoryName) {

		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result.data = categoryService.save(categoryName);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

}
