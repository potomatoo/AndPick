package com.ssafy.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ssafy.model.dto.Rss;
import com.ssafy.model.service.RssService;

@Component
public class RssThread implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	RssService rssService;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		// TODO Auto-generated method stub
		
		System.out.println("[RSS   ] Start RSS Parsing...");
		List<Rss> rssList = rssService.searchAll();
		
		for(Rss item : rssList) {
			RssParser parser = new RssParser(item.getRssUrl());
			parser.setRedisTemplate(redisTemplate);
			
			
			new Thread(parser).start();
		}
	
	}

}
