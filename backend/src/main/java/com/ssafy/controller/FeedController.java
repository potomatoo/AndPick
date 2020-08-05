package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.FeedService;

@Controller

public class FeedController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/feed/save")
	public Object saveFeed(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedName") String feedName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (feedName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Feed feed = new Feed();
		feed.setFeedName(feedName);
		feed.setUserNo(user.getUserNo());

		result = feedService.saveFeed(user, feed);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/feed/list")
	public Object findAllFeed(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = feedService.findAllByuser(user);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/feed/put")
	public Object saveFeed(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedId") Long feedId,
			@RequestParam("feedName") String feedName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Feed feed = new Feed();
		feed.setFeedId(feedId);
		feed.setFeedName(feedName);
		feed.setUserNo(user.getUserNo());

		result = feedService.updateFeed(user, feed);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/feed/delete")
	public Object saveFeed(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedId") Long feedId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Feed feed = new Feed();
		feed.setFeedId(feedId);
		feed.setUserNo(user.getUserNo());

		result = feedService.deleteFeed(user, feed);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/feed/feedid")
	public Object findFeedByFeedid(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("feedId") Long feedId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Feed feed = new Feed();
		feed.setFeedId(feedId);

		result = feedService.findOneByFeedId(user, feed);
		System.out.println(result);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
