package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.PostDir;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface PostDirRepository extends JpaRepository<PostDir, Long> {
	public PostDir findOneByPostDirId(long postDirId);

	public List<PostDir> findByUserNo(long userNo);

	@Modifying
	@Query(value = "delete from SSAFYDB.post_tag where post_id in (select post_id from SSAFYDB.post where post_dir_id = :post_dir_id)", nativeQuery = true)
	public void deleteTagbyPostDirId(@Param("post_dir_id") long post_dir_id);

	@Modifying
	@Query(value = "delete from SSAFYDB.post where post_dir_id = :post_dir_id", nativeQuery = true)
	public void deletePostbyPostDirId(@Param("post_dir_id") long post_dir_id);

}
