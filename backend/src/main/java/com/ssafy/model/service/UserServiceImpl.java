package com.ssafy.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;
import com.ssafy.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User Signup(User user) {
		// TODO Auto-generated method stub
		if (user.getUserId() == null || user.getUserName() == null) {
			return null;
		}

		if (userRepository.findByUserId(user.getUserId()) != null) {
			return null;
		}

		User result = userRepository.save(user);
		result.setUserPassword(null);
		return result;
	}

	@Override
	@Transactional
	public User findByUser_id(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public boolean deleteUser(User user) {
		try {
			userRepository.delete(user);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public User UpdateUser(User user) {
		User result = userRepository.save(user);
		result.setUserPassword(null);
		return result;
	}
}
