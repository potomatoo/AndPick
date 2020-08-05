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

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.SubscribeService;

@Controller
public class SubscribeController {

	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/subscribe/save")
	public Object saveSubscribe(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedId") long feedId,
			@RequestParam("subscribeName") String subscribeName, @RequestParam("rssUrl") String rssUrl,
			@RequestParam("categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		result.data = subscribeService.saveSubscribe(user, feedId, subscribeName, rssUrl, categoryName);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "구독이 저장되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "저장에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/subscribe/update")
	public Object updateSubscribe(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedId") long feedId,
			@RequestParam("subscribeId") long subscribeId, @RequestParam("subscribeName") String subscribeName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		result.data = subscribeService.updateSubscribe(user, feedId, subscribeId, subscribeName);

		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "수정되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "수정에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/subscribe/delete")
	public Object updateSubscribe(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("subscribeId") long subscribeId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		result.data = subscribeService.deleteSubscribe(subscribeId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "삭제되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "삭제에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/user")
	public Object findSubscribeByUser(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		result.data = subscribeService.findSubscribeByUser(user);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "사용자의 구독 목록 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/feed")
	public Object findSubscribeByFeed(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("feedId") long feedId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.data = subscribeService.findSubscribeByFeed(feedId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "피드로 조회한 결과입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/subscribe")
	public Object findSubscribeBySubscribe(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("subscribeId") long subscribeId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.data = subscribeService.findSubscribeBySubscribe(subscribeId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "구독 Id로 조회한 결과입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
