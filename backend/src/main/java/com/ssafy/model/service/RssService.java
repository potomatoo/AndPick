package com.ssafy.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Rss;

@Service
public interface RssService {

	public List<Rss> searchAll();

}
