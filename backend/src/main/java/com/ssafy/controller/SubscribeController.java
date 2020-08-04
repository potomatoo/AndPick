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

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
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
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		if (subscribeName == null || rssUrl == null || categoryName == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요 ";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Category category = new Category();
		category.setCategoryName(categoryName);

		Rss rss = new Rss();
		rss.setRssUrl(rssUrl);
		rss.setCategory(category);

		Subscribe subscribe = new Subscribe();
		subscribe.setSubscribeName(subscribeName);
		subscribe.setFeedId(feedId);
		subscribe.setUserNo(user.getUserNo());

		subscribe.setRss(rss);

		result = subscribeService.saveSubscribe(user, subscribe);

		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
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
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (subscribeName == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Subscribe subscribe = new Subscribe();

		subscribe.setSubscribeId(subscribeId);
		subscribe.setFeedId(feedId);
		subscribe.setSubscribeName(subscribeName);
		subscribe.setUserNo(user.getUserNo());

		result = subscribeService.updateSubscribe(user, subscribe);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
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
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Subscribe subscribe = new Subscribe();
		subscribe.setSubscribeId(subscribeId);

		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/user")
	public Object findSubscribeByUser(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = subscribeService.findSubscribeByUser(user);

		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/feed")
	public Object findSubscribeByFeed(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("feedId") long feedId) {
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

		result = subscribeService.findSubscribeByFeed(user, feed);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/subscribe/find/subscribe")
	public Object findSubscribeBySubscribe(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("subscribeId") long subscribeId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Subscribe subscribe = new Subscribe();
		subscribe.setSubscribeId(subscribeId);

		result = subscribeService.findSubscribeBySubscribe(user, subscribe);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
