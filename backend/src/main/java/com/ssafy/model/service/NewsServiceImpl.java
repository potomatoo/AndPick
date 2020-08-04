package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.News;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.BoardRepository;
import com.ssafy.model.repository.NewsRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private BoardRepository boardRepository;

	@Override
	public BasicResponse saveNews(User user, News news) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		Board board = boardRepository.findOneByBoardId(news.getBoardId());

		if (board.getUserNo() != user.getUserNo()) {
			response.status = false;
			response.message = "보드 정보가 일치하지 않습니다.";
			return response;
		}

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
	public BasicResponse deleteNews(User user, News news) {
		// TODO Auto-generated method stub
		BasicResponse response = new BasicResponse();

		if (user == null) {
			response.message = "잘못된 사용자 정보입니다.";
			response.status = false;
			return response;
		}

		news = newsRepository.findOneByNewsId(news.getNewsId());

		if (news.getUserNo() != user.getUserNo()) {
			response.status = false;
			response.message = "뉴스 정보가 일치하지 않습니다.";
			return response;
		}

		newsRepository.deleteById(news.getNewsId());

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
		BasicResponse result = new BasicResponse();

		result.data = newsRepository.findByUserNo(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "보드 조회에 성공하였습니다.";
		} else {
			result.message = "보드 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findAllByBoardId(User user, Board board) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 정보입니다.";
			return result;
		}

		board = boardRepository.findOneByBoardId(board.getBoardId());

		if (user.getUserNo() != board.getUserNo()) {
			result.status = false;
			result.message = "잘못된 보드 정보입니다.";
			return result;
		}

		result.data = newsRepository.findByBoardId(board.getBoardId());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "보드 조회에 성공하였습니다.";
		} else {
			result.message = "보드 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findOneById(User user, News news) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		if (user == null) {
			result.message = "잘못된 사용자 정보입니다.";
			result.status = false;
			return result;
		}

		news = newsRepository.findOneByNewsId(news.getNewsId());

		if (user.getUserNo() != news.getUserNo()) {
			result.message = "잘못된 뉴스 정보입니다.";
			result.status = false;
			return result;
		}

		result.data = news;
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "보드 조회에 성공하였습니다.";
		} else {
			result.message = "보드 조회에 실패하였습니다.";
		}

		return result;
	}

}
