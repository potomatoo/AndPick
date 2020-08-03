package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	public List<News> findByUserNo(long userNo);

	public List<News> findByBoardId(long boardId);

	public News findOneByNewsId(long newsId);
}
