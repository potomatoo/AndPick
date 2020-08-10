package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Post;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	public Post findByPostId(Long postId);

	public List<Post> findByUserNo(long userNo);

	public List<Post> findByPostDirId(long postDirId);

	public List<Post> findByPostTitleLike(String postTitle);

	@Modifying
	@Query(value = "select * from SSAFYDB.post join SSAFYDB.post_tag  on SSAFYDB.post.post_id = SSAFYDB.post_tag.post_id where user_no = :user_no and tag_name = :tag_name ", nativeQuery = true)
	public List<Post> findAllByTagName(@Param("tag_name") String tag_name, @Param("user_no") long user_no);
}
