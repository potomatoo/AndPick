package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	public Tag findOneByTagName(String tagName);

	public List<Tag> findByTagNameLike(String tagName);

}
