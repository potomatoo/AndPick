package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.FeedRepository;
import com.ssafy.model.repository.SubscribeRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class FeedServiceImpl implements FeedService {
	@Autowired
	private FeedRepository feedRepository;
	@Autowired
	private SubscribeRepository subscribeRepository;

	@Override
	@Transactional
	public BasicResponse saveFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		List<Feed> feedList = feedRepository.findByUserNo(user.getUserNo());

		for (Feed item : feedList) {
			if (item.getFeedName() == feed.getFeedName()) {
				result.status = false;
				result.message = "해당 이름의 피드가 존재합니다.";
				return result;
			}
		}

		result.data = feedRepository.save(feed);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "피드 저장에 완료하였습니다.";
		} else {
			result.message = "피드 저장에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse findAllByuser(User user) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = feedRepository.findByUserNo(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "피드 조회를 완료하였습니다.";
		} else {
			result.message = "피드 조회에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse updateFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed checkFeed = feedRepository.findOneByFeedId(feed.getFeedId());

		if (checkFeed == null || checkFeed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보가 일치하지 않습니다.";
			return result;
		}

		List<Feed> feedList = feedRepository.findByUserNo(user.getUserNo());

		for (Feed item : feedList) {
			if (item.getFeedName() == feed.getFeedName()) {
				result.status = false;
				result.message = "해당 이름의 피드가 존재합니다.";
				return result;
			}
		}
		checkFeed.setFeedName(feed.getFeedName());
		result.data = feedRepository.save(checkFeed);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "피드 수정를 완료하였습니다.";
		} else {
			result.message = "피드 수정에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse deleteFeed(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Feed checkFeed = feedRepository.findOneByFeedId(feed.getFeedId());

		if (checkFeed == null || checkFeed.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "피드 정보가 일치하지 않습니다.";
			return result;
		}

		feedRepository.deleteSubscribe(feed.getFeedId());
		feedRepository.deleteById(feed.getFeedId());

		result.status = true;
		result.message = "삭제에 성공하였습니다.";

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findOneByFeedId(User user, Feed feed) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		feed = feedRepository.findOneByFeedId(feed.getFeedId());

		if (feed == null || user.getUserNo() != feed.getUserNo()) {
			result.status = false;
			result.message = "피드 정보가 일치하지 않습니다.";
			return result;
		}

		result.data = feed;
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "피드 조회에 성공하였습니다.";
		} else {
			result.message = "피드 조회에 실패하였습니다.";
		}

		return result;
	}

}
