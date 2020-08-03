package com.ssafy.model.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.News;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public BasicResponse saveNews(User user, String newsTitle, String newsLink, Date newsDate, String newsDescription,
			long boardId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		if (newsTitle == null || newsLink == null || newsDate == null || newsDescription == null) {
			response.message = "잘못된 요정 정보 입니다.";
			response.status = false;
			return response;
		}

		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setNewsLink(newsLink);
		news.setNewsDate(newsDate);
		news.setNewsDescription(newsDescription);
		news.setBoardId(boardId);
		news.setUserNo(user.getUserNo());

		response.data = newsRepository.save(news);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "뉴스 저장에 성공하였습니다.";
		} else {
			response.message = "뉴스 저장에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse deleteNews(User user, long newsId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		newsRepository.deleteById(newsId);

		response.status = true;
		if (response.status) {
			response.message = "뉴스 삭제에 성공하였습니다.";
		} else {
			response.message = "뉴스 삭제에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse findAllNews(User user) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		response.data = newsRepository.findByUserNo(user.getUserNo());
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 조회에 성공하였습니다.";
		} else {
			response.message = "보드 조회에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse findAllByBoardId(User user, long boardId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		response.data = newsRepository.findByBoardId(boardId);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 조회에 성공하였습니다.";
		} else {
			response.message = "보드 조회에 실패하였습니다.";
		}

		return response;
	}

	@Override
	public BasicResponse findOneById(User user, long newsId) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		response.data = newsRepository.findOneByNewsId(newsId);
		response.status = (response.data != null) ? true : false;
		if (response.status) {
			response.message = "보드 조회에 성공하였습니다.";
		} else {
			response.message = "보드 조회에 실패하였습니다.";
		}

		return response;
	}

}
