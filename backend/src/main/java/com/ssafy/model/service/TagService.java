package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Tag;

@Service
public interface TagService {
	public Tag saveTag(String tagName);

	public List<Tag> findAll();

	public List<Tag> findAllByName(String tagName);

	public Tag findOneByName(String tagName);

	public Tag findOneById(long tagId);

	public Tag updateTag(long tagId, String tagName);

	public boolean deleteTag(long tagId);
}
