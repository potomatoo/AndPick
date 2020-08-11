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

import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.TagService;

@Controller
public class TagController {
	@Autowired
	private TagService tagService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping("/api/tag/save")
	public Object saveTag(@RequestHeader("Authorization") String jwtToken, @RequestParam("tagName") String tagName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (tagName == null) {
			result.status = false;
			result.message = "필수값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Tag tag = new Tag();
		tag.setTagName(tagName);

		result = tagService.saveTag(tag);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/api/tag/find/all")
	public Object findAll(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = tagService.findAll();
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/api/tag/find/like")
	public Object findNameLike(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("tagName") String tagName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (tagName == null) {
			result.status = false;
			result.message = "필수값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Tag tag = new Tag();
		tag.setTagName(tagName);

		result = tagService.findAllByName(tag);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/api/tag/find/name")
	public Object findName(@RequestHeader("Authorization") String jwtToken, @RequestParam("tagName") String tagName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (tagName == null) {
			result.status = false;
			result.message = "필수값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Tag tag = new Tag();
		tag.setTagName(tagName);

		result = tagService.findOneByName(tag);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/api/tag/count")
	public Object findAllTagCount(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = tagService.findAllTagCount(user);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

}
