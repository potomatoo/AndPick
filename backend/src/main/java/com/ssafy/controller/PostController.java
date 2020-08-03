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

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/post/save")
	public Object savePostDir(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirId") long postDirId, @RequestParam("postTitle") String postTitle,
			@RequestParam("postContent") String postContent) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		Post post = new Post();
		post.setPostDirId(postDirId);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setPostDate(new Date());

		result.data = postService.savePost(user, post);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "게시글 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 저장에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/user")
	public Object findByUser(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postService.findByUser(user);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "게시글 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/dirid")
	public Object findByDirId(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirId") long postDirId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postService.findByDirId(user, postDirId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "게시글 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/postid")
	public Object findByPostId(@RequestHeader("Authorization") String jwtToken, @RequestParam("postId") long postId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postService.findByPostId(user, postId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "게시글 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/title")
	public Object findByTitle(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postTitle") String title) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postService.findByTitle(user, title);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "게시글 조회가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/post/delete")
	public Object savePostDir(@RequestHeader("Authorization") String jwtToken, @RequestParam("postId") long postId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postService.deletePost(user, postId);

		if (result.status) {
			result.message = "게시글 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "게시글 저장에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
