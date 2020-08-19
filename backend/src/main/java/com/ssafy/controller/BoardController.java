package com.ssafy.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.Board;
import com.ssafy.model.dto.News;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/board/save")
	public Object saveBoard(@RequestHeader("Authorization") String jwtToken, @RequestBody Board board) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		if (board.getBoardName() == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Board boardDto = new Board();
		boardDto.setBoardName(board.getBoardName());
		boardDto.setUserNo(user.getUserNo());

		result = boardService.svaeBoard(user, boardDto);
		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/board/find/all")
	public Object findAllBoard(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		result = boardService.findAllBoard(user);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/board/find/id")
	public Object findOneByID(@RequestHeader("Authorization") String jwtToken, @RequestParam("boardId") long boardId) {
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

		result = boardService.findOneBoard(user, board);
		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/board/update")
	@Transactional
	public Object updateBoard(@RequestHeader("Authorization") String jwtToken, @RequestParam("boardId") long boardId,
			@RequestParam("boardName") String boardName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		if (boardName == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		Board board = new Board();
		board.setBoardId(boardId);
		board.setBoardName(boardName);
		board.setUserNo(user.getUserNo());

		result = boardService.updateBoard(user, board);
		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/board/delete")
	public Object deleteBoard(@RequestHeader("Authorization") String jwtToken, @RequestParam("boardId") long boardId) {
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

		result = boardService.deleteBoard(user, board);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PostMapping(value = "/api/board/copy")
	public Object copyBoard(@RequestHeader("Authorization") String jwtToken, @RequestParam("boardId") long boardId,
			@RequestBody News news) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}

		news.setBoardId(boardId);
		news.setNewsId(0);
		result = boardService.copyNews(news);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
