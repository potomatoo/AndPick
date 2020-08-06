package com.ssafy.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.News;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/news/save")
	public Object saveNews(@RequestHeader("Authorization") String jwtToken, @RequestParam("newsTitle") String newsTitle,
			@RequestParam("newsLink") String newsLink, @RequestParam("newsDate") Date newsDate,
			@RequestParam("newsDescription") String newsDescription, @RequestParam("boardId") long boardId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		if (newsTitle == null || newsLink == null || newsDate == null || newsDescription == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		News news = new News();
		news.setNewsTitle(newsTitle);
		news.setNewsLink(newsLink);
		news.setNewsDescription(newsDescription);
		news.setNewsDate(newsDate);
		news.setBoardId(boardId);
		news.setUserNo(user.getUserNo());

		result = newsService.saveNews(user, news);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

	@DeleteMapping(value = "/api/news/delete")
	public Object deleteNews(@RequestHeader("Authorization") String jwtToken, @RequestParam("newsId") long newsId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		News news = new News();
		news.setNewsId(newsId);

		result = newsService.deleteNews(user, news);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

	@GetMapping(value = "/api/news/find/all")
	public Object findAllNews(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		result = newsService.findAllNews(user);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/news/find/board")
	public Object findAllByBoard(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("boardId") long boardId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Board board = new Board();
		board.setBoardId(boardId);

		result = newsService.findAllByBoardId(user, board);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

	@GetMapping(value = "/api/news/find/id")
	public Object findOneById(@RequestHeader("Authorization") String jwtToken, @RequestParam("newsId") long newsId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		News news = new News();
		news.setNewsId(newsId);

		result = newsService.findOneById(user, news);
		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}
}
