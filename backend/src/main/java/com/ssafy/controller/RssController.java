package com.ssafy.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.RssService;

@Controller
public class RssController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RssService rssService;

	@GetMapping(value = "/api/rss/list/all")
	public Object findAllRss(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findAll();
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/rss/list/category")
	public Object findRssByCategoryName(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (categoryName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findByCategoryName(categoryName);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/rss/list/name")
	public Object findRssByRssName(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("rssName") String rssName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (rssName == null) {
			result.status = false;
			result.message = "필수값을 입력해 주세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findByRssName(rssName);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping(value = "/api/rss/save")
	public Object saveRss(@RequestHeader("Authorization") String jwtToken, @RequestBody Rss rss) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (rss.getRssName() == null || rss.getRssUrl() == null || rss.getCategory().getCategoryName() == null) {
			result.status = false;
			result.message = "필수값을 입력하세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Rss rssDto = new Rss();

		rssDto.setRssName(rss.getRssName());
		rssDto.setRssUrl(rss.getRssUrl());

		Category category = new Category();
		category.setCategoryName(rss.getCategory().getCategoryName());

		result.data = rssService.saveRss(rssDto, category);
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

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Feed feed = new Feed();
		feed.setFeedId(feedId);

		result = rssService.findItemByFeed(user, feed);

		if (!result.status) {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		List<RssItem> rssItem = new LinkedList<RssItem>();

		for (Subscribe item : (List<Subscribe>) result.data) {
			RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(item.getRss().getRssUrl());

			for (RssItem cur : channel.getItems()) {
				cur.setRssTitle(item.getRss().getRssName());
				cur.setSubscribeName(item.getSubscribeName());
				rssItem.add(cur);
			}

		}
		if (rssItem != null)
			Collections.sort(rssItem, new Comparator<RssItem>() {
				@Override
				public int compare(RssItem o1, RssItem o2) {
					// TODO Auto-generated method stub
					return o2.getPubDate().compareTo(o1.getPubDate());
				}
			});

		result.status = true;
		result.message = "피드의 뉴스 목록입니다.";
		result.data = rssItem;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "api/rss/item/subscribe")
	public Object findItemBySubscribe(@RequestHeader("Authorization") String jwtToken,
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

		result = rssService.findItemBySubscribe(user, subscribe);

		if (!result.status) {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}
		List<RssItem> rssItem = new LinkedList<RssItem>();

		Subscribe resultSubscribe = (Subscribe) result.data;

		RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(resultSubscribe.getRss().getRssUrl());
		for (RssItem cur : channel.getItems()) {
			cur.setRssTitle(resultSubscribe.getRss().getRssName());
			cur.setSubscribeName(resultSubscribe.getSubscribeName());
			rssItem.add(cur);
		}

		result.status = true;
		result.message = "rss test api...";
		result.data = rssItem;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/api/rss/find/channel/all")
	public Object findAllRssChannel(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findAll();

		if (result.status) {
			List<Object> list = new ArrayList<>();
			for (Rss rss : (List<Rss>) result.data) {
				list.add(redisTemplate.opsForValue().get("limit " + rss.getRssUrl()));
			}
			result.data = list;
			result.message = "rss 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "rss채널 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/rss/find/channel/categorylike")
	public Object findLikeCategoryRssChannel(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("categoryName") String categoryName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findByCategoryNameLike(categoryName);

		if (result.status) {
			List<Object> list = new ArrayList<>();
			for (Rss rss : (List<Rss>) result.data) {
				list.add(redisTemplate.opsForValue().get("limit " + rss.getRssUrl()));
			}
			result.data = list;
			result.message = "rss 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "rss채널 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/rss/find/channel/rsslike")
	public Object findLikeNameRssChannel(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("rssName") String rssName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findByRssNameLike(rssName);

		if (result.status) {
			List<Object> list = new ArrayList<>();
			for (Rss rss : (List<Rss>) result.data) {
				list.add(redisTemplate.opsForValue().get("limit " + rss.getRssUrl()));
			}
			result.data = list;
			result.message = "rss 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "rss채널 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/rss/main")
	public Object findLikeCategoryRssChannel(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = rssService.findMain();
		Map<String, List<String>> rsslist = (Map<String, List<String>>) result.data;
		Map<String, List<RssItem>> mainData = new HashMap<String, List<RssItem>>();
		for (String key : rsslist.keySet()) {
			List<RssItem> list = new ArrayList<RssItem>();

			for (String rssurl : rsslist.get(key)) {
				RssChannel rssChnnel = (RssChannel) redisTemplate.opsForValue().get(rssurl);
				list.addAll(rssChnnel.getItems());
			}
			if (list.size() > 3) {
				mainData.put(key, list.subList(0, 3));
			} else {
				mainData.put(key, list.subList(0, list.size()));
			}

		}

		result.data = mainData;

		if (result.status) {
			result.message = "main data 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "main data 조회에 실패 하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/api/public/count")
	public Object count() {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		result = rssService.findCount();

		if (result.status) {
			result.message = "숫자 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "숫자 조회에 실패 하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
