package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.dto.TagCount;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface RssRepository extends JpaRepository<Rss, Long> {
	public List<Rss> findByCategory(Category category);

	public List<Rss> findByRssNameLike(String rssName);

	public Rss findOneByRssUrl(String rssUrl);

	public Rss findOneByRssId(long rssId);

	@Modifying
	@Query(value = "select rss_id,rss_url,rss.category_id,rss_name from SSAFYDB.rss join SSAFYDB.category on SSAFYDB.rss.category_id = SSAFYDB.category.category_id where SSAFYDB.category.category_name like :category_name", nativeQuery = true)
	public List<Rss> findByCategoryNameLike(String category_name);

}
