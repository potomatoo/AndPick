package com.ssafy.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.model.response.BasicResponse;

@Service
public interface PostTagService {

	public BasicResponse findAllCount();

	public BasicResponse findNameLikeCount(String tagName);

	public BasicResponse findName(String tagName);

}
