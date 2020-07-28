package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Rss;
import com.ssafy.model.repository.RssRepository;

@Service
public class RssServiceImpl implements RssService{
	
	@Autowired
	RssRepository rssRepository;
	
	@Override
	public List<Rss> searchAll() {
		return rssRepository.findAll();
	}

}
