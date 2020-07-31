package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;

@Service
public interface SubscribeService {
	public Subscribe saveSubscribe(User user, long feedId, String subscribeName, String rssUrl, String categoryName);

	public Subscribe updateSubscribe(User user, long feedId, long subscribeId, String subscribeName);

	public boolean deleteSubscribe(long subscribeId);

	public List<Subscribe> findSubscribeByUser(User user);

	public List<Subscribe> findSubscribeByFeed(long feedId);

	public Subscribe findSubscribeBySubscribe(long subscribeId);

}
