package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	public Feed findOneByFeedName(String feedName);
	public List<Feed> findByUserNo(Long userNo);
}
