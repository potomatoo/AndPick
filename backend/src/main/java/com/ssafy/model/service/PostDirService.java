package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.User;

@Service
public interface PostDirService {
	public PostDir save(User user, PostDir postDir);

	public PostDir update(User user, long postDirId, String postDirName);

	public List<PostDir> findPostDirByUSer(User user);

	public PostDir findPostDirByID(User user, long postDirId);

	public boolean deletePostDir(User user, long postDirId);
}
