package com.ssafy.model.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.model.response.BasicResponse;

@Service
public class PostTagServiceImpl implements PostTagService {

	@Override
	@Transactional
	public BasicResponse findAllCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public BasicResponse findNameLikeCount(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public BasicResponse findName(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}

}
