package com.ssafy.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import com.ssafy.model.repository.UserRepository;
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
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
	public BasicResponse findItemByFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed checkFeed = feedRepository.findOneByFeedId(feed.getFeedId());

		if (checkFeed == null) {
			result.status = false;
			result.message = "해당 피드가 없습니다.";
			return result;
		}

		if (checkFeed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보와 피드 정보가 일치하지 않습니다.";
			return result;
		}

		List<Subscribe> subscribe = checkFeed.getSubscribeList();

		if (subscribe.size() == 0) {
			result.status = true;
			result.message = "피드에 rss가 없습니다.";
			result.data = subscribe;
		} else {
			result.status = true;
			result.message = "RSS 목록입니다.";
			result.data = subscribe;
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findItemBySubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Subscribe checkSubscribe = subscribeRepository.findOneBySubscribeId(subscribe.getSubscribeId());
		if (checkSubscribe == null) {
			result.status = false;
			result.message = "해당 구독 정보가 없습니다.";
			return result;
		}

		if (checkSubscribe.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보와 구독 정보가 일치하지 않습니다.";
			return result;
		}

		result.status = true;
		result.data = checkSubscribe;
		result.message = "해당 구독에 대한 RSS입니다.";
		return result;
	}

	@Override
	public BasicResponse findByCategoryNameLike(String categoryName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();
		String likeQuery = "%" + categoryName + "%";
		result.data = rssRepository.findByCategoryNameLike(likeQuery);
		System.out.println(result.data);
		if (result.data == null) {
			result.status = false;
			result.message = "RSS 조회에 실패하였습니다.";
			return result;
		} else {
			result.status = true;
			result.message = "RSS 조회에 성공하였습니다.";
			return result;
		}
	}

	@Override
	public BasicResponse findByRssNameLike(String rssName) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();
		String likeQuery = "%" + rssName + "%";
		result.data = rssRepository.findByRssNameLike(likeQuery);
		System.out.println(result.data);
		if (result.data == null) {
			result.status = false;
			result.message = "RSS 조회에 실패하였습니다.";
			return result;
		} else {
			result.status = true;
			result.message = "RSS 조회에 성공하였습니다.";
			return result;
		}
	}

	@Override
	public BasicResponse findMain() {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		List<String> categoryList = categoryRepository.findRandomCategoryId();
		Map<String, List<String>> rssForCateogry = new HashMap<String, List<String>>();

		for (String category : categoryList) {
			rssForCateogry.put(category, rssRepository.findByCategoryName(category));
		}

		result.data = rssForCateogry;
		if (result.data == null) {
			result.status = false;
			result.message = "RSS 조회에 실패하였습니다.";
		} else {
			result.status = true;
			result.message = "RSS 조회에 성공하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findCount() {
		// TODO Auto-generated method stub
		HashMap<String, Long> count = new HashMap<>();

		count.put("userCount", userRepository.count());
		count.put("rssCount", rssRepository.count());
		count.put("subscribeCount", subscribeRepository.count());

		BasicResponse result = new BasicResponse();
		result.data = count;
		result.status = (result.data != null) ? true : false;

		return result;
	}

}
