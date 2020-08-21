package com.ssafy.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.model.dto.Scrap;

@Repository
public interface ScrapRepository extends MongoRepository<Scrap, String> {

}
