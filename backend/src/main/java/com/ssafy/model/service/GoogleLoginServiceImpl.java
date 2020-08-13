package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.User;
import com.ssafy.model.repository.UserRepository;

@Service
public class GoogleLoginServiceImpl implements GoogleLoginService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public User googleLogin(String email) {
		// TODO Auto-generated method stub

		User user = userRepository.findByUserId(email);

		if (user == null) {
			User signIn = new User();
			signIn.setUserId(email);
			signIn.setUserPassword(encoder.encode(email));
			signIn.setUserType(1);
			signIn.setUserName(email.split("@")[0]);
			user = userRepository.save(signIn);
			return user;
		} else if (user.getUserType() == 1) {
			return user;
		}
		return null;
	}

}
