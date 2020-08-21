package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.PostTag;
import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface PostService {
	public BasicResponse savePost(User user, Post post, List<PostTag> tags);

	public BasicResponse findByUser(User user);

	public BasicResponse findByDirId(User user, PostDir postDir);

	public BasicResponse findByPostId(User user, Post post);

	public BasicResponse updatePost(User user, Post post, List<PostTag> tags);

	public BasicResponse deletePost(User user, Post post);

	public BasicResponse getPostDetailByDetailKey(String key);

	public BasicResponse findByTagName(User user, Tag tag);

}
