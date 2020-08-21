package com.ssafy.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.PostDetail;

@Repository
public interface PostDetailRepository extends MongoRepository<PostDetail, String> {
	List<PostDetail> findByContent(String content);
}
