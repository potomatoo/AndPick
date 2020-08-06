package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.News;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	public List<News> findByUserNo(long userNo);

	public List<News> findByBoardId(long boardId);

	public News findOneByNewsId(long newsId);
	

	@Modifying
	@Query(value = "delete from SSAFYDB.news where board_id=:board_id", nativeQuery = true)
	public void deleteNews(@Param("board_id") Long board_id);
}
