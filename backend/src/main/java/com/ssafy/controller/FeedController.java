package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.User;
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

		Feed feed = new Feed();
		feed.setFeedName(feedName);

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user != null) {
			feed.setUserNo(user.getUserNo());
			result.data = feedService.save(feed);
		}

		result.status = (result.data != null) ? true : false;

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
		if (user != null) {
			result.data = feedService.findAllByuser(user.getUserNo());
		}

		result.status = (result.data != null) ? true : false;

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

		Feed feed = new Feed();
		feed.setFeedId(feedId);
		feed.setFeedName(feedName);

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user != null) {
			feed.setUserNo(user.getUserNo());
			result.data = feedService.save(feed);
		}

		result.status = (result.data != null) ? true : false;

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
		if (user != null) {
			feedService.deleteFeed(feedId);
		}

		result.status = true;
		result.message = "삭제가 완료되었습니다.";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}
}
