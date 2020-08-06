package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface PostService {
	public BasicResponse savePost(User user, Post post, String[] tags);

	public BasicResponse findByUser(User user);

	public BasicResponse findByDirId(User user, PostDir postDir);

	public BasicResponse findByPostId(User user, Post post);

	public BasicResponse updatePost(User user, Post post, String[] tags);

	public BasicResponse deletePost(User user, Post post);

}
