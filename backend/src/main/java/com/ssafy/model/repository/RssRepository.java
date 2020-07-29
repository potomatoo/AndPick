package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Rss;

@Repository
public interface RssRepository extends JpaRepository<Rss, Long> {
	public List<Rss> findByCategory(Category category);

	public List<Rss> findByRssNameLike(String rssName);
}
