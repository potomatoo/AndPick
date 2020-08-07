package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public Post findByPostId(Long postId);
	public List<Post> findByUserNo(long userNo);
	public List<Post> findByPostDirId(long postDirId);
	public List<Post> findByPostTitleLike(String postTitle);
}
