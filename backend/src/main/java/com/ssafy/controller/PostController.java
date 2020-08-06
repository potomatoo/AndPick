package com.ssafy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.PostTag;
import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
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
			@RequestParam("postContent") String postContent, @RequestParam("tagList") String[] tags) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (postTitle == null || postContent == null) {
			result.status = false;
			result.message = "필수값을 입력하세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Post post = new Post();
		post.setPostDirId(postDirId);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setPostDate(new Date());
		post.setUserNo(user.getUserNo());

		result = postService.savePost(user, post, tags);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/user")
	public Object findByUser(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		result = postService.findByUser(user);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
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
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		PostDir postDir = new PostDir();
		postDir.setPostDirId(postDirId);

		result = postService.findByDirId(user, postDir);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/post/find/postid")
	public Object findByPostId(@RequestHeader("Authorization") String jwtToken, @RequestParam("postId") long postId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Post post = new Post();
		post.setPostId(postId);

		result = postService.findByPostId(user, post);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/post/update")
	public Object updatePostDir(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirId") long postDirId, @RequestParam("postId") long postId,
			@RequestParam("postTitle") String postTitle, @RequestParam("postContent") String postContent,
			@RequestParam("tagList") String[] tags) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (postTitle == null || postContent == null) {
			result.status = false;
			result.message = "필수값을 입력하세요.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Post post = new Post();

		post.setPostId(postId);
		post.setPostDirId(postDirId);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setUserNo(user.getUserNo());
		post.setPostDate(new Date());

		result = postService.savePost(user, post, tags);
		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/post/delete")
	public Object deletePost(@RequestHeader("Authorization") String jwtToken, @RequestParam("postId") long postId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		Post post = new Post();
		post.setPostId(postId);

		result = postService.deletePost(user, post);

		if (result.status) {
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}
}
