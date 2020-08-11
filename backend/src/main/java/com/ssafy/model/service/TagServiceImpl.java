package com.ssafy.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.PostTagRepository;
import com.ssafy.model.repository.TagRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private PostTagRepository postTagRepository;

	@Override
	@Transactional
	public BasicResponse saveTag(Tag tag) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Tag checkTag = tagRepository.findOneByTagName(tag.getTagName());
		if (checkTag != null) {
			result.status = false;
			result.message = "중복된 태그 입니다.";
			result.data = checkTag;
			return result;
		}
		System.out.println(tag);
		result.data = tagRepository.save(tag);
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "태그 저장에 성공하였습니다.";
		} else {
			result.message = "태그 저장에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findAll() {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();
		System.out.println("?");
		result.data = tagRepository.findAll();
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "태그 조회에 성공하였습니다.";
		} else {
			result.message = "태그 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findAllByName(Tag tag) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = tagRepository.findByTagNameLike("%" + tag.getTagName() + "%");
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "태그 조회에 성공하였습니다.";
		} else {
			result.message = "태그 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	@Transactional
	public BasicResponse findOneByName(Tag tag) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = tagRepository.findOneByTagName(tag.getTagName());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "태그 조회에 성공하였습니다.";
		} else {
			result.message = "태그 조회에 실패하였습니다.";
		}

		return result;
	}

	@Override
	public BasicResponse findAllTagCount(User user) {
		// TODO Auto-generated method stub

		BasicResponse result = new BasicResponse();

		result.data = postTagRepository.findAllTagCount(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "태그 조회에 성공하였습니다.";
		} else {
			result.message = "태그 조회에 실패하였습니다.";
		}

		return result;
	}
}
