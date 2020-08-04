package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface FeedService {
	public BasicResponse saveFeed(User user, Feed feed);

	public BasicResponse findAllByuser(User user);

	public BasicResponse deleteFeed(User user, Feed feed);

	public BasicResponse updateFeed(User user, Feed feed);
}
