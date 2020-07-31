package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.CategoryRepository;
import com.ssafy.model.repository.FeedRepository;
import com.ssafy.model.repository.RssRepository;
import com.ssafy.model.repository.SubscribeRepository;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
	private SubscribeRepository subscribeRepository;
	@Autowired
	private RssRepository rssRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FeedRepository feedRepository;

	@Override
	@Transactional
	public Subscribe saveSubscribe(User user, long feedId, String subscribeName, String rssUrl, String categoryName) {
		// TODO Auto-generated method stub

		if (user == null || rssUrl == null || subscribeName == null || categoryName == null)
			return null;

		Feed feed = feedRepository.findOneByFeedId(feedId);

		for (Subscribe item : feed.getSubscribeList()) {
			if (item.getSubscribeName().equals(subscribeName) || item.getRss().getRssUrl().equals(rssUrl))
				return null;
		}

		Subscribe subscribe = new Subscribe();

		Rss rss = rssRepository.findOneByRssUrl(rssUrl);

		if (rss == null) {
			rss = new Rss();
			rss.setRssUrl(rssUrl);
			Category category = categoryRepository.findOneByCategoryName(categoryName);
			if (category == null) {
				category = new Category();
				category.setCategoryName(categoryName);
				category = categoryRepository.save(category);
			}
			rss.setCategory(category);
			rss.setRssName(subscribeName);

			rss = rssRepository.save(rss);
		}

		subscribe.setSubscribeName(subscribeName);
		subscribe.setFeedId(feedId);
		subscribe.setUserNo(user.getUserNo());
		subscribe.setRss(rss);

		return subscribeRepository.save(subscribe);
	}

	@Override
	public Subscribe updateSubscribe(User user, long feedId, long subscribeId, String subscribeName) {
		// TODO Auto-generated method stub

		Feed feed = feedRepository.findOneByFeedId(feedId);
		Subscribe subscribe = subscribeRepository.getOne(subscribeId);

		if (user == null || feed == null || subscribe == null) {
			return null;
		}

		for (Subscribe item : feed.getSubscribeList()) {
			if (item.getSubscribeName().equals(subscribeName))
				return null;
		}

		subscribe.setSubscribeName(subscribeName);
		return subscribeRepository.save(subscribe);
	}

	@Override
	public boolean deleteSubscribe(long subscribeId) {
		// TODO Auto-generated method stub
		subscribeRepository.deleteById(subscribeId);
		return true;
	}

	@Override
	public List<Subscribe> findSubscribeByUser(User user) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;

		return subscribeRepository.findByUserNo(user.getUserNo());
	}

	@Override
	public List<Subscribe> findSubscribeByFeed(long feedId) {
		// TODO Auto-generated method stub
		return subscribeRepository.findByFeedId(feedId);
	}

	@Override
	public Subscribe findSubscribeBySubscribe(long subscribeId) {
		// TODO Auto-generated method stub
		return subscribeRepository.findOneBySubscribeId(subscribeId);
	}

}
