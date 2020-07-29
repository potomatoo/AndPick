package com.ssafy.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;
import com.ssafy.model.service.RssService;

@Controller
public class RssController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RssService rssService;

	@RequestMapping(value = "/api/public/test/rss")
	public Object getRss() {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		List<Rss> rssList = rssService.findAll();
		List<RssItem> rssItem = new LinkedList<RssItem>();

		for (Rss item : rssList) {
			RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(item.getRssUrl());
			System.out.println(channel);
			rssItem.addAll(channel.getItems());
		}

		// sort과정
		List<RssItem> resultItem = new ArrayList<RssItem>(10);

		for (int i = 0; i < 10; i++) {
			if (i >= rssItem.size())
				break;
			resultItem.add(rssItem.get(i));
		}

		result.status = true;
		result.message = "rss test api...";
		result.data = resultItem;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/api/rss/list/all")
	public Object findAllRss(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.message = "모든 rss 정보입니다.";
		result.data = rssService.findAll();

		response = new ResponseEntity<>(result, HttpStatus.OK);

		return response;
	}

	@GetMapping(value = "/api/rss/list/category")
	public Object findRssByCategoryName(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.data = rssService.findByCategoryName(categoryName);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "선택한 카테고리의 RSS리스트 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			result.message = "잘못된 카테고리 명입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

		}

		return response;
	}

	@GetMapping(value = "/api/rss/list/name")
	public Object findRssByRssName(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("rssName") String rssName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result.data = rssService.findByRssName(rssName);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "rss이름으로 검색 결과 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "해당하는 rss가 없습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping(value = "/api/rss/save")
	public Object saveRss(@RequestHeader("Authorization") String jwtToken, @RequestParam("rssName") String rssName,
			@RequestParam("rssUrl") String rssUrl, @RequestParam("categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		Rss rss = new Rss();

		rss.setRssName(rssName);
		rss.setRssUrl(rssUrl);

		result.data = rssService.saveRss(rss, categoryName);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "rss 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "rss 저장에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/rss/item/feed")
	public Object findItemByFeed(@RequestHeader("Authorization") String jwtToken, @RequestParam("feedId") long feedId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		List<Rss> rssList = rssService.findItemByFeed(feedId);
		List<RssItem> rssItem = new LinkedList<RssItem>();

		for (Rss item : rssList) {
			RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(item.getRssUrl());
			System.out.println(channel);
			rssItem.addAll(channel.getItems());
		}

		// sort과정
		List<RssItem> resultItem = new ArrayList<RssItem>(10);

		for (int i = 0; i < 10; i++) {
			if (i >= rssItem.size())
				break;
			resultItem.add(rssItem.get(i));
		}

		result.status = true;
		result.message = "rss test api...";
		result.data = resultItem;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "api/rss/item/subscribe")
	public Object findItemBySubscribe(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("subscribeId") long subscribeId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		List<Rss> rssList = rssService.findItemBySubscribe(subscribeId);
		List<RssItem> rssItem = new LinkedList<RssItem>();

		for (Rss item : rssList) {
			RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(item.getRssUrl());
			System.out.println(channel);
			rssItem.addAll(channel.getItems());
		}

		// sort과정
		List<RssItem> resultItem = new ArrayList<RssItem>(10);

		for (int i = 0; i < 10; i++) {
			if (i >= rssItem.size())
				break;
			resultItem.add(rssItem.get(i));
		}

		result.status = true;
		result.message = "rss test api...";
		result.data = resultItem;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
