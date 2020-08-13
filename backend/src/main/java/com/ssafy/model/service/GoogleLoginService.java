package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;

@Service
public interface GoogleLoginService {

	public User googleLogin(String email);

}
