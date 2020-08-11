package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.response.BasicResponse;

@Service
public interface TagService {
	public BasicResponse saveTag(Tag tag);

	public BasicResponse findAll();

	public BasicResponse findAllByName(Tag tag);

	public BasicResponse findOneByName(Tag tag);

	public BasicResponse findAllTagCount(User user);
}
