package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;
import com.ssafy.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User Signup(User model) {
		// TODO Auto-generated method stub
		System.out.println("Service");
		
		if(model.getUserId()==null||model.getUserName()==null) {
			return null;
		}
		
		if (userRepository.findByUserId(model.getUserId()) != null) {
			return null;
		}
		return userRepository.save(model);
	}

	@Override
	public User findByUser_id(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

}
