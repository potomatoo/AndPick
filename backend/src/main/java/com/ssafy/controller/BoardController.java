package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/board/save")
	public Object saveBoard(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("boardName") String boardName) {
		ResponseEntity response = null;
		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		BasicResponse result = boardService.svaeBoard(user, boardName);

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
		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		BasicResponse result = boardService.findAllBoard(user);

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
		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		BasicResponse result = boardService.findOneBoard(user, boardId);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/board/update")
	public Object updateBoard(@RequestHeader("Authorization") String jwtToken, @RequestParam("boardId") long boardId,
			@RequestParam("boardName") String boardName) {
		ResponseEntity response = null;
		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		BasicResponse result = boardService.updateBoard(user, boardId, boardName);

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
		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		BasicResponse result = boardService.deleteBoard(user, boardId);

		if (result.status) {
			response = new ResponseEntity(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
