package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.Feed;
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.Subscribe;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.BoardRepository;
import com.ssafy.model.repository.FeedRepository;
import com.ssafy.model.repository.PostDirRepository;
import com.ssafy.model.repository.SubscribeRepository;
import com.ssafy.model.repository.UserRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FeedRepository feedrepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private PostDirRepository postDirRepository;
	@Autowired
	private SubscribeRepository subscribeRepository;

	@Override
	@Transactional
	public User Signup(User user) {
		// TODO Auto-generated method stub
		if (user.getUserId() == null || user.getUserName() == null) {
			return null;
		}

		if (userRepository.findByUserId(user.getUserId()) != null) {
			return null;
		}
		User result = userRepository.save(user);

		// 피드 추가
		Feed defaultFeed = new Feed();
		defaultFeed.setFeedName("기업 정보");
		defaultFeed.setUserNo(result.getUserNo());
		defaultFeed = feedrepository.save(defaultFeed);
		// 구독 추가
		Subscribe defaultSubscribe = new Subscribe();
		defaultSubscribe.setSubscribeName("삼성 반도체 이야기");
		defaultSubscribe.setUserNo(result.getUserNo());
		defaultSubscribe.setFeedId(defaultFeed.getFeedId());

		Rss samsungRss = new Rss();
		samsungRss.setRssId(49);

		defaultSubscribe.setRss(samsungRss);
		subscribeRepository.save(defaultSubscribe);
		// 보드 추가

		Board defaultBoard = new Board();
		defaultBoard.setBoardName("기사 스크랩");
		defaultBoard.setUserNo(result.getUserNo());
		boardRepository.save(defaultBoard);
		// post_dir 추가
		PostDir defaultPostDir = new PostDir();
		defaultPostDir.setUserNo(result.getUserNo());
		defaultPostDir.setPostDirName("나의 글쓰기");
		postDirRepository.save(defaultPostDir);
		return result;
	}

	@Override
	@Transactional
	public User findByUser_id(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		try {
			userRepository.delete(user);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		User result = userRepository.save(user);
		return result;
	}

}
