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
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.User;
import com.ssafy.model.service.PostDirService;

@Controller
public class PostDirController {
	@Autowired
	private PostDirService postDirService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostMapping(value = "/api/postdir/save")
	public Object savePostDir(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirName") String postDirName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		PostDir postDir = new PostDir();
		postDir.setPostDirName(postDirName);

		result.data = postDirService.save(user, postDir);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "폴더 저장이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "폴더 저장에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/api/postdir/update")
	public Object savePostDir(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirId") long postDirId, @RequestParam("postDirName") String postDirName) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postDirService.update(user, postDirId, postDirName);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "폴더 수정이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "폴더 수정에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/postdir/find/user")
	public Object findByUser(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postDirService.findPostDirByUSer(user);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "사용자의 모든 폴더 정보입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping(value = "/api/postdir/find/postdir")
	public Object findByPostDir(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("postDirId") long postDirId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.data = postDirService.findPostDirByID(user, postDirId);
		result.status = (result.data != null) ? true : false;

		if (result.status) {
			result.message = "id로 폴더를 조회합니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@DeleteMapping(value = "/api/postdir/delete")
	public Object deleteDir(@RequestHeader("Authorization") String jwtToken, @RequestParam("postDirId") long postDirId) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);

		result.status = postDirService.deletePostDir(user, postDirId);

		if (result.status) {
			result.message = "삭제에 성공하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "삭제에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
