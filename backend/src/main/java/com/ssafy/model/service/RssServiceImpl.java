package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Category;
import com.ssafy.model.dto.Rss;
import com.ssafy.model.repository.CategoryRepository;
import com.ssafy.model.repository.RssRepository;

@Service
public class RssServiceImpl implements RssService {

	@Autowired
	private RssRepository rssRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Rss> findAll() {
		return rssRepository.findAll();
	}

	@Override
	public List<Rss> findByCategoryName(String categoryName) {
		// TODO Auto-generated method stub

		Category category = categoryRepository.findOneByCategoryName(categoryName);

		if (category == null) {
			return null;
		}

		return rssRepository.findByCategory(category);
	}

	@Override
	public List<Rss> findByRssName(String rssName) {
		// TODO Auto-generated method stub
		return rssRepository.findByRssNameLike('%' + rssName + '%');
	}

}
