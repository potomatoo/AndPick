package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.PostDir;

@Repository
public interface PostDirRepository extends JpaRepository<PostDir, Long> {
	public PostDir findOneByPostDirId(long postDirId);

	public List<PostDir> findByUserNo(long userNo);
}
