package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;

@Service
public interface UserService {
	public User Signup(User model);
	public User findByUser_id(String userId);
}
