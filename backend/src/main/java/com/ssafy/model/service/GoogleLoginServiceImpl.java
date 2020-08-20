package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class GoogleLoginServiceImpl implements GoogleLoginService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private FeedRepository feedrepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private PostDirRepository postDirRepository;
	@Autowired
	private SubscribeRepository subscribeRepository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public User googleLogin(String email) {
		// TODO Auto-generated method stub

		User user = userRepository.findByUserId(email);

		if (user == null) {
			User signIn = new User();
			signIn.setUserId(email);
			signIn.setUserPassword(encoder.encode(email));
			signIn.setUserType(1);
			signIn.setUserName(email.split("@")[0]);
			user = userRepository.save(signIn);

			// 피드 추가
			Feed defaultFeed = new Feed();
			defaultFeed.setFeedName("기업 정보");
			defaultFeed.setUserNo(user.getUserNo());
			defaultFeed = feedrepository.save(defaultFeed);

			Feed tutorialFeed = new Feed();
			tutorialFeed.setFeedName("우클릭으로 삭제하세요");
			tutorialFeed.setUserNo(user.getUserNo());
			tutorialFeed = feedrepository.save(tutorialFeed);
			// 구독 추가
			Subscribe defaultSubscribe = new Subscribe();
			defaultSubscribe.setSubscribeName("삼성 반도체 이야기");
			defaultSubscribe.setUserNo(user.getUserNo());
			defaultSubscribe.setFeedId(defaultFeed.getFeedId());

			Rss samsungRss = new Rss();
			samsungRss.setRssId(49);

			defaultSubscribe.setRss(samsungRss);
			subscribeRepository.save(defaultSubscribe);
			// 보드 추가

			Board defaultBoard = new Board();
			defaultBoard.setBoardName("기사 스크랩");
			defaultBoard.setUserNo(user.getUserNo());
			boardRepository.save(defaultBoard);
			// post_dir 추가
			PostDir defaultPostDir = new PostDir();
			defaultPostDir.setUserNo(user.getUserNo());
			defaultPostDir.setPostDirName("나의 글쓰기");
			postDirRepository.save(defaultPostDir);

			return user;
		} else if (user.getUserType() == 1) {
			return user;
		}
		return null;
	}

}
