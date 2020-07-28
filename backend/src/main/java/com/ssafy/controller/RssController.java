package com.ssafy.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.RssChannel;
import com.ssafy.model.dto.RssItem;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.RssService;

@Controller
public class RssController {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	RssService rssService;

	@RequestMapping(value = "/api/public/test/rss" ,produces = "application/json; charset=utf8")
	public Object getRss() {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		List<Rss> rssList = rssService.searchAll();
		List<RssItem> rssItem = new LinkedList<RssItem>();

		for (Rss item : rssList) {
			RssChannel channel = (RssChannel) redisTemplate.opsForValue().get(item.getRssUrl());
			System.out.println(channel);
			rssItem.addAll(channel.getItems());
		}
		
		//sort과정
		List<RssItem> resultItem = new ArrayList<RssItem>(10);

		for(int i=0;i<10;i++) {
			resultItem.add(rssItem.get(i));
		}
		
		result.status = true;
		result.message= "rss test api...";
		result.data = resultItem;
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
