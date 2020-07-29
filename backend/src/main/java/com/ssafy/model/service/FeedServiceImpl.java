package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Feed;
import com.ssafy.model.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	private FeedRepository feedRepository;

	@Override
	public Feed save(Feed feed) {
		// TODO Auto-generated method stub

		if (feed.getFeedName() == null)
			return null;

		if (feedRepository.findOneByFeedName(feed.getFeedName()) != null)
			return null;

		return feedRepository.save(feed);
	}

	@Override
	public List<Feed> findAllByuser(Long userNo) {
		// TODO Auto-generated method stub
		return feedRepository.findByUserNo(userNo);
	}

	@Override
	public void deleteFeed(Long feedId) {
		// TODO Auto-generated method stub
		Feed feed = new Feed();
		feed.setFeedId(feedId);
		feedRepository.delete(feed);
	}

}
