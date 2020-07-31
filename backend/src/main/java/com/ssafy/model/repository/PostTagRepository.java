package com.ssafy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.PostTag;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

}
