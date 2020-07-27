package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;

@Service
public interface UserService {
	public User Signup(User user);
	public User findByUser_id(String userId);
	public boolean deleteUser(User user);
	public User UpdateUser(User user);
}
