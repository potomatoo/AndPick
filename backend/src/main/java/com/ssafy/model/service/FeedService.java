package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Feed;

@Service
public interface FeedService {
	public Feed save(Feed feed);

	public List<Feed> findAllByuser(Long userNo);

	public void deleteFeed(Long feedId);
}
