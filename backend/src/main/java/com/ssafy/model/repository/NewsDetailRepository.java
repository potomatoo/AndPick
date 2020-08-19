package com.ssafy.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.NewsDetail;

@Repository
public interface NewsDetailRepository extends MongoRepository<NewsDetail, String> {

}
