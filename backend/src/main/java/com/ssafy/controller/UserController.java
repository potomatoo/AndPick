package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;
import com.ssafy.model.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@PostMapping("/api/public/signup")
	public Object signup(@RequestBody User user) {

		User userDto = userService.Signup(new User(user.getUserId(), encoder.encode(user.getUserPassword()),
				user.getUserName(), user.getUserType()));
		userDto.setUserPassword(null);
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		if (userDto != null) {
			result.status = true;
			result.message = "Sign up success";
			result.data = userDto;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.message = "회원 가입에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.CONFLICT);
		}
		return response;
	}

	@PostMapping("/api/public/signup/checkid")
	public Object checkId(@RequestParam(value = "userId") String userId) {
		User user = userService.findByUser_id(userId);

		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		if (user != null) {
			result.status = false;
			result.message = "이미 가입된 이메일 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.CONFLICT);
		} else {
			result.status = true;
			result.message = "사용가능한 이메일 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/api/user/delete")
	public Object deleteUser(@RequestHeader("Authorization") String jwtToken) {

		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) this.redisTemplate.opsForValue().get(jwtToken);

		if (userService.deleteUser(user)) {
			result.status = true;
			result.message = "삭제가 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.message = "삭제에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.CONFLICT);
		}

		return response;
	}

	@PutMapping("/api/user/update")
	public Object updateUser(@RequestHeader("Authorization") String jwtToken,
			@RequestParam(value = "userName") String userName) {

		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) this.redisTemplate.opsForValue().get(jwtToken);
		user.setUserName(userName);

		User update = userService.updateUser(user);
		this.redisTemplate.opsForValue().set(jwtToken, user);

		update.setUserPassword("");
		if (update != null) {
			result.status = true;
			result.message = "회원정보 수정에 성공하였습니다.";
			result.data = update;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.message = "회원정보 수정에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.CONFLICT);
		}

		return response;
	}

	@GetMapping(value = "/api/user/detail")
	public Object userDetail(@RequestHeader("Authorization") String jwtToken) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}
		user.setUserPassword("");
		result.data = user;
		result.status = true;
		if (result.status) {
			result.message = "회원 정보 조회에 성공하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "회원 정보 조회에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping("/api/update/user/password")
	public Object upateUserPassword(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("userPassword") String userPassword, @RequestParam("changePassword") String changePassword) {
		ResponseEntity response = null;
		BasicResponse result = new BasicResponse();

		User user = (User) redisTemplate.opsForValue().get(jwtToken);
		if (user == null) {
			result.status = false;
			result.message = "잘못된 사용자 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (userPassword == null || changePassword == null) {
			result.status = false;
			result.message = "필수 값을 입력하세요";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		if (!encoder.matches(userPassword, user.getUserPassword())) {
			result.status = false;
			result.message = "기존 비밀번호가 다릅니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			return response;
		}

		user.setUserPassword(encoder.encode(changePassword));
		result.data = userService.updateUser(user);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "비밀번호 변경에 성공하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.message = "비밀번호 변경에 실패하였습니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

}
