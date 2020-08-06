package com.ssafy.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Post;
import com.ssafy.model.dto.PostDir;
import com.ssafy.model.dto.PostTag;
import com.ssafy.model.dto.Tag;
import com.ssafy.model.dto.User;
import com.ssafy.model.repository.PostDirRepository;
import com.ssafy.model.repository.PostRepository;
import com.ssafy.model.repository.PostTagRepository;
import com.ssafy.model.repository.TagRepository;
import com.ssafy.model.response.BasicResponse;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostDirRepository postDirRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private PostTagRepository postTagRepository;

	@Override
	@Transactional
	public BasicResponse savePost(User user, Post post, String[] tags) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		PostDir postdir = postDirRepository.findOneByPostDirId(post.getPostDirId());

		if (postdir == null || postdir.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "폴더의 정보가 일치하지 않습니다.";
			return result;
		}

		post = postRepository.save(post);

		List<PostTag> list = new ArrayList<PostTag>();
		if (tags != null && tags.length != 0) {
			for (String tagName : tags) {
				Tag tag = new Tag();
				tag.setTagName(tagName);

				tag = tagRepository.save(tag);

				PostTag postTag = new PostTag();
				postTag.setPostId(post.getPostId());
				postTag.setTagName(tag.getTagName());

				list.add(postTagRepository.save(postTag));
			}
		}
		post.setTagList(list);
		result.data = post;
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "게시글 저장에 완료하였습니다.";
		} else {
			result.message = "게시글 저장에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse findByUser(User user) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		result.data = postRepository.findByUserNo(user.getUserNo());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "게시글 조회를 완료하였습니다.";
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse findByDirId(User user, PostDir postDir) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		postDir = postDirRepository.findOneByPostDirId(postDir.getPostDirId());

		if (postDir == null || postDir.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "폴더 정보가 다릅니다.";
			return result;
		}

		result.data = postRepository.findByPostDirId(postDir.getPostDirId());
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "게시글 조회를 완료하였습니다.";
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse findByPostId(User user, Post post) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		post = postRepository.findByPostId(post.getPostId());

		if (post == null || post.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보가 다릅니다.";
			return result;
		}
		result.data = post;
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "게시글 조회를 완료하였습니다.";
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse updatePost(User user, Post post, String[] tags) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		Post checkPost = postRepository.findByPostId(post.getPostId());

		if (post == null || checkPost.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보가 다릅니다.";
			return result;
		}

		for (PostTag postTag : checkPost.getTagList()) {
			postTagRepository.deleteById(postTag.getPostTagId());
		}

		post = postRepository.save(post);
		List<PostTag> list = new ArrayList<PostTag>();
		if (tags != null && tags.length != 0) {
			for (String tagName : tags) {
				Tag tag = new Tag();
				tag.setTagName(tagName);

				tag = tagRepository.save(tag);

				PostTag postTag = new PostTag();
				postTag.setPostId(post.getPostId());
				postTag.setTagName(tag.getTagName());

				list.add(postTagRepository.save(postTag));
			}
		}
		post.setTagList(list);
		result.data = post;
		result.status = (result.data != null) ? true : false;
		if (result.status) {
			result.message = "게시글 조회를 완료하였습니다.";
		} else {
			result.message = "게시글 조회에 실패하였습니다.";
		}
		return result;
	}

	@Override
	@Transactional
	public BasicResponse deletePost(User user, Post post) {
		// TODO Auto-generated method stub
		BasicResponse result = new BasicResponse();

		post = postRepository.findByPostId(post.getPostId());

		if (post == null || post.getUserNo() != user.getUserNo()) {
			result.status = false;
			result.message = "사용자 정보가 다릅니다.";
			return result;
		}

		postRepository.delete(post);

		result.status = true;
		result.message = "삭제에 성공하였습니다.";

		return result;
	}

}
