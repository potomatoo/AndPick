package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.User;

@Service
public interface PostService {
	public Post savePost(User user, Post post);

	public List<Post> findByUser(User user);

	public List<Post> findByDirId(User user, long postDirId);

	public Post findByPostId(User user, long postId);

	public List<Post> findByTitle(User user, String title);

	public boolean deletePost(User user, long postId);

}
