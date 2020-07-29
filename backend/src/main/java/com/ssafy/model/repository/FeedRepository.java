package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	public Feed findOneByFeedName(String feedName);

	public List<Feed> findByUserNo(Long userNo);

	public Feed findOneByFeedId(Long feedId);

	@Modifying
	@Query(value = "delete from SSAFYDB.subscribe where feed_id=:feed_id ", nativeQuery = true)
	public void deleteSubscribe(@Param("feed_id") Long feed_id);
}
