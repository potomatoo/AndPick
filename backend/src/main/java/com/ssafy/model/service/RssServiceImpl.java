package com.ssafy.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.CategoryRepository;
import com.ssafy.model.repository.FeedRepository;
import com.ssafy.model.repository.RssRepository;
import com.ssafy.model.repository.SubscribeRepository;
import com.ssafy.model.response.BasicResponse;

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
	public BasicResponse findAll() {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = rssRepository.findAll();
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "모든 RSS 조회에 성공하였습니다.";
		} else {
			result.message = "모든 RSS 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Category category = categoryRepository.findOneByCategoryName(categoryName);
		if (category == null) {
			result.status = false;
			result.message = "해당 카테고리가 없습니다.";
			return result;
		}

		result.data = rssRepository.findByCategory(category);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리별 RRS 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리별 RRS 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findByRssName(String rssName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = rssRepository.findByRssNameLike('%' + rssName + '%');
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "카테고리별 RRS 조회에 성공하였습니다.";
		} else {
			result.message = "카테고리별 RRS 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse saveRss(Rss rss, Category category) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Rss checkDublicate = rssRepository.findOneByRssUrl(rss.getRssUrl());

		if (checkDublicate != null) {
			result.status = false;
			result.message = "해당 url의 rss가 존재합니다.";
			return result;
		}

		Category checkCategory = categoryRepository.findOneByCategoryName(category.getCategoryName());

		if (checkCategory == null) {
			category = categoryRepository.save(checkCategory);
		}

		rss.setCategory(category);

		result.data = rssRepository.save(rss);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "RSS 저장에 성공하였습니다.";
		} else {
			result.message = "RSS 저장에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findItemByFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed checkFeed = feedRepository.findOneByFeedId(feed.getFeedId());

		if (checkFeed == null) {
			result.status = false;
			result.message = "해당 피드가 없습니다.";
			return result;
		}
		
		if(checkFeed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보와 피드 정보가 일치하지 않습니다.";
			return result;		
		}
		

		List<Subscribe> subscribe = subscribeRepository.findByFeedId(feed.getFeedId());
		List<Rss> list = new ArrayList<Rss>();

		for (Subscribe item : subscribe) {
			Rss rss = item.getRss();
			rss.setRssName(item.getSubscribeName());
			list.add(rss);
		}

		if (list.size() == 0) {
			result.status = false;
			result.message = "피드에 rss가 없습니다.";
		} else {
			result.status = true;
			result.message = "RSS 목록입니다.";
			result.data = list;
		}

		return result;
	}

	@Override
	public BasicResponse findItemBySubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Subscribe checkSubscribe = subscribeRepository.findOneBySubscribeId(subscribe.getSubscribeId());
		if (checkSubscribe == null) {
			result.status = false;
			result.message = "해당 구독 정보가 없습니다.";
			return result;
		}

		if(checkSubscribe.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보와 구독 정보가 일치하지 않습니다.";
			return result;		
		}
		

		result.status = true;
		result.data = checkSubscribe;
		result.message = "해당 구독에 대한 RSS입니다.";
		return result;
	}

}
