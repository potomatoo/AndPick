package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.CategoryCount;
import com.ssafy.model.dto.Rss;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findByCategoryNameLike(String name);

	public Category findOneByCategoryName(String name);

	@Modifying
	@Query(value = "select SSAFYDB.category.category_name from SSAFYDB.category order by rand() limit 3", nativeQuery = true)
	public List<String> findRandomCategoryId();

	@Modifying
	@Query(value = "select SSAFYDB.category.category_name as categoryName, SSAFYDB.category.category_id as categoryId, count(*) as count from SSAFYDB.rss join SSAFYDB.category on SSAFYDB.rss.category_id = SSAFYDB.category.category_id group by rss.category_id", nativeQuery = true)
	public List<CategoryCount> findCategoryCount();
}
