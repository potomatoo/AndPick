package com.ssafy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.model.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
