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
import com.ssafy.model.response.BasicResponse;

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
	public BasicResponse saveSubscribeNew(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed feed = feedRepository.findOneByFeedId(subscribe.getFeedId());

		if (feed == null) {
			result.status = false;
			result.message = "잘못된 피드 정보입니다.";
			return result;
		}

		if (feed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보와 사용자 정보가 일치하지 않습니다.";
			return result;
		}

		for (Subscribe item : feed.getSubscribeList()) {
			if (item.getRss().getRssUrl().equals(subscribe.getRss().getRssUrl())) {
				result.status = false;
				result.message = "해당 RSS가 이미 피드에 저장되어 있습니다.";
				return result;
			}

			if (item.getSubscribeName().equals(subscribe.getSubscribeName())) {
				result.status = false;
				result.message = "해당 RSS이름이 사용중입니다.";
				return result;
			}
		}

		Rss checkRss = rssRepository.findOneByRssUrl(subscribe.getRss().getRssUrl());
		if (checkRss == null) {
			checkRss = new Rss();
			Category checkCateory = categoryRepository
					.findOneByCategoryName(subscribe.getRss().getCategory().getCategoryName());

			if (checkCateory == null) {
				checkCateory = categoryRepository.save(subscribe.getRss().getCategory());
			}
			checkRss.setCategory(checkCateory);
			checkRss.setRssName(subscribe.getSubscribeName());
			checkRss.setRssUrl(subscribe.getRss().getRssUrl());

			subscribe.setRss(rssRepository.save(checkRss));
		} else {
			subscribe.setRss(checkRss);
		}

		result.data = subscribeRepository.save(subscribe);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "구독 저장에 완료하였습니다.";
		} else {
			result.message = "구독 저장에 실패하였습니다.";
		}
		return result;
	}

	@Override
	public BasicResponse saveSubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed feed = feedRepository.findOneByFeedId(subscribe.getFeedId());

		if (feed == null) {
			result.status = false;
			result.message = "잘못된 피드 정보입니다.";
			return result;
		}

		if (feed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보와 사용자 정보가 일치하지 않습니다.";
			return result;
		}

		for (Subscribe item : feed.getSubscribeList()) {
			if (item.getRss().getRssUrl().equals(subscribe.getRss().getRssUrl())) {
				result.status = false;
				result.message = "해당 RSS가 이미 피드에 저장되어 있습니다.";
				return result;
			}

			if (item.getSubscribeName().equals(subscribe.getSubscribeName())) {
				result.status = false;
				result.message = "해당 RSS이름이 사용중입니다.";
				return result;
			}
		}

		Rss checkRss = rssRepository.findOneByRssId(subscribe.getRss().getRssId());
		if (checkRss == null) {
			result.status = false;
			result.message = "해당 RSS가 존재하지 않습니다.";
			return result;
		} else {
			subscribe.setRss(checkRss);
		}

		result.data = subscribeRepository.save(subscribe);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "구독 저장에 완료하였습니다.";
		} else {
			result.message = "구독 저장에 실패하였습니다.";
		}
		return result;
	}

	@Override
	public BasicResponse updateSubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed checkFeed = feedRepository.findOneByFeedId(subscribe.getFeedId());

		if (checkFeed == null || checkFeed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보가 올바르지 않습니다.";
			return result;
		}

		Subscribe checkSubscribe = subscribeRepository.getOne(subscribe.getSubscribeId());

		if (checkSubscribe == null || checkSubscribe.getFeedId() != subscribe.getFeedId()
				|| checkSubscribe.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "구독 정보가 올바르지 않습니다.";
			return result;
		}

		checkSubscribe.setSubscribeName(subscribe.getSubscribeName());

		for (Subscribe item : checkFeed.getSubscribeList()) {
			if (item.getSubscribeName().equals(checkSubscribe.getSubscribeName())) {
				result.status = false;
				result.message = "같은 이름이 피드에 존재합니다.";
				return result;
			}
		}

		result.data = subscribeRepository.save(checkSubscribe);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "구독 수정에 완료하였습니다.";
		} else {
			result.message = "구독 수정에 실패하였습니다.";
		}
		return result;
	}

	@Override
	public BasicResponse deleteSubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Subscribe checkSubscribe = subscribeRepository.findOneBySubscribeId(subscribe.getSubscribeId());

		if (subscribe == null || subscribe.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "구독 정보가 일치하지 않습니다.";
			return result;
		}

		subscribeRepository.deleteById(checkSubscribe.getSubscribeId());
		result.status = true;
		result.message = "구독 삭제에 성공하였습니다.";

		return result;
	}

	@Override
	public BasicResponse findSubscribeByUser(User user) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = subscribeRepository.findByUserNo(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "조회에 완료하였습니다.";
		} else {
			result.message = "조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findSubscribeByFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		feed = feedRepository.findOneByFeedId(feed.getFeedId());
		if (feed == null || feed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보가 일치하지 않습니다.";
			return result;
		}

		result.data = subscribeRepository.findByFeedId(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "조회에 완료하였습니다.";
		} else {
			result.message = "조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findSubscribeBySubscribe(User user, Subscribe subscribe) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		subscribe = subscribeRepository.findOneBySubscribeId(subscribe.getSubscribeId());

		if (subscribe == null || subscribe.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "구독정보가 일치하지 않습니다.";
			return result;
		}

		result.data = subscribeRepository.findOneBySubscribeId(subscribe.getSubscribeId());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "조회에 완료하였습니다.";
		} else {
			result.message = "조회에 실패하였습니다.";
		}

		return result;
	}

}
