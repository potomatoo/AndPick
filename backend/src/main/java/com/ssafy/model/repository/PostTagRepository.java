package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.PostTag;
import com.ssafy.model.dto.TagCount;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

	@Modifying
	@Query(value = "select SSAFYDB.post_tag.tag_name as tagName, count(*) as count from SSAFYDB.post_tag join SSAFYDB.post on SSAFYDB.post.post_id = SSAFYDB.post_tag.post_id where SSAFYDB.post.user_no = :user_no group by tag_name", nativeQuery = true)
	public List<TagCount> findAllTagCount(@Param("user_no") long user_no);

	@Modifying
	@Query(value = "delete from SSAFYDB.post_tag where post_id = :post_id", nativeQuery = true)
	public void deletePostTagByPostId(@Param("post_id") long post_id);

}
