package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post savePost(User user, Post post) {
		// TODO Auto-generated method stub

		if (user == null || post.getPostTitle() == null)
			return null;

		post.setUserNo(user.getUserNo());

		return postRepository.save(post);
	}

	@Override
	public List<Post> findByUser(User user) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;
		return postRepository.findByUserNo(user.getUserNo());
	}

	@Override
	public List<Post> findByDirId(User user, long postDirId) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;
		return postRepository.findByPostDirId(postDirId);
	}

	@Override
	public Post findByPostId(User user, long postId) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;
		return postRepository.findByPostId(postId);
	}

	@Override
	public List<Post> findByTitle(User user, String title) {
		// TODO Auto-generated method stub
		if (user == null)
			return null;

		return postRepository.findByPostTitleLike('%' + title + '%');
	}

	@Override
	public boolean deletePost(User user, long postId) {
		// TODO Auto-generated method stub
		return false;
	}

}
