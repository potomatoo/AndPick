package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface SubscribeService {
	public BasicResponse saveSubscribeNew(User user, Subscribe subscribe);

	public BasicResponse saveSubscribe(User user, Subscribe subscribe);

	public BasicResponse updateSubscribe(User user, Subscribe subscribe);

	public BasicResponse deleteSubscribe(User user, Subscribe subscribe);

	public BasicResponse findSubscribeByUser(User user);

	public BasicResponse findSubscribeByFeed(User user, Feed feed);

	public BasicResponse findSubscribeBySubscribe(User user, Subscribe subscribe);

}
