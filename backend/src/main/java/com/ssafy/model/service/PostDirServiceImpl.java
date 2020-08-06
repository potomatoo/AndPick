package com.ssafy.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.PostDirRepository;

@Service
public class PostDirServiceImpl implements PostDirService {
	@Autowired
	PostDirRepository postDirRepository;

	@Override
	@Transactional
	public PostDir save(User user, PostDir postDir) {
		// TODO Auto-generated method stub
		if (user == null || postDir.getPostDirName() == null)
			return null;

		postDir.setUserNo(user.getUserNo());

		return postDirRepository.save(postDir);
	}

	@Override
	@Transactional
	public PostDir update(User user, long postDirId, String postDirName) {
		// TODO Auto-generated method stub
		if (user == null || postDirName == null)
			return null;

		PostDir postdir = postDirRepository.findOneByPostDirId(postDirId);

		postdir.setPostDirName(postDirName);

		return postDirRepository.save(postdir);
	}

	@Override
	@Transactional
	public List<PostDir> findPostDirByUSer(User user) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;

		return postDirRepository.findByUserNo(user.getUserNo());
	}

	@Override
	@Transactional
	public PostDir findPostDirByID(User user, long postDirId) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;

		return postDirRepository.findOneByPostDirId(postDirId);
	}

	@Override
	@Transactional
	public boolean deletePostDir(User user, long postDirId) {
		// TODO Auto-generated method stub
		if (user == null)
			return false;

		postDirRepository.deleteById(postDirId);
		return true;
	}

}
