package com.ssafy.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.repository.CategoryRepository;
import com.ssafy.model.repository.FeedRepository;
import com.ssafy.model.repository.RssRepository;
import com.ssafy.model.repository.SubscribeRepository;

@Service
public class RssServiceImpl implements RssService {

	@Autowired
	private RssRepository rssRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private SubscribeRepository subscribeRepository;

	@Override
	public List<Rss> findAll() {
		return rssRepository.findAll();
	}

	@Override
	public List<Rss> findByCategoryName(String categoryName) {
		// TODO Auto-generated method stub

		Category category = categoryRepository.findOneByCategoryName(categoryName);

		if (category == null) {
			return null;
		}

		return rssRepository.findByCategory(category);
	}

	@Override
	public List<Rss> findByRssName(String rssName) {
		// TODO Auto-generated method stub
		return rssRepository.findByRssNameLike('%' + rssName + '%');
	}

	@Override
	public Rss saveRss(Rss rss, String categoryName) {
		// TODO Auto-generated method stub

		Category category = categoryRepository.findOneByCategoryName(categoryName);

		if (category == null) {
			category = new Category();
			category.setCategoryName(categoryName);

			category = categoryRepository.save(category);
		}
		rss.setCategory(category);

		if (rssRepository.findOneByRssUrl(rss.getRssUrl()) != null)
			return null;

		return rssRepository.save(rss);
	}

	@Override
	public List<Rss> findItemByFeed(long feedId) {
		// TODO Auto-generated method stub

		List<Subscribe> subscribe = subscribeRepository.findByFeedId(feedId);

		List<Rss> list = new ArrayList<Rss>();

		for (Subscribe item : subscribe) {
			Rss rss = item.getRss();
			rss.setRssName(item.getSubscribeName());
			list.add(rss);
		}

		return list;
	}

	@Override
	public List<Rss> findItemBySubscribe(long subscribeId) {
		// TODO Auto-generated method stub

		List<Rss> list = new ArrayList<Rss>();

		Subscribe subscribe = subscribeRepository.findOneBySubscribeId(subscribeId);
		Rss rss = subscribe.getRss();
		rss.setRssName(subscribe.getSubscribeName());

		list.add(rss);

		return list;
	}

}
