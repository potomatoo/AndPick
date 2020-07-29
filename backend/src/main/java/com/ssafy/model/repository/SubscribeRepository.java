package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
	public List<Subscribe> findByUserNo(long UserNo);

	public List<Subscribe> findByFeedId(long FeedId);

	public Subscribe findOneBySubscribeId(long SubscribeId);
}
