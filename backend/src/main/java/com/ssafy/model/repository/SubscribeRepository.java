package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
	public List<Subscribe> findByUserNo(long userNo);

	public List<Subscribe> findByFeedId(long feedId);

	public Subscribe findOneBySubscribeId(long subscribeId);
}
