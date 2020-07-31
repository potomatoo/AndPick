package com.ssafy.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Tag;
import com.ssafy.model.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	@Transactional
	public Tag saveTag(String tagName) {
		// TODO Auto-generated method stub
		if (tagName == null)
			return null;
		
		if (tagRepository.findOneByTagName(tagName) != null) {
			return null;
		}

		Tag tag = new Tag();
		tag.setTagName(tagName);

		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> findAll() {
		// TODO Auto-generated method stub
		return tagRepository.findAll();
	}

	@Override
	public List<Tag> findAllByName(String tagName) {
		// TODO Auto-generated method stub
		if (tagName == null || tagName.equals("")) {
			return null;
		}
		return tagRepository.findByTagNameLike('%' + tagName + '%');
	}

	@Override
	public Tag findOneByName(String tagName) {
		// TODO Auto-generated method stub
		if (tagName == null || tagName.equals(""))
			return null;
		return tagRepository.findOneByTagName(tagName);
	}

	@Override
	public Tag findOneById(long tagId) {
		// TODO Auto-generated method stub
		return tagRepository.findOneByTagId(tagId);
	}

	@Override
	public Tag updateTag(long tagId, String tagName) {
		// TODO Auto-generated method stub
		if (tagName == null || tagName.equals(""))
			return null;

		Tag tag = new Tag();

		tag.setTagId(tagId);
		tag.setTagName(tagName);

		return tagRepository.save(tag);
	}

	@Override
	@Transactional
	public boolean deleteTag(long tagId) {
		// TODO Auto-generated method stub
		Tag tag = tagRepository.findOneByTagId(tagId);

		if (tag == null)
			return false;

		tagRepository.delete(tag);
		return true;
	}

}
