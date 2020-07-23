package com.ssafy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.model.BasicResponse;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.UserRepository;
import com.ssafy.model.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/api/public/signup")
	public Object signup(@RequestParam(value = "user_id") String userId,
			@RequestParam(value = "user_password") String userPassword,
			@RequestParam(value = "user_name") String userName, @RequestParam(value = "user_type") int userType) {

		User user = userService.Signup(new User(userId, encoder.encode(userPassword), userName, userType));

		BasicResponse result = new BasicResponse();

		ResponseEntity response = null;

		if (user != null) {
			result.status = true;
			result.message = "Sign up success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.message = "중복된 이메일 입니다.";
			response = new ResponseEntity<>(result, HttpStatus.CONFLICT);
		}
		return response;
	}

}
